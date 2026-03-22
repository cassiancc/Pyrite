package cc.cassian.pyrite.compat;

//? =1.21.1 {
import cc.cassian.pyrite.functions.ModHelpers;
import com.github.smallinger.copperagebackport.block.shelf.ShelfBlock;
import com.github.smallinger.copperagebackport.registry.ModBlockEntities;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
//? neoforge {
/*import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
*///?}

import java.util.ArrayList;
import java.util.function.Supplier;

import static com.github.smallinger.copperagebackport.registry.ModBlockEntities.SHELF_BLOCK_ENTITY;

public class CopperAgeBackportCompat {
    public static ArrayList<Block> SHELVES = new ArrayList<>();

    public static Block registerShelf(BlockBehaviour.Properties blockSettings) {
		ShelfBlock shelfBlock = new ShelfBlock(blockSettings);
		SHELVES.add(shelfBlock);
		return shelfBlock;
    }


    public static void add(Block newBlock) {
        SHELVES.add(newBlock);
    }

	public static void registerToBlockEntity() {
		SHELVES.forEach(block-> {
			SHELF_BLOCK_ENTITY.get().addSupportedBlock(block);
		});
	}
}
//?}