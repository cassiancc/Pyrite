package cc.cassian.pyrite.registry.fabric;

import cc.cassian.pyrite.blocks.*;
import cc.cassian.pyrite.compat.ChestsCompat;
import cc.cassian.pyrite.compat.ColumnsCompat;
import cc.cassian.pyrite.compat.FarmersDelightCompat;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.*;
import java.util.function.Supplier;

import static cc.cassian.pyrite.functions.ModHelpers.*;
import static cc.cassian.pyrite.functions.fabric.FabricHelpers.*;
import static cc.cassian.pyrite.registry.PyriteItemGroups.*;
import static cc.cassian.pyrite.registry.fabric.PyriteItemGroupsImpl.*;

@SuppressWarnings("unused")
public class BlockCreatorImpl {
    // All blocks and their IDs.
    public static final LinkedHashMap<String, Block> BLOCKS = new LinkedHashMap<>();
    // All blocks without block items and their IDs.
    public static final LinkedHashMap<String, Block> ITEMLESS_BLOCKS = new LinkedHashMap<>();
    // All items and their IDs.
    public static final LinkedHashMap<String, Item> ITEMS = new LinkedHashMap<>();

    /**
     * Implements {@link BlockCreator#createWoodType(String, BlockSetType)} on Fabric.
     */
    public static WoodType createWoodType(String blockID, BlockSetType setType) {
        return WoodTypeBuilder.copyOf(WoodType.OAK).register(locate(blockID), setType);
    }

	/**
     * Implements {@link BlockCreator#platformRegister(String, String, AbstractBlock.Settings, WoodType, BlockSetType, ParticleEffect, Block, String, MapColor)} on Fabric.
     */
    public static void platformRegister(String blockID, String blockType, AbstractBlock.Settings blockSettings, WoodType woodType, BlockSetType blockSetType, ParticleEffect particle, Block copyBlock, String group, MapColor color) {
        int power = power(blockID);
        Block newBlock = null;
        switch (blockType.toLowerCase()) {
            case "block", "lamp":
                if (isCopper(blockID)) {
					newBlock = new OxidizableBlock(getOxidizationState(blockID), blockSettings.ticksRandomly());
					var waxedBlock = new ModBlock(blockSettings);
					BLOCKS.put("waxed_"+blockID, waxedBlock);
					match(()->waxedBlock, copyBlock, "waxed_"+group,"waxed_"+ blockID);
					OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxedBlock);
				}
                else
                    newBlock = new ModBlock(blockSettings, power);
                break;
            case "crafting":
                boolean burnable = !(blockID.contains("crimson") || blockID.contains("warped"));
				// Register Crafting table.
				if (burnable) {
					newBlock = new ModCraftingTable(blockSettings.burnable());
					FUEL_BLOCKS.put(newBlock, 300);
				} else
					newBlock = new ModCraftingTable(blockSettings);
                break;
            case "chest":
                if (FabricLoader.getInstance().isModLoaded("lolmcv")) {
                    newBlock = ChestsCompat.registerChest(blockID, blockSettings, group, copyBlock);
                    ChestsCompat.add(newBlock);
                }
                break;
            case "cabinet":
                if (ModHelpers.isModLoaded("farmersdelight")) {
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
					newBlock = new OxidizableSlabBlock(getOxidizationState(blockID), blockSettings);
					Block waxed = new ModSlab(blockSettings);
					BLOCKS.put("waxed_" + blockID, waxed);
					match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
					OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
				} else
                     newBlock = new ModSlab(blockSettings, power);
                break;
            case "stairs":
                if (isCopper(blockID)) {
					newBlock = new OxidizableStairsBlock(getOxidizationState(blockID), copyBlock.getDefaultState(), blockSettings);
					Block waxed = new ModStairs(copyBlock.getDefaultState(), blockSettings);
					BLOCKS.put("waxed_"+blockID, waxed);
					OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
				} else
					newBlock = new ModStairs(copyBlock.getDefaultState(), blockSettings);
                break;
            case "wall":
				if (isCopper(blockID)) {
                    // wall
					newBlock = new OxidizableWallBlock(getOxidizationState(blockID), blockSettings);
					Block waxed = new ModWall(blockSettings);
					BLOCKS.put("waxed_" + blockID, waxed);
					match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
					OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
                    // column
                    if (FabricLoader.getInstance().isModLoaded("columns"))
                        ColumnsCompat.registerCopperColumn(blockID, blockSettings, group, copyBlock);
				} else {
                    newBlock = new ModWall(blockSettings, power);
                    if (FabricLoader.getInstance().isModLoaded("columns"))
                        ColumnsCompat.registerColumn(blockID.replace("wall", "column"), blockSettings, group, copyBlock);
                }
                break;
            case "fence":
                newBlock = new FenceBlock(blockSettings);
                break;
            case "log":
				if (isCopper(blockID)) {
					newBlock = new OxidizablePillarBlock(getOxidizationState(blockID), blockSettings);
					Block waxed = new ModPillar(blockSettings);
					BLOCKS.put("waxed_" + blockID, waxed);
					match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
					OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
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
					Block waxed = new ModPane(blockSettings);
					BLOCKS.put("waxed_" + blockID, waxed);
					match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
					OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
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
                newBlock = new FlowerBlock(StatusEffects.NIGHT_VISION, 5, blockSettings);
                addTransparentBlock(newBlock);
                // register flower pot
                final Block FLOWER_POTTED = Blocks.createFlowerPotBlock(newBlock);
                ITEMLESS_BLOCKS.put("potted_"+blockID, FLOWER_POTTED);
                addTransparentBlock(FLOWER_POTTED);
                break;
            case "fence_gate":
                newBlock = new FenceGateBlock(woodType, blockSettings);
                break;
            case "wall_gate":
				if (isCopper(blockID)) {
					newBlock = new OxidizableWallGateBlock(getOxidizationState(blockID), blockSettings);
					Block waxed = new WallGateBlock(blockSetType, blockSettings);
					BLOCKS.put("waxed_" + blockID, waxed);
					match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
					OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
				} else
					newBlock = new WallGateBlock(blockSetType, blockSettings);
                break;
            case "sign":
                //Sign Blocks
                newBlock = new SignBlock(woodType, blockSettings);
                ITEMLESS_BLOCKS.put(blockID, newBlock);
                //Wall Sign Blocks
                final WallSignBlock WALL_SIGN = new WallSignBlock(woodType, blockSettings);
                ITEMLESS_BLOCKS.put(blockID.replace("_sign", "_wall_sign"), WALL_SIGN);
                // Register item for signs.
                final Item SIGN_ITEM = new SignItem(new Item.Settings().maxCount(16), newBlock, WALL_SIGN);
                ITEMS.put(blockID, SIGN_ITEM);
                SIGNS.add(SIGNS.size(), () -> SIGN_ITEM);
                BlockEntityType.SIGN.addSupportedBlock(newBlock);
                BlockEntityType.SIGN.addSupportedBlock(WALL_SIGN);
                break;
            case "hanging_sign":
                //Sign Blocks
                newBlock = new HangingSignBlock(woodType, blockSettings);
                ITEMLESS_BLOCKS.put(blockID, newBlock);
                //Wall Sign Blocks
                final WallHangingSignBlock HANGING_WALL_SIGN = new WallHangingSignBlock(woodType, blockSettings);
                ITEMLESS_BLOCKS.put(blockID.replace("_sign", "_wall_sign"), HANGING_WALL_SIGN);
                // Register item for signs.
                final Item HANGING_SIGN_ITEM = new HangingSignItem(newBlock, HANGING_WALL_SIGN, new Item.Settings().maxCount(16));
                ITEMS.put(blockID, HANGING_SIGN_ITEM);
                SIGNS.add(() -> HANGING_SIGN_ITEM);
                BlockEntityType.HANGING_SIGN.addSupportedBlock(newBlock);
                BlockEntityType.HANGING_SIGN.addSupportedBlock(HANGING_WALL_SIGN);
                break;
            case "door":
				if (isCopper(blockID)) {
					newBlock = new OxidizableDoorBlock(blockSetType, getOxidizationState(blockID), blockSettings.nonOpaque());
					Block waxed = new DoorBlock(blockSetType, blockSettings.nonOpaque());
					BLOCKS.put("waxed_" + blockID, waxed);
					match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
					OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
				}
				else
					newBlock = new DoorBlock(blockSetType, blockSettings.nonOpaque());
                addTransparentBlock(newBlock);
                break;
            case "trapdoor":
                if (isCopper(blockID)) {
					newBlock = new OxidizableTrapdoorBlock(blockSetType, getOxidizationState(blockID), blockSettings.nonOpaque());
					Block waxed = new TrapdoorBlock(blockSetType, blockSettings.nonOpaque());
					BLOCKS.put("waxed_" + blockID, waxed);
					match(()->waxed, copyBlock, "waxed_"+group, "waxed_" + blockID);
					OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
				}
                else
                    newBlock = new TrapdoorBlock(blockSetType, blockSettings.nonOpaque());
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
                newBlock = new ModTorch(blockSettings.nonOpaque(), torchParticle);
                addTransparentBlock(newBlock);
                break;
            case "torch_lever":
                newBlock = new TorchLever(blockSettings.nonOpaque(), particle);
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
            return;
        if (!blockType.contains("sign")) {
            BLOCKS.put(blockID, newBlock);
        }
        if (blockID.contains("grass")) {
            addGrassBlock();
        }
        Block finalNewBlock = newBlock;
        match(()-> finalNewBlock, copyBlock, group, blockID);
    }

    /**
     * Implements {@link BlockCreator#registerPyriteItem(String)} on Fabric.
     * This registers a basic item with no additional settings - primarily used for Dye.
     */
    public static void registerPyriteItem(String itemID) {
        var item = new Item(new Item.Settings());
        ITEMS.put(itemID, item);
        DYES.add(()-> item);
    }

    public static BlockItem addBlockItem(String blockID, Block block) {
        Item.Settings settings = new Item.Settings();
        if (blockID.contains("netherite"))
            settings = settings.fireproof();
        return new BlockItem(block, settings);
    }

    public static void register() {
        //Register blocks and block items.
        for (Map.Entry<String, Block> entry : BLOCKS.entrySet()) {
            final Block block = entry.getValue();
            final String blockID = entry.getKey();
            Registry.register(Registries.BLOCK, locate(blockID), block);
            Registry.register(Registries.ITEM, locate(blockID), addBlockItem(blockID, block));
        }
        //Registers blocks without block items.
        for (Map.Entry<String, Block> entry : ITEMLESS_BLOCKS.entrySet()) {
            final Block block = entry.getValue();
            final String blockID = entry.getKey();
            Registry.register(Registries.BLOCK, locate(blockID), block);
        }
        //Registers items.
        for (Map.Entry<String, Item> entry : ITEMS.entrySet()) {
            final Item item = entry.getValue();
            final String itemID = entry.getKey();
            Registry.register(Registries.ITEM, locate(itemID), item);
        }


		for (Map.Entry<String, Supplier<Block>> entry : COPPER_BLOCKS.entrySet()) {
			OxidizableBlocksRegistry.registerOxidizableBlockPair(entry.getValue().get(), getBlock(entry.getKey().replace("copper", "exposed_copper")));
		}
		for (Map.Entry<String, Supplier<Block>> entry : EXPOSED_COPPER_BLOCKS.entrySet()) {
			OxidizableBlocksRegistry.registerOxidizableBlockPair(entry.getValue().get(), getBlock(entry.getKey().replace("exposed", "weathered")));
		}
		for (Map.Entry<String, Supplier<Block>> entry : WEATHERED_COPPER_BLOCKS.entrySet()) {
			OxidizableBlocksRegistry.registerOxidizableBlockPair(entry.getValue().get(), getBlock(entry.getKey().replace("weathered", "oxidized")));
		}

        // Register item group.
        addItemGroup("pyrite_group", "glowing_obsidian", BLOCKS);
        // Add items to item group.
        modifyEntries();
    }
}
