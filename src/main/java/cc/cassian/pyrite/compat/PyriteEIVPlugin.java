package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.core.PyriteTags;
import de.crafty.eiv.common.api.recipe.ItemView;
import net.minecraft.core.registries.BuiltInRegistries;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteEIVPlugin {
    public static void hideStacks() {
        BuiltInRegistries.ITEM.entrySet().forEach(((itemEntry) -> {
            if (itemEntry.getKey().location().getNamespace().equals(MOD_ID) && !itemEntry.getValue().getDefaultInstance().is(PyriteTags.ENABLED))
                ItemView.excludeItem(itemEntry.getValue());
        }));
    }
}
