package cc.cassian.pyrite.compat;

//? if <1.21.4 {
/*import io.github.lieonlion.mcv.block.MoreChestBlock;
import static io.github.lieonlion.mcv.init.McvBlockInit.MORE_CHEST_BLOCK_ENTITY;
*///?} else if fabric && <26.1 {
import dev.lieonlion.mcv.block.FabricMoreChestBlock;
import static dev.lieonlion.mcv.init.FabricMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY;
//?} else if neoforge {
/*import static dev.lieonlion.mcv.init.NeoForgeMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY;
import dev.lieonlion.mcv.block.NeoForgeMoreChestBlock;
*///?}

import cc.cassian.pyrite.functions.ModHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


import java.util.ArrayList;
import java.util.function.Supplier;

public class ChestsCompat {

    public static Block registerChest(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock, MapColor color) {
        //? if <1.21.4 {
        /*return new MoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", ""));
        *///?} else if fabric && <26.1 {
        return new FabricMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", ""));
        //?} else if neoforge {
        /*return new NeoForgeMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", ""));
        *///?} else {
        /*return null;
        *///?}
    }

    public static void add(Block chest) {
        //? if <26.1
        ModHelpers.addSupportedBlock(MORE_CHEST_BLOCK_ENTITY, chest);
        ModHelpers.addSupportedBlock(()->BlockEntityType.CHEST, chest);
    }
}
