package cc.cassian.pyrite.client;

import cc.cassian.pyrite.Pyrite;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.ColorResolver;

import static cc.cassian.pyrite.Pyrite.pyriteBlocks;


@Environment(EnvType.CLIENT)
public class PyriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlocks.get(0), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlocks.get(1), RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), pyriteBlocks.get(10));
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 5730613, pyriteBlocks.get(10));

    }
}
