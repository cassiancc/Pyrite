package com.congodsted.pyrite.client;

import com.congodsted.pyrite.Pyrite;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class PyriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Pyrite.FRAMED_GLASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Pyrite.FRAMED_GLASS_PANE, RenderLayer.getCutout());
    }
}
