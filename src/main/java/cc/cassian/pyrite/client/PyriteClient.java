package cc.cassian.pyrite.client;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import java.util.Collections;
import java.util.List;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteClient {

	public static List<BlockTintSource> registerColor() {
		return Collections.singletonList(BlockTintSources.grassBlock());
	}


	public static void addTooltip(List<Component> lines, ItemStack stack) {
		if (Pyrite.CONFIG.disabledContentTooltip) {
			if (BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace().equals(MOD_ID) && !stack.is(PyriteItemTags.ENABLED)) {
				lines.add(Component.translatable("config.pyrite.disabled").withStyle(ChatFormatting.RED));
			}
		}
	}
}
