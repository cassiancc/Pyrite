package cc.cassian.pyrite.compat;

import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

import static cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl.BLOCKS;

public class ChestsCompat {
    public static ArrayList<Supplier<Block>> CHESTS = new ArrayList<>();

    public static Supplier<Block> registerChest(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        return BLOCKS.register(blockID, () -> new MoreChestBlock(blockSettings,() -> McvBlockInit.MORE_CHEST_BLOCK_ENTITY.get(), "pyrite_"+ blockID.replace("_chest", "")));
    }

    public static void add(Supplier<Block> newBlock) {
        CHESTS.add(newBlock);
    }

    public static void registerToBlockEntity(BlockEntityTypeAddBlocksEvent event) {
        for (Supplier<Block> chest : CHESTS) {
            event.modify(McvBlockInit.MORE_CHEST_BLOCK_ENTITY.get(), chest.get());
        }
    }
}
