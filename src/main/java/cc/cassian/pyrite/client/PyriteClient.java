package cc.cassian.pyrite.client;

import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.util.ModHelpers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import java.util.Collections;
import java.util.List;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteClient {

	public static List<BlockTintSource> registerColor() {
		return Collections.singletonList(BlockTintSources.grassBlock());
	}

	public static void addTooltip(List<Component> lines, ItemStack stack) {
		if (Pyrite.CONFIG.disabledContentTooltip && BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace().equals(MOD_ID)) {
			boolean enabled = ModHelpers.enabled(stack);
			MutableComponent e = null;
			if (!enabled) {
				e = Component.translatable("config.pyrite.disabled").withStyle(ChatFormatting.RED);
			}
			if (Platform.INSTANCE.isDevEnvironment()) {
				if (enabled)
					e = Component.literal("Enabled by current configuration").withStyle(ChatFormatting.GREEN);
				e.append(", requires all of: ");
				boolean first = true;
				for (String requiredOption : ModHelpers.getRequiredOptions(BuiltInRegistries.ITEM.getKey(stack.getItem()))) {
					if (!first) {
						e.append(", ");
					}
					e.append(requiredOption);
					first = false;
				}
			}
			if (e != null)
				lines.add(e);
		}
	}
}
