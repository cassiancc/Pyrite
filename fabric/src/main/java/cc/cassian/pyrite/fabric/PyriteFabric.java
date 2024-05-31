package cc.cassian.pyrite.fabric;

import cc.cassian.pyrite.Pyrite;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static cc.cassian.pyrite.Pyrite.modID;
import static cc.cassian.pyrite.fabric.FabricHelpers.PYRITE_GROUP;
import static cc.cassian.pyrite.fabric.FabricHelpers.registerFuelBlocks;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init("fabric");
        Registry.register(Registries.ITEM_GROUP, Identifier.of(modID, "pyrite_group"), PYRITE_GROUP);
        registerFuelBlocks();
    }
}