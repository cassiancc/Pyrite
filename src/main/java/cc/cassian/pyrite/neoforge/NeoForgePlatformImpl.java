package cc.cassian.pyrite.neoforge;

//? neoforge {
/*import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
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
import org.jspecify.annotations.Nullable;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class NeoForgePlatformImpl implements Platform {

    public static final LinkedHashMultimap<BlockEntityType<?>, Block> SUPPORTED_BLOCKS = LinkedHashMultimap.create();

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
        //? if >1.21.4 {
        return !FMLEnvironment.isProduction();
        //?} else {
        /^return !FMLEnvironment.production;
        ^///?}
    }

    @Override
    public Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public void addSupportedBlock(BlockEntityType<?> be, Block block) {
        SUPPORTED_BLOCKS.put(be, block);
    }

    @Override
    public void registerOxidizableBlockPair(Block block, Block block1) {
        //fabric
    }

    @Override
    public void registerWaxableBlockPair(Block newBlock, Block waxed) {
        //fabric
    }

    @Override
    public WoodType createWoodType(String blockID, BlockSetType setType) {
        WoodType woodType = new WoodType(Pyrite.of(blockID).toString(), setType);
		WoodType.register(woodType);
		return woodType;
    }

}
*///?}