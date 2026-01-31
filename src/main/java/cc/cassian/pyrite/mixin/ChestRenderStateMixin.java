package cc.cassian.pyrite.mixin;


import cc.cassian.pyrite.access.ChestRenderStateAccess;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ChestRenderState.class)
public class ChestRenderStateMixin implements ChestRenderStateAccess {
	@Unique
	public String pyrite$variant;


	public ChestRenderStateMixin() {
		pyrite$variant = "";
	}

	@Override
	public String pyrite$getVariant() {
		return pyrite$variant;
	}

	@Override
	public void pyrite$setVariant(String variant) {
		pyrite$variant = variant;
	}
}
