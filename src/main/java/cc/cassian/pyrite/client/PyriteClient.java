package cc.cassian.pyrite.client;

import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class PyriteClient {
	public static int registerColor(BlockState state, BlockAndTintGetter view, BlockPos pos, int tintIndex) {
		if (view == null  || pos == null) return 9551193;
		return BiomeColors.getAverageGrassColor(view, pos);
	}

	public static int registerColor(ItemStack stack, int i) {
		return 9551193;
	}
}
