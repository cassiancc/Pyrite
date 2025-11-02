package cc.cassian.pyrite.functions.neoforge;

//? if neoforge {

import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;


public class NeoHelpers {

    public static final ArrayList<Supplier<Block>> GRASS_BLOCKS = new ArrayList<>();
    public static final HashMap<Supplier<Block>, Integer> FUEL_BLOCKS = new HashMap<>();
    public static final HashMap<Supplier<Block>, Integer> FLAMMABLE_BLOCKS = new HashMap<>();

    public static void addGrassBlock(Supplier<Block> newBlock) {
        GRASS_BLOCKS.add(newBlock);
    }

}

//?}