package cc.cassian.pyrite.fabric;

//? fabric {
import cc.cassian.pyrite.Platform;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

import java.nio.file.Path;

public class FabricPlatformImpl implements Platform {

    @Override
    public void registerWaxableBlockPair(Block newBlock, Block waxed) {
        //? if >26 {
        /*OxidizableBlocksRegistry.registerWaxable(newBlock, waxed);
        *///?} else {
        OxidizableBlocksRegistry.registerWaxableBlockPair(newBlock, waxed);
        //?}
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