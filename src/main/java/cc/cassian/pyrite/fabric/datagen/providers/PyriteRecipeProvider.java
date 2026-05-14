//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
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
					if (!boatId.toString().contains("chest")) {
						Item planks = getItem(boatId.withPath(p -> p.replace("boat", "planks")));
						woodenBoat(boat.value(), planks);
					} else {
						chestBoat(boat.value(), getItem(boatId.withPath(p -> p.replace("chest_boat", "planks"))));
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
				for (BlockEntry<Block> craftingTable : PyriteItemGroups.CRAFTING_TABLES) {
					Identifier tableId = craftingTable.getId();
					Item planks = getItemOrVanilla(tableId.withPath(p -> p.replace("crafting_table", "planks")));
					this.shaped(RecipeCategory.DECORATIONS, craftingTable.asItem())
							.group("crafting_table")
							.define('#', planks)
							.pattern("##")
							.pattern("##")
							.unlockedBy("unlock_right_away", PlayerTrigger.TriggerInstance.tick())
							.showNotification(false)
							.save(withConditions(output, ResourceConditions.allModsLoaded()));
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

			public void generateRecipes(final BlockFamily family) {

				family.getVariants().forEach((variant, result) -> {
					if (family.shouldGenerateCraftingRecipe()) {
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