package cc.cassian.pyrite.fabric.datagen;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;

import java.util.Optional;

public class PyriteModelTemplates {

	public static final ModelTemplate WALL_GATE_CLOSED = create("template_wall_gate", TextureSlot.TEXTURE, TextureSlot.PARTICLE);
	public static final ModelTemplate WALL_GATE_OPEN  = create("template_wall_gate_open", "_open", TextureSlot.TEXTURE, TextureSlot.PARTICLE);
	public static final ModelTemplate WALL_GATE_WALL_CLOSED = create("template_wall_gate_wall", "_wall", TextureSlot.TEXTURE, TextureSlot.PARTICLE);
	public static final ModelTemplate WALL_GATE_WALL_OPEN = create("template_wall_gate_wall_open", "_wall_open", TextureSlot.TEXTURE, TextureSlot.PARTICLE);

	private static ModelTemplate create(final String id, final TextureSlot... slots) {
		return new ModelTemplate(Optional.of(Pyrite.of("block/" + id)), Optional.empty(), slots);
	}

	private static ModelTemplate create(final String id, final String suffix, final TextureSlot... slots) {
		return new ModelTemplate(Optional.of(Pyrite.of("block/" + id)), Optional.of(suffix), slots);
	}
}
