package cc.cassian.pyrite.fabric;

//? fabric {
import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
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

    @Override
    public void registerWaxableBlockPair(Block newBlock, Block waxed) {
        OxidizableBlocksRegistry.registerWaxable(newBlock, waxed);
        WAXABLES.put(newBlock.properties().blockId().identifier(),  waxed.properties().blockId().identifier().withPrefix("waxed_"));
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
    public void registerOxidizableBlockPair(Block block, Block block1) {
        OxidizableBlocksRegistry.registerNextStage(block, block1);
        OXIDIZABLES.put(block.properties().blockId().identifier(),  block1.properties().blockId().identifier());
    }

}
//?}