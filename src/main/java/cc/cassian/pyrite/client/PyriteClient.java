package cc.cassian.pyrite.client;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteClient {
	public static int registerColor(BlockState state, BlockAndTintGetter view, BlockPos pos, int tintIndex) {
		if (view == null  || pos == null) return 9551193;
		return BiomeColors.getAverageGrassColor(view, pos);
	}

	public static int registerColor(ItemStack stack, int i) {
		return 9551193;
	}

	public static void addTooltip(List<Component> lines, ItemStack stack) {
		if (Pyrite.CONFIG.disabledContentTooltip) {
			if (BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace().equals(MOD_ID) && !stack.is(PyriteItemTags.ENABLED)) {
				lines.add(Component.translatable("config.pyrite.disabled").withStyle(ChatFormatting.RED));
			}
		}
	}
}
