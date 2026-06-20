package cc.cassian.pyrite.blocks;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;

public class PyriteStandingSignBlock extends StandingSignBlock {
    private final String id;

    public PyriteStandingSignBlock(WoodType type, Properties properties, String blockID) {
        super(type, properties);
        this.id = blockID;
    }

    //? if <1.21.2 {
    @Override
    public String getDescriptionId() {
        return Pyrite.of(id).toLanguageKey("block");
    }
    //?}
}
