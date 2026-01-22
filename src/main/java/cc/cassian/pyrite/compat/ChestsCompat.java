package cc.cassian.pyrite.compat;

//? if neoforge {
/*import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import static cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl.BLOCKS;

//? if >1.21.4 {
import static dev.lieonlion.mcv.init.NeoForgeMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY;
import dev.lieonlion.mcv.block.NeoForgeMoreChestBlock;
//?} else {
/^import io.github.lieonlion.mcv.block.MoreChestBlock;
import static io.github.lieonlion.mcv.init.McvBlockInit.MORE_CHEST_BLOCK_ENTITY;
^///?}

*///?}

//? if fabric && <26.1 {
    //? if >1.21.4 {
    import dev.lieonlion.mcv.block.FabricMoreChestBlock;
    import dev.lieonlion.mcv.init.FabricMoreChestVariantsBlocks;
    //?} else {
    /*import io.github.lieonlion.mcv.block.MoreChestBlock;
    import io.github.lieonlion.mcv.init.McvBlockInit;
    *///?}
//?}


import cc.cassian.pyrite.Platform;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


import java.util.ArrayList;
import java.util.function.Supplier;

public class ChestsCompat {
    public static ArrayList<Supplier<Block>> CHESTS = new ArrayList<>();

    //? if fabric && <26.1 {
    public static Block registerChest(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock, MapColor color) {
        //? if >1.21.4 {
        return new FabricMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", ""));
        //?} else if =1.21.1 {
        /*return new MoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", ""));
        *///?}
    }
    //?} else if neoforge {
    /*public static Supplier<Block> registerChest(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock, MapColor color) {
        //? if <1.21.4 {
        /^return BLOCKS.register(blockID, ()-> new MoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", "")));
        ^///?} else {
        return BLOCKS.register(blockID, () -> new NeoForgeMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", "")));
        //?}
    }
    *///?}

    public static void add(Supplier<Block> newBlock) {
        CHESTS.add(newBlock);
    }

    public static void registerToBlockEntity(
            //? if neoforge
            /*BlockEntityTypeAddBlocksEvent event*/
    ) {
        for (Supplier<Block> chest : CHESTS) {
            //? if <1.21.4 && fabric {
            /*McvBlockInit.MORE_CHEST_BLOCK_ENTITY.addSupportedBlock(chest.get());
            BlockEntityType.CHEST.addSupportedBlock(chest.get());
            *///?} else if neoforge {
            /*event.modify(MORE_CHEST_BLOCK_ENTITY.get(), chest.get());
            *///?} else if <26.1 && fabric {
            Platform.INSTANCE.addSupportedBlock(FabricMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY, chest.get());
            Platform.INSTANCE.addSupportedBlock(BlockEntityType.CHEST, chest.get());
            //?}
        }
    }
}
