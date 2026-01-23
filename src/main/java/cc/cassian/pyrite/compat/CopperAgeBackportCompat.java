package cc.cassian.pyrite.compat;

//? =1.21.1 {
/*import cc.cassian.pyrite.functions.ModHelpers;
import com.github.smallinger.copperagebackport.block.shelf.ShelfBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
//? neoforge {
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
//?}

import java.util.ArrayList;
import java.util.function.Supplier;

import static com.github.smallinger.copperagebackport.registry.ModBlockEntities.SHELF_BLOCK_ENTITY;

public class CopperAgeBackportCompat {
    public static ArrayList<Supplier<Block>> SHELVES = new ArrayList<>();

    public static Block registerShelf(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock, MapColor color) {
        return new ShelfBlock(blockSettings);
    }


    public static void add(Block newBlock) {
        ModHelpers.addSupportedBlock(SHELF_BLOCK_ENTITY, newBlock);
    }
}
*///?}