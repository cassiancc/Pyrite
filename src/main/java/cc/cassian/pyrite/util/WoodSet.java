package cc.cassian.pyrite.util;

import cc.cassian.pyrite.entries.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public record WoodSet(String blockID, BlockSetType blockSetType, WoodType woodType, Block planks, Block stairs,
                      Block slab, Block fence, Block fenceGate, Block door, Block trapdoor, Block pressurePlate,
                      Block button, Block craftingTable, Block ladder, Block sign, Block hangingSign, Block shelf,
                      ItemEntry<Item> boat, ItemEntry<Item> chestBoat, Block chest) {
}
