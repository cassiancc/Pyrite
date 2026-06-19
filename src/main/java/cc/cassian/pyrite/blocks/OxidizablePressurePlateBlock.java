package cc.cassian.pyrite.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class OxidizablePressurePlateBlock extends ModPressurePlate implements WeatheringCopper {
	private final WeatherState oxidationLevel;

	public OxidizablePressurePlateBlock(BlockSetType blockSetType, WeatherState oxidizationState, Properties properties) {
		super(properties, blockSetType);
		this.oxidationLevel = oxidizationState;
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		this.changeOverTime(state, world, pos, random);
	}

	@Override
	protected boolean isRandomlyTicking(BlockState state) {
		return WeatheringCopper.getNext(state.getBlock()).isPresent();
	}

	@Override
	public WeatherState getAge() {
		return oxidationLevel;
	}
}
