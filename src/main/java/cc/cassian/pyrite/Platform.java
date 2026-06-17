package cc.cassian.pyrite;

import cc.cassian.pyrite.entries.BlockEntry;
//? fabric {

import cc.cassian.pyrite.fabric.FabricPlatformImpl;
//?}
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
//? neoforge {
/*import cc.cassian.pyrite.neoforge.NeoForgePlatformImpl;
*///?}
import java.nio.file.Path;

public interface Platform {

    //? fabric {
    Platform INSTANCE = new FabricPlatformImpl();

	//?}
    //? neoforge {
    /*Platform INSTANCE = new NeoForgePlatformImpl();
    *///?}


    boolean isModLoaded(String modid);
    String loader();
    boolean isDevEnvironment();
    Path getConfigDir();

    void registerOxidizableBlockPair(BlockEntry<?> block, BlockEntry<?> block1);

    void registerWaxableBlockPair(BlockEntry<?> newBlock, BlockEntry<?> waxed);

    WoodType createWoodType(String blockID, BlockSetType setType);
}
