package cc.cassian.pyrite.fabric.client;

//? if fabric {

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.client.PyriteClient;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.world.level.block.Block;

import static cc.cassian.pyrite.entity.ModEntities.BOATS;
import static cc.cassian.pyrite.functions.ModHelpers.*;

public class PyriteFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block grassBlock : GRASS_BLOCKS) {
            ColorProviderRegistry.BLOCK.register(PyriteClient::registerColor, grassBlock);
            ColorProviderRegistry.ITEM.register(PyriteClient::registerColor, grassBlock);
        }
        for (Block transparentBlock : TRANSPARENT_BLOCKS) {
            BlockRenderLayerMap.INSTANCE.putBlock(transparentBlock, RenderType.cutout());
        }
        for (Block translucentBlock : TRANSLUCENT_BLOCKS) {
            BlockRenderLayerMap.INSTANCE.putBlock(translucentBlock, RenderType.translucent());
        }
        if (Pyrite.CONFIG.disabledContentTooltip) {
            ItemTooltipCallback.EVENT.register(((stack, tooltipContext, tooltipType, lines) -> PyriteClient.addTooltip(lines, stack)));
        }
        BOATS.forEach(boat -> {
            TerraformBoatClientHelper.registerModelLayers(boat.identifier(), false);
        });

    }
}
//?}