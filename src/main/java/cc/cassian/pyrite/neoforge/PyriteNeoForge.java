package cc.cassian.pyrite.neoforge;

//? if neoforge {

/*import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.compat.PyriteRRVPlugin;
import cc.cassian.pyrite.condition.PyriteResourceConditions;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.registry.BlockCreator;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Map;
import java.util.function.Supplier;

import static cc.cassian.pyrite.functions.ModHelpers.SUPPORTED_BLOCKS;
import static cc.cassian.pyrite.registry.PyriteItemGroups.POTTED_FLOWERS;


@Mod(Pyrite.MOD_ID)
@EventBusSubscriber(modid = Pyrite.MOD_ID)
public final class PyriteNeoForge {
    public PyriteNeoForge(IEventBus eventBus, ModContainer container) {
        // Run our common setup.
    }

    @SubscribeEvent
    private static void register(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.BLOCK)) {
            PyriteResourceConditions.register();
            Pyrite.init();
            BlockCreator.register();
        }
    }

    @SubscribeEvent
    private static void buildCreativeGroups(BuildCreativeModeTabContentsEvent event) {
        PyriteItemGroups.buildContents(event);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    private static void addSupportedBlocks(BlockEntityTypeAddBlocksEvent event) {
        SUPPORTED_BLOCKS.forEach((blockEntityTypeSupplier, block)-> {
            event.modify(blockEntityTypeSupplier.get(), block);
        });
    }

    // Adds Pyrite's flowers to the flower pot block.
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;
        for (Map.Entry<String, BlockEntry<FlowerPotBlock>> entry : POTTED_FLOWERS.entrySet()) {
            String flowerID = entry.getKey();
            Supplier<FlowerPotBlock> flowerPot = ()-> entry.getValue().get();
            pot.addPlant(Pyrite.of(flowerID), flowerPot);
        }
    }

    @SubscribeEvent
    private static void onUseWithItem(UseItemOnBlockEvent event) {
        InteractionResult actionResult = ModHelpers.updateTorchColour(event.getItemStack(), event.getPlayer(), event.getLevel(), event.getPos());
        if (actionResult.equals(InteractionResult.SUCCESS)) event.cancelWithResult(InteractionResult.SUCCESS);
    }

    @SubscribeEvent
    private static void addOddities(AddPackFindersEvent event) {
        ModLists.DATAPACKS.forEach((key, value) -> {
            if (value) {
                event.addPackFinders(Pyrite.of("resourcepacks/"+key), PackType.SERVER_DATA, Component.literal(key), PackSource.BUILT_IN, true, Pack.Position.TOP);
            }
        });
        if (Pyrite.CONFIG.crafting_tables)
            event.addPackFinders(Pyrite.of("resourcepacks/pyrite_crafting_tables"), PackType.CLIENT_RESOURCES, Component.literal("pyrite/pyrite_crafting_tables"), PackSource.BUILT_IN, true, Pack.Position.TOP);
    }

    @SubscribeEvent
    private static void hideStacks(TagsUpdatedEvent commonSetupEvent) {
        if (ModList.get().isLoaded("rrv")) {
            PyriteRRVPlugin.hideStacks();
        }
    }
}

*///?}