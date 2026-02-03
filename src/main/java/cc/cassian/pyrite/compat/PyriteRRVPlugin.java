package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.core.PyriteItemTags;
//? if >1.21.4 {
import cc.cassian.rrv.api.recipe.ItemView;
//?}

import net.minecraft.core.registries.BuiltInRegistries;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteRRVPlugin {
    public static void hideStacks() {
        //? if >1.21.4 {
        BuiltInRegistries.ITEM.entrySet().forEach(((itemEntry) -> {
            if (itemEntry.getKey().identifier().getNamespace().equals(MOD_ID) && !itemEntry.getValue().getDefaultInstance().is(PyriteItemTags.ENABLED))
                ItemView.excludeItem(itemEntry.getValue());
        }));
        //?}
    }
}
