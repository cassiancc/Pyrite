package cc.cassian.pyrite.neoforge;


import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.config.ModConfig;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl;
import cc.cassian.pyrite.neoforge.client.PyriteNeoForgeClient;
import cc.cassian.pyrite.registry.neoforge.PyriteItemGroupsImpl;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemActionResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
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
        eventBus.addListener(PyriteItemGroupsImpl::buildContents);
        NeoForge.EVENT_BUS.addListener(PyriteNeoForge::onUseWithItem);
        eventBus.addListener(PyriteNeoForge::addOddities);

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

    private static void onUseWithItem(UseItemOnBlockEvent event) {
        ActionResult actionResult = ModHelpers.updateTorchColour(event.getItemStack(), event.getPlayer(), event.getLevel(), event.getPos());
        if (actionResult.equals(ActionResult.SUCCESS)) event.cancelWithResult(ItemActionResult.SUCCESS);
    }

    private static void addOddities(AddPackFindersEvent event) {
        ModLists.DATAPACKS.forEach((key, value) -> {
            if (value) {
                event.addPackFinders(ModHelpers.locate("resourcepacks/"+key), ResourceType.SERVER_DATA, Text.literal(key), ResourcePackSource.BUILTIN, true, ResourcePackProfile.InsertionPosition.TOP);
            }
        });
        if (Pyrite.CONFIG.crafting_tables)
            event.addPackFinders(ModHelpers.locate("resourcepacks/pyrite_crafting_tables"), ResourceType.CLIENT_RESOURCES, Text.literal("pyrite/pyrite_crafting_tables"), ResourcePackSource.BUILTIN, true, ResourcePackProfile.InsertionPosition.TOP);
    }
}
