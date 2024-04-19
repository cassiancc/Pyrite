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
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.ColorResolver;

import static cc.cassian.pyrite.Pyrite.pyriteBlocks;


@Environment(EnvType.CLIENT)
public class PyriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlocks.get(1), RenderLayer.getCutout());
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), pyriteBlocks.get(10));
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 7183658, pyriteBlocks.get(10));

        for (Block pyriteBlock : pyriteBlocks) {
            if (pyriteBlock instanceof DoorBlock) {
                BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlock, RenderLayer.getCutout());
            }
            else if (pyriteBlock instanceof TrapdoorBlock) {
                BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlock, RenderLayer.getCutout());
            }
            else if (pyriteBlock instanceof GlassBlock) {
                BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlock, RenderLayer.getCutout());
            }

        }}
}
