package cc.cassian.pyrite.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

public class OxidizableBarsBlock extends IronBarsBlock implements WeatheringCopper {
	private final WeatherState oxidationLevel;

	public OxidizableBarsBlock(WeatherState oxidationLevel, Properties settings) {
		super(settings);
		this.oxidationLevel = oxidationLevel;
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
