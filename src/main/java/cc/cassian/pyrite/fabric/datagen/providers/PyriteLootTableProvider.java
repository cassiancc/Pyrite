//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.condition.PyriteResourceConditions;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static cc.cassian.pyrite.util.ModHelpers.getRequiredOptions;

@SuppressWarnings("all")
public class PyriteLootTableProvider extends FabricBlockLootTableProvider {


	public PyriteLootTableProvider(FabricDataOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(packOutput, registriesFuture);
	}

	@Override
	public void generate() {
		BuiltInRegistries.BLOCK.entrySet().forEach(blockEntry -> {
			var key = blockEntry.getKey();
			if (key.location().getNamespace().equals(Pyrite.MOD_ID)) {
				var value = blockEntry.getValue();
				ResourceLocation id = blockEntry.getKey().location();
				String name = id.getPath();
				try {
					switch (value.getClass().getSimpleName()) {
						case "Block", "ModBlock", "ModWood", "ModCraftingTable", "SwitchableGlass", "OxidizableWallBlock", "OxidizablePillarBlock", "ModFacingBlock", "WallHangingSignBlock", "GravelBlock", "OxidizableWallGateBlock", "FenceGateBlock", "ModTorch", "ModCarpet", "TorchLever", "FlowerBlock", "FenceBlock", "ModWoodenButton", "ModPillar", "WallSignBlock", "LadderBlock", "TrapDoorBlock", "ConcretePowderBlock", "WeatheringCopperFullBlock", "ChestBlock", "ModStairs", "WeatheringCopperStairBlock", "ModPressurePlate", "ShelfBlock", "PyriteWallHangingSignBlock", "ModWall", "WallGateBlock", "PyriteWallSignBlock", "CabinetBlock":
							configuredOutput(id).dropSelf(value); break;
						case "ModPane": {
							if (key.location().toString().contains("pane")) dropWhenSilkTouch(value);
							else configuredOutput(id).dropSelf(value);
						} break;
						case "ModSlab", "WeatheringCopperSlabBlock": add(value, configuredOutput(id).createSlabItemTable(value)); break;
						case "DoorBlock": add(value, configuredOutput(id).createDoorTable(value)); break;
						case "FlowerPotBlock": configuredOutput(id).dropPottedContents(value); break;
						case "ModGlass", "StainedFramedGlass", "StainedGlassPaneBlock": configuredOutput(id).dropWhenSilkTouch(value); break;
						case "PyriteStandingSignBlock": break;
						default: {
							configuredOutput(id).dropSelf(value);
							Pyrite.LOGGER.error("Loot table for %s not implemented!".formatted(value.getClass().getSimpleName().toString()));
						}
					}
				} catch (Exception ex) {
					Pyrite.LOGGER.error("Failed to generate loot table for %s!".formatted(name));
				}
			}
		});
	}

	private BlockLootSubProvider configuredOutput(ResourceLocation id) {
		List<String> requiredOptions = getRequiredOptions(id);
		return configuredOutput(requiredOptions);
	}

	private BlockLootSubProvider configuredOutput(List<String> requiredOptions, String... additionalOptionsArray) {
		var options = new ArrayList<>(requiredOptions);
		options.addAll(Arrays.stream(additionalOptionsArray).toList());
		if (options.isEmpty()) return withConditions(ResourceConditions.alwaysTrue());
		return withConditions(PyriteResourceConditions.config(options));
	}
}
//?}