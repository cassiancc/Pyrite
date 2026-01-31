package cc.cassian.pyrite.mixin;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.access.ChestRenderStateAccess;
import cc.cassian.pyrite.core.PyriteTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChestRenderer.class)
public class ChestRendererMixin {

	//? if >1.21.2 {

	@Inject(method = "extractRenderState(Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/client/renderer/blockentity/state/ChestRenderState;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V", at = @At(value = "RETURN"))
	private void setVariantOnRenderState(BlockEntity blockEntity, ChestRenderState chestRenderState, float f, Vec3 vec3, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay, CallbackInfo ci) {
		var state = blockEntity.getBlockState();
		if (state.is(PyriteTags.CHESTS)) {
			((ChestRenderStateAccess) chestRenderState).pyrite$setVariant(BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath().replace("_chest", ""));
		}
	}

	@WrapOperation(method = "submit(Lnet/minecraft/client/renderer/blockentity/state/ChestRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Sheets;chooseMaterial(Lnet/minecraft/client/renderer/blockentity/state/ChestRenderState$ChestMaterialType;Lnet/minecraft/world/level/block/state/properties/ChestType;)Lnet/minecraft/client/resources/model/Material;"))
	private Material changeMaterial(ChestRenderState.ChestMaterialType chestMaterialType, ChestType chestType, Operation<Material> original, @Local ChestRenderState chestRenderState) {
		var access = ((ChestRenderStateAccess) chestRenderState);
		if (!access.pyrite$getVariant().isEmpty()) {
			String addon = switch (chestType) {
				case LEFT -> "_left";
				case RIGHT -> "_right";
				case SINGLE -> "";
			};
			return Sheets.CHEST_MAPPER.apply(Pyrite.of(access.pyrite$getVariant() + addon));
		}
		return original.call(chestMaterialType, chestType);
	}

	//?}
}
