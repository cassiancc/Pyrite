package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.util.ModHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class FarmersDelightCompat {

    public static Block registerCabinet(String blockID, BlockBehaviour.Properties blockSettings, String group, Block copyBlock) {
        return new CabinetBlock(blockSettings);
    }

    public static void add(Block newBlock) {
        ModHelpers.addSupportedBlock(ModBlockEntityTypes.CABINET, newBlock);
    }
}
