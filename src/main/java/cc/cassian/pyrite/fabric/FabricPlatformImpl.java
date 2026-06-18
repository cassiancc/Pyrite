package cc.cassian.pyrite.fabric;

//? fabric {
import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.entries.BlockEntry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FabricPlatformImpl implements Platform {

    public static Map<Identifier, Identifier> WAXABLES = new HashMap<>();
    public static Map<Identifier, Identifier> OXIDIZABLES = new HashMap<>();
    public static Map<Identifier, Identifier> STRIPPABLES = new HashMap<>();

    @Override
    public void registerWaxableBlockPair(BlockEntry<?> newBlock, BlockEntry<?> waxed) {
        OxidizableBlocksRegistry.registerWaxable(newBlock.get(), waxed.get());
        WAXABLES.put(newBlock.getId(),  waxed.getId());
    }

    @Override
    public void registerStrippableBlockPair(BlockEntry<?> newBlock, BlockEntry<?> waxed) {
        StrippableBlockRegistry.register(newBlock.get(), waxed.get());
        STRIPPABLES.put(newBlock.getId(),  waxed.getId());
    }

    @Override
    public WoodType createWoodType(String blockID, BlockSetType setType) {
        return WoodTypeBuilder.copyOf(WoodType.OAK).register(Pyrite.of(blockID), setType);
    }

    @Override
    public boolean isModLoaded(String modid) {
        return FabricLoader.getInstance().isModLoaded(modid);
    }

    @Override
    public String loader() {
        return "fabric";
    }

    @Override
    public boolean isDevEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Path getConfigDir() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public void registerOxidizableBlockPair(BlockEntry<?> block, BlockEntry<?> block1) {
        OxidizableBlocksRegistry.registerNextStage(block.get(), block1.get());
        OXIDIZABLES.put(block.getId(),  block1.getId());
    }

}
//?}