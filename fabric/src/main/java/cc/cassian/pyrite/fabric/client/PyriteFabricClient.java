package cc.cassian.pyrite.fabric.client;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.block.*;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static cc.cassian.pyrite.Pyrite.MOD_ID;
import static cc.cassian.pyrite.functions.fabric.FabricHelpers.*;


@Environment(EnvType.CLIENT)
public class PyriteFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block grassBlock : GRASS_BLOCKS) {
            ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
                if (view == null) return 9551193;
                return BiomeColors.getGrassColor(view, pos);
            }, grassBlock);
        }
        for (Block transparentBlock : TRANSPARENT_BLOCKS) {
            BlockRenderLayerMap.putBlock(transparentBlock, BlockRenderLayer.CUTOUT);
        }
        for (Block translucentBlock : TRANSLUCENT_BLOCKS) {
            BlockRenderLayerMap.putBlock(translucentBlock, BlockRenderLayer.TRANSLUCENT);
        }
        if (Pyrite.CONFIG.disabledContentTooltip) {
            ItemTooltipCallback.EVENT.register(((stack, tooltipContext, tooltipType, lines) -> {
                if (Registries.ITEM.getId(stack.getItem()).getNamespace().equals(MOD_ID) && !stack.isIn(PyriteTags.ENABLED)) {
                    lines.add(Text.literal("Disabled by current configuration").formatted(Formatting.RED));
                }
            }));
        }

    }
}