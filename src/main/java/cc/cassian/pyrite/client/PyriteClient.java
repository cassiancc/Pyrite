package cc.cassian.pyrite.client;

import cc.cassian.pyrite.ModGlass;
import cc.cassian.pyrite.Pyrite;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;

import static cc.cassian.pyrite.Pyrite.pyriteBlocks;
import static cc.cassian.pyrite.Pyrite.transparentBlocks;


@Environment(EnvType.CLIENT)
public class PyriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), pyriteBlocks.get(12));
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 7183658, pyriteBlocks.get(12));
        for (Block pyriteBlock : transparentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlock, RenderLayer.getCutout());
        }}
}
