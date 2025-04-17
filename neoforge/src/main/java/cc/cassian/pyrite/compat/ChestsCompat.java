package cc.cassian.pyrite.compat;

import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

import static cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl.BLOCKS;

public class ChestsCompat {
    public static ArrayList<Supplier<Block>> CHESTS = new ArrayList<>();

    public static Supplier<Block> registerChest(String blockID, AbstractBlock.Settings blockSettings, String group, Block copyBlock) {
        Supplier<Block> chest = BLOCKS.register(blockID, () -> new MoreChestBlock(blockSettings,() -> McvBlockInit.MORE_CHEST_BLOCK_ENTITY.get(), blockID.replace("_chest", "")));
        return chest;
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
