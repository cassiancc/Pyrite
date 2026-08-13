package cc.cassian.pyrite.neoforge;

//? neoforge {
/*import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.mru.util.ItemLikeEntry;
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
    public void registerOxidizableBlockPair(ItemLikeEntry<?> block, ItemLikeEntry<?> block1) {}

    @Override
    public void registerWaxableBlockPair(ItemLikeEntry<?> newBlock, ItemLikeEntry<?> waxed) {}

    @Override
    public void registerStrippableBlockPair(ItemLikeEntry<?> newBlock, ItemLikeEntry<?> waxed) {}

    @Override
    public WoodType createWoodType(String blockID, BlockSetType setType) {
        WoodType woodType = new WoodType(Pyrite.of(blockID).toString(), setType);
		WoodType.register(woodType);
		return woodType;
    }

}
*///?}