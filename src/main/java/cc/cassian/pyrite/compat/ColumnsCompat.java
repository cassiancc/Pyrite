package cc.cassian.pyrite.compat;

//? if fabric {

import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.Platform;
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
        BlockEntry<OxidizableColumnBlock> column = new BlockEntry<>(columnID, new OxidizableColumnBlock(ModHelpers.getOxidizationState(blockID), blockSettings));
        BlockCreator.putBlock(column);
        BlockEntry<Block> waxedColumn = new BlockEntry<>("waxed_"+columnID, new ColumnBlock(blockSettings));
        BlockCreator.putBlock(waxedColumn);
        PyriteItemGroups.match(column, copyBlock, group);
        PyriteItemGroups.match(waxedColumn, copyBlock, "waxed_"+group);
        Platform.INSTANCE.registerWaxableBlockPair(column, waxedColumn);
    }

    public static void registerColumn(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        String columnID = blockID.replace("wall", "column");
        Block column = new ColumnBlock(blockSettings);
        BlockEntry<Block> entry = new BlockEntry<>(columnID, column);
        BlockCreator.putBlock(entry);
        PyriteItemGroups.match(entry, copyBlock, group);
    }
}

//?}
