package cc.cassian.pyrite.fabric;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.compat.ChestsCompat;
import cc.cassian.pyrite.compat.FarmersDelightCompat;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.fabric.BlockCreatorImpl;
import cc.cassian.pyrite.functions.fabric.FabricHelpers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init();
        BlockCreatorImpl.register();
        FabricHelpers.registerFuelBlocks();
        if (FabricLoader.getInstance().isModLoaded("lolmcv"))
            ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> ChestsCompat.registerToBlockEntity());
        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
            if (FabricLoader.getInstance().isModLoaded("lolmcv"))
                ChestsCompat.registerToBlockEntity();
            if (FabricLoader.getInstance().isModLoaded("farmersdelight"))
                FarmersDelightCompat.registerToBlockEntity();
        });
        UseBlockCallback.EVENT.register((ModHelpers::updateTorchColour));
        ModLists.DATAPACKS.forEach((key, value) -> {
            if (value) {
                ResourceManagerHelper.registerBuiltinResourcePack(
                        ModHelpers.locate(key),
                        FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
                        ResourcePackActivationType.DEFAULT_ENABLED);
            }
        });
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
        BuiltInRegistries.BLOCK.addAlias(Pyrite.of(MOD_ID, id), Pyrite.of("minecraft", id));
        BuiltInRegistries.ITEM.addAlias(Pyrite.of(MOD_ID, id), Pyrite.of("minecraft", id));
    }
}