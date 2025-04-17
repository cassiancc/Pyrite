package cc.cassian.pyrite.neoforge;


import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl;
import cc.cassian.pyrite.neoforge.client.PyriteNeoForgeClient;
import cc.cassian.pyrite.registry.neoforge.PyriteItemGroupsImpl;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;


@Mod(Pyrite.MOD_ID)
public final class PyriteNeoForge {
    public PyriteNeoForge(IEventBus eventBus, ModContainer container) {
        // Run our common setup.
        Pyrite.init();
        // Run our client setup.
        if (FMLEnvironment.dist.equals(Dist.CLIENT))
            PyriteNeoForgeClient.init(eventBus);
        // Run NeoForged specific setup.
        BlockCreatorImpl.register(eventBus);
        eventBus.addListener(BlockCreatorImpl::addSupportedBlocks);
        eventBus.addListener(BlockCreatorImpl::commonSetup);
        eventBus.addListener(PyriteItemGroupsImpl::buildContents);

    }
}
