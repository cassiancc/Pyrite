package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.util.ModHelpers;
import com.github.smallinger.copperagebackport.block.shelf.ShelfBlock;
import com.github.smallinger.copperagebackport.registry.ModBlockEntities;
import com.github.smallinger.copperagebackport.registry.ModParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

import static cc.cassian.pyrite.registry.BlockCreator.createTorchLever;

public class CopperAgeBackportCompat {
	public static void registerTorch() {
		createTorchLever("copper_torch_lever", Blocks.TORCH, ParticleTypes.SOUL_FIRE_FLAME);
	}

	public static Block registerShelf(BlockBehaviour.Properties blockSettings) {
		var newBlock = new ShelfBlock(blockSettings.strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion());
		ModHelpers.addSupportedBlock(ModBlockEntities.SHELF_BLOCK_ENTITY, newBlock);
		return newBlock;

	}

	public static BlockEntry<Block> getShelfEntry(String blockID, MapColor color, int blockLux, String group, BlockSetType GENERATED_SET, WoodType GENERATED_TYPE) {
		return BlockCreator.createPyriteBlock("%s_shelf".formatted(blockID), "shelf", Blocks.OAK_PLANKS, color, blockLux, GENERATED_SET, GENERATED_TYPE, group);
	}

	public static void addParticle(Level world, double xPlus, double yPlus, double zPlus) {
		world.addParticle(ModParticles.COPPER_FIRE_FLAME.get(), xPlus, yPlus, zPlus, 0.0, 0.0, 0.0);
	}
}
