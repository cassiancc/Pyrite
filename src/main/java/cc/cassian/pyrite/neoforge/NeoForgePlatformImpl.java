package cc.cassian.pyrite.neoforge;

//? neoforge {
/*import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.entries.BlockEntry;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class NeoForgePlatformImpl implements Platform {

    @Override
    public boolean isModLoaded(String modid) {
        return ModList.get().isLoaded(modid);
    }

    @Override
    public String loader() {
        return "neoforge";
    }

    @Override
    public boolean isDevEnvironment() {
        return !FMLEnvironment.isProduction();
    }

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public void registerOxidizableBlockPair(BlockEntry<?> block, BlockEntry<?> block1) {}

    @Override
    public void registerWaxableBlockPair(BlockEntry<?> newBlock, BlockEntry<?> waxed) {}

    @Override
    public void registerStrippableBlockPair(BlockEntry<?> newBlock, BlockEntry<?> waxed) {}

    @Override
    public WoodType createWoodType(String blockID, BlockSetType setType) {
        WoodType woodType = new WoodType(Pyrite.of(blockID).toString(), setType);
		WoodType.register(woodType);
		return woodType;
    }

}
*///?}