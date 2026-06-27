package cc.cassian.pyrite.mixin;

import cc.cassian.pyrite.blocks.WallGateBlock;
import cc.cassian.pyrite.core.PyriteBlockTags;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceGateBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {
	//? <26.2 {
	@ModifyReturnValue(method = "createToolProperties", at = @At(value = "RETURN"))
	private static Tool extendShears(Tool original, @Local HolderGetter<Block> registrationLookup) {
		var rules = new ArrayList<>(List.of(
				Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(PyriteBlockTags.SHEARS_EXTREME_BREAKING_SPEED), 15.0F),
				Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(PyriteBlockTags.SHEARS_MAJOR_BREAKING_SPEED), 5.0F),
				Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(PyriteBlockTags.SHEARS_MINOR_BREAKING_SPEED), 2.0F)
		));
		rules.addAll(original.rules());
		return new Tool(rules, original.defaultMiningSpeed(), original.damagePerBlock(), original.canDestroyBlocksInCreative());
	}
	//?}
}
