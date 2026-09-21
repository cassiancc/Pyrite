package cc.cassian.pyrite.fabric;

//? fabric {
import cc.cassian.mru.fabric.VersionedUtil;
import cc.cassian.pyrite.PyritePlatform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.mru.util.ItemLikeEntry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FabricPlatformImpl implements PyritePlatform {

    public static Map<Identifier, Identifier> WAXABLES = new HashMap<>();
    public static Map<Identifier, Identifier> OXIDIZABLES = new HashMap<>();
    public static Map<Identifier, Identifier> STRIPPABLES = new HashMap<>();

    @Override
    public void registerWaxableBlockPair(ItemLikeEntry<?> newBlock, ItemLikeEntry<?> waxed) {
        OxidizableBlocksRegistry.registerWaxable((Block) newBlock.get(), (Block) waxed.get());
        WAXABLES.put(newBlock.id(),  waxed.id());
    }

    @Override
    public void registerStrippableBlockPair(ItemLikeEntry<?> newBlock, ItemLikeEntry<?> waxed) {
        VersionedUtil.registerStrippable((Block) newBlock.get(), (Block) waxed.get());
        STRIPPABLES.put(newBlock.id(),  waxed.id());
    }

    @Override
    public WoodType createWoodType(String blockID, BlockSetType setType) {
        return WoodTypeBuilder.copyOf(WoodType.OAK).register(Pyrite.of(blockID), setType);
    }

    @Override
    public void registerOxidizableBlockPair(ItemLikeEntry<?> block, ItemLikeEntry<?> block1) {
        OxidizableBlocksRegistry.registerNextStage((Block) block.get(), (Block) block1.get());
        OXIDIZABLES.put(block.id(),  block1.id());
    }

}
//?}