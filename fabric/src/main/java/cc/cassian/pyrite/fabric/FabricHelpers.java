package cc.cassian.pyrite.fabric;


import net.fabricmc.fabric.api.registry.FuelRegistry;

import static cc.cassian.pyrite.functions.architectury.ArchitecturyHelpers.fuel;


public class FabricHelpers {
    public static void registerFuelBlocks() {
        fuel.forEach((fuelBlock, fuelLength) -> FuelRegistry.INSTANCE.add(fuelBlock.get(), fuelLength));
    }


}
