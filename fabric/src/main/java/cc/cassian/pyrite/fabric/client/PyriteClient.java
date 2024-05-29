package cc.cassian.pyrite.fabric.client;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import static cc.cassian.pyrite.functions.ModHelpers.*;


@Environment(EnvType.CLIENT)
public class PyriteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        for (RegistrySupplier<Block> pyriteBlock : transparentBlocks) {
            BlockRenderLayerMap.INSTANCE.putBlock(pyriteBlock.get(), RenderLayer.getCutout());
        }}
}