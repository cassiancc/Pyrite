package cc.cassian.pyrite.forge;

import cc.cassian.pyrite.Pyrite;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static cc.cassian.pyrite.functions.ModHelpers.grassBlocks;
import static cc.cassian.pyrite.functions.ModHelpers.transparentBlocks;

@Mod.EventBusSubscriber(modid = Pyrite.modID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class PyriteClient {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event){
        BlockColors blockColors = event.getBlockColors();
        for (RegistrySupplier<Block> pyriteBlock : grassBlocks) {
            event.register(((state, view, pos, tintIndex) -> {
                assert view != null;
                return BiomeColors.getGrassColor(view, pos);
            }), pyriteBlock.get());

        }
    }

    public static void registerItemColors(RegisterColorHandlersEvent.Item event){
        for (RegistrySupplier<Block> pyriteBlock : grassBlocks) {
            ItemColors itemColors = event.getItemColors();
            BlockColors blockColors = event.getBlockColors();
            event.register((stack, tintIndex) -> {
                BlockState blockstate = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
                return blockColors.getColor(blockstate, null, null, tintIndex);
            }, pyriteBlock.get());
        }}
}
