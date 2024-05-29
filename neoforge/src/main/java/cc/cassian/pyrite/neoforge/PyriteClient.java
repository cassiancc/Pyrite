package cc.cassian.pyrite.neoforge;


import cc.cassian.pyrite.Pyrite;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import static cc.cassian.pyrite.functions.ModHelpers.grassBlocks;

@Mod(Pyrite.modID)
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
