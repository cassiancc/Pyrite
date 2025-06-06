package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.blocks.fabric.OxidizableColumnBlock;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import io.github.haykam821.columns.block.ColumnBlock;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

import static cc.cassian.pyrite.registry.fabric.BlockCreatorImpl.BLOCKS;

public class ColumnsCompat {
    public static void registerCopperColumn(String blockID, AbstractBlock.Settings blockSettings, String group, Block copyBlock) {
        String columnID = blockID.replace("wall", "column");
        String waxedColumnID = "waxed_"+columnID;
        Block column = new OxidizableColumnBlock(ModHelpers.getOxidizationState(blockID), blockSettings);
        BLOCKS.put(columnID, column);
        Block waxed_column = new ColumnBlock(blockSettings);
        BLOCKS.put(waxedColumnID, waxed_column);
        PyriteItemGroups.match(()->column, copyBlock, group, columnID);
        PyriteItemGroups.match(()->waxed_column, copyBlock, "waxed_"+group, waxedColumnID);
        OxidizableBlocksRegistry.registerWaxableBlockPair(column, waxed_column);
    }

    public static void registerColumn(String blockID, AbstractBlock.Settings blockSettings, String group, Block copyBlock) {
        String columnID = blockID.replace("wall", "column");
        Block column = new ColumnBlock(blockSettings);
        BLOCKS.put(columnID, column);
        PyriteItemGroups.match(()->column, copyBlock, group, columnID);
    }
}
