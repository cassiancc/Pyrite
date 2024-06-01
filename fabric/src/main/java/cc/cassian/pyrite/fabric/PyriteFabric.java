package cc.cassian.pyrite.fabric;

import cc.cassian.pyrite.Pyrite;
import net.fabricmc.api.ModInitializer;
import net.minecraft.SharedConstants;

import static cc.cassian.pyrite.fabric.FabricHelpers.registerFuelBlocks;

public class PyriteFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Pyrite.init(SharedConstants.WORLD_VERSION + "-fabric");
        registerFuelBlocks();
    }
}