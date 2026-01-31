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
//? if >26  {
/*import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
*///?} else {
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
//?}
//? >1.21.2 {
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import static net.fabricmc.fabric.api.resource.v1.pack.PackActivationType.DEFAULT_ENABLED;
//?} else {
/*import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import static net.fabricmc.fabric.api.resource.ResourcePackActivationType.DEFAULT_ENABLED;
*///?}

import net.fabricmc.loader.api.FabricLoader;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init();
        BlockCreator.register();
        FabricHelpers.registerFuelBlocks();
        //? <26.1 {
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register(PyriteItemGroups::buildContents);
        //?} else {
        /*CreativeModeTabEvents.MODIFY_OUTPUT_ALL.register(PyriteItemGroups::buildContents);
        *///?}

        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> ModHelpers.SUPPORTED_BLOCKS.forEach((be, block) -> {
            if (be != null && be.get() != null)
			    Platform.INSTANCE.addSupportedBlock(be.get(), block);
		}));

        UseBlockCallback.EVENT.register((ModHelpers::updateTorchColour));

        ModLists.DATAPACKS.forEach((key, value) -> {
            if (value) {
                //? >1.21.2 {
                ResourceLoader.registerBuiltinPack(
                //?} else {
                /*ResourceManagerHelper.registerBuiltinResourcePack(
                *///?}
                        Pyrite.of(key),
                        FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
                        DEFAULT_ENABLED);
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