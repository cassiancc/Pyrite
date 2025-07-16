package cc.cassian.pyrite.compat;

import io.github.lieonlion.mcv.block.MoreChestBlock;
import io.github.lieonlion.mcv.init.McvBlockInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

import java.util.ArrayList;

public class FarmersDelightCompat {
    public static ArrayList<Block> CABINETS = new ArrayList<>();

    public static Block registerCabinet(String blockID, AbstractBlock.Settings blockSettings, String group, Block copyBlock) {
        return new CabinetBlock(blockSettings);
    }

    public static void add(Block newBlock) {
        CABINETS.add(newBlock);
    }

    public static void registerToBlockEntity() {
        for (Block chest : CABINETS) {
            ModBlockEntityTypes.CABINET.get().addSupportedBlock(chest);
        }
    }
}
