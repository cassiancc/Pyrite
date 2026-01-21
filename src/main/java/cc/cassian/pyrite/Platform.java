package cc.cassian.pyrite;

//? fabric {
import cc.cassian.pyrite.fabric.FabricPlatformImpl;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
//?}
//? neoforge {
/*import cc.cassian.pyrite.neoforge.NeoForgePlatformImpl;
*///?}
import java.nio.file.Path;

public interface Platform {

    //? fabric {
    Platform INSTANCE = new FabricPlatformImpl();

	void registerWaxableBlockPair(Block newBlock, Block waxed);
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

}
