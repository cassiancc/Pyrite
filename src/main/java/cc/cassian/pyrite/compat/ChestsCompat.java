package cc.cassian.pyrite.compat;


//? if neoforge {
import static dev.lieonlion.mcv.init.NeoForgeMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY;
import dev.lieonlion.mcv.block.NeoForgeMoreChestBlock;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import static cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl.BLOCKS;
//?} else {
/*import dev.lieonlion.mcv.block.FabricMoreChestBlock;
import dev.lieonlion.mcv.init.FabricMoreChestVariantsBlocks;
*///?}


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


import java.util.ArrayList;
import java.util.function.Supplier;

public class ChestsCompat {
    public static ArrayList<Supplier<Block>> CHESTS = new ArrayList<>();

    public static Supplier<Block> registerChest(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock, MapColor color) {
        //? if fabric {
        /*return ()-> new FabricMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", ""));
        *///?} else {
        return BLOCKS.register(blockID, () -> new NeoForgeMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", "")));
        //?}
    }

    public static void add(Supplier<Block> newBlock) {
        CHESTS.add(newBlock);
    }

    public static void registerToBlockEntity(
            //? if neoforge
            BlockEntityTypeAddBlocksEvent event
    ) {
        for (Supplier<Block> chest : CHESTS) {
            //? if neoforge {
            event.modify(MORE_CHEST_BLOCK_ENTITY.get(), chest.get());
            //?} else {
            /*FabricMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY.addSupportedBlock(chest.get());
            BlockEntityType.CHEST.addSupportedBlock(chest.get());
            *///?}
        }
    }
}
