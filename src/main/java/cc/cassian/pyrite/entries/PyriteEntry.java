package cc.cassian.pyrite.entries;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public interface PyriteEntry {
    Identifier getId();

    Item asItem();

    default String getPath() {
        return getId().getPath();
    }
}
