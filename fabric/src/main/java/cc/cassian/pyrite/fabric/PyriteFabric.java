package cc.cassian.pyrite.fabric;

import cc.cassian.pyrite.Pyrite;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;

import static cc.cassian.pyrite.functions.ModHelpers.fuel;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init();
        fuel.forEach((fuelBlock, fuelLength) -> FuelRegistry.INSTANCE.add(fuelBlock.get(), fuelLength));
    }
}