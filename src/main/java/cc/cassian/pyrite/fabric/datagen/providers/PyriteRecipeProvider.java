//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.core.PyriteBlockItemTags;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

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