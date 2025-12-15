package cc.cassian.pyrite.compat;
//? =1.21.1 {
/*import com.github.smallinger.copperagebackport.block.shelf.ShelfBlock;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
//? neoforge {
/^import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import static cc.cassian.pyrite.functions.neoforge.NeoHelpers.FUEL_BLOCKS;
import static cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl.WOOD_BLOCKS;
^///?}

import java.util.ArrayList;
import java.util.function.Supplier;

import static com.github.smallinger.copperagebackport.registry.ModBlockEntities.SHELF_BLOCK_ENTITY;

public class CopperAgeBackportCompat {
    public static ArrayList<Supplier<Block>> SHELVES = new ArrayList<>();

    //? if fabric {
    public static Block registerShelf(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock, MapColor color) {
        return new ShelfBlock(blockSettings);
    }
    //?} else {
    /^public static Supplier<Block> registerShelf(BlockBehaviour.Properties blockSettings) {
        Supplier<Block> blockSupplier = () -> new ShelfBlock(blockSettings);
        FUEL_BLOCKS.put(blockSupplier, 300);
        WOOD_BLOCKS.add(blockSupplier);
        return blockSupplier;
    }
    ^///?}

    public static void add(Supplier<Block> newBlock) {
        SHELVES.add(newBlock);
    }

    public static void registerToBlockEntity(
            //? if neoforge
            /^BlockEntityTypeAddBlocksEvent event^/
    ) {
        for (Supplier<Block> chest : SHELVES) {
            //? fabric {
            SHELF_BLOCK_ENTITY.get().addSupportedBlock(chest.get());
            //?} else if neoforge {
            /^event.modify(SHELF_BLOCK_ENTITY.get(), chest.get());
             ^///?}
        }
    }
}
*///?}