package cc.cassian.pyrite.fabric;

//? fabric {

/*import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.compat.ChestsCompat;
import cc.cassian.pyrite.compat.FarmersDelightCompat;
import cc.cassian.pyrite.compat.PyriteEIVPlugin;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.fabric.BlockCreatorImpl;
import cc.cassian.pyrite.functions.fabric.FabricHelpers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
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
        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
            if (FabricLoader.getInstance().isModLoaded("lolmcv"))
                ChestsCompat.registerToBlockEntity();
            if (FabricLoader.getInstance().isModLoaded("farmersdelight"))
                FarmersDelightCompat.registerToBlockEntity();
            if (FabricLoader.getInstance().isModLoaded("lolmcv"))
                ChestsCompat.registerToBlockEntity();
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
        CommonLifecycleEvents.TAGS_LOADED.register(((registryAccess, bl) -> {
            if (FabricLoader.getInstance().isModLoaded("eiv")) {
                PyriteEIVPlugin.hideStacks();
            }
        }));
    }

    public static void addAlias(String id) {
        BuiltInRegistries.BLOCK.addAlias(Pyrite.of(MOD_ID, id), Pyrite.of("minecraft", id));
        BuiltInRegistries.ITEM.addAlias(Pyrite.of(MOD_ID, id), Pyrite.of("minecraft", id));
    }
}

*///?}