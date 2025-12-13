package cc.cassian.pyrite.neoforge;

//? neoforge {
/*import cc.cassian.pyrite.Platform;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class NeoForgePlatformImpl implements Platform {

    @Override
    public boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    @Override
    public String loader() {
        return "neoforge";
    }

    @Override
    public boolean isDevEnvironment() {
        //? if >1.21.4 {
        return !FMLEnvironment.isProduction();
        //?} else {
        /^return !FMLEnvironment.production;
        ^///?}
    }

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

}
*///?}