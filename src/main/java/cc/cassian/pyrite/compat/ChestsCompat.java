package cc.cassian.pyrite.compat;

//? if <1.21.4 {
/*import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.block.MoreChestBlock;
import static io.github.lieonlion.mcv.init.McvBlockInit.MORE_CHEST_BLOCK_ENTITY;
*///?} else if fabric {
import dev.lieonlion.mcv.block.FabricMoreChestBlock;
import static dev.lieonlion.mcv.init.FabricMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY;
//?} else if neoforge {
/*import static dev.lieonlion.mcv.init.NeoForgeMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY;
import dev.lieonlion.mcv.block.NeoForgeMoreChestBlock;
*///?}

import cc.cassian.pyrite.Platform;
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
        *///?} else if fabric {
        return new FabricMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", ""));
        //?} else if neoforge {
        /*return new NeoForgeMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", ""));
        *///?}
    }

    public static void add(Block chest) {
        //? if <1.21.4 {
        /*Platform.INSTANCE.addSupportedBlock(MORE_CHEST_BLOCK_ENTITY.get(), chest);
        *///?} else if <26.1 && fabric {
        Platform.INSTANCE.addSupportedBlock(MORE_CHEST_BLOCK_ENTITY, chest);
        //?} else if <26.1 && neoforge {
        /*Platform.INSTANCE.addSupportedBlock(MORE_CHEST_BLOCK_ENTITY.get(), chest);
        *///?}
        Platform.INSTANCE.addSupportedBlock(BlockEntityType.CHEST, chest);
    }
}
