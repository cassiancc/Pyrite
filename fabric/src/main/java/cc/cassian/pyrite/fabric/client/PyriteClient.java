package cc.cassian.pyrite.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;

import static cc.cassian.pyrite.functions.fabric.FabricCommonHelpers.grassBlocks;
import static cc.cassian.pyrite.functions.fabric.FabricCommonHelpers.transparentBlocks;


@Environment(EnvType.CLIENT)
public class PyriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block grassBlock : grassBlocks) {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), grassBlock);
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 9551193, grassBlock);
        }
        for (Block transparentBlock : transparentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(transparentBlock, RenderLayer.getCutout());
        }}
}