package cc.cassian.pyrite.neoforge;


import cc.cassian.pyrite.Pyrite;
import net.minecraft.SharedConstants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;



@Mod(Pyrite.modID)
public final class PyriteNeoForge {
    public PyriteNeoForge(IEventBus eventBus, ModContainer container) {
        // Run our common setup.
        Pyrite.init(SharedConstants.WORLD_VERSION + "-neoforge");
        eventBus.addListener(PyriteClient::registerBlockColors);
        eventBus.addListener(PyriteClient::registerItemColorHandlers);


    }
}
