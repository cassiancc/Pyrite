package cc.cassian.pyrite.fabric;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.compat.ChestsCompat;
import cc.cassian.pyrite.registry.fabric.BlockCreatorImpl;
import cc.cassian.pyrite.functions.fabric.FabricHelpers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init();
        BlockCreatorImpl.register();
        FabricHelpers.registerFuelBlocks();
        if (FabricLoader.getInstance().isModLoaded("lolmcv"))
            ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> ChestsCompat.registerToBlockEntity());
    }
}