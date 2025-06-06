package cc.cassian.pyrite.functions.neoforge;

import net.minecraft.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.Tags;

@SuppressWarnings("unused")
public class ModHelpersImpl {
    public static boolean isModLoaded(String modID) {
        return ModList.get().isLoaded(modID);
    }

    public static boolean isShield(ItemStack stack) {
        return stack.isIn(Tags.Items.TOOLS_SHIELD);
    }

    public static boolean isDevEnvironment() {
        return !FMLEnvironment.production;
    }
}
