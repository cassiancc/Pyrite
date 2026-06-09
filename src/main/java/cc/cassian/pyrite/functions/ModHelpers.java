package cc.cassian.pyrite.functions;

import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteItemTags;
import cc.cassian.pyrite.entries.BlockEntry;
import com.google.common.collect.LinkedHashMultimap;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
//? if >1.21.8
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static cc.cassian.pyrite.Pyrite.*;

public class ModHelpers {

    public static final ArrayList<Block> GRASS_BLOCKS = new ArrayList<>();
    public static final ArrayList<Block> TRANSPARENT_BLOCKS = new ArrayList<>();
    public static final ArrayList<Block> TRANSLUCENT_BLOCKS = new ArrayList<>();
    public static final LinkedHashMultimap<Supplier<BlockEntityType<?>>, Block> SUPPORTED_BLOCKS = LinkedHashMultimap.create();

    public static List<String> getRequiredOptions(Identifier itemId) {
        List<String> requiredOptions = new ArrayList<>();
        String path = itemId.getPath();
        if (path.contains("crafting_table")) {
            requiredOptions.add("crafting_tables");
        }
        if (path.contains("azalea")) {
            requiredOptions.add("azalea");
        } else if (path.contains("mushroom")) {
            requiredOptions.add("mushrooms");
        } else if (path.contains("glow") || path.contains("honey") || path.equals("locked_chest") || path.contains("nostalgia") || path.contains("switchable_glass") || path.contains("rose") || path.contains("paeonia") || path.contains("buttercup") || path.contains("pink_daisy") || path.contains("star_") || path.contains("dragon_") || path.contains("poisonous_")) {
            requiredOptions.add("oddities");
        }
        if (path.contains("torch_lever")) {
            requiredOptions.add("torch_levers");
        }
        if (path.contains("wool_stair") || path.contains("wool_slab")) {
            requiredOptions.add("wool_stairs_and_slabs");
        }
        if (path.contains("concrete_stair") || path.contains("concrete_slab")) {
            requiredOptions.add("concrete_stairs_and_slabs");
        }
        if (path.contains("framed_glass")) {
            requiredOptions.add("framed_glass");
        }
        if (path.contains("wall_gate")) {
            requiredOptions.add("wall_gates");
        }
        for (Block vanillaResourceBlock : ModLists.getVanillaResourceBlocks()) {
            var resourceBlockPath = BuiltInRegistries.BLOCK.getKey(vanillaResourceBlock).getPath().replace("_block", "").replace("weathered_", "").replace("oxidized_", "").replace("exposed_", "");
            if (path.contains(resourceBlockPath)) {
                if (!requiredOptions.contains(resourceBlockPath))
                    requiredOptions.add(resourceBlockPath);
            }
        }
        return requiredOptions;
    }

    public static boolean enabled(List<String> options) {
        for (String option : options) {
            @SuppressWarnings("all")
            var value = ((TrackedValue<Boolean>) Pyrite.CONFIG.getValue(List.of(option))).value();
            if (value == false) return false;
        }
        return true;
    }

    public static boolean enabled(Identifier itemId) {
        return enabled(getRequiredOptions(itemId));
    }

    public static boolean enabled(ItemStack stack) {
        return enabled(BuiltInRegistries.ITEM.getKey(stack.getItem()));
    }

    public static boolean enabled(BlockEntry<?> stack) {
        return enabled(stack.getId());
    }

    public static BlockBehaviour.Properties copyBlock(Block copyBlock) {
        return BlockBehaviour.Properties.ofFullCopy(copyBlock);
    }

    public static ToIntFunction<BlockState> parseLux(int lux) {
        return state -> lux;
    }

    public static String findVanillaBlockID(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

    public static BlockBehaviour.Properties flowerPotProperties(ResourceKey<Block> blockResourceKey) {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)
                //? if >1.21.4 {
                .setId(blockResourceKey)
                //?}
                ;
    }

    public static ResourceKey<Block> registryKeyBlock(String id) {
        return ResourceKey.create(Registries.BLOCK, of(id));
    }

    public static ResourceKey<Item> registryKeyItem(String id) {
        return ResourceKey.create(Registries.ITEM, of(id));
    }

    public static Item.Properties newItemSettings(String id) {
        return new Item.Properties().setId(registryKeyItem(id));
    }

    public static Item.Properties newBlockItemSettings(String id) {
        return newItemSettings(id).useBlockDescriptionPrefix();
    }

    public static Block getBlock(String id) {
        return getBlock(of(id));
    }

    public static Block getBlock(Identifier id) {
        return BuiltInRegistries.BLOCK.getValue(id);
    }


    public static MapColor checkDyeMapColour(String dye) {
        return switch (dye) {
            case "glow" -> MapColor.COLOR_CYAN;
            case "dragon" -> MapColor.COLOR_BLACK;
            case "star" -> MapColor.QUARTZ;
            case "honey" -> MapColor.COLOR_YELLOW;
            case "nostalgia" -> MapColor.COLOR_BROWN;
            case "rose" -> MapColor.FIRE;
            case "poisonous" -> MapColor.COLOR_LIGHT_GREEN;
            default -> DyeColor.valueOf(dye.toUpperCase()).getMapColor();
        };
    }

    public static int checkDyeLux(String dye) {
        return switch (dye) {
            case "glow" -> 8;
            case "star" -> 15;
            default -> 0;
        };
    }

    public static ParticleOptions getTorchParticle(String dye) {
        return switch (dye) {
            case "dragon" -> PowerParticleOption.create(ParticleTypes.DRAGON_BREATH, 1);
            case "glow" -> ParticleTypes.GLOW;
            case "star" -> ParticleTypes.ENCHANT;
            default -> ParticleTypes.SMOKE;
        };
    }

    public static int power(String blockID) {
        if (blockID.contains("redstone")) return 15;
        else return 0;
    }

    public static DyeColor getDyeColorFromFramedId(String blockID) {
        String dye;
        if (blockID.contains("framed"))
            dye = blockID.split("_framed")[0];
        else if (blockID.contains("stained"))
            dye = blockID.split("_stained")[0];
        else dye = "";
        return switch (dye) {
            case "glow" -> DyeColor.CYAN;
            case "dragon" -> DyeColor.BLACK;
            case "star" -> DyeColor.LIGHT_BLUE;
            case "honey" -> DyeColor.YELLOW;
            case "nostalgia" -> DyeColor.BROWN;
            case "rose" -> DyeColor.PINK;
            case "poisonous" -> DyeColor.LIME;
            default -> DyeColor.byName(dye, DyeColor.WHITE);
        };
    }

    public static @NotNull BlockSetType getBlockSetType(String blockID) {
        boolean openByHand = !blockID.equals("emerald") && (!blockID.equals("netherite") && (!blockID.equals("diamond")));
        SoundType soundGroup = switch (blockID) {
            case "amethyst" -> SoundType.AMETHYST;
            case "copper", "exposed_copper", "weathered_copper", "oxidized_copper" -> SoundType.COPPER;
            case "quartz", "lapis", "diamond", "redstone", "gold" -> SoundType.STONE;
            default -> SoundType.METAL;
        };

        return new BlockSetType(blockID, openByHand, openByHand, openByHand, BlockSetType.PressurePlateSensitivity.EVERYTHING, soundGroup, SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);
    }

    public static boolean isCopper(String blockID) {
        return blockID.contains("copper");
    }

    public static WeatheringCopper.WeatherState getOxidizationState(String blockID) {
        if (blockID.contains("oxidized"))
            return WeatheringCopper.WeatherState.OXIDIZED;
        else if (blockID.contains("weathered"))
            return WeatheringCopper.WeatherState.WEATHERED;
        else if (blockID.contains("exposed"))
            return WeatheringCopper.WeatherState.EXPOSED;
        return WeatheringCopper.WeatherState.UNAFFECTED;
    }

    public static void log(String log) {
        if (Platform.INSTANCE.isDevEnvironment())
            LOGGER.info(log);
    }

    @SuppressWarnings("unused") // fabric
    public static InteractionResult updateTorchColour(Player player, Level world, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getItemInHand(hand);
        BlockState state = world.getBlockState(hitResult.getBlockPos());
        return updateTorchColour(stack, state, player, world, hitResult.getBlockPos());
    }

    @SuppressWarnings("unused") // neoforge
    public static InteractionResult updateTorchColour(ItemStack itemStack, @Nullable Player player, Level level, BlockPos pos) {
        return updateTorchColour(itemStack, level.getBlockState(pos), player, level, pos);
    }

    public static InteractionResult updateTorchColour(ItemStack stack, BlockState state, Player player, Level world, BlockPos pos) {
        if (stack.is(PyriteItemTags.DYES)) {
            Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
            Block dyedTorch = getBlock(Identifier.fromNamespaceAndPath(MOD_ID, id.getPath().replace("dye", "torch")));
            if (state.is(Blocks.TORCH)) {
                world.setBlockAndUpdate(pos, dyedTorch.withPropertiesOf(state));
                stack.consume(1, player);
            } else if (state.is(Blocks.WALL_TORCH)) {
                world.setBlockAndUpdate(pos, dyedTorch.withPropertiesOf(state).setValue(BlockStateProperties.ATTACH_FACE, AttachFace.WALL));
                stack.consume(1, player);
            } else return InteractionResult.PASS;
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public static void addAlias(String id) {
        BuiltInRegistries.BLOCK.addAlias(Pyrite.of(id), Pyrite.of("minecraft", id));
        BuiltInRegistries.ITEM.addAlias(Pyrite.of(id), Pyrite.of("minecraft", id));
    }


    public static void addTransparentBlock(Block newBlock) {
        TRANSPARENT_BLOCKS.add(newBlock);
    }
    public static void addTranslucentBlock(Block newBlock) {
        TRANSLUCENT_BLOCKS.add(newBlock);
    }

    @SuppressWarnings("all")
    public static void addSupportedBlock(Supplier<?> be, Block block) {
        SUPPORTED_BLOCKS.put((Supplier<BlockEntityType<?>>) be, block);
    }

    public static void addSupportedBlock(BlockEntityType<?> be, Block block) {
        SUPPORTED_BLOCKS.put(()->be, block);
    }
}