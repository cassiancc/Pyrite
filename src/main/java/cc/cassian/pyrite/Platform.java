package cc.cassian.pyrite;

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

    void addSupportedBlock(BlockEntityType<?> be, Block block);

    void registerOxidizableBlockPair(Block block, Block block1);

    void registerWaxableBlockPair(Block newBlock, Block waxed);

    WoodType createWoodType(String blockID, BlockSetType setType);
}
