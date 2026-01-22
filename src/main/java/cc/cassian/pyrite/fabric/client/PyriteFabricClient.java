package cc.cassian.pyrite.fabric.client;

//? if fabric {

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.client.PyriteClient;
import cc.cassian.pyrite.core.PyriteTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
//? if >26 {
/*import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
*///?} else {
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
//?}
//? if >1.21.4 {
import net.fabricmc.fabric.api.client.rendering.v1.ChunkSectionLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
//?} else {
/*import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
*///?}
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.functions.fabric.FabricHelpers.*;

public class PyriteFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block grassBlock : GRASS_BLOCKS) {
            //? if >26 {
            /*BlockColorRegistry.register(PyriteClient::registerColor, grassBlock);
            *///?} else {
            ColorProviderRegistry.BLOCK.register(PyriteClient::registerColor, grassBlock);
            //?}
        }
        for (Block transparentBlock : TRANSPARENT_BLOCKS) {
            //? if >26 {
            /*ChunkSectionLayerMap.putBlock(transparentBlock, ChunkSectionLayer.CUTOUT);
            *///?} else if >1.21.4 {
            BlockRenderLayerMap.putBlock(transparentBlock, ChunkSectionLayer.CUTOUT);
            //?} else {
            /*BlockRenderLayerMap.INSTANCE.putBlock(transparentBlock, RenderType.cutout());
            *///?}
        }
        for (Block translucentBlock : TRANSLUCENT_BLOCKS) {
            //? if >26 {
            /*ChunkSectionLayerMap.putBlock(translucentBlock, ChunkSectionLayer.TRANSLUCENT);
            *///?} else if >1.21.4 {
            BlockRenderLayerMap.putBlock(translucentBlock, ChunkSectionLayer.TRANSLUCENT);
            //?} else {
            /*BlockRenderLayerMap.INSTANCE.putBlock(translucentBlock, RenderType.translucent());
            *///?}
        }
        if (Pyrite.CONFIG.disabledContentTooltip) {
            ItemTooltipCallback.EVENT.register(((stack, tooltipContext, tooltipType, lines) -> {
                if (BuiltInRegistries.ITEM.getKey(stack.getItem()).getNamespace().equals(MOD_ID) && !stack.is(PyriteTags.ENABLED)) {
                    lines.add(Component.literal("Disabled by current configuration").withStyle(ChatFormatting.RED));
                }
            }));
        }

    }
}
//?}