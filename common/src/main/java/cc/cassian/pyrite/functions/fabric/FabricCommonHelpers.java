package cc.cassian.pyrite.functions.fabric;

import net.minecraft.block.Block;

import java.util.ArrayList;

import static cc.cassian.pyrite.functions.fabric.FabricRegistry.pyriteBlocks;

public class FabricCommonHelpers {
    public static ArrayList<Block> transparentBlocks = new ArrayList<>();
    public static ArrayList<Block> grassBlocks = new ArrayList<>();
    public static void addGrassBlock() {
        grassBlocks.add(getLastBlock());
    }

    public static Block getLastBlock() {
        return pyriteBlocks.get(pyriteBlocks.size() - 1);
    }

    public static void addTransparentBlock() {
        transparentBlocks.add(getLastBlock());
    }
}
