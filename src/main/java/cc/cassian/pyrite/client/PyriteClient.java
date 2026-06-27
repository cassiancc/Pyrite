package cc.cassian.pyrite.client;

import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.util.ModHelpers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collections;
import java.util.List;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteClient {

	public static List<BlockTintSource> registerColor() {
		return Collections.singletonList(new BlockTintSource() {
			public int color(final BlockState state) {
				return GrassColor.getDefaultColor();
			}

			public int colorInWorld(final BlockState state, final BlockAndTintGetter level, final BlockPos pos) {
				return BiomeColors.getAverageGrassColor(level, pos);
			}

			public int colorAsTerrainParticle(final BlockState state, final BlockAndTintGetter level, final BlockPos pos) {
				return GrassColor.getDefaultColor();
			}
		});
	}

	public static void addTooltip(List<Component> lines, ItemStack stack) {
		Identifier id = BuiltInRegistries.ITEM.getKey(stack.getItem());
		if ((Pyrite.CONFIG.disabledContentTooltip || Pyrite.CONFIG.enabledContentTooltip) && id.getNamespace().equals(MOD_ID)) {
			boolean enabled = ModHelpers.enabled(id);
			if (enabled && Pyrite.CONFIG.enabledContentTooltip) {
				lines.add(Component.translatable("config.pyrite.enabled").withStyle(ChatFormatting.GREEN));
				addRequiredOptions(lines, id);
			}
			if (!enabled && Pyrite.CONFIG.disabledContentTooltip) {
				lines.add(Component.translatable("config.pyrite.disabled").withStyle(ChatFormatting.RED));
				addRequiredOptions(lines, id);
			}
		}
	}

	private static void addRequiredOptions(List<Component> lines, Identifier id) {
		for (String requiredOption : ModHelpers.getRequiredOptions(id)) {
			var color = ModHelpers.enabled(List.of(requiredOption)) ? ChatFormatting.GREEN : ChatFormatting.RED;
			lines.add(Component.literal("  - " + requiredOption).withStyle(color));
		}
	}
}
