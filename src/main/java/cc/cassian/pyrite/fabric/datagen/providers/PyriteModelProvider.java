package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.functions.ModLists;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;

@NullMarked
public class PyriteModelProvider extends FabricModelProvider {
    public PyriteModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        for (String dye : ModLists.DYES) {
            var wool = getBlockOrVanilla(Pyrite.of(dye + "_wool"));
            blockModelGenerators.family(wool).stairs(getBlock(Pyrite.of(dye+"_wool_stairs"))).slab(getBlock(Pyrite.of(dye+"_wool_slab")));
        }
    }

    private Block getBlock(Identifier id) {
        return BuiltInRegistries.BLOCK.getOrThrow(ResourceKey.create(Registries.BLOCK, id)).value();
    }

    private Block getBlockOrVanilla(Identifier id) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, id);
        if (BuiltInRegistries.BLOCK.get(key).isPresent()) {
            return BuiltInRegistries.BLOCK.getOrThrow(key).value();
        } else {
            return BuiltInRegistries.BLOCK.getOrThrow(ResourceKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(id.getPath()))).value();
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        for (ItemEntry<Item> boat : PyriteItemGroups.BOATS) {
            itemModelGenerators.generateFlatItem(boat.asItem(), ModelTemplates.FLAT_ITEM);
        }
    }
}
