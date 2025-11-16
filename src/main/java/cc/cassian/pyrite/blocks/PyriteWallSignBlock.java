package cc.cassian.pyrite.blocks;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

public class PyriteWallSignBlock extends WallSignBlock {
    private final String id;

    public PyriteWallSignBlock(WoodType type, Properties properties, String blockID) {
        super(type, properties);
        this.id = blockID;
    }

    //? if <1.21.2 {
    /*@Override
    public String getDescriptionId() {
        return Pyrite.of(id).toLanguageKey("block");
    }
    *///?}
}
