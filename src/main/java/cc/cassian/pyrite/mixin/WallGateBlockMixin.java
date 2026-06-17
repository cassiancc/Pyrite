package cc.cassian.pyrite.mixin;

import cc.cassian.pyrite.blocks.WallGateBlock;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WallBlock.class)
public class WallGateBlockMixin {

	@ModifyReturnValue(method = "connectsTo", at = @At(value = "RETURN"))
	private boolean setVariantOnRenderState(boolean original, final BlockState state, final boolean faceSolid, final Direction direction) {
		return original || (state.getBlock() instanceof WallGateBlock && FenceGateBlock.connectsToDirection(state, direction));
	}

}
