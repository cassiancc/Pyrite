package cc.cassian.pyrite.neoforge;

//? if neoforge {

/*import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.compat.PyriteEIVPlugin;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl;
import cc.cassian.pyrite.neoforge.client.PyriteNeoForgeClient;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

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
        eventBus.addListener(PyriteItemGroups::buildContents);
        NeoForge.EVENT_BUS.addListener(PyriteNeoForge::onUseWithItem);
        eventBus.addListener(PyriteNeoForge::addOddities);
        NeoForge.EVENT_BUS.addListener(PyriteNeoForge::hideStacks);
    }

    private static void onUseWithItem(UseItemOnBlockEvent event) {
        InteractionResult actionResult = ModHelpers.updateTorchColour(event.getItemStack(), event.getPlayer(), event.getLevel(), event.getPos());
        if (actionResult.equals(InteractionResult.SUCCESS)) event.cancelWithResult(InteractionResult.SUCCESS);
    }

    private static void addOddities(AddPackFindersEvent event) {
        ModLists.DATAPACKS.forEach((key, value) -> {
            if (value) {
                event.addPackFinders(Pyrite.of("resourcepacks/"+key), PackType.SERVER_DATA, Component.literal(key), PackSource.BUILT_IN, true, Pack.Position.TOP);
            }
        });
        if (Pyrite.CONFIG.crafting_tables)
            event.addPackFinders(Pyrite.of("resourcepacks/pyrite_crafting_tables"), PackType.CLIENT_RESOURCES, Component.literal("pyrite/pyrite_crafting_tables"), PackSource.BUILT_IN, true, Pack.Position.TOP);
    }

    private static void hideStacks(TagsUpdatedEvent commonSetupEvent) {
        if (ModList.get().isLoaded("eiv")) {
            PyriteEIVPlugin.hideStacks();
        }
    }
}

*///?}