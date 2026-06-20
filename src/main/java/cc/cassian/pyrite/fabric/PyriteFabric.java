package cc.cassian.pyrite.fabric;

//? fabric {

import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.compat.*;
import cc.cassian.pyrite.condition.PyriteResourceConditions;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.util.ModHelpers;
import cc.cassian.pyrite.util.ModLists;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;

import static net.fabricmc.fabric.api.resource.ResourcePackActivationType.DEFAULT_ENABLED;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        PyriteResourceConditions.register();
        Pyrite.init();
        BlockCreator.register();
        registerFuelBlocks();
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register(PyriteItemGroups::buildContents);

        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> ModHelpers.SUPPORTED_BLOCKS.forEach((be, block) -> {
            if (be != null && be.get() != null)
			    be.get().addSupportedBlock(block);
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

        DefaultItemComponentEvents.MODIFY.register(m->{
            m.modify(Collections.singleton(Items.CRAFTING_TABLE), ((builder, item) -> {
                if (Pyrite.CONFIG.crafting_tables)
                    builder.set(DataComponents.ITEM_NAME, Component.translatable("block.pyrite.oak_crafting_table"));
            }));
        });
    }

    public static final HashMap<Block, Integer> FUEL_BLOCKS = new HashMap<>();

    public static void registerFuelBlocks() {
        for (Map.Entry<Block, Integer> fuelBlock : FUEL_BLOCKS.entrySet()) {
            FuelRegistry.INSTANCE.add(fuelBlock.getKey(), fuelBlock.getValue());
        }
    }
}

//?}