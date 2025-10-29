package cc.cassian.pyrite.fabric.client;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.functions.fabric.FabricHelpers.*;


@Environment(EnvType.CLIENT)
public class PyriteFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block grassBlock : GRASS_BLOCKS) {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
                if (view == null) return 9551193;
                return BiomeColors.getAverageGrassColor(view, pos);
            }, grassBlock);
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 9551193, grassBlock);
        }
        for (Block transparentBlock : TRANSPARENT_BLOCKS) {
            BlockRenderLayerMap.INSTANCE.putBlock(transparentBlock, RenderType.cutout());
        }
        for (Block translucentBlock : TRANSLUCENT_BLOCKS) {
            BlockRenderLayerMap.INSTANCE.putBlock(translucentBlock, RenderType.translucent());
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