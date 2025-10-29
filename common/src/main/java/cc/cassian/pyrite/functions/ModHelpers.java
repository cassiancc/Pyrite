package cc.cassian.pyrite.functions;

import cc.cassian.pyrite.core.PyriteTags;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DragonBreathParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.function.ToIntFunction;

import static cc.cassian.pyrite.Pyrite.LOGGER;
import static cc.cassian.pyrite.Pyrite.MOD_ID;


public class ModHelpers {

    public static AbstractBlock.Settings copyBlock(Block copyBlock) {
        return AbstractBlock.Settings.copy(copyBlock);
    }

    public static ToIntFunction<BlockState> parseLux(int lux) {
        return state -> lux;
    }

    public static String findVanillaBlockID(Block block) {
        return block.toString().substring(block.toString().indexOf(":") + 1, block.toString().indexOf("}"));
    }

    public static Identifier locate(String id) {
        return Identifier.of(MOD_ID, id);
    }

    public static RegistryKey<Block> registryKeyBlock(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, locate(id));
    }

    public static RegistryKey<Item> registryKeyItem(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, locate(id));
    }

    public static Item.Settings newItemSettings(String id) {
        return new Item.Settings().registryKey(registryKeyItem(id));
    }

    public static Item.Settings newBlockItemSettings(String id) {
        return newItemSettings(id).useBlockPrefixedTranslationKey(); 
    }

    public static Block getBlock(String id) {
        return Registries.BLOCK.get(ModHelpers.locate(id));
    }

    public static MapColor checkDyeMapColour(String dye) {
        return switch (dye) {
            case "glow" -> MapColor.CYAN;
            case "dragon" -> MapColor.BLACK;
            case "star" -> MapColor.OFF_WHITE;
            case "honey" -> MapColor.YELLOW;
            case "nostalgia" -> MapColor.BROWN;
            case "rose" -> MapColor.BRIGHT_RED;
            case "poisonous" -> MapColor.LIME;
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

    public static ParticleEffect getTorchParticle(String dye) {
        return switch (dye) {
            case "dragon" -> DragonBreathParticleEffect.of(ParticleTypes.DRAGON_BREATH, 1);
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
            default -> DyeColor.byId(dye, DyeColor.WHITE);
        };
    }

    public static @NotNull BlockSetType getBlockSetType(String blockID) {
        boolean openByHand = !blockID.equals("emerald") && (!blockID.equals("netherite") && (!blockID.equals("diamond")));
        BlockSoundGroup soundGroup = switch (blockID) {
            case "amethyst":
                yield BlockSoundGroup.AMETHYST_BLOCK;
            case "copper", "exposed_copper", "weathered_copper", "oxidized_copper":
                yield BlockSoundGroup.COPPER;
            case "quartz", "lapis", "diamond", "redstone", "gold":
                yield BlockSoundGroup.STONE;
             default:
                yield BlockSoundGroup.METAL;
        };

        return new BlockSetType(blockID, openByHand, openByHand, openByHand, BlockSetType.ActivationRule.EVERYTHING, soundGroup, SoundEvents.BLOCK_IRON_DOOR_CLOSE, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON);
    }

    @ExpectPlatform
    public static boolean isModLoaded(String modID) {
        throw new AssertionError();
    }

    public static boolean isShield(ItemStack stack) {
        return stack.isIn(PyriteTags.SHIELDS);
    }

    public static boolean isCopper(String blockID) {
        return blockID.contains("copper");
    }

    public static Oxidizable.OxidationLevel getOxidizationState(String blockID) {
        if (blockID.contains("oxidized"))
            return Oxidizable.OxidationLevel.OXIDIZED;
        else if (blockID.contains("weathered"))
            return Oxidizable.OxidationLevel.WEATHERED;
        else if (blockID.contains("exposed"))
            return Oxidizable.OxidationLevel.EXPOSED;
        return Oxidizable.OxidationLevel.UNAFFECTED;
    }

    public static void log(String log) {
        if (isDevEnvironment())
            LOGGER.info(log);
    }

    @ExpectPlatform
    public static boolean isDevEnvironment() {
        throw new AssertionError();
    }

    public static ActionResult updateTorchColour(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getStackInHand(hand);
        BlockState state = world.getBlockState(hitResult.getBlockPos());
        return updateTorchColour(stack, state, player, world, hitResult.getBlockPos());
    }

    public static ActionResult updateTorchColour(ItemStack stack, BlockState state, PlayerEntity player, World world, BlockPos pos) {
        if (stack.isIn(PyriteTags.DYES)) {
            Identifier id = Registries.ITEM.getId(stack.getItem());
            Block dyedTorch = Registries.BLOCK.get(Identifier.of(MOD_ID, id.getPath().replace("dye", "torch")));
            if (state.isOf(Blocks.TORCH)) {
                world.setBlockState(pos, dyedTorch.getStateWithProperties(state));
                stack.decrementUnlessCreative(1, player);
            } else if (state.isOf(Blocks.WALL_TORCH)) {
                world.setBlockState(pos, dyedTorch.getStateWithProperties(state).with(Properties.BLOCK_FACE, BlockFace.WALL));
                stack.decrementUnlessCreative(1, player);
            } else return ActionResult.PASS;
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    public static ActionResult updateTorchColour(ItemStack itemStack, @Nullable PlayerEntity player, World level, BlockPos pos) {
        return updateTorchColour(itemStack, level.getBlockState(pos), player, level, pos);
    }

    @ExpectPlatform
    public static Path getConfigDir() {
        return null;
    }
}