package cc.cassian.pyrite.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import static cc.cassian.pyrite.functions.ModHelpers.*;


@Environment(EnvType.CLIENT)
public class PyriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block pyriteBlock : grassBlocks) {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), pyriteBlock);
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 7183658, pyriteBlock);
        }
        for (Block pyriteBlock : transparentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlock, RenderLayer.getCutout());
        }}
}