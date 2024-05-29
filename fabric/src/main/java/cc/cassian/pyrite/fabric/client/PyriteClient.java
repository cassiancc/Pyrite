package cc.cassian.pyrite.fabric.client;

import dev.architectury.registry.registries.RegistrySupplier;
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
        for (RegistrySupplier<Block> grassBlock : grassBlocks) {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> BiomeColors.getGrassColor(view, pos), grassBlock.get());
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 9551193, grassBlock.get());
        }
        for (RegistrySupplier<Block> transparentBlock : transparentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(transparentBlock.get(), RenderLayer.getCutout());
        }}
}