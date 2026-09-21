package cc.cassian.pyrite;

import cc.cassian.mru.util.ItemLikeEntry;
//? fabric {

import cc.cassian.pyrite.fabric.FabricPlatformImpl;
//?}
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
//? neoforge {
/*import cc.cassian.pyrite.neoforge.NeoForgePlatformImpl;
*///?}


public interface PyritePlatform {

    //? fabric {
    PyritePlatform INSTANCE = new FabricPlatformImpl();

	//?}
    //? neoforge {
    /*PyritePlatform INSTANCE = new NeoForgePlatformImpl();
    *///?}

    void registerOxidizableBlockPair(ItemLikeEntry<?> block, ItemLikeEntry<?> block1);

    void registerWaxableBlockPair(ItemLikeEntry<?> newBlock, ItemLikeEntry<?> waxed);

    void registerStrippableBlockPair(ItemLikeEntry<?> newBlock, ItemLikeEntry<?> waxed);

    WoodType createWoodType(String blockID, BlockSetType setType);
}
