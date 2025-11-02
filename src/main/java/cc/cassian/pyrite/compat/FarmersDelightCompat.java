package cc.cassian.pyrite.compat;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

import java.util.ArrayList;

public class FarmersDelightCompat {
    public static ArrayList<Block> CABINETS = new ArrayList<>();

    public static Block registerCabinet(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        return new CabinetBlock(blockSettings);
    }

    public static void add(Block newBlock) {
        CABINETS.add(newBlock);
    }

    public static void registerToBlockEntity() {
        for (Block chest : CABINETS) {
            //? if fabric
            ModBlockEntityTypes.CABINET.get().addSupportedBlock(chest);
        }
    }
}
