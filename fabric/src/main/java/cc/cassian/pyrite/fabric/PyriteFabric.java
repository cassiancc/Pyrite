package cc.cassian.pyrite.fabric;

import cc.cassian.pyrite.Pyrite;
import net.fabricmc.api.ModInitializer;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init();
    }
}