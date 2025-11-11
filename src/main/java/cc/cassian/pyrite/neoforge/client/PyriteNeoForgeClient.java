package cc.cassian.pyrite.neoforge.client;

//? if neoforge {

/*import cc.cassian.pyrite.Pyrite;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static cc.cassian.pyrite.functions.neoforge.NeoHelpers.GRASS_BLOCKS;

@EventBusSubscriber(modid = Pyrite.MOD_ID, value = Dist.CLIENT)
public class PyriteNeoForgeClient {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        for (Supplier<Block> pyriteBlock : GRASS_BLOCKS) {
            event.register(((state, view, pos, tintIndex) -> {
                if (view == null) return 9551193;
                return BiomeColors.getAverageGrassColor(view, pos);
            }), pyriteBlock.get());

        }
    }

}

*///?}