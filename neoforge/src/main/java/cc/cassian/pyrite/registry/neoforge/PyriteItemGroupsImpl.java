package cc.cassian.pyrite.registry.neoforge;

import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.minecraft.item.ItemGroups;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class PyriteItemGroupsImpl {
	@SubscribeEvent
	public static void buildContents(BuildCreativeModeTabContentsEvent event) {
		if (event.getTabKey() == ItemGroups.REDSTONE) {
			event.addAll(PyriteItemGroups.getBlockCollectionList(BlockCreatorImpl.REDSTONE_BLOCKS));
		}
	}
}
