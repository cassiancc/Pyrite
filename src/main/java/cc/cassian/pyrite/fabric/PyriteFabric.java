package cc.cassian.pyrite.fabric;

//? fabric {

import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.compat.*;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static net.fabricmc.fabric.api.resource.ResourcePackActivationType.DEFAULT_ENABLED;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init();
        BlockCreator.register();
        registerFuelBlocks();
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register(PyriteItemGroups::buildContents);

        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> ModHelpers.SUPPORTED_BLOCKS.forEach((be, block) -> {
            if (be != null && be.get() != null)
			    Platform.INSTANCE.addSupportedBlock(be.get(), block);
		}));

        UseBlockCallback.EVENT.register((ModHelpers::updateTorchColour));

        ModLists.DATAPACKS.forEach((key, value) -> {
            if (value) {
                ResourceManagerHelper.registerBuiltinResourcePack(
                        Pyrite.of(key),
                        FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
                        DEFAULT_ENABLED);
            }
        });
    }

    public static final HashMap<Block, Integer> FUEL_BLOCKS = new HashMap<>();

    public static void registerFuelBlocks() {
		FUEL_BLOCKS.forEach(FuelRegistry.INSTANCE::add);
    }
}

//?}