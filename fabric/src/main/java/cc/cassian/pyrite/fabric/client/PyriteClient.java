package cc.cassian.pyrite.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;


@Environment(EnvType.CLIENT)
public class PyriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Object grassBlock : grassBlocks) {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), (Block) grassBlock);
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 9551193, (Block) grassBlock);
        }
        for (Object transparentBlock : transparentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock((Block) transparentBlock, RenderLayer.getCutout());
        }}
}