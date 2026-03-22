package cc.cassian.pyrite.neoforge.client;

//? if neoforge {

/*import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.client.PyriteClient;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.entity.ModEntities.BOATS;
import static cc.cassian.pyrite.functions.ModHelpers.GRASS_BLOCKS;

@Mod(value = MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
public class PyriteNeoForgeClient {

    public PyriteNeoForgeClient(IEventBus eventBus, ModContainer container) {
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        for (Block pyriteBlock : GRASS_BLOCKS) {
            event.register((PyriteClient.registerColor()), pyriteBlock);
        }
    }

    @SubscribeEvent
    public static void disabledContentTooltip(ItemTooltipEvent event) {
        PyriteClient.addTooltip(event.getToolTip(), event.getItemStack());
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        BOATS.forEach((id, entityType) -> {
            var layer = new ModelLayerLocation(Pyrite.of("boat/" + id), "main");
            event.registerLayerDefinition(layer, BoatModel::createBoatModel);
            EntityRenderers.register(entityType, (context) -> new BoatRenderer(context, layer));
        });
    }

}

*///?}