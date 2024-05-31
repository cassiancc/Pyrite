package cc.cassian.pyrite.fabric;


import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import static cc.cassian.pyrite.functions.ModHelpers.fuel;
import static cc.cassian.pyrite.functions.FabricRegistry.pyriteBlocks;
import static cc.cassian.pyrite.functions.FabricRegistry.pyriteItems;

public class FabricHelpers {
    public static void registerFuelBlocks() {
        fuel.forEach((fuelBlock, fuelLength) -> FuelRegistry.INSTANCE.add((ItemConvertible) fuelBlock, fuelLength));
    }

    //Add items to the Pyrite Item Group
    public static final ItemGroup PYRITE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(pyriteBlocks.get(2)))
            .displayName(Text.translatable("itemGroup.pyrite.group"))
            .entries((context, entries) -> {
                for (Block block : pyriteBlocks) {
                    entries.add(block);
                }
                for (Item item : pyriteItems) {
                    entries.add(item);
                }
            })
            .build();
}
