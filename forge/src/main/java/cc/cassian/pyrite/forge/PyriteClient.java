package cc.cassian.pyrite.forge;

import cc.cassian.pyrite.Pyrite;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.item.BlockItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cc.cassian.pyrite.functions.architectury.ArchitecturyHelpers.grassBlocks;

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

    // Client-side mod bus event handler
    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        for (RegistrySupplier<Block> pyriteBlock : grassBlocks) {
            event.register((stack, tintIndex) -> 9551193, pyriteBlock.get());
        }
    }
}
