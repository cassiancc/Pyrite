package cc.cassian.pyrite;

//? fabric {
import cc.cassian.pyrite.fabric.FabricPlatformImpl;
//?}
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
}
