package cc.cassian.pyrite.fabric.client;

//? if fabric {

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.client.PyriteClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
//? if >1.21.4 {
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
//?} else {
/*import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
*///?}
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Block;

import static cc.cassian.pyrite.entity.ModEntities.BOATS;

import static cc.cassian.pyrite.functions.ModHelpers.*;

public class PyriteFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block grassBlock : GRASS_BLOCKS) {
            //? if >26 {
            /*net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry.register(PyriteClient::registerColor, grassBlock);
            *///?} else {
            net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry.BLOCK.register(PyriteClient::registerColor, grassBlock);
            //?}
            //? <1.21.4
            //net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry.ITEM.register(PyriteClient::registerColor, grassBlock);
        }
        for (Block transparentBlock : TRANSPARENT_BLOCKS) {
            //? if >26 {
            /*net.fabricmc.fabric.api.client.rendering.v1.ChunkSectionLayerMap.putBlock(transparentBlock, ChunkSectionLayer.CUTOUT);
            *///?} else if >1.21.4 {
            net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap.putBlock(transparentBlock, ChunkSectionLayer.CUTOUT);
            //?} else {
            /*BlockRenderLayerMap.INSTANCE.putBlock(transparentBlock, RenderType.cutout());
            *///?}
        }
        for (Block translucentBlock : TRANSLUCENT_BLOCKS) {
            //? if >26 {
            /*net.fabricmc.fabric.api.client.rendering.v1.ChunkSectionLayerMap.putBlock(translucentBlock, ChunkSectionLayer.TRANSLUCENT);
            *///?} else if >1.21.4 {
            net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap.putBlock(translucentBlock, ChunkSectionLayer.TRANSLUCENT);
            //?} else {
            /*BlockRenderLayerMap.INSTANCE.putBlock(translucentBlock, RenderType.translucent());
            *///?}
        }
        if (Pyrite.CONFIG.disabledContentTooltip) {
            ItemTooltipCallback.EVENT.register(((stack, tooltipContext, tooltipType, lines) -> PyriteClient.addTooltip(lines, stack)));
        }

        //? if >1.21.2 {
        BOATS.forEach((id, entityType) -> {
            var layer = new ModelLayerLocation(Pyrite.of("boat/" + id), "main");
            EntityModelLayerRegistry.registerModelLayer(layer, BoatModel::createBoatModel);
            EntityRenderers.register(entityType, (context) -> new BoatRenderer(context, layer));
        });
        //?} else {
        /*BOATS.forEach(boat -> {
            TerraformBoatClientHelper.registerModelLayers(boat.location(), false);
        });
        *///?}

    }
}
//?}