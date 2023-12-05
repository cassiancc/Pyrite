package com.pyrite.entity;

import com.pyrite.client.PyriteClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class GrebeEntityRenderer extends MobEntityRenderer<Grebe, GrebeEntityModel> {
    public GrebeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GrebeEntityModel(context.getPart(PyriteClient.GREBE)), 0.3f);
    }

    @Override
    public Identifier getTexture(Grebe entity) {
        return new Identifier("pyrite", "textures/entity/grebe/grebe.png");
    }
    @Override
    protected float getAnimationProgress(Grebe chickenEntity, float f) {
        float g = MathHelper.lerp(f, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
        float h = MathHelper.lerp(f, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0f) * h;
    }
}
