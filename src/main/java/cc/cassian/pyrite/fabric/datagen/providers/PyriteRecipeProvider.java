//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.ModCraftingTable;
import cc.cassian.pyrite.condition.PyriteResourceConditions;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.registry.TurfSet;
import cc.cassian.pyrite.util.BrickSet;
import cc.cassian.pyrite.util.WoodSet;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.fabric.impl.resource.conditions.conditions.AllModsLoadedResourceCondition;
import net.minecraft.advancements.criterion.PlayerTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.*;
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

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static cc.cassian.pyrite.functions.ModHelpers.getRequiredOptions;
import static cc.cassian.pyrite.registry.BlockCreator.*;

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
					if (!family.shouldGenerateStonecutterRecipe()) {
						try {
							generateRecipes(family);
						} catch (Exception e) {
							Pyrite.LOGGER.info(e.getMessage() + family.getBaseBlock().getName());
						}
					}
				}
				for (WoodSet woodSet : WOOD_SETS) {
					shelf(woodSet.shelf(), woodSet.planks(), getRequiredOptions(Pyrite.of(woodSet.blockID())));
					chest(woodSet.chest(), woodSet.planks(), getRequiredOptions(Pyrite.of(woodSet.blockID())));
				}

				for (TurfSet turfSet : TURF_SETS) {
					List<String> requiredOptions = getRequiredOptions(Pyrite.of(turfSet.name() + "_turf"));
					twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, turfSet.turf(), turfSet.grassBlock(), requiredOptions);
					slab(turfSet.slab(), turfSet.turf(), requiredOptions);
					stairs(turfSet.stair(), turfSet.turf(), requiredOptions);
					carpet(turfSet.carpet(), turfSet.turf(), requiredOptions);
				}

				for (BrickSet brickSet : BRICK_SETS) {
					List<String> requiredOptions = getRequiredOptions(Pyrite.of(brickSet.blockID()));
					slab(brickSet.slab(), brickSet.base(), requiredOptions);
					stairs(brickSet.stairs(), brickSet.base().asItem(), requiredOptions);
					wall(brickSet.wall(), brickSet.base().asItem(), requiredOptions);
					var wallGateOptions = new ArrayList<>(requiredOptions);
					wallGateOptions.add("wall_gates");
					wallGate(brickSet.wallGate(), brickSet.base().asItem(), brickSet.wall().asItem(), wallGateOptions);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, brickSet.slab(), brickSet.base(), 2, requiredOptions);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, brickSet.stairs(), brickSet.base(), requiredOptions);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, brickSet.wall(), brickSet.base(), requiredOptions);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, brickSet.wallGate(), brickSet.base(), wallGateOptions);
				}


				for (Block wall : ModLists.getVanillaWalls()) {
					Identifier wallId = wall.properties().blockId().identifier();
					Item wallGate = getItem(Pyrite.of(wallId.getPath()+"_gate"));
					Item base = getItem(wallId.withPath(block -> block.replace("_wall", "").replace("brick", "bricks").replace("tile", "tiles")));
					wallGate(wallGate, base, wall, List.of("wall_gates"));
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wallGate, base, List.of("wall_gates"));
				}

				for (String s : List.of(
						"cobblestone", "cobbled_deepslate", "smooth_stone", "andesite", "diorite", "calcite", "granite", "sandstone", "red_sandstone"
				)) {
					Item base = getItem(Identifier.withDefaultNamespace(s));
					Item bricks = getItem(s + "_bricks");
					List<String> options = List.of(s + "_bricks");
					if (!s.contains("sandstone")) {
						bricksBuilder(RecipeCategory.BUILDING_BLOCKS, bricks, Ingredient.of(base)).unlockedBy(getHasName(base), has(base)).save(configuredOutput(options));
						shapeless(RecipeCategory.BUILDING_BLOCKS, getItem("mossy_"+s+"_bricks")).requires(bricks).requires(Items.MOSS_BLOCK).unlockedBy(getHasName(base), has(base)).save(configuredOutput(options), "mossy_"+s+"_bricks_from_moss");
						shapeless(RecipeCategory.BUILDING_BLOCKS, getItem("mossy_"+s+"_bricks")).requires(bricks).requires(Items.VINE).unlockedBy(getHasName(base), has(base)).save(configuredOutput(options), "mossy_"+s+"_bricks_from_vine");
					}
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, bricks, base, options);
				}
				shapeless(RecipeCategory.BUILDING_BLOCKS, getItem("mossy_deepslate_bricks")).requires(Items.DEEPSLATE_BRICKS).requires(Items.MOSS_BLOCK).unlockedBy(getHasName(Items.DEEPSLATE_BRICKS), has(Items.DEEPSLATE_BRICKS)).save(configuredOutput(List.of()), "mossy_deepslate_bricks_from_moss");
				shapeless(RecipeCategory.BUILDING_BLOCKS, getItem("mossy_deepslate_bricks")).requires(Items.DEEPSLATE_BRICKS).requires(Items.VINE).unlockedBy(getHasName(Items.DEEPSLATE_BRICKS), has(Items.DEEPSLATE_BRICKS)).save(configuredOutput(List.of()), "mossy_deepslate_bricks_from_vine");


				for (Block resourceBlock : ModLists.getVanillaResourceBlocks()) {
					var id = ModHelpers.findVanillaBlockID(resourceBlock);
					var requiredOptions = getRequiredOptions(Pyrite.of(id));
					Item baseBlock = getItemOrVanilla(Pyrite.of(id));
					var cutBlockId = "cut_%s".formatted(id.replace("_block", ""));
					registries.get(ResourceKey.create(Registries.ITEM, Pyrite.of(cutBlockId))).map(Holder.Reference::value).ifPresent((cutBlock) -> {
						// cut
						cut(RecipeCategory.BUILDING_BLOCKS, cutBlock, baseBlock, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, cutBlock, baseBlock, 8, requiredOptions);
						// cut slab
						Item slab = getItem(cutBlockId + "_slab");
						slab(slab, cutBlock, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, slab, cutBlock, 2, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, slab, baseBlock, 8, requiredOptions);
						// cut stairs
						Item stairs = getItem(cutBlockId + "_stairs");
						stairs(stairs, cutBlock, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stairs, cutBlock, 1, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stairs, baseBlock, 4, requiredOptions);
						// cut wall
						Item wall = getItem(cutBlockId + "_wall");
						wall(wall, cutBlock, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wall, cutBlock, 1, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wall, baseBlock, 4, requiredOptions);
						// cut wall gate
						Item wallGate = getItem(cutBlockId + "_wall_gate");
						wallGate(wallGate, cutBlock, wall, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wallGate, cutBlock, 1, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wallGate, baseBlock, 4, requiredOptions);
					});
					var smoothBlockId = "smooth_%s".formatted(id.replace("_block", ""));
					registries.get(ResourceKey.create(Registries.ITEM, Pyrite.of(smoothBlockId))).map(Holder.Reference::value).ifPresent((smoothBlock) -> {
						// smooth
						twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, smoothBlock, baseBlock, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, smoothBlock, baseBlock, 8, requiredOptions);
						// smooth slab
						Item slab = getItem(smoothBlockId + "_slab");
						slab(slab, smoothBlock, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, slab, smoothBlock, 2, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, slab, baseBlock, 8, requiredOptions);
						// smooth stairs
						Item stairs = getItem(smoothBlockId + "_stairs");
						stairs(stairs, smoothBlock, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stairs, smoothBlock, 1, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, stairs, baseBlock, 4, requiredOptions);
						// smooth wall
						Item wall = getItem(smoothBlockId + "_wall");
						wall(wall, smoothBlock, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wall, smoothBlock, 1, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wall, baseBlock, 4, requiredOptions);
						// smooth wall gate
						Item wallGate = getItem(smoothBlockId + "_wall_gate");
						wallGate(wallGate, smoothBlock, wall, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wallGate, smoothBlock, 1, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, wallGate, baseBlock, 4, requiredOptions);
					});
					var brickBlockId = "%s_brick".formatted(id.replace("_block", ""));
					registries.get(ResourceKey.create(Registries.ITEM, Pyrite.of(brickBlockId + "s"))).map(Holder.Reference::value).ifPresent((brickBlock) -> {
						// brick
						twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, brickBlock, baseBlock, requiredOptions);
						stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, brickBlock, baseBlock, 8, requiredOptions);
					});
					// button
					var buttonId = "%s_button".formatted(id.replace("_block", ""));
					shapeless(RecipeCategory.REDSTONE, getItem(buttonId)).group("button").requires(baseBlock).requires(ItemTags.BUTTONS).unlockedBy(getHasName(baseBlock), has(baseBlock)).save(configuredOutput(requiredOptions));
					// door
					var doorId = "%s_door".formatted(id.replace("_block", ""));
					getOptionalItem(doorId).ifPresent(door->{
						doorBuilder(door, Ingredient.of(baseBlock)).unlockedBy(getHasName(baseBlock), has(baseBlock)).save(configuredOutput(requiredOptions));
					});
					// trapdoor
					var trapDoorId = "%s_trapdoor".formatted(id.replace("_block", ""));
					getOptionalItem(trapDoorId).ifPresent(trapDoor->{
						trapdoorBuilder(trapDoor, Ingredient.of(baseBlock)).unlockedBy(getHasName(baseBlock), has(baseBlock)).save(configuredOutput(requiredOptions));
					});
					// trapdoor
					var pressurePlateId = "%s_pressure_plate".formatted(id.replace("_block", ""));
					getOptionalItem(pressurePlateId).ifPresent(trapDoor->{
						pressurePlateBuilder(RecipeCategory.REDSTONE, trapDoor, Ingredient.of(baseBlock)).unlockedBy(getHasName(baseBlock), has(baseBlock)).save(configuredOutput(requiredOptions));
					});
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

				Item baseFramedGlass = getItem(Pyrite.of("framed_glass"));
				for (String dye : ModLists.DYES) {
					var dyeTag = TagKey.create(Registries.ITEM, Pyrite.of("c", "dyes/"+dye));
					Ingredient dyeIngredient = ingredientOf(dyeTag);
					List<String> requiredOptions = new ArrayList<>();
					if (ModLists.PYRITE_DYES.contains(dye)) {
						requiredOptions.add("oddities");
					}
					Identifier torchId = Pyrite.of(dye + "_torch");
					shapeless(RecipeCategory.DECORATIONS, getItem(torchId)).group("torch").requires(dyeTag).requires(Items.TORCH).unlockedBy(getItemName(Items.TORCH), has(Items.TORCH)).save(configuredOutput(requiredOptions, "torches"));
					Identifier torchLeverId = Pyrite.of(dye + "_torch_lever");
					shapeless(RecipeCategory.REDSTONE, getItem(torchLeverId)).group("torch_lever").requires(getItem(torchId)).requires(Items.LEVER).unlockedBy(getItemName(Items.TORCH), has(Items.TORCH)).save(configuredOutput(requiredOptions, "torch_levers", "torches"));
					// wool
					var wool = getItemOrVanilla((dye + "_wool"));
					var woolOptions = new ArrayList<>(requiredOptions);
					woolOptions.add("wool_stairs_and_slabs");
					slab(getItem(Pyrite.of(dye+"_wool_slab")), wool, woolOptions);
					stairs(getItem(Pyrite.of(dye+"_wool_stairs")), wool, woolOptions);
					// concrete
					var concrete = getItemOrVanilla(dye + "_concrete");
					var concreteOptions = new ArrayList<>(requiredOptions);
					concreteOptions.add("concrete_stairs_and_slabs");
					Item concreteSlab = getItem(Pyrite.of(dye + "_concrete_slab"));
					slab(concreteSlab, concrete, concreteOptions);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, concreteSlab, concrete, 2, concreteOptions);
					Item concreteStairs = getItem(Pyrite.of(dye + "_concrete_stairs"));
					stairs(concreteStairs, concrete, concreteOptions);
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, concreteStairs, concrete, 1, concreteOptions);
					// framed glass
					var framedGlassOptions = new ArrayList<>(requiredOptions);
					framedGlassOptions.add("framed_glass");
					Item dyedFramedGlass = getItem(Pyrite.of(dye + "_framed_glass"));
					coloredBaseBlockFromBaseBlockAndDye(dyedFramedGlass, dyeIngredient, Ingredient.of(baseFramedGlass), framedGlassOptions);
					stainedGlassPaneFromStainedGlass(getItem(dye+"_framed_glass_pane"), dyedFramedGlass, framedGlassOptions);
					// terracotta bricks
					var terracottaOptions = new ArrayList<>(requiredOptions);
					terracottaOptions.add("terracotta_bricks");
					Item terracotta = getItemOrVanilla(dye + "_terracotta");
					Item terracottaBricks = getItem(dye + "_terracotta_bricks");
					coloredBaseBlockFromBaseBlockAndDye(terracottaBricks, dyeIngredient, Ingredient.of(getItem("terracotta_bricks")), terracottaOptions);
					bricksBuilder(RecipeCategory.BUILDING_BLOCKS, terracottaBricks, Ingredient.of(terracotta)).unlockedBy(getHasName(terracotta), has(terracotta)).save(configuredOutput(terracottaOptions), dye+"_terracotta_bricks_from_terracotta");
					stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, terracottaBricks, terracotta, terracottaOptions);
					//TODO dyed bricks
					//TODO dyed lamps
					//TODO dyed torches
					//TODO dyed shelves
					//TODO dyed chests
				}
				// terracotta
				Item terracotta = getItemOrVanilla("terracotta");
				Item terracottaBricks = getItem("terracotta_bricks");
				bricksBuilder(RecipeCategory.BUILDING_BLOCKS, terracottaBricks, Ingredient.of(terracotta)).unlockedBy(getHasName(terracotta), has(terracotta)).save(configuredOutput(List.of("terracotta_bricks")));
				stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, terracottaBricks, terracotta, List.of("terracotta_bricks"));
				// torch lever
				shapeless(RecipeCategory.REDSTONE, getItem(Pyrite.of("torch_lever"))).group("torch_lever").requires(Items.TORCH).requires(Items.LEVER).unlockedBy(getItemName(Items.TORCH), has(Items.TORCH)).save(configuredOutput(List.of("torch_levers")));
				// framed glass
				shaped(RecipeCategory.BUILDING_BLOCKS, baseFramedGlass)
						.group("framed_glass")
						.pattern("X#X")
						.pattern("#X#")
						.pattern("X#X")
						.define('#', Items.GLASS)
						.define('X', Items.IRON_NUGGET)
						.unlockedBy(getItemName(Items.IRON_INGOT), has(Items.IRON_INGOT))
						.save(configuredOutput(List.of("framed_glass")));

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
						var ladderOptions = new ArrayList<>(requiredOptions);
						ladderOptions.add("ladders");
						Item planks = getItemOrVanilla(blockId.withPath(p -> p.replace("ladder", "planks")));
						this.shaped(RecipeCategory.DECORATIONS, entry.asItem(), 3)
								.group("ladder")
								.define('#', planks)
								.define('S', ConventionalItemTags.WOODEN_RODS)
								.pattern("S S")
								.pattern("S#S")
								.pattern("S S")
								.unlockedBy(getItemName(planks), has(planks))
								.save(configuredOutput(ladderOptions));
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
				}
			}

            private Optional<Item> getOptionalItem(String doorId) {
                return registries.get(ResourceKey.create(Registries.ITEM, Pyrite.of(doorId))).map(Holder::value);
            }

			public void carpet(final ItemLike result, final ItemLike sourceItem, List<String> requiredOptions) {
				this.shaped(RecipeCategory.DECORATIONS, result, 3)
						.define('#', sourceItem)
						.pattern("##")
						.group("carpet")
						.unlockedBy(getHasName(sourceItem), this.has(sourceItem))
						.save(configuredOutput(requiredOptions));
			}

			public void slab(final ItemLike result, final ItemLike base, List<String> requiredOptions) {
				this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, result, Ingredient.of(base)).unlockedBy(getHasName(base), this.has(base)).unlockedBy(getItemName(base), has(base)).save(configuredOutput(requiredOptions));
			}

			public void stairs(final ItemLike result, final ItemLike base, List<String> requiredOptions) {
				this.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4).define('#', base).pattern("#  ").pattern("## ").pattern("###").unlockedBy(getItemName(base), has(base)).save(configuredOutput(requiredOptions));
			}

			public void wall(final ItemLike result, final ItemLike base, List<String> requiredOptions) {
				this.wallBuilder(RecipeCategory.BUILDING_BLOCKS, result, Ingredient.of(base)).unlockedBy(getHasName(base), this.has(base)).save(configuredOutput(requiredOptions));
			}

			public void wallGate(final ItemLike result, final ItemLike base, final ItemLike wall, List<String> requiredOptions) {
				this.shaped(RecipeCategory.REDSTONE, result)
						.define('#', wall)
						.define('W', base)
						.pattern("#W#")
						.pattern("#W#").unlockedBy(getItemName(base), has(base)).save(configuredOutput(requiredOptions));
			}

			public void shelf(final ItemLike result, final ItemLike planks, List<String> requiredOptions) {
				this.shaped(RecipeCategory.DECORATIONS, result, 2)
						.define('#', planks)
						.pattern("###")
						.pattern("   ")
						.pattern("###")
						.group("shelf")
						.unlockedBy(getHasName(planks), this.has(planks))
						.save(configuredOutput(requiredOptions));
			}

			public void chest(final ItemLike result, final ItemLike planks, List<String> requiredOptions) {
				this.shaped(RecipeCategory.DECORATIONS, result, 1)
						.define('#', planks)
						.pattern("###")
						.pattern("# #")
						.pattern("###")
						.group("chest")
						.unlockedBy(getHasName(planks), this.has(planks))
						.save(withConditions(configuredOutput(requiredOptions), new AllModsLoadedResourceCondition(List.of("lolmcv"))));
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

			public void stainedGlassPaneFromStainedGlass(final ItemLike result, final ItemLike stainedGlass, List<String> requiredOptions) {
				this.shaped(RecipeCategory.DECORATIONS, result, 16)
						.define('#', stainedGlass)
						.pattern("###")
						.pattern("###")
						.group("stained_glass_pane")
						.unlockedBy("has_glass", this.has(stainedGlass))
						.save(configuredOutput(requiredOptions));
			}


			private Item getItem(Identifier id) {
                return registries.getOrThrow(ResourceKey.create(Registries.ITEM, id)).value();
            }

			private Item getItem(String id) {
				return registries.getOrThrow(ResourceKey.create(Registries.ITEM, Pyrite.of(id))).value();
			}

			private Item getItemOrVanilla(String id) {
				ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Pyrite.of(id));
				if (registries.get(key).isPresent()) {
					return registries.getOrThrow(key).value();
				} else {
					return registries.getOrThrow(ResourceKey.create(Registries.ITEM, Identifier.withDefaultNamespace(id))).value();
				}
			}

			private Item getItemOrVanilla(Identifier id) {
				return getItemOrVanilla(id.getPath());
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
				List<String> requiredOptions = getRequiredOptions(id);
				return withConditions(configuredOutput(requiredOptions));
			}

			private RecipeOutput configuredOutput(List<String> requiredOptions, String... additionalOptionsArray) {
				var options = new ArrayList<>(requiredOptions);
				options.addAll(Arrays.stream(additionalOptionsArray).toList());
				if (options.isEmpty()) return output;
				return withConditions(output, PyriteResourceConditions.config(options));
			}

			public final void sign(final ItemLike result, final Item planks, List<String> requiredOptions) {
				this.shaped(RecipeCategory.DECORATIONS, result, 3).group("sign").define('#', planks).define('X', Items.STICK).pattern("###").pattern("###").pattern(" X ").unlockedBy("has_planks", this.has(planks)).save(configuredOutput(requiredOptions));
			}

			public void hangingSign(final ItemLike result, final Item planks, List<String> requiredOptions) {
				this.shaped(RecipeCategory.DECORATIONS, result, 6).group("hanging_sign").define('#', planks).define('X', Items.IRON_CHAIN).pattern("X X").pattern("###").pattern("###").unlockedBy("has_stripped_logs", this.has(planks)).save(configuredOutput(requiredOptions));
			}

			public void twoByTwoPacker(final RecipeCategory category, final ItemLike result, final ItemLike ingredient, List<String> requiredOptions) {
				twoByTwoPacker(category, result, ingredient, 1, requiredOptions);
			}

			public void twoByTwoPacker(final RecipeCategory category, final ItemLike result, final ItemLike ingredient, int count, List<String> requiredOptions) {
				this.shaped(category, result, count)
						.define('#', ingredient)
						.pattern("##")
						.pattern("##")
						.unlockedBy(getHasName(ingredient), this.has(ingredient))
						.save(configuredOutput(requiredOptions));
			}

			public void stonecutterResultFromBase(final RecipeCategory category, final ItemLike result, final ItemLike base, List<String> requiredOptions) {
				this.stonecutterResultFromBase(category, result, base, 1, requiredOptions);
			}

			public void stonecutterResultFromBase(final RecipeCategory category, final ItemLike result, final ItemLike base, final int count, List<String> requiredOptions) {
				SingleItemRecipeBuilder.stonecutting(Ingredient.of(base), category, result, count)
						.unlockedBy(getHasName(base), this.has(base))
						.save(configuredOutput(requiredOptions), getConversionRecipeName(result, base) + "_stonecutting");
			}

			public void cut(final RecipeCategory category, final ItemLike result, final ItemLike base, List<String> requiredOptions) {
				this.cutBuilder(category, result, Ingredient.of(base)).unlockedBy(getHasName(base), this.has(base)).save(configuredOutput(requiredOptions));
			}

			public void generateRecipes(final BlockFamily family) {
				family.getVariants().forEach((variant, result) -> {
					if (family.shouldGenerateCraftingRecipe()) {
						ItemLike base = this.getBaseBlockForCrafting(family, variant);
                        switch (variant) {
							case SLAB -> {
								slab(result, base, getRequiredOptions(result.properties().blockId().identifier()));
							}
							default -> {
								if (result == null) return;

								this.generateCraftingRecipe(family, variant, result, base, getRequiredOptions(base.asItem().builtInRegistryHolder().key().identifier()));
								if (variant == BlockFamily.Variant.CRACKED) {
									this.smeltingResultFromBase(result, base);
								}
							}
                        }
					}

					if (family.shouldGenerateStonecutterRecipe()) {
						Block base = family.getBaseBlock();
						this.generateStonecutterRecipe(family, variant, base);
					}
				});
			}

			public final void generateCraftingRecipe(final BlockFamily family, final BlockFamily.Variant variant, final Block result, final ItemLike base, List<String> requiredOptions) {
				RecipeProvider.FamilyCraftingRecipeProvider recipeFunction = SHAPE_BUILDERS.get(variant);
				if (recipeFunction != null) {
					RecipeBuilder builder = recipeFunction.create(this, result, base);
					family.getRecipeGroupPrefix().ifPresent(prefix -> builder.group(prefix + (variant == BlockFamily.Variant.CUT ? "" : "_" + variant.getRecipeGroup())));
					builder.unlockedBy((String)family.getRecipeUnlockedBy().orElseGet(() -> getHasName(base)), this.has(base));
					builder.save(configuredOutput(requiredOptions));
				}
			}
		};
	}

	@Override
	public String getName() {
		return "Recipes";
	}
}
//?}