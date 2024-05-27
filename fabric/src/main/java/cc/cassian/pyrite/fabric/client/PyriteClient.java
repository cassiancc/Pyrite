package cc.cassian.pyrite.fabric.client;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.block.Block;

import static cc.cassian.pyrite.functions.ModHelpers.grassBlocks;
import static cc.cassian.pyrite.functions.ModHelpers.transparentBlocks;


@Environment(EnvType.CLIENT)
public class PyriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block pyriteBlock : grassBlocks) {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getAverageGrassColor(view, pos), pyriteBlock);
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 7183658, pyriteBlock);
        }
        for (Block pyriteBlock : transparentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlock, RenderType.cutout());
        }}
}
