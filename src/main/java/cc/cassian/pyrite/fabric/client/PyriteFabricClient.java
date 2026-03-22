package cc.cassian.pyrite.fabric.client;

//? if fabric {

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.client.PyriteClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Block;
import static cc.cassian.pyrite.entity.ModEntities.BOATS;
import static cc.cassian.pyrite.entity.ModEntities.CHEST_BOATS;
import static cc.cassian.pyrite.functions.ModHelpers.*;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import static net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry.registerModelLayer;


public class PyriteFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block grassBlock : GRASS_BLOCKS) {
            BlockColorRegistry.register(PyriteClient.registerColor(), grassBlock);
        }

        if (Pyrite.CONFIG.disabledContentTooltip) {
            ItemTooltipCallback.EVENT.register(((stack, tooltipContext, tooltipType, lines) -> PyriteClient.addTooltip(lines, stack)));
        }

        BOATS.forEach((id, entityType) -> {
            var layer = new ModelLayerLocation(Pyrite.of("boat/" + id), "main");
            registerModelLayer(layer, BoatModel::createBoatModel);
            EntityRenderers.register(entityType, (context) -> new BoatRenderer(context, layer));
        });
        CHEST_BOATS.forEach((id, entityType) -> {
            var layer = new ModelLayerLocation(Pyrite.of("chest_boat/" + id), "main");
            registerModelLayer(layer, BoatModel::createChestBoatModel);
            EntityRenderers.register(entityType, (context) -> new BoatRenderer(context, layer));
        });

    }
}
//?}