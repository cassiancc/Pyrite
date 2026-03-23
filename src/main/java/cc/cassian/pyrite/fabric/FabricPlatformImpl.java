package cc.cassian.pyrite.fabric;

//? fabric {
import cc.cassian.pyrite.Platform;
import cc.cassian.pyrite.Pyrite;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.nio.file.Path;

public class FabricPlatformImpl implements Platform {

    @Override
    public void registerWaxableBlockPair(Block newBlock, Block waxed) {
        OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
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
    public void addSupportedBlock(BlockEntityType<?> be, Block block) {
        //? if >26 {
        /*be.addValidBlock(block);
        *///?} else {
        be.addSupportedBlock(block);
        //?}
    }

    @Override
    public void registerOxidizableBlockPair(Block block, Block block1) {
        //? if >26 {
        /*OxidizableBlocksRegistry.registerNextStage(block, block1);
        *///?} else {
        OxidizableBlocksRegistry.registerOxidizableBlockPair(block, block1);
         //?}
    }

}
//?}