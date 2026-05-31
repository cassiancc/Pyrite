//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.ModCraftingTable;
import cc.cassian.pyrite.condition.PyriteResourceConditions;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.criterion.PlayerTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("all")
public class PyriteRecipeProvider extends FabricRecipeProvider {


	public PyriteRecipeProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(packOutput, registriesFuture);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
		return new RecipeProvider(registries, output) {
			@Override
			public void buildRecipes() {
				for (BlockFamily family : BlockCreator.FAMILIES) {
					try {
						generateRecipes(family);
					} catch (Exception e) {
						Pyrite.LOGGER.info(e.getMessage() + family.getBaseBlock().getName());
					}
				}
				for (ItemEntry<Item> boat : PyriteItemGroups.BOATS) {
					Identifier boatId = boat.getId();
					List<String> requiredOptions = new ArrayList<>(List.of());
					if (boatId.toString().contains("azalea")) {
						requiredOptions.add("azalea");
					}
					else if (boatId.toString().contains("mushroom")) {
						requiredOptions.add("mushrooms");
					}
					if (!boatId.toString().contains("chest")) {
						Item planks = getItem(boatId.withPath(p -> p.replace("boat", "planks")));
						woodenBoat(boat.value(), planks, requiredOptions);
					} else {
						chestBoat(boat.value(), getItem(boatId.withPath(p -> p.replace("chest_boat", "planks"))), requiredOptions);
					}
				}
				for (ItemEntry<Item> boat : PyriteItemGroups.SIGNS) {
					Identifier boatId = boat.getId();
					if (!boatId.toString().contains("hanging")) {
						Item planks = getItem(boatId.withPath(p -> p.replace("sign", "planks")));
						signBuilder(boat.value(), Ingredient.of(planks));
					} else {
						hangingSign(boat.value(), getItem(boatId.withPath(p -> p.replace("hanging_sign", "planks"))));
					}
				}
				for (BlockEntry<Block> craftingTable : BlockCreator.BLOCKS) {
					if (craftingTable.value() instanceof ModCraftingTable) {
						Identifier tableId = craftingTable.getId();
						Item planks = getItemOrVanilla(tableId.withPath(p -> p.replace("crafting_table", "planks")));
						List<String> requiredOptions = new ArrayList<>(List.of("crafting_tables"));
						if (tableId.toString().contains("azalea")) {
							requiredOptions.add("azalea");
						}
						else if (tableId.toString().contains("mushroom")) {
							requiredOptions.add("mushrooms");
						}
						this.shaped(RecipeCategory.DECORATIONS, craftingTable.asItem())
								.group("crafting_table")
								.define('#', planks)
								.pattern("##")
								.pattern("##")
								.unlockedBy("unlock_right_away", PlayerTrigger.TriggerInstance.tick())
								.showNotification(false)
								.save(configuredOutput(requiredOptions));
					}
				}
			}

            private Item getItem(Identifier id) {
                return registries.getOrThrow(ResourceKey.create(Registries.ITEM, id)).value();
            }

			private Item getItemOrVanilla(Identifier id) {
				ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
				if (registries.get(key).isPresent()) {
					return registries.getOrThrow(key).value();
				} else {
					return registries.getOrThrow(ResourceKey.create(Registries.ITEM, Identifier.withDefaultNamespace(id.getPath()))).value();
				}
			}

			private boolean is(Holder<Block> holder, PyriteBlockItemTags.BlockItemTagId planks) {
                return holder.is(planks.block());
            }

			public void woodenBoat(final ItemLike result, final ItemLike planks, List<String> requiredOptions) {
				this.shaped(RecipeCategory.TRANSPORTATION, result).define('#', planks).pattern("# #").pattern("###").group("boat").unlockedBy("in_water", insideOf(Blocks.WATER)).save(configuredOutput(requiredOptions));
			}

			public void chestBoat(final ItemLike chestBoat, final ItemLike boat, List<String> requiredOptions) {
				this.shapeless(RecipeCategory.TRANSPORTATION, chestBoat).requires(Blocks.CHEST).requires(boat).group("chest_boat").unlockedBy("has_boat", this.has(ItemTags.BOATS)).save(configuredOutput(requiredOptions));
			}

			private RecipeOutput configuredOutput(List<String> requiredOptions) {
				if (requiredOptions.isEmpty()) return output;
				return withConditions(output, PyriteResourceConditions.config(requiredOptions));
			}

			public void generateRecipes(final BlockFamily family) {

				family.getVariants().forEach((variant, result) -> {
					if (family.shouldGenerateCraftingRecipe()) {
						if (result == null) return;
						ItemLike base = this.getBaseBlockForCrafting(family, variant);
						this.generateCraftingRecipe(family, variant, result, base);
						if (variant == BlockFamily.Variant.CRACKED) {
							this.smeltingResultFromBase(result, base);
						}
					}

					if (family.shouldGenerateStonecutterRecipe()) {
						Block base = family.getBaseBlock();
						this.generateStonecutterRecipe(family, variant, base);
					}
				});
			}
		};
	}



	@Override
	public String getName() {
		return "Recipes";
	}
}
//?}