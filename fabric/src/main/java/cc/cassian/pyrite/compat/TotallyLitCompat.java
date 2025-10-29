package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.blocks.ModWallMounted;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.registry.fabric.BlockCreatorImpl;
import io.github.realguyman.totally_lit.TotallyLit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;

import static cc.cassian.pyrite.functions.fabric.FabricHelpers.addTransparentBlock;

public class TotallyLitCompat {
    public static ArrayList<Block> UNLIT_TORCHES = new ArrayList<>();

    public static void registerTorch(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        var block = new ModWallMounted(blockSettings.lightLevel((state)->0));
        BlockCreatorImpl.BLOCKS.put(blockID, block);
        add(block);
        PyriteItemGroups.match(()->block, copyBlock, group, "unlit_torch");
        TotallyLit.TORCH_MAP.put(copyBlock, block);
        addTransparentBlock(block);
    }

    public static void add(Block newBlock) {
        UNLIT_TORCHES.add(newBlock);
    }
}
