//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.ModCraftingTable;
import cc.cassian.pyrite.condition.PyriteResourceConditions;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.core.PyriteItemTags;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static cc.cassian.pyrite.functions.ModHelpers.getRequiredOptions;

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
					List<String> requiredOptions = getRequiredOptions(boatId);
					if (!boatId.toString().contains("chest")) {
						Item planks = getItem(boatId.withPath(p -> p.replace("boat", "planks")));
						woodenBoat(boat.value(), planks, requiredOptions);
					} else {
						chestBoat(boat.value(), getItem(boatId.withPath(p -> p.replace("chest_", ""))), requiredOptions);
					}
				}
				for (ItemEntry<Item> sign : PyriteItemGroups.SIGNS) {
					Identifier signId = sign.getId();
					if (!signId.toString().contains("hanging")) {
						Item planks = getItem(signId.withPath(p -> p.replace("sign", "planks")));
						sign(sign.value(), planks, getRequiredOptions(signId));
					} else {
						hangingSign(sign.value(), getItem(signId.withPath(p -> p.replace("hanging_sign", "planks"))), getRequiredOptions(signId));
					}
				}

				for (String dye : ModLists.DYES) {
					var dyeTag = TagKey.create(Registries.ITEM, Pyrite.of("c", "dyes/"+dye));
					List<String> requiredOptions = new ArrayList<>();
					if (ModLists.PYRITE_DYES.contains(dye)) {
						requiredOptions.add("oddities");
					}
					Identifier torchId = Pyrite.of(dye + "_torch");
					shapeless(RecipeCategory.DECORATIONS, getItem(torchId)).group("torch").requires(dyeTag).requires(Items.TORCH).unlockedBy(getItemName(Items.TORCH), has(Items.TORCH)).save(configuredOutput(requiredOptions));
					Identifier torchLeverId = Pyrite.of(dye + "_torch_lever");
					shapeless(RecipeCategory.REDSTONE, getItem(torchLeverId)).group("torch_lever").requires(getItem(torchId)).requires(Items.LEVER).unlockedBy(getItemName(Items.TORCH), has(Items.TORCH)).save(configuredOutput(requiredOptions));
				}

				for (BlockEntry<Block> entry : BlockCreator.BLOCKS) {
					Identifier blockId = entry.getId();
					List<String> requiredOptions = getRequiredOptions(blockId);
					if (entry.value() instanceof ModCraftingTable) {
						Item planks = getItemOrVanilla(blockId.withPath(p -> p.replace("crafting_table", "planks")));
						this.shaped(RecipeCategory.DECORATIONS, entry.asItem())
								.group("crafting_table")
								.define('#', planks)
								.pattern("##")
								.pattern("##")
								.unlockedBy("unlock_right_away", PlayerTrigger.TriggerInstance.tick())
								.showNotification(false)
								.save(configuredOutput(requiredOptions));
					} else if (entry.value() instanceof LadderBlock) {
						Item planks = getItemOrVanilla(blockId.withPath(p -> p.replace("ladder", "planks")));
						this.shaped(RecipeCategory.DECORATIONS, entry.asItem(), 3)
								.group("ladder")
								.define('#', planks)
								.define('S', ConventionalItemTags.WOODEN_RODS)
								.pattern("S S")
								.pattern("S#S")
								.pattern("S S")
								.unlockedBy(getItemName(planks), has(planks))
								.save(configuredOutput(requiredOptions));
					} else if (entry.getId().getPath().contains("_planks")) {
						if (entry.getId().getPath().contains("brown_mushroom")) {
							planksFromLogs(entry.value(), PyriteBlockItemTags.AZALEA_LOGS.item(), 4);
						} else if (entry.getId().getPath().contains("red_mushroom")) {
							planksFromLogs(entry.value(), getItem(Pyrite.of("red_mushroom_stem")), 4, requiredOptions);
						}else if (entry.getId().getPath().contains("azalea")) {
							planksFromLogs(entry.value(), getItem(Pyrite.of("brown_mushroom_stem")), 4, requiredOptions);
						} else {
							Ingredient dye = getDyeTag(blockId.withPath(p -> p.replace("_stained_planks", "")));
							this.coloredBaseBlockFromBaseBlockAndDye(entry.value(), dye, ingredientOf(ItemTags.PLANKS), requiredOptions);
						}
					} else if (entry.getId().getPath().contains("_wood")) {
						woodFromLogs(entry.value(), getItem(Pyrite.of(entry.getPath().replace("wood", "log"))), requiredOptions);
					}
//					else if (entry.getId().getPath().contains("_torch") && !entry.getId().getPath().contains("lever")) {
//						shapeless(RecipeCategory.DECORATIONS, entry.asItem()).group("torch").requires(getDyeTag(blockId.withPath(p -> p.replace("_torch", ""))));
//					}
				}
			}

            private Ingredient getDyeTag(Identifier stainedPlanks) {
                return ingredientOf(TagKey.create(Registries.ITEM, Pyrite.of("c", "dyes/"+stainedPlanks.getPath())));
            }

			public void planksFromLogs(final ItemLike result, final TagKey<Item> logs, final int count, List<String> requiredOptions) {
				this.shapeless(RecipeCategory.BUILDING_BLOCKS, result, count).requires(logs).group("planks").unlockedBy("has_logs", this.has(logs)).save(configuredOutput(requiredOptions));
			}

			public void planksFromLogs(final ItemLike result, final Item logs, final int count, List<String> requiredOptions) {
				this.shapeless(RecipeCategory.BUILDING_BLOCKS, result, count).requires(logs).group("planks").unlockedBy("has_logs", this.has(logs)).save(configuredOutput(requiredOptions));
			}

			public void woodFromLogs(final ItemLike result, final ItemLike log, List<String> requiredOptions) {
				this.shaped(RecipeCategory.BUILDING_BLOCKS, result, 3).define('#', log).pattern("##").pattern("##").group("bark").unlockedBy("has_log", this.has(log)).save(configuredOutput(requiredOptions));
			}

			private Ingredient ingredientOf(TagKey<Item> planks) {
                return Ingredient.of(registries.getOrThrow(planks));
            }

			public void coloredBaseBlockFromBaseBlockAndDye(final ItemLike result, final Ingredient dye, Ingredient baseBlock, List<String> requiredOptions) {
				this.shaped(RecipeCategory.BUILDING_BLOCKS, result, 8)
						.define('#', baseBlock)
						.define('X', dye)
						.pattern("###")
						.pattern("#X#")
						.pattern("###")
						.group("planks")
						.unlockedBy("has_base", this.has(getItem(Identifier.withDefaultNamespace("oak_planks"))))
						.save(configuredOutput(requiredOptions));
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
				this.shaped(RecipeCategory.TRANSPORTATION, result)
						.define('#', planks)
						.pattern("# #")
						.pattern("###")
						.group("boat")
						.unlockedBy("in_water", insideOf(Blocks.WATER))
						.save(configuredOutput(requiredOptions));
			}

			public void chestBoat(final ItemLike chestBoat, final ItemLike boat, List<String> requiredOptions) {
				this.shapeless(RecipeCategory.TRANSPORTATION, chestBoat)
						.requires(ConventionalItemTags.WOODEN_CHESTS)
						.requires(boat)
						.group("chest_boat")
						.unlockedBy("has_boat", this.has(ItemTags.BOATS))
						.save(configuredOutput(requiredOptions));
			}

			private RecipeOutput configuredOutput(Identifier id) {
				return withConditions(configuredOutput(getRequiredOptions(id)));
			}

			private RecipeOutput configuredOutput(List<String> requiredOptions) {
				if (requiredOptions.isEmpty()) return output;
				return withConditions(output, PyriteResourceConditions.config(requiredOptions));
			}

			public final void sign(final ItemLike result, final Item planks, List<String> requiredOptions) {
				this.shaped(RecipeCategory.DECORATIONS, result, 3).group("sign").define('#', planks).define('X', Items.STICK).pattern("###").pattern("###").pattern(" X ").unlockedBy("has_planks", this.has(planks)).save(configuredOutput(requiredOptions));
			}

			public void hangingSign(final ItemLike result, final Item planks, List<String> requiredOptions) {
				this.shaped(RecipeCategory.DECORATIONS, result, 6).group("hanging_sign").define('#', planks).define('X', Items.IRON_CHAIN).pattern("X X").pattern("###").pattern("###").unlockedBy("has_stripped_logs", this.has(planks)).save(configuredOutput(requiredOptions));
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