package cc.cassian.pyrite.util.sets;

import cc.cassian.mru.util.ItemLikeEntry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public record WoodSet(String blockID, BlockSetType blockSetType, WoodType woodType,
					  ItemLikeEntry<Block> planks, ItemLikeEntry<Block> stairs, ItemLikeEntry<Block> slab,
					  ItemLikeEntry<Block> fence, ItemLikeEntry<Block> fenceGate,
					  ItemLikeEntry<Block> door, ItemLikeEntry<Block> trapdoor,
					  ItemLikeEntry<Block> pressurePlate, ItemLikeEntry<Block> button,
					  ItemLikeEntry<Block> craftingTable, ItemLikeEntry<Block> ladder,
					  ItemLikeEntry<Block> sign, ItemLikeEntry<Block> hangingSign,
					  ItemLikeEntry<Block> shelf,
                      ItemLikeEntry<Item> boat, ItemLikeEntry<Item> chestBoat,
					  ItemLikeEntry<Block> chest, ItemLikeEntry<Block> cabinet) {

	public ItemLikeEntry<Block> wallSign() {
		Identifier key = sign().id().withPath(p -> p.replace("sign", "wall_sign"));
		return new ItemLikeEntry<>(key, BuiltInRegistries.BLOCK.getValue(key));
	}

	public ItemLikeEntry<Block> hangingWallSign() {
		Identifier key = hangingSign().id().withPath(p -> p.replace("sign", "wall_sign"));
		return new ItemLikeEntry<>(key, BuiltInRegistries.BLOCK.getValue(key));
	}
}
