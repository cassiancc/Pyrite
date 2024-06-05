package cc.cassian.pyrite.functions.fabric;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

import static cc.cassian.pyrite.functions.fabric.FabricRegistry.pyriteBlocks;

public class FabricCommonHelpers {
    public static ArrayList<Block> transparentBlocks = new ArrayList<>();
    public static ArrayList<Block> translucentBlocks = new ArrayList<>();
    public static ArrayList<Block> grassBlocks = new ArrayList<>();
    public static HashMap<Block, Integer> fuel = new HashMap<>();

    public static void addGrassBlock() {
        grassBlocks.add(getLastBlock());
    }
    public static void addTransparentBlock() {
        transparentBlocks.add(getLastBlock());
    }
    public static void addTranslucentBlock() {
        translucentBlocks.add(getLastBlock());
    }

    public static Block getLastBlock() {
        return pyriteBlocks.get(pyriteBlocks.size() - 1);
    }


}
