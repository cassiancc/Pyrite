package cc.cassian.pyrite.item;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
//~ if >26.2 'SignItem'->'StandingAndWallBlockItem'
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallSignBlock;

//~ if >26.2 ' SignItem'->' StandingAndWallBlockItem'
public class ModSignItem extends StandingAndWallBlockItem {
	//? if >26.2 {
	public ModSignItem(Block newBlock, WallSignBlock wallSign, Properties properties) {
		super(newBlock, wallSign, Direction.DOWN, properties);
	}
	//?} else {
	/*public ModSignItem(Block newBlock, WallSignBlock wallSign, Properties properties) {
		super(newBlock, wallSign, properties);
	}
	*///?}
}
