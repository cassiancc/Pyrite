package cc.cassian.pyrite.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import cc.cassian.pyrite.Pyrite;

import static cc.cassian.pyrite.functions.ModHelpers.*;

@Mod(Pyrite.modID)
public final class PyriteForge {
    public PyriteForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(Pyrite.modID, FMLJavaModLoadingContext.get().getModEventBus());
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Run our common setup.
        Pyrite.init();
        modEventBus.addListener(PyriteClient::registerBlockColors);



    }
}

