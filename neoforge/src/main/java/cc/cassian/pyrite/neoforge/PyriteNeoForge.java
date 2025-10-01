package cc.cassian.pyrite.neoforge;


import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl;
import cc.cassian.pyrite.neoforge.client.PyriteNeoForgeClient;
import cc.cassian.pyrite.registry.neoforge.PyriteItemGroupsImpl;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

import static cc.cassian.pyrite.Pyrite.MOD_ID;


@Mod(Pyrite.MOD_ID)
public final class PyriteNeoForge {
    public PyriteNeoForge(IEventBus eventBus, ModContainer container) {
        // Run our common setup.
        Pyrite.init();
        // Run our client setup.
        if (FMLEnvironment.getDist().isClient())
            PyriteNeoForgeClient.init(eventBus);
        // Run NeoForged specific setup.
        BlockCreatorImpl.register(eventBus);
        eventBus.addListener(BlockCreatorImpl::addSupportedBlocks);
        eventBus.addListener(BlockCreatorImpl::commonSetup);
        eventBus.addListener(PyriteItemGroupsImpl::buildContents);

        addAlias("copper_bars");
        addAlias("exposed_copper_bars");
        addAlias("weathered_copper_bars");
        addAlias("oxidized_copper_bars");
        addAlias("waxed_copper_bars");
        addAlias("waxed_exposed_copper_bars");
        addAlias("waxed_weathered_copper_bars");
        addAlias("waxed_oxidized_copper_bars");
    }

    public static void addAlias(String id) {
        Registries.BLOCK.addAlias(Identifier.of(MOD_ID, id), Identifier.ofVanilla(id));
    }
}
