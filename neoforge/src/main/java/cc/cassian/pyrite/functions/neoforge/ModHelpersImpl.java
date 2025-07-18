package cc.cassian.pyrite.functions.neoforge;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;

@SuppressWarnings("unused")
public class ModHelpersImpl {
    public static boolean isModLoaded(String modID) {
        return ModList.get().isLoaded(modID);
    }

    public static boolean isDevEnvironment() {
        return !FMLEnvironment.production;
    }
}
