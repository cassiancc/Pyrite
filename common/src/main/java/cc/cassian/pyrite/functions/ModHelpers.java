package cc.cassian.pyrite.functions;

import java.util.ArrayList;
import java.util.function.ToIntFunction;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import static cc.cassian.pyrite.Pyrite.*;

public class ModHelpers {
    public static ArrayList<Block> transparentBlocks = new ArrayList<>();
    public static ArrayList<Block> grassBlocks = new ArrayList<>();

    public static void addTransparentBlock() {
        transparentBlocks.add(getLastBlock());
    }

    public static void addGrassBlock() {
        grassBlocks.add(getLastBlock());
    }

    public static Block getLastBlock() {
        return pyriteBlocks.get(pyriteBlocks.size() - 1);
    }

    public static Block getLastBlock(int index) {
        return pyriteBlocks.get(pyriteBlocks.size() - index);
    }

    public static BlockBehaviour.Properties copyBlock(Block copyBlock) {
        return BlockBehaviour.Properties.copy(copyBlock);
    }

    public static ToIntFunction<BlockState> parseLux(int lux) {
        return state -> lux;
    }

    public static String findVanillaBlockID(Block block) {
        return block.toString().substring(block.toString().indexOf(":") + 1, block.toString().indexOf("}"));
    }

    public static MapColor checkDyeMapColour(String dye) {
        return switch (dye) {
            case "glow" -> MapColor.COLOR_CYAN;
            case "dragon" -> MapColor.COLOR_BLACK;
            case "star" -> MapColor.TERRACOTTA_WHITE;
            case "honey" -> MapColor.COLOR_YELLOW;
            case "nostalgia" -> MapColor.COLOR_BROWN;
            case "rose" -> MapColor.COLOR_RED;
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
