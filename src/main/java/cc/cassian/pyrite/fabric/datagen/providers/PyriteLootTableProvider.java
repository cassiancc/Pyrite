//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.*;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import dev.lieonlion.quad.tags.QuadBlockTags;
import net.fabricmc.fabric.api.block.v1.BlockFunctionalityTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@SuppressWarnings("all")
public class PyriteLootTableProvider extends FabricBlockLootSubProvider {


	public PyriteLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(packOutput, registriesFuture);
	}

	@Override
	public void generate() {
		BuiltInRegistries.BLOCK.entrySet().forEach(blockEntry -> {
			var key = blockEntry.getKey();
			if (key.identifier().getNamespace().equals(Pyrite.MOD_ID)) {
				var value = blockEntry.getValue();
				switch (value.getClass().getSimpleName()) {
					case "Block", "ModBlock", "ModWood", "ModCraftingTable", "SwitchableGlass", "OxidizableWallBlock", "OxidizablePillarBlock", "ModFacingBlock", "WallHangingSignBlock", "GravelBlock", "OxidizableWallGateBlock", "FenceGateBlock", "ModTorch", "ModCarpet", "TorchLever", "FlowerBlock", "FenceBlock", "ModWoodenButton", "ModPillar", "WallSignBlock", "LadderBlock", "TrapDoorBlock", "ConcretePowderBlock", "WeatheringCopperFullBlock", "ChestBlock", "ModStairs", "WeatheringCopperStairBlock", "ModPressurePlate", "ShelfBlock", "PyriteWallHangingSignBlock", "ModWall", "WallGateBlock", "PyriteWallSignBlock", "CabinetBlock": dropSelf(value); break;
					case "ModPane": {
						if (key.identifier().toString().contains("pane")) dropWhenSilkTouch(value);
						else dropSelf(value);
					} break;
					case "ModSlab", "WeatheringCopperSlabBlock": add(value, createSlabItemTable(value)); break;
					case "DoorBlock": add(value, createDoorTable(value)); break;
					case "FlowerPotBlock": dropPottedContents(value); break;
					case "ModGlass", "StainedFramedGlass", "StainedGlassPaneBlock": dropWhenSilkTouch(value); break;
					case "PyriteStandingSignBlock": break;
					default: {
						dropSelf(value);
						Pyrite.LOGGER.error("Loot table for %s not implemented!".formatted(value.getClass().getSimpleName().toString()));
					}
				}
			}
		});
	}
}
//?}