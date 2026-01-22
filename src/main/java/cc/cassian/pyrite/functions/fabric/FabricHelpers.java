package cc.cassian.pyrite.functions.fabric;

//? if fabric {

//? if >26 {
/*import net.fabricmc.fabric.api.registry.FuelValueEvents;
*///?} else if >1.21.4 {
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
//?} else {
/*import net.fabricmc.fabric.api.registry.FuelRegistry;
*///?}
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class FabricHelpers {
    public static final HashMap<Block, Integer> FUEL_BLOCKS = new HashMap<>();

    public static void registerFuelBlocks() {
        for (Map.Entry<Block, Integer> fuelBlock : FUEL_BLOCKS.entrySet()) {
            //? if >26 {
            /*FuelValueEvents.BUILD.register((builder, context) -> {
                builder.add(fuelBlock.getKey(), fuelBlock.getValue());
            });
            *///?} else if >1.21.4 {
            FuelRegistryEvents.BUILD.register((builder, context) -> {
                builder.add(fuelBlock.getKey(), fuelBlock.getValue());
            });
            //?} else {
            /*FuelRegistry.INSTANCE.add(fuelBlock.getKey(), fuelBlock.getValue());
            *///?}
        }
    }

}

//?}
