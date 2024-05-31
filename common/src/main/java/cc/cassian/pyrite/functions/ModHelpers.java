package cc.cassian.pyrite.functions;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.ToIntFunction;


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

}