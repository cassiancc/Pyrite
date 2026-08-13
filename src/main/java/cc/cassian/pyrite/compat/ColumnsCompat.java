package cc.cassian.pyrite.compat;

//? if fabric {

import cc.cassian.mru.util.ItemLikeEntry;
import cc.cassian.pyrite.PyritePlatform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.OxidizableColumnBlock;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.util.ModHelpers;
import io.github.haykam821.columns.block.ColumnBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ColumnsCompat {
    public static void registerCopperColumn(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        String columnID = blockID.replace("wall", "column");
        ItemLikeEntry<OxidizableColumnBlock> column = Pyrite.entryOf(columnID, new OxidizableColumnBlock(ModHelpers.getOxidizationState(blockID), blockSettings));
        BlockCreator.putBlock(column);
        ItemLikeEntry<Block> waxedColumn = Pyrite.entryOf("waxed_"+columnID, new ColumnBlock(blockSettings));
        BlockCreator.putBlock(waxedColumn);
        PyriteItemGroups.match(column, copyBlock, group);
        PyriteItemGroups.match(waxedColumn, copyBlock, "waxed_"+group);
        PyritePlatform.INSTANCE.registerWaxableBlockPair(column, waxedColumn);
    }

    public static void registerColumn(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        String columnID = blockID.replace("wall", "column");
        Block column = new ColumnBlock(blockSettings);
        ItemLikeEntry<Block> entry = Pyrite.entryOf(columnID, column);
        BlockCreator.putBlock(entry);
        PyriteItemGroups.match(entry, copyBlock, group);
    }
}

//?}
