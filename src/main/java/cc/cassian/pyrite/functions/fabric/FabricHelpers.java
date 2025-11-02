package cc.cassian.pyrite.functions.fabric;

//? if fabric {

/*import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static cc.cassian.pyrite.registry.fabric.BlockCreatorImpl.BLOCKS;

public class FabricHelpers {

    public static final ArrayList<Block> TRANSPARENT_BLOCKS = new ArrayList<>();
    public static final ArrayList<Block> TRANSLUCENT_BLOCKS = new ArrayList<>();
    public static final ArrayList<Block> GRASS_BLOCKS = new ArrayList<>();
    public static final HashMap<Block, Integer> FUEL_BLOCKS = new HashMap<>();

    public static void registerFuelBlocks() {
        for (Map.Entry<Block, Integer> fuelBlock : FUEL_BLOCKS.entrySet()) {
            FuelRegistryEvents.BUILD.register((builder, context) -> {
                builder.add(fuelBlock.getKey(), fuelBlock.getValue());
            });
        }
    }

    public static void addGrassBlock() {
        GRASS_BLOCKS.add(getLastBlock());
    }
    public static void addTransparentBlock(Block newBlock) {
        TRANSPARENT_BLOCKS.add(newBlock);
    }
    public static void addTranslucentBlock(Block newBlock) {
        TRANSLUCENT_BLOCKS.add(newBlock);
    }
    public static Block getLastBlock() {
        return BLOCKS.lastEntry().getValue();
    }


}

*///?}
