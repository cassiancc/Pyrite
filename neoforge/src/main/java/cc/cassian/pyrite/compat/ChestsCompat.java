package cc.cassian.pyrite.compat;


import dev.lieonlion.mcv.block.NeoForgeMoreChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;

import java.util.ArrayList;
import java.util.function.Supplier;

import static cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl.BLOCKS;
import static dev.lieonlion.mcv.init.NeoForgeMoreChestVariantsBlocks.MORE_CHEST_BLOCK_ENTITY;

public class ChestsCompat {
    public static ArrayList<Supplier<Block>> CHESTS = new ArrayList<>();

    public static Supplier<Block> registerChest(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock, MapColor color) {
        return BLOCKS.register(blockID, () -> new NeoForgeMoreChestBlock(color,"pyrite_"+ blockID.replace("_chest", "")));
    }

    public static void add(Supplier<Block> newBlock) {
        CHESTS.add(newBlock);
    }

    public static void registerToBlockEntity(BlockEntityTypeAddBlocksEvent event) {
        for (Supplier<Block> chest : CHESTS) {
            event.modify(MORE_CHEST_BLOCK_ENTITY.get(), chest.get());
        }
    }
}
