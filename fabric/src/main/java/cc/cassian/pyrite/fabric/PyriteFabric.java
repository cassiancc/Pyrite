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
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init();
        BlockCreatorImpl.register();
        FabricHelpers.registerFuelBlocks();
        if (FabricLoader.getInstance().isModLoaded("lolmcv"))
            ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> ChestsCompat.registerToBlockEntity());
        addAlias("copper_bars");
        addAlias("exposed_copper_bars");
        addAlias("weathered_copper_bars");
        addAlias("oxidized_copper_bars");
        addAlias("waxed_copper_bars");
        addAlias("waxed_exposed_copper_bars");
        addAlias("waxed_weathered_copper_bars");
        addAlias("waxed_oxidized_copper_bars");
    }

    public static void addAlias(String id) {
        Registries.BLOCK.addAlias(Identifier.of(MOD_ID, id), Identifier.ofVanilla(id));
    }
}