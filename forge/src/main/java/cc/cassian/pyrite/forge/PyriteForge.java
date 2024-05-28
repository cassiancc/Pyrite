package cc.cassian.pyrite.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import cc.cassian.pyrite.Pyrite;

import static cc.cassian.pyrite.functions.ModHelpers.*;

@Mod(Pyrite.modID)
public final class PyriteForge {
    public PyriteForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(Pyrite.modID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        Pyrite.init();



    }
}

