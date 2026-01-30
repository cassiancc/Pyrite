package cc.cassian.pyrite.neoforge.client;

//? if neoforge {

/*import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.client.PyriteClient;
import cc.cassian.pyrite.compat.ChestsCompat;
import cc.cassian.pyrite.core.PyriteTags;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.entity.ModEntities.BOATS;
import static cc.cassian.pyrite.functions.ModHelpers.GRASS_BLOCKS;
import static cc.cassian.pyrite.neoforge.NeoForgePlatformImpl.SUPPORTED_BLOCKS;

@Mod(value = MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
public class PyriteNeoForgeClient {

    public PyriteNeoForgeClient(IEventBus eventBus, ModContainer container) {
        BOATS.forEach(boat -> {
            TerraformBoatClientHelper.registerModelLayers(boat.location(), false);
        });
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        for (Block pyriteBlock : GRASS_BLOCKS) {
            event.register((PyriteClient::registerColor), pyriteBlock);
        }
    }

    //? <1.21.4 {
    /^@SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        for (Block pyriteBlock : GRASS_BLOCKS) {
            event.register(PyriteClient::registerColor, pyriteBlock);
        }
    }
    ^///?}

    @SubscribeEvent
    public static void disabledContentTooltip(ItemTooltipEvent event) {
        PyriteClient.addTooltip(event.getToolTip(), event.getItemStack());
    }

    @SubscribeEvent
    public static void disabledContentTooltip(EntityRenderersEvent.RegisterLayerDefinitions event) {
        BOATS.forEach(boat -> {
            TerraformBoatClientHelper.registerModelLayers(boat.location(), false);
            event.registerLayerDefinition(TerraformBoatClientHelper.getLayer(boat.location(), false, true), BoatModel::createBodyModel);
            event.registerLayerDefinition(TerraformBoatClientHelper.getLayer(boat.location(), false, false), BoatModel::createBodyModel);
        });
    }

}

*///?}