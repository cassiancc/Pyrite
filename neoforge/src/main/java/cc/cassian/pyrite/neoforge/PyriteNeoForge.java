package cc.cassian.pyrite.neoforge;


import cc.cassian.pyrite.Pyrite;
import net.minecraft.SharedConstants;
import net.neoforged.fml.common.Mod;

@Mod(Pyrite.modID)
public final class PyriteNeoForge {
    public PyriteNeoForge() {
        // Run our common setup.
        Pyrite.init(SharedConstants.WORLD_VERSION + "-neoforge");

    }
}
