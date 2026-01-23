package cc.cassian.pyrite.neoforge.client;

//? if neoforge {

/*import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.client.PyriteClient;
import cc.cassian.pyrite.core.PyriteTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.functions.neoforge.NeoHelpers.GRASS_BLOCKS;

@EventBusSubscriber(modid = Pyrite.MOD_ID, value = Dist.CLIENT)
public class PyriteNeoForgeClient {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        for (Supplier<Block> pyriteBlock : GRASS_BLOCKS) {
            event.register((PyriteClient::registerColor), pyriteBlock.get());
        }
    }

    //? <1.21.4 {
    /^@SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        for (Supplier<Block> pyriteBlock : GRASS_BLOCKS) {
            event.register(PyriteClient::registerColor, pyriteBlock.get());
        }
    }
    ^///?}

    @SubscribeEvent
    public static void disabledContentTooltip(ItemTooltipEvent event) {
        PyriteClient.addTooltip(event.getToolTip(), event.getItemStack());
    }

}

*///?}