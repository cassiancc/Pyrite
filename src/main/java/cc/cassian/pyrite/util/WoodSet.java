package cc.cassian.pyrite.util;

import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public record WoodSet(String blockID, BlockSetType blockSetType, WoodType woodType, BlockEntry<Block> planks, BlockEntry<Block> stairs,
                      BlockEntry<Block> slab, BlockEntry<Block> fence, BlockEntry<Block> fenceGate, BlockEntry<Block> door, BlockEntry<Block> trapdoor, BlockEntry<Block> pressurePlate,
                      BlockEntry<Block> button, BlockEntry<Block> craftingTable, BlockEntry<Block> ladder, BlockEntry<Block> sign, BlockEntry<Block> hangingSign, BlockEntry<Block> shelf,
                      ItemEntry<Item> boat, ItemEntry<Item> chestBoat, BlockEntry<Block> chest) {
}
