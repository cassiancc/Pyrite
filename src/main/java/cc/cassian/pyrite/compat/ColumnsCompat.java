package cc.cassian.pyrite.compat;

//? if fabric {

import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.blocks.OxidizableColumnBlock;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.registry.PyriteItemGroups;
//? <26.1
//import io.github.haykam821.columns.block.ColumnBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static cc.cassian.pyrite.registry.BlockCreator.BLOCKS;

public class ColumnsCompat {
    public static void registerCopperColumn(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        //? <26.1 {
        /*String columnID = blockID.replace("wall", "column");
        String waxedColumnID = "waxed_"+columnID;
        var column = new OxidizableColumnBlock(ModHelpers.getOxidizationState(blockID), blockSettings);
        BLOCKS.put(columnID, column);
        Block waxed_column = new ColumnBlock(blockSettings);
        BLOCKS.put(waxedColumnID, waxed_column);
        PyriteItemGroups.match(()->column, copyBlock, group, columnID);
        PyriteItemGroups.match(()->waxed_column, copyBlock, "waxed_"+group, waxedColumnID);
        Platform.INSTANCE.registerWaxableBlockPair(column, waxed_column);
        *///?}
    }

    public static void registerColumn(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        //? <26.1 {
        /*String columnID = blockID.replace("wall", "column");
        Block column = new ColumnBlock(blockSettings);
        BLOCKS.put(columnID, column);
        PyriteItemGroups.match(()->column, copyBlock, group, columnID);
        *///?}
    }
}

//?}
