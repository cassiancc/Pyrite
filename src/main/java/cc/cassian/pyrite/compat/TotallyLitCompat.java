package cc.cassian.pyrite.compat;

//? if fabric {

import cc.cassian.pyrite.blocks.ModWallMounted;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
//? <26
//import io.github.realguyman.totally_lit.TotallyLit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;


public class TotallyLitCompat {
    public static ArrayList<Block> UNLIT_TORCHES = new ArrayList<>();

    public static void registerTorch(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        var block = new ModWallMounted(blockSettings.lightLevel((state)->0));
        BlockCreator.putBlock(blockID, block);
        add(block);
        PyriteItemGroups.match(new BlockEntry<>(blockID, block), copyBlock, group);
        //? <26
        //TotallyLit.TORCH_MAP.put(copyBlock, block);
        ModHelpers.addTransparentBlock(block);
    }

    public static void add(Block newBlock) {
        UNLIT_TORCHES.add(newBlock);
    }
}

//?}