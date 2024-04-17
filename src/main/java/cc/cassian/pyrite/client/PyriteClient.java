package cc.cassian.pyrite.client;

import cc.cassian.pyrite.Pyrite;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class PyriteClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Pyrite.FRAMED_GLASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Pyrite.FRAMED_GLASS_PANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Pyrite.TINTED_GLASS, RenderLayer.getTranslucent());

    }
}
