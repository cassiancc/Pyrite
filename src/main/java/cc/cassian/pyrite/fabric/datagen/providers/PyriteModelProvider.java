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
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.jspecify.annotations.NullMarked;

import java.util.HashMap;
import java.util.Map;

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
            var stairs = getBlock(Pyrite.of(dye+"_wool_stairs"));
            var slab = getBlock(Pyrite.of(dye+"_wool_slab"));
            stairs(stairs, wool);
            slab(slab, wool);
        }
    }

    public void stairs(final Block stairs, Block baseBlock) {
        MultiVariant inner = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModelTemplates.STAIRS_INNER, stairs, baseBlock));
        Identifier straight = this.getOrCreateModel(ModelTemplates.STAIRS_STRAIGHT, stairs, baseBlock);
        MultiVariant outer = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModelTemplates.STAIRS_OUTER, stairs, baseBlock));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, inner, BlockModelGenerators.plainVariant(straight), outer));
        blockModelGenerators.registerSimpleItemModel(stairs, straight);
    }

    public void slab(final Block slab, Block baseBlock) {
        Identifier bottom = this.getOrCreateModel(ModelTemplates.SLAB_BOTTOM, slab, baseBlock);
        MultiVariant top = BlockModelGenerators.plainVariant(this.getOrCreateModel(ModelTemplates.SLAB_TOP, slab, baseBlock));
        blockModelGenerators.blockStateOutput
                .accept(BlockModelGenerators.createSlab(slab, BlockModelGenerators.plainVariant(bottom), top, BlockModelGenerators.variant(new Variant(baseBlock.properties().blockIdOrThrow().identifier()))));
        blockModelGenerators.registerSimpleItemModel(slab, bottom);
    }

    private Identifier getOrCreateModel(final ModelTemplate modelTemplate, final Block block, Block baseBlock) {
        return modelTemplate.create(block, getMapping(baseBlock), blockModelGenerators.modelOutput);
    }

    private TextureMapping getMapping(Block baseBlock) {
        return TexturedModel.CUBE.get(baseBlock).getMapping();
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
