package cc.cassian.pyrite.fabric.client;

//? if fabric {

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.client.PyriteClient;
import cc.cassian.pyrite.client.renderer.PyriteBoatRenderer;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;

import static cc.cassian.pyrite.entity.ModEntities.BOATS;
import static cc.cassian.pyrite.entity.ModEntities.CHEST_BOATS;
import static cc.cassian.pyrite.util.ModHelpers.*;
import static net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry.registerModelLayer;


public class PyriteFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (Block grassBlock : GRASS_BLOCKS) {
            ColorProviderRegistry.BLOCK.register(PyriteClient::registerColor, grassBlock);
            ColorProviderRegistry.ITEM.register(PyriteClient::registerColor, grassBlock);
        }

        if (Pyrite.CONFIG.disabledContentTooltip) {
            ItemTooltipCallback.EVENT.register(((stack, tooltipContext, tooltipType, lines) -> PyriteClient.addTooltip(lines, stack)));
        }
        Pyrite.CONFIG.registerCallback((config)->{
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null) {
                player.sendSystemMessage(Component.translatable("config.pyrite.changed").withStyle(ChatFormatting.GREEN));
            }
        });

        BOATS.forEach((id, entityType) -> {
            var layer = new ModelLayerLocation(Pyrite.of("boat/" + id), "main");
            registerModelLayer(layer, BoatModel::createBodyModel);
            EntityRendererRegistry.register(entityType, (context) -> new PyriteBoatRenderer(context, false, layer));
        });
        CHEST_BOATS.forEach((id, entityType) -> {
            var layer = new ModelLayerLocation(Pyrite.of("chest_boat/" + id), "main");
            registerModelLayer(layer, ChestBoatModel::createBodyModel);
            EntityRendererRegistry.register(entityType, (context) -> new PyriteBoatRenderer(context, true, layer));
        });

        for (BlockEntry<Block> block : BlockCreator.BLOCKS) {
            if (block.getPath().contains("stained_glass") || block.getPath().contains("stained_framed_glass") || block.get() instanceof FlowerBlock || block.get() instanceof FlowerPotBlock) {
                BlockRenderLayerMap.INSTANCE.putBlock(block.get(), RenderType.translucent());
            }
            else if (block.getPath().contains("ladder") || block.getPath().contains("bars")  || block.getPath().contains("door") || block.getPath().contains("torch") || block.getPath().contains("glass")) {
                BlockRenderLayerMap.INSTANCE.putBlock(block.get(), RenderType.cutout());
            }
        }

    }
}
//?}