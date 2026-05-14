package cc.cassian.pyrite.registry;

import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.*;
import cc.cassian.pyrite.compat.*;
import cc.cassian.pyrite.entity.ModEntities;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

//? fabric
import static cc.cassian.pyrite.fabric.PyriteFabric.FUEL_BLOCKS;
import static cc.cassian.pyrite.functions.ModHelpers.*;
import static cc.cassian.pyrite.functions.ModLists.*;

public class BlockCreator {
    // All blocks and their IDs.
    public static final LinkedHashMap<String, Block> BLOCKS = new LinkedHashMap<>();
    // All blocks without block items and their IDs.
    public static final LinkedHashMap<String, Block> ITEMLESS_BLOCKS = new LinkedHashMap<>();
    // All items and their IDs.
    public static final LinkedHashMap<String, Item> ITEMS = new LinkedHashMap<>();
    public static final ArrayList<BlockFamily> FAMILIES = new ArrayList<>();
    /**
     * This registers a basic item with no additional settings - primarily used for Dye.
     */
    public static void registerPyriteItem(String itemID) {
        var item = new Item(newItemSettings(itemID));
        ITEMS.put(itemID, item);
        PyriteItemGroups.DYES.add(new ItemEntry<>(itemID, item));
    }

    /**
     * This registers a custom item.
     */
    public static ItemEntry<Item> registerPyriteItem(String itemID, Function<Item.Properties, Item> itemFactory) {
        var item = itemFactory.apply(newItemSettings(itemID));
        ITEMS.put(itemID, item);
        return new ItemEntry<>(itemID, item);
    }

    public static ItemEntry<Item> addBlockItem(String blockID, Block block) {
        Item.Properties settings = newBlockItemSettings(blockID);
        if (blockID.contains("netherite"))
            settings = settings.fireResistant();
        return new ItemEntry<>(blockID, new BlockItem(block, settings));
    }

    public static void register() {
        //Register blocks and block items.
        for (Map.Entry<String, Block> entry : BLOCKS.entrySet()) {
            final Block block = entry.getValue();
            final String blockID = entry.getKey();
            Registry.register(BuiltInRegistries.BLOCK, Pyrite.of(blockID), block);
            Registry.register(BuiltInRegistries.ITEM, Pyrite.of(blockID), addBlockItem(blockID, block).get());
        }
        //Registers blocks without block items.
        for (Map.Entry<String, Block> entry : ITEMLESS_BLOCKS.entrySet()) {
            final Block block = entry.getValue();
            final String blockID = entry.getKey();
            Registry.register(BuiltInRegistries.BLOCK, Pyrite.of(blockID), block);
        }
        //Registers items.
        for (Map.Entry<String, Item> entry : ITEMS.entrySet()) {
            final Item item = entry.getValue();
            final String itemID = entry.getKey();
            Registry.register(BuiltInRegistries.ITEM, Pyrite.of(itemID), item);
        }


        for (BlockEntry<Block> entry : PyriteItemGroups.COPPER_BLOCKS) {
            Platform.INSTANCE.registerOxidizableBlockPair(entry.getValue().get(), getBlock(entry.getKey().replace("copper", "exposed_copper")));
        }
        for (BlockEntry<Block> entry : PyriteItemGroups.EXPOSED_COPPER_BLOCKS) {
            Platform.INSTANCE.registerOxidizableBlockPair(entry.getValue().get(), getBlock(entry.getKey().replace("exposed", "weathered")));
        }
        for (BlockEntry<Block> entry : PyriteItemGroups.WEATHERED_COPPER_BLOCKS) {
            Platform.INSTANCE.registerOxidizableBlockPair(entry.getValue().get(), getBlock(entry.getKey().replace("weathered", "oxidized")));
        }

        // Register item group.
        PyriteItemGroups.addItemGroup("pyrite_group", "glowstone_lamp", BLOCKS);
    }

    final static Block[] vanillaWood = getVanillaWood();
    public static Block platformRegister(String blockID, String blockType, BlockBehaviour.Properties blockSettings, WoodType woodType, BlockSetType blockSetType, ParticleOptions particle, Block copyBlock, String group, MapColor color) {
        int power = power(blockID);
        Block newBlock = null;
        blockSettings = blockSettings.setId(registryKeyBlock(blockID));
        boolean burnable = !(blockID.contains("crimson") || blockID.contains("warped"));
        switch (blockType.toLowerCase()) {
            case "block", "lamp":
                if (isCopper(blockID)) {
                    newBlock = new WeatheringCopperFullBlock(getOxidizationState(blockID), blockSettings.randomTicks());
                    var waxedBlock = new ModBlock(BlockBehaviour.Properties.ofFullCopy(newBlock).setId(registryKeyBlock("waxed_"+ blockID)));
                    BLOCKS.put("waxed_"+blockID, waxedBlock);
                    PyriteItemGroups.match(()->waxedBlock, copyBlock, "waxed_"+group,"waxed_"+ blockID);
                    Platform.INSTANCE.registerWaxableBlockPair(newBlock, waxedBlock);
                }
                else
                    newBlock = new ModBlock(blockSettings, power);
                break;
            case "crafting":
                // Register Crafting table.
                if (burnable) {
                    newBlock = new ModCraftingTable(blockSettings.ignitedByLava());
                    //? fabric
                    FUEL_BLOCKS.put(newBlock, 300);
                } else
                    newBlock = new ModCraftingTable(blockSettings);
                break;
            case "shelf":
                // Register Shelf
                newBlock = new ShelfBlock(blockSettings);
                ModHelpers.addSupportedBlock(BlockEntityType.SHELF, newBlock);
                break;
            case "chest":
                if (Platform.INSTANCE.isModLoaded("lolmcv")) {
                    newBlock = new ChestBlock(()->BlockEntityType.CHEST, SoundEvents.CHEST_OPEN, SoundEvents.CHEST_CLOSE, blockSettings);
                    ModHelpers.addSupportedBlock(()->BlockEntityType.CHEST, newBlock);
                }
                break;
            case "cabinet":
                if (Platform.INSTANCE.isModLoaded("farmersdelight")) {
                    newBlock = FarmersDelightCompat.registerCabinet(blockID, blockSettings, group, copyBlock);
                    FarmersDelightCompat.add(newBlock);
                }
                break;
            case "ladder":
                newBlock = new LadderBlock(blockSettings);
                addTransparentBlock(newBlock);
                break;
            case "carpet":
                newBlock = new ModCarpet(blockSettings);
                break;
            case "slab":
                if (isCopper(blockID)) {
                    newBlock = new WeatheringCopperSlabBlock(getOxidizationState(blockID), blockSettings);
                    Block waxed = new ModSlab(BlockBehaviour.Properties.ofFullCopy(newBlock).setId(registryKeyBlock("waxed_"+ blockID)));
                    BLOCKS.put("waxed_" + blockID, waxed);
                    PyriteItemGroups.match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
                    Platform.INSTANCE.registerWaxableBlockPair(newBlock, waxed);
                } else
                    newBlock = new ModSlab(blockSettings, power);
                break;
            case "stairs":
                if (isCopper(blockID)) {
                    newBlock = new WeatheringCopperStairBlock(getOxidizationState(blockID), copyBlock.defaultBlockState(), blockSettings);
                    Block waxed = new ModStairs(copyBlock.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(newBlock).setId(registryKeyBlock("waxed_"+ blockID)));
                    BLOCKS.put("waxed_"+blockID, waxed);
                    Platform.INSTANCE.registerWaxableBlockPair(newBlock, waxed);
                } else
                    newBlock = new ModStairs(copyBlock.defaultBlockState(), blockSettings);
                break;
            case "wall":
                if (isCopper(blockID)) {
                    // wall
                    newBlock = new OxidizableWallBlock(getOxidizationState(blockID), blockSettings);
                    Block waxed = new ModWall(BlockBehaviour.Properties.ofFullCopy(newBlock).setId(registryKeyBlock("waxed_"+ blockID)));
                    BLOCKS.put("waxed_" + blockID, waxed);
                    PyriteItemGroups.match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
                    Platform.INSTANCE.registerWaxableBlockPair(newBlock, waxed);
                    // column
                    //? fabric {
                    if (Platform.INSTANCE.isModLoaded("columns"))
                        ColumnsCompat.registerCopperColumn(blockID, blockSettings, group, copyBlock);
                    //?}
                } else {
                    newBlock = new ModWall(blockSettings, power);
                    //? fabric {
                    if (Platform.INSTANCE.isModLoaded("columns"))
                        ColumnsCompat.registerColumn(blockID.replace("wall", "column"), blockSettings, group, copyBlock);
                    //?}
                }
                break;
            case "fence":
                newBlock = new FenceBlock(blockSettings);
                break;
            case "log":
                if (isCopper(blockID)) {
                    newBlock = new OxidizablePillarBlock(getOxidizationState(blockID), blockSettings);
                    Block waxed = new ModPillar(BlockBehaviour.Properties.ofFullCopy(newBlock).setId(registryKeyBlock("waxed_"+ blockID)));
                    BLOCKS.put("waxed_" + blockID, waxed);
                    PyriteItemGroups.match(new BlockEntry<>("waxed_"+blockID, waxed), copyBlock, "waxed_"+group, "waxed_" + blockID);
                    Platform.INSTANCE.registerWaxableBlockPair(newBlock, waxed);
                } else
                    newBlock = new ModPillar(blockSettings, power);
                break;
            case "wood":
                newBlock = new ModWood(blockSettings);
                break;
            case "facing":
                newBlock = new ModFacingBlock(blockSettings, power);
                break;
            case "bars", "glass_pane":
                if (isCopper(blockID)) {
                    newBlock = new OxidizableBarsBlock(getOxidizationState(blockID), blockSettings);
                    Block waxed = new ModPane(BlockBehaviour.Properties.ofFullCopy(newBlock).setId(registryKeyBlock("waxed_"+ blockID)));
                    BLOCKS.put("waxed_" + blockID, waxed);
                    PyriteItemGroups.match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
                    Platform.INSTANCE.registerWaxableBlockPair(newBlock, waxed);
                    addTransparentBlock(waxed);
                } else {
                    newBlock = new ModPane(blockSettings, power);
                }
                addTransparentBlock(newBlock);
                break;
            case "stained_framed_glass_pane":
                newBlock = new StainedGlassPaneBlock(getDyeColorFromFramedId(blockID), blockSettings);
                addTranslucentBlock(newBlock);
                break;
            case "glass":
                newBlock = new ModGlass(blockSettings);
                addTransparentBlock(newBlock);
                break;
            case "stained_framed_glass":
                newBlock = new StainedFramedGlass(getDyeColorFromFramedId(blockID), blockSettings);
                addTranslucentBlock(newBlock);
                break;
            case "gravel":
                newBlock = new GravelBlock(blockSettings);
                break;
            case "flower":
                // register flower
                newBlock = new FlowerBlock(MobEffects.NIGHT_VISION, 5, blockSettings);
                addTransparentBlock(newBlock);
                // register flower pot
                final FlowerPotBlock FLOWER_POTTED = new FlowerPotBlock(newBlock, flowerPotProperties(registryKeyBlock("potted_"+blockID)));
                ITEMLESS_BLOCKS.put("potted_"+blockID, FLOWER_POTTED);
                PyriteItemGroups.POTTED_FLOWERS.put(blockID, new BlockEntry<>("potted_"+blockID, FLOWER_POTTED));
                addTransparentBlock(FLOWER_POTTED);
                break;
            case "fence_gate":
                newBlock = new FenceGateBlock(woodType, blockSettings);
                break;
            case "wall_gate":
                if (isCopper(blockID)) {
                    newBlock = new OxidizableWallGateBlock(getOxidizationState(blockID), blockSettings);
                    Block waxed = new WallGateBlock(blockSetType, BlockBehaviour.Properties.ofFullCopy(newBlock).setId(registryKeyBlock("waxed_"+ blockID)));
                    BLOCKS.put("waxed_" + blockID, waxed);
                    PyriteItemGroups.match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
                    Platform.INSTANCE.registerWaxableBlockPair(newBlock, waxed);
                } else
                    newBlock = new WallGateBlock(blockSetType, blockSettings);
                break;
            case "sign":
                //Sign Blocks
                newBlock = new PyriteWallSignBlock(woodType, blockSettings, blockID);
                ITEMLESS_BLOCKS.put(blockID, newBlock);
                //Wall Sign Blocks
                final WallSignBlock WALL_SIGN = new WallSignBlock(woodType, blockSettings);
                ITEMLESS_BLOCKS.put(blockID.replace("_sign", "_wall_sign"), WALL_SIGN);
                // Register item for signs.
                final Item SIGN_ITEM = new SignItem(newBlock, WALL_SIGN, newBlockItemSettings(blockID).stacksTo(16));
                ITEMS.put(blockID, SIGN_ITEM);
                PyriteItemGroups.SIGNS.add(PyriteItemGroups.SIGNS.size(), new ItemEntry<>(blockID, SIGN_ITEM));
                ModHelpers.addSupportedBlock(BlockEntityType.SIGN, newBlock);
                ModHelpers.addSupportedBlock(BlockEntityType.SIGN, WALL_SIGN);
                break;
            case "hanging_sign":
                //Sign Blocks
                newBlock = new PyriteWallHangingSignBlock(woodType, blockSettings, blockID);
                ITEMLESS_BLOCKS.put(blockID, newBlock);
                //Wall Sign Blocks
                final WallHangingSignBlock HANGING_WALL_SIGN = new WallHangingSignBlock(woodType, blockSettings);
                ITEMLESS_BLOCKS.put(blockID.replace("_sign", "_wall_sign"), HANGING_WALL_SIGN);
                // Register item for signs.
                final Item HANGING_SIGN_ITEM = new HangingSignItem(newBlock, HANGING_WALL_SIGN, newBlockItemSettings(blockID).stacksTo(16));
                ITEMS.put(blockID, HANGING_SIGN_ITEM);
                PyriteItemGroups.SIGNS.add(new ItemEntry<>(blockID, HANGING_SIGN_ITEM));
                ModHelpers.addSupportedBlock(BlockEntityType.HANGING_SIGN, newBlock);
                ModHelpers.addSupportedBlock(BlockEntityType.HANGING_SIGN, HANGING_WALL_SIGN);
                break;
            case "door":
                if (isCopper(blockID)) {
                    newBlock = new WeatheringCopperDoorBlock(blockSetType, getOxidizationState(blockID), blockSettings.noOcclusion());
                    Block waxed = new DoorBlock(blockSetType, blockSettings.noOcclusion());
                    BLOCKS.put("waxed_" + blockID, waxed);
                    PyriteItemGroups.match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
                    Platform.INSTANCE.registerWaxableBlockPair(newBlock, waxed);
                }
                else
                    newBlock = new DoorBlock(blockSetType, blockSettings.noOcclusion());
                addTransparentBlock(newBlock);
                break;
            case "trapdoor":
                if (isCopper(blockID)) {
                    newBlock = new WeatheringCopperTrapDoorBlock(blockSetType, getOxidizationState(blockID), blockSettings.noOcclusion());
                    Block waxed = new TrapDoorBlock(blockSetType, blockSettings.noOcclusion());
                    BLOCKS.put("waxed_" + blockID, waxed);
                    PyriteItemGroups.match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
                    Platform.INSTANCE.registerWaxableBlockPair(newBlock, waxed);
                }
                else
                    newBlock = new TrapDoorBlock(blockSetType, blockSettings.noOcclusion());
                addTransparentBlock(newBlock);
                break;
            case "button":
                newBlock = new ModWoodenButton(blockSettings, blockSetType);
                break;
            case "pressure_plate":
                newBlock = new ModPressurePlate(blockSettings, blockSetType);
                break;
            case "torch":
                var torchParticle = particle;
                if (particle == null)
                    torchParticle = ParticleTypes.FLAME;
                newBlock = new ModTorch(blockSettings.noOcclusion(), torchParticle);
                //? fabric {
                if (Platform.INSTANCE.isModLoaded("totally_lit") && !ModLists.PYRITE_DYES.contains(blockID.replace("_torch", "")))
                    TotallyLitCompat.registerTorch("unlit_"+blockID, blockSettings.noOcclusion(), "unlit_torch", newBlock);
                //?}
                addTransparentBlock(newBlock);
                break;
            case "torch_lever":
                newBlock = new TorchLever(blockSettings.noOcclusion(), particle);
                addTransparentBlock(newBlock);
                break;
            case "concrete_powder":
                newBlock = new ConcretePowderBlock(getLastBlock(), blockSettings);
                break;
            case "switchable_glass":
                newBlock = new SwitchableGlass(blockSettings);
                addTranslucentBlock(newBlock);
                break;
            default:
                log("%s created as a generic block, block provided: %s".formatted(blockID, blockType));
                newBlock = new Block(blockSettings);
                break;
        }
        if (newBlock == null)
            return null;
        if (!blockType.contains("sign")) {
            BLOCKS.put(blockID, newBlock);
        }
        if (blockID.contains("grass")) {
            addGrassBlock();
        }
        Block finalNewBlock = newBlock;
        PyriteItemGroups.match(()-> finalNewBlock, copyBlock, group, blockID);
        return finalNewBlock;
    }

    public static Block getLastBlock() {
        return BLOCKS.lastEntry().getValue();
    }

    public static void addGrassBlock() {
        GRASS_BLOCKS.add(getLastBlock());
    }

    public static void generateResourceBlocks() {
        for (Block resourceBlock : getVanillaResourceBlocks()) {
            String block = findVanillaBlockID(resourceBlock);
            //If the block provided isn't a wall block, add the wall tag.
            if (block.contains("block")) {
                block = block.substring(0, block.indexOf("_block"));
            }
            createResourceBlockSet(block, resourceBlock);
        }
    }

    public static void createTorchLever(String blockID, Block baseTorch, ParticleOptions particle) {
        sendToRegistry(blockID, "torch_lever", BlockBehaviour.Properties.ofFullCopy(baseTorch), particle, "torch_lever");
    }
    public static void createTorch(String blockID, ParticleOptions particle) {
        sendToRegistry(blockID, "torch", BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH), particle, "torch");
    }

    public static void generateVanillaCraftingTables() {
        //Autogenerate Vanilla Crafting Tables
        for (Block plankBlock : vanillaWood) {
            //Find block ID
            String block = findVanillaBlockID(plankBlock);
            //If the block provided isn't a wall block, add the wall tag.
            if (block.contains("planks")) {
                block = block.substring(0, block.indexOf("_planks"));
            }
            //Create block.
            createPyriteBlock(block + "_crafting_table","crafting", plankBlock, "crafting_table");
        }
    }

    //Primarily used for Framed Glass, Glowstone/Dyed Lamps, Glowing Obsidian
    public static void createPyriteBlock(String blockID, String blockType, Float strength, MapColor color, int lightLevel, String group) {
        BlockBehaviour.Properties settings = BlockBehaviour.Properties.of().strength(strength).lightLevel(state -> lightLevel).mapColor(color);
        if (Objects.equals(blockType, "obsidian")) {
            sendToRegistry(blockID, "block", settings.strength(strength, 1200f).pushReaction(PushReaction.BLOCK), group);
        }
        else if (blockType.equals("lamp")) {
            sendToRegistry(blockID, blockType, settings.sound(SoundType.GLASS), group);
        }
        else {
            sendToRegistry(blockID, blockType, settings.sound(SoundType.GLASS).noOcclusion().isRedstoneConductor(BlockCreator::never), group);
        }
    }

    private static boolean never(BlockState state, BlockGetter world, BlockPos pos) {
        return false;
    }

    //Create and then add carpets
    private static void createCarpet(String blockID, String group) {
        BlockBehaviour.Properties blockSettings = copyBlock(Blocks.MOSS_CARPET);
        sendToRegistry(blockID, "carpet", blockSettings, group);
    }

    //Create and then add most of the manually generated blocks.
    public static Block createPyriteBlock(String blockID, String blockType, Block copyBlock, String group) {
        return platformRegister(blockID, blockType, copyBlock(copyBlock), WoodType.CRIMSON, BlockSetType.IRON, null, copyBlock, group, null);
    }

    //Create a slab from the last block added.
    public static Block createStair(String blockID, Block copyBlock, String group) {
        BlockBehaviour.Properties blockSettings = copyBlock(copyBlock);
        return sendToRegistry(blockID+"_stairs", copyBlock, blockSettings, group);
    }

    //Create a slab from the last block added.
    public static Block createSlab(String blockID, Block copyBlock, String group) {
        BlockBehaviour.Properties blockSettings = copyBlock(copyBlock);
        return sendToRegistry(blockID+"_slab", "slab", blockSettings, group);
    }

    //Create blocks that require a change in light level, e.g. Locked Chests
    public static void createPyriteBlock(String blockID, String blockType, Block copyBlock, int lux, String group) {
        BlockBehaviour.Properties blockSettings = copyBlock(copyBlock).lightLevel(parseLux(lux));
        platformRegister(blockID, blockType, blockSettings, null, null, null, copyBlock, group, null);
    }

    private static Block sendToRegistry(String blockID, String blockType, BlockBehaviour.Properties blockSettings, String group) {
        return platformRegister(blockID, blockType, blockSettings, null, null, null, null, group, null);

    }
    private static Block sendToRegistry(String blockID, Block copyBlock, BlockBehaviour.Properties blockSettings, String group) {
        return platformRegister(blockID, "stairs", blockSettings,  null, null, null, copyBlock, group, null);
    }
    
    //Add blocks with particles - Torches/Torch Levers
    private static Block sendToRegistry(String blockID, String blockType, BlockBehaviour.Properties blockSettings, ParticleOptions particle, String group) {
        return platformRegister(blockID, blockType, blockSettings, null, null, particle, null, group, null);
    }

    //Create blocks that require a Block Set.
    public static Block createPyriteBlock(String blockID, String blockType, Block copyBlock, BlockSetType set, String group) {
        return platformRegister(blockID, blockType, copyBlock(copyBlock),  null, set, null, copyBlock, group, null);
    }

    //Create most of the generic Stained Blocks, then add them.
    public static Block createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, String group) {
        BlockBehaviour.Properties blockSettings = copyBlock(copyBlock).mapColor(color).lightLevel(parseLux(lux));
        if ((copyBlock.equals(Blocks.OAK_PLANKS)) || (copyBlock.equals(Blocks.OAK_SLAB) || (copyBlock.equals(Blocks.OAK_STAIRS)))) {
            blockSettings = blockSettings.ignitedByLava();
        }
        return platformRegister(blockID, blockType, blockSettings,  null, null, null, copyBlock, group, color);
    }

    //Create basic blocks.
    public static Block createPyriteBlock(String blockID, Block copyBlock, String group) {
        BlockBehaviour.Properties blockSettings = copyBlock(copyBlock);
        return platformRegister(blockID, "block", blockSettings,  null, null, null, null, group, null);
    }

    //Create Stained blocks that require a wood set or wood type, then add them.
    public static Block createPyriteBlock(String blockID, String blockType, Block copyBlock, MapColor color, int lux, BlockSetType set, WoodType type, String group) {
        BlockBehaviour.Properties blockSettings = copyBlock(copyBlock).mapColor(color).lightLevel(parseLux(lux));
        if (!blockType.equals("button")) {
            blockSettings = blockSettings.ignitedByLava();
        }
        return platformRegister(blockID, blockType, blockSettings,  type, set, null, null, group, color);
    }

    public static void generateFlowers() {
        for (Map.Entry<String, Block> entry : ModLists.FLOWERS.entrySet()) {
            createPyriteBlock(entry.getKey(), "flower", entry.getValue(), "flower");
        }
    }

    public static void generateTurfSets() {
        for (Map.Entry<String, Block> entry : TURF_SETS.entrySet()) {
            createTurfSet(entry.getKey(), entry.getValue());
        }
    }

    public static void generateNostalgiaBlocks() {
        for (Map.Entry<String, Block> entry : NOSTALGIA_BLOCKS.entrySet()) {
            createPyriteBlock(entry.getKey(), "block", entry.getValue(), entry.getKey());
        }
        createPyriteBlock("nostalgia_gravel", "gravel", Blocks.GRAVEL, "gravel");
    }

    //Generate an entire brick set.
    public static void generateBrickSet(String blockID, Block copyBlock, MapColor color, int lux, @Nullable String group) {
        if (group == null)
            group = blockID;
        //Bricks
        createPyriteBlock( blockID+"s", "block", copyBlock, color, lux, group);
        //Brick Stairs
        createPyriteBlock( blockID+"_stairs", "stairs", copyBlock, color, lux, group);
        //Brick Slab
        createPyriteBlock( blockID+"_slab", "slab", copyBlock, color, lux, group);
        //Brick Wall
        createPyriteBlock( blockID+"_wall", "wall", copyBlock, color, lux, group);
        //Brick Wall Gate
        createPyriteBlock(blockID+"_wall_gate","wall_gate", copyBlock, BlockSetType.STONE, group);
    }

    public static void generateBrickSet(String blockID, Block copyBlock, MapColor color) {
        generateBrickSet(blockID, copyBlock, color, 0, blockID);
    }

    public static void generateBrickSet(String blockID, Block copyBlock) {
        generateBrickSet(blockID, copyBlock, copyBlock.defaultMapColor());
    }

    public static void generateBrickSet(String blockID, Block copyBlock, MapColor color, boolean generateMossySet, String group) {
        generateBrickSet(blockID, copyBlock, color, 0, group);
        if (generateMossySet)
            generateBrickSet("mossy_"+blockID, copyBlock, color, 0, null);
    }

    public static void generateBrickSet(String blockID, Block copyBlock, MapColor color, boolean generateMossySet) {
        generateBrickSet(blockID, copyBlock, color, generateMossySet, blockID);
    }

        //Generate a Turf block set - including block and its slab, stair, and carpet variants.
    public static void createTurfSet(String blockID, Block copyBlock) {
        createPyriteBlock( blockID+"_turf", "block", copyBlock, blockID);
        createStair(blockID, copyBlock, blockID);
        createSlab(blockID, copyBlock, blockID);
        createCarpet(blockID+"_carpet", blockID);
    }

    /**
     * Generate an entire wood set.
     */
    public static void createWoodSet(String blockID, MapColor color, int blockLux, String group) {
        BlockSetType GENERATED_SET = new BlockSetType(blockID);
        WoodType GENERATED_TYPE = Platform.INSTANCE.createWoodType(blockID, GENERATED_SET);

        // Planks
        var planks = createPyriteBlock("%s_planks".formatted(blockID), "block", Blocks.OAK_PLANKS, color, blockLux, group);

        // Stairs
        var stairs = createPyriteBlock("%s_stairs".formatted(blockID), "stairs",Blocks.OAK_STAIRS, color, blockLux, group);

        // Slabs
        var slab = createPyriteBlock("%s_slab".formatted(blockID), "slab", Blocks.OAK_SLAB, color, blockLux, group);

        // Fences
        var fence = createPyriteBlock("%s_fence".formatted(blockID), "fence", Blocks.OAK_FENCE, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Fence Gates
        var fenceGate = createPyriteBlock("%s_fence_gate".formatted(blockID), "fence_gate", Blocks.OAK_FENCE_GATE, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Doors
        var door = createPyriteBlock("%s_door".formatted(blockID), "door", Blocks.OAK_DOOR, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Trapdoors
        var trapdoor = createPyriteBlock("%s_trapdoor".formatted(blockID), "trapdoor", Blocks.OAK_TRAPDOOR, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Pressure Plates
        var pressurePlate = createPyriteBlock("%s_pressure_plate".formatted(blockID), "pressure_plate", Blocks.OAK_PRESSURE_PLATE, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Buttons
        var button = createPyriteBlock("%s_button".formatted(blockID), "button", Blocks.OAK_BUTTON, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Crafting Tables
        createPyriteBlock("%s_crafting_table".formatted(blockID), "crafting", Blocks.CRAFTING_TABLE, color, blockLux, group);

        // Ladders
        createPyriteBlock("%s_ladder".formatted(blockID), "ladder", Blocks.LADDER, color, blockLux, group);

        // Signs
        var sign = createPyriteBlock("%s_sign".formatted(blockID), "sign", Blocks.OAK_SIGN, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Hanging Signs
        var hangingSign = createPyriteBlock("%s_hanging_sign".formatted(blockID), "hanging_sign", Blocks.OAK_HANGING_SIGN, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Chest
        if (Platform.INSTANCE.isModLoaded("lolmcv"))
            createPyriteBlock("%s_chest".formatted(blockID), "chest", Blocks.CHEST, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Cabinet
        if (Platform.INSTANCE.isModLoaded("farmersdelight"))
            createPyriteBlock("%s_cabinet".formatted(blockID), "cabinet", Blocks.BARREL, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Shelf
        createPyriteBlock("%s_shelf".formatted(blockID), "shelf", Blocks.OAK_SHELF, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);

        // Boat
        EntityType<Boat> boatEntityType = ModEntities.registerBoat(blockID, () -> BuiltInRegistries.ITEM.getValue(Pyrite.of("%s_boat".formatted(blockID))));
        var boat = registerPyriteItem("%s_boat".formatted(blockID), (prop)-> new BoatItem(boatEntityType, prop.stacksTo(1)));
        PyriteItemGroups.BOATS.add(boat);
        EntityType<ChestBoat> chestBoatEntityType = ModEntities.registerChestBoat(blockID, () -> BuiltInRegistries.ITEM.getValue(Pyrite.of("%s_chest_boat".formatted(blockID))));
        var chestBoat = registerPyriteItem("%s_chest_boat".formatted(blockID), (prop)-> new BoatItem(chestBoatEntityType, prop.stacksTo(1)));
        PyriteItemGroups.BOATS.add(chestBoat);

        var family = new BlockFamily.Builder(planks).slab(slab).stairs(stairs).trapdoor(trapdoor).door(door).button(button).fence(fence).pressurePlate(pressurePlate).fenceGate(fenceGate).getFamily();
        FAMILIES.add(family);
    }

    /**
     * Generate an entire wood set, alongside Logs, Wood, and Stripped Logs/Wood.
     */
    public static void createWoodSetWithLog(String blockID, MapColor color, int blockLux) {
        createPyriteBlock("%s_log".formatted(blockID), "log", Blocks.OAK_LOG, color, blockLux, "wood");
        createPyriteBlock("stripped_%s_log".formatted(blockID), "log", Blocks.STRIPPED_OAK_LOG, color, blockLux, "wood");
        createPyriteBlock("%s_wood".formatted(blockID), "wood", Blocks.OAK_WOOD, color, blockLux, "wood");
        createPyriteBlock("stripped_%s_wood".formatted(blockID), "wood", Blocks.STRIPPED_OAK_WOOD, color, blockLux, "wood");
        createWoodSet(blockID, color, blockLux, "wood");
    }

    /**
     * Generate an entire Cut Block set.
     */
    public static void createCutBlocks(String blockID, Block block) {
        var family = new BlockFamily.Builder(block);
        String cutBlockID = "cut_" + blockID;
        if (!blockID.contains("copper")) {
            //Cut Block
            createPyriteBlock(cutBlockID, block, blockID);
            //Cut Stairs
            family.stairs(createStair(cutBlockID, block, blockID));
            //Cut Slab
            family.slab(createSlab(cutBlockID, block, blockID));
        }
        //Cut Wall
        family.wall(createPyriteBlock("%s_wall".formatted(cutBlockID), "wall", block, blockID));
        //Cut Wall Gate
        family.customFenceGate(createPyriteBlock("%s_wall_gate".formatted(cutBlockID),"wall_gate", block, blockID));
        FAMILIES.add(family.generateStonecutterRecipe().getFamily());
    }
    /**
     * Generate an entire Smooth Block set.
     */
    public static void createSmoothBlocks(String blockID, Block block) {
        Block smoothBlock = Blocks.SMOOTH_QUARTZ;
        Block stair = null;
        Block slab = null;
        String smoothBlockID = "smooth_" + blockID;
        if (!Objects.equals(blockID, "quartz")) {
            //Smooth Block
            smoothBlock = createPyriteBlock(smoothBlockID, block, blockID);
            //Smooth Stairs
            stair = createStair(smoothBlockID, block, blockID);
            //Smooth Slab
            slab = createSlab(smoothBlockID, block, blockID);
        }
        //Smooth Wall
        var wall = createPyriteBlock("%s_wall".formatted(smoothBlockID), "wall", block, blockID);
        //Smooth Wall Gate
        var wallGate = createPyriteBlock("%s_wall_gate".formatted(smoothBlockID),"wall_gate", block, blockID);
        FAMILIES.add(new BlockFamily.Builder(smoothBlock).customFenceGate(wallGate).wall(wall).stairs(stair).slab(slab).generateStonecutterRecipe().getFamily());
    }

    //Create a set of Resource Blocks
    public static void createResourceBlockSet(String blockID, Block block) {
        BlockFamily.Builder family = new BlockFamily.Builder(block);
        //Create Cut Blocks for those that don't already exist (Copper)
        createCutBlocks(blockID, block);
        //Create Bricks/Chiseled/Pillar/Smooth for those that don't already exist (Quartz)
        if (!Objects.equals(blockID, "quartz")) {
            //Brick Blocks
            family.bricks(createPyriteBlock("%s_bricks".formatted(blockID), block, blockID));
            //Chiseled Blocks - Copper Blocks
            if (!blockID.contains("copper")) {
               family.chiseled(createPyriteBlock("chiseled_%s_block".formatted(blockID), "log", block, blockID));
            }
            //Pillar Blocks
            createPyriteBlock("%s_pillar".formatted(blockID), "log", block, blockID);
        }
        //Smooth Blocks
        createSmoothBlocks(blockID, block);
        createPyriteBlock("nostalgia_%s_block".formatted(blockID), block, blockID);
        //Block set for modded blocks
        BlockSetType set = getBlockSetType(blockID);
        //Create Bars/Doors/Trapdoors/Plates for those that don't already exist (Iron)
        if (!blockID.equals("iron")) {
            //createPyriteBlock("%s_bars".formatted(blockID),"bars", block, blockID);
            //Disable Copper doors in 1.21+
            if (!blockID.contains("copper")) {
                createPyriteBlock("%s_bars".formatted(blockID),"bars", block, blockID);
                family.door(createPyriteBlock("%s_door".formatted(blockID),"door", block, set, blockID));
                family.trapdoor(createPyriteBlock("%s_trapdoor".formatted(blockID),"trapdoor", block, set, blockID));
            }
            //Create Plates for those that don't already exist (Iron and Gold)
            if (!blockID.equals("gold")) {
                family.pressurePlate(createPyriteBlock("%s_pressure_plate".formatted(blockID),"pressure_plate", block, set, blockID));
            }
        }
        //Create buttons for all blocks.
        family.button(createPyriteBlock("%s_button".formatted(blockID),"button", block, set, blockID));
        FAMILIES.add(family.generateStonecutterRecipe().getFamily());
    }

}
