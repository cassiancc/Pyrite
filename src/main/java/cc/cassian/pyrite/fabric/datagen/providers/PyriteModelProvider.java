package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.blocks.PyriteStandingSignBlock;
import cc.cassian.pyrite.blocks.PyriteWallSignBlock;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.util.ModLists;
import cc.cassian.pyrite.util.sets.WoodSet;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HangingSignBlock;
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

        //? if >26.1 {

        /*for (WoodSet woodSet : BlockCreator.WOOD_SETS) {
            var sign = woodSet.sign().get();
            TextureMapping mapping = (new TextureMapping()).put(TextureSlot.ALL, TextureMapping.getBlockTexture(sign)).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(woodSet.planks().get()));
            MultiVariant standingRot0 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(sign, "_rot_0"), mapping, blockModelGenerators.modelOutput));
            MultiVariant standingRot1 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(sign, "_rot_1"), mapping, blockModelGenerators.modelOutput));
            MultiVariant standingRot2 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(sign, "_rot_2"), mapping, blockModelGenerators.modelOutput));
            MultiVariant standingRot3 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(sign, "_rot_3"), mapping, blockModelGenerators.modelOutput));
            blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSign(sign, standingRot0, standingRot1, standingRot2, standingRot3));

        }


        BuiltInRegistries.BLOCK.entrySet().forEach((entry)->{
            var block = entry.getValue();
            var name = entry.getKey().identifier().getPath();
            if (block instanceof PyriteWallSignBlock wallSign) {
                TextureMapping mapping = (new TextureMapping()).put(TextureSlot.ALL, TextureMapping.getBlockTexture(getSign(name))).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(getBaseBlock(name)));
                MultiVariant wallModel = BlockModelGenerators.plainVariant(ModelTemplates.WALL_SIGN.create(wallSign, mapping, blockModelGenerators.modelOutput));
                blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(wallSign, wallModel).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT));
            } else if (block instanceof HangingSignBlock hangingSignBlock) {

            }
        });
        *///?}
    }

    private static Block getBaseBlock(String sign) {
        return BuiltInRegistries.BLOCK.getValue(Pyrite.of(sign.replace("_sign", "_planks")));
    }

    private static Block getSign(String sign) {
        return BuiltInRegistries.BLOCK.getValue(Pyrite.of(sign.replace("_wall_", "")));
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
