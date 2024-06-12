package cc.cassian.pyrite.neoforge;


import cc.cassian.pyrite.Pyrite;
import net.minecraft.SharedConstants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;



@Mod(Pyrite.modID)
public final class PyriteNeoForge {
    public PyriteNeoForge() {
        // Run our common setup.
        Pyrite.init(SharedConstants.WORLD_VERSION + "-neoforge");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(PyriteClient::registerBlockColors);
        modEventBus.addListener(PyriteClient::registerItemColorHandlers);


    }
}
