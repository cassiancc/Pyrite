package cc.cassian.pyrite.entries;

import net.minecraft.resources.Identifier;
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
}
