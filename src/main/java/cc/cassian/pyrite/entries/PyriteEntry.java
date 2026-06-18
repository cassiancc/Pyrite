package cc.cassian.pyrite.entries;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public interface PyriteEntry extends ItemLike {
    Identifier getId();

    Item asItem();

    default String getPath() {
        return getId().getPath();
    }

    default boolean isVanilla() {
        return getId().getNamespace().equals("minecraft");
    }

	default ResourceKey<Item> itemKey() {
		return ResourceKey.create(Registries.ITEM, this.getId());
	}
}
