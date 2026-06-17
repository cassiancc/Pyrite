package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.util.ModHelpers;
import cc.cassian.rrv.api.recipe.ItemView;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class PyriteRRVPlugin {
    public static void hideStacks() {
        BuiltInRegistries.ITEM.entrySet().forEach(((itemEntry) -> {
            Identifier itemId = itemEntry.getKey().identifier();
            if (itemId.getNamespace().equals(Pyrite.MOD_ID) && !ModHelpers.enabled(itemId))
                ItemView.excludeItem(itemEntry.getValue());
        }));
    }
}
