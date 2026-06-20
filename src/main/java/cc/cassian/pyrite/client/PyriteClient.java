package cc.cassian.pyrite.client;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.util.ModHelpers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteClient {

	public static int registerColor(BlockState state, BlockAndTintGetter view, BlockPos pos, int tintIndex) {
		if (view == null  || pos == null) return 9551193;
		return BiomeColors.getAverageGrassColor(view, pos);
	}

	public static int registerColor(ItemStack stack, int i) {
		return 9551193;
	}

	public static void addTooltip(List<Component> lines, ItemStack stack) {
		ResourceLocation id = BuiltInRegistries.ITEM.getKey(stack.getItem());
		if ((Pyrite.CONFIG.disabledContentTooltip || Pyrite.CONFIG.enabledContentTooltip) && id.getNamespace().equals(MOD_ID)) {
			boolean enabled = ModHelpers.enabled(id);
			if (enabled && Pyrite.CONFIG.enabledContentTooltip) {
				lines.add(Component.translatable("config.pyrite.enabled").withStyle(ChatFormatting.GREEN));
				addRequiredOptions(lines, id);
			}
			if (!enabled && Pyrite.CONFIG.disabledContentTooltip) {
				lines.add(Component.translatable("config.pyrite.disabled").withStyle(ChatFormatting.RED));
				addRequiredOptions(lines, id);
			}
		}
	}

	private static void addRequiredOptions(List<Component> lines, ResourceLocation id) {
		for (String requiredOption : ModHelpers.getRequiredOptions(id)) {
			var color = ModHelpers.enabled(List.of(requiredOption)) ? ChatFormatting.GREEN : ChatFormatting.RED;
			lines.add(Component.literal("  - " + requiredOption).withStyle(color));
		}
	}

	public static ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, Boat.Type type, boolean chestBoat) {
		ModelLayerLocation modelLayerLocation = chestBoat ? ModelLayers.createChestBoatModelName(type) : ModelLayers.createBoatModelName(type);
		ModelPart modelPart = context.bakeLayer(modelLayerLocation);
		if (type == Boat.Type.BAMBOO) {
			return chestBoat ? new ChestRaftModel(modelPart) : new RaftModel(modelPart);
		} else {
			return chestBoat ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
		}
	}
}
