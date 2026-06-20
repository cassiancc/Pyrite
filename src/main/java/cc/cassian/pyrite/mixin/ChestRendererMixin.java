package cc.cassian.pyrite.mixin;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteBlockTags;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.minecraft.client.renderer.Sheets.CHEST_SHEET;

@Mixin(ChestRenderer.class)
public class ChestRendererMixin {

	@WrapOperation(method = "render(Lnet/minecraft/world/level/block/entity/BlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Sheets;chooseMaterial(Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/level/block/state/properties/ChestType;Z)Lnet/minecraft/client/resources/model/Material;"))
	private Material changeMaterial(BlockEntity blockEntity, ChestType chestType, boolean holiday, Operation<Material> original, @Local BlockState state) {
		if (state.is(PyriteBlockTags.CHESTS)) {
			String variant = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath().replace("_chest", "");
			String addon = switch (chestType) {
				case LEFT -> "_left";
				case RIGHT -> "_right";
				case SINGLE -> "";
			};
			return new Material(CHEST_SHEET, Pyrite.of("entity/chest/" + variant + addon));
		}
		return original.call(blockEntity, chestType, holiday);
	}

}