package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteTags;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;

import java.util.ArrayList;
import java.util.List;

@EmiEntrypoint
public class EmiCompat implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        if (Pyrite.CONFIG.removeDisabledContentFromEMI) {
            if (!Pyrite.CONFIG.oddities)
                removeEmiStacks(registry, PyriteTags.ODDITIES);
        }

    }

    private static void removeEmiStacks(EmiRegistry registry, TagKey<Item> itemTag) {
        List<Item> items = new ArrayList<>();
        Registries.ITEM.getEntryList(itemTag).ifPresent((holders) -> holders.forEach((holder) -> items.add(holder.value())));
        items.forEach((item) -> registry.removeEmiStacks(EmiStack.of(item)));
    }
}
