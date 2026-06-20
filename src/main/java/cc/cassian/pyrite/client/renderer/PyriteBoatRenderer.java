//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.cassian.pyrite.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.joml.Quaternionf;

@Environment(EnvType.CLIENT)
public class PyriteBoatRenderer extends EntityRenderer<Boat> {
	private final ModelLayerLocation layer;
	private final ListModel<Boat> listModel;

	public PyriteBoatRenderer(EntityRendererProvider.Context context, boolean chestBoat, ModelLayerLocation layer) {
		super(context);
		this.shadowRadius = 0.8F;
		this.layer = layer;
		this.listModel = createBoatModel(context, chestBoat);
	}

	private ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, boolean chestBoat) {
		ModelPart modelPart = context.bakeLayer(layer);
		return chestBoat ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
	}

	public void render(Boat entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		poseStack.translate(0.0F, 0.375F, 0.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
		float f = (float)entity.getHurtTime() - partialTicks;
		float g = entity.getDamage() - partialTicks;
		if (g < 0.0F) {
			g = 0.0F;
		}

		if (f > 0.0F) {
			poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(f) * f * g / 10.0F * (float)entity.getHurtDir()));
		}

		float h = entity.getBubbleAngle(partialTicks);
		if (!Mth.equal(h, 0.0F)) {
			poseStack.mulPose((new Quaternionf()).setAngleAxis(entity.getBubbleAngle(partialTicks) * ((float)Math.PI / 180F), 1.0F, 0.0F, 1.0F));
		}

		poseStack.scale(-1.0F, -1.0F, 1.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
		listModel.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer vertexConsumer = buffer.getBuffer(listModel.renderType(getTextureLocation(entity)));
		listModel.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
		if (!entity.isUnderWater()) {
			VertexConsumer vertexConsumer2 = buffer.getBuffer(RenderType.waterMask());
			if (listModel instanceof WaterPatchModel waterPatchModel) {
				waterPatchModel.waterPatch().render(poseStack, vertexConsumer2, packedLight, OverlayTexture.NO_OVERLAY);
			}
		}

		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	public ResourceLocation getTextureLocation(Boat entity) {
		return layer.getModel().withPath(p->"textures/entity/"+p+".png");
	}
}
