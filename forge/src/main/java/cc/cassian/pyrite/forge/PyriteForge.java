package cc.cassian.pyrite.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.SharedConstants;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import cc.cassian.pyrite.Pyrite;

@Mod(Pyrite.modID)
public final class PyriteForge {
    public PyriteForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(Pyrite.modID, FMLJavaModLoadingContext.get().getModEventBus());
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Run our common setup.
        Pyrite.init(SharedConstants.WORLD_VERSION + "-forge");
        modEventBus.addListener(PyriteClient::registerBlockColors);



    }
}

