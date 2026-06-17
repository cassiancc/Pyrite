package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.util.ModLists;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NullMarked;

import static cc.cassian.pyrite.util.ModHelpers.getBlockEntry;

@NullMarked
public class PyriteModelProvider extends FabricModelProvider {
    @SuppressWarnings("all")
    private BlockModelGenerators blockModelGenerators;

    public PyriteModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        this.blockModelGenerators = blockModelGenerators;
        for (String dye : ModLists.DYES) {
            var wool = getBlockOrVanilla(Pyrite.of(dye + "_wool"));
			BlockEntry<Block> stairs = getBlockEntry(Pyrite.of(dye+"_wool_stairs"));
			BlockEntry<Block> slab = getBlockEntry(Pyrite.of(dye+"_wool_slab"));
            stairs(stairs, wool);
            slab(slab, wool);
        }
    }

    public void stairs(final BlockEntry<Block> stairs, BlockEntry<Block> baseBlock) {
        MultiVariant inner = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModelTemplates.STAIRS_INNER, stairs.get(), baseBlock.get()));
        Identifier straight = this.getOrCreateModel(ModelTemplates.STAIRS_STRAIGHT, stairs.get(), baseBlock.get());
        MultiVariant outer = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModelTemplates.STAIRS_OUTER, stairs.get(), baseBlock.get()));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs.get(), inner, BlockModelGenerators.plainVariant(straight), outer));
        blockModelGenerators.registerSimpleItemModel(stairs.get(), straight);
    }

    public void slab(final BlockEntry<Block> slab, BlockEntry<Block> baseBlock) {
        Identifier bottom = this.getOrCreateModel(ModelTemplates.SLAB_BOTTOM, slab.get(), baseBlock.get());
        MultiVariant top = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModelTemplates.SLAB_TOP, slab.get(), baseBlock.get()));
        blockModelGenerators.blockStateOutput
                .accept(BlockModelGenerators.createSlab(slab.get(), BlockModelGenerators.plainVariant(bottom), top, BlockModelGenerators.variant(new Variant(baseBlock.getId().withPrefix("block/")))));
        blockModelGenerators.registerSimpleItemModel(slab.get(), bottom);
    }

    private Identifier getOrCreateModel(final ModelTemplate modelTemplate, final Block block, Block baseBlock) {
        return modelTemplate.create(block, getMapping(baseBlock), blockModelGenerators.modelOutput);
    }

    private TextureMapping getMapping(Block baseBlock) {
        return TexturedModel.CUBE.get(baseBlock).getMapping();
    }

    private BlockEntry<Block> getBlockOrVanilla(Identifier id) {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, id);
        if (BuiltInRegistries.BLOCK.get(key).isPresent()) {
            return new BlockEntry<>(id, BuiltInRegistries.BLOCK.getOrThrow(key).value());
        } else {
            Identifier location = Identifier.withDefaultNamespace(id.getPath());
            return new BlockEntry<>(location, BuiltInRegistries.BLOCK.getOrThrow(ResourceKey.create(Registries.BLOCK, location)).value());
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        for (ItemEntry<Item> boat : PyriteItemGroups.BOATS) {
            itemModelGenerators.generateFlatItem(boat.asItem(), ModelTemplates.FLAT_ITEM);
        }
    }
}
