package cc.cassian.pyrite.blocks;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

public class PyriteWallHangingSignBlock extends WallHangingSignBlock {
    private final String id;

    public PyriteWallHangingSignBlock(WoodType woodType, Properties blockSettings, String blockID) {
        super(woodType, blockSettings);
        this.id = blockID;
    }

    //? if <1.21.2 {
    @Override
    public String getDescriptionId() {
        return Pyrite.of(id).toLanguageKey("block");
    }
    //?}
}
