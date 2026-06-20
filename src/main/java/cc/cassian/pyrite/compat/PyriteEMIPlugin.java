package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.util.ModHelpers;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

@EmiEntrypoint
public class PyriteEMIPlugin implements EmiPlugin {
    public static void hideStacks(EmiRegistry emiRegistry) {
        BuiltInRegistries.ITEM.entrySet().forEach(((itemEntry) -> {
            Identifier itemId = itemEntry.getKey().identifier();
            if (itemId.getNamespace().equals(Pyrite.MOD_ID) && !ModHelpers.enabled(itemId))
                emiRegistry.removeEmiStacks(EmiStack.of(itemEntry.getValue()));
        }));
    }

    @Override
    public void register(EmiRegistry emiRegistry) {
        hideStacks(emiRegistry);
    }
}
