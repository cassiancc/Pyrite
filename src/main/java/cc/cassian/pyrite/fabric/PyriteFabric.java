package cc.cassian.pyrite.fabric;

//? fabric {

import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.compat.*;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.functions.fabric.FabricHelpers;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init();
        BlockCreator.register();
        FabricHelpers.registerFuelBlocks();
        PyriteItemGroups.buildContents();

        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> ModHelpers.SUPPORTED_BLOCKS.forEach((be, block) -> Platform.INSTANCE.addSupportedBlock(be.get(), block)));

        UseBlockCallback.EVENT.register((ModHelpers::updateTorchColour));

        ModLists.DATAPACKS.forEach((key, value) -> {
            if (value) {
                ResourceManagerHelper.registerBuiltinResourcePack(
                        Pyrite.of(key),
                        FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
                        ResourcePackActivationType.DEFAULT_ENABLED);
            }
        });

        CommonLifecycleEvents.TAGS_LOADED.register(((registryAccess, bl) -> {
            if (Platform.INSTANCE.isModLoaded("rrv")) {
                PyriteRRVPlugin.hideStacks();
            }
        }));
    }
}

//?}