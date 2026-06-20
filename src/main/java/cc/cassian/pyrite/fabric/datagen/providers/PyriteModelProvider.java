//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.util.ModHelpers;
import cc.cassian.pyrite.util.ModLists;
import cc.cassian.pyrite.util.sets.ResourceBlockSet;
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
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NullMarked;

import java.util.Map;

import static cc.cassian.pyrite.registry.BlockCreator.RESOURCE_BLOCK_SETS;
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

        for (WoodSet woodSet : BlockCreator.WOOD_SETS) {
            // sign
            sign(woodSet);
            // hanging sign
            hangingSign(woodSet);
        }

        for (ResourceBlockSet set : RESOURCE_BLOCK_SETS) {
            var id = ModHelpers.findVanillaBlockID(set.block());
            if (id.contains("copper")) {
                button(HoneycombItem.getWaxed(set.button().defaultBlockState()).get().getBlock(), set.block());
                pressurePlate(HoneycombItem.getWaxed(set.pressurePlate().defaultBlockState()).get().getBlock(), set.block());
            }
        }
    }

    public void pressurePlate(final Block block, final Block texture) {
        TextureMapping mapping = new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(texture)).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(texture));
        MultiVariant off = BlockModelGenerators.plainVariant(ModelTemplates.PRESSURE_PLATE_UP.create(block, mapping, blockModelGenerators.modelOutput));
        MultiVariant on = BlockModelGenerators.plainVariant(ModelTemplates.PRESSURE_PLATE_DOWN.create(block, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(block, off, on));
    }
    
    private void button(final Block block, final Block texture) {
        TextureMapping mapping = new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(texture)).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(texture));
        MultiVariant normal = BlockModelGenerators.plainVariant(ModelTemplates.BUTTON.create(block, mapping, blockModelGenerators.modelOutput));
        MultiVariant pressed = BlockModelGenerators.plainVariant(ModelTemplates.BUTTON_PRESSED.create(block, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton(block, normal, pressed));
        Identifier inventory = ModelTemplates.BUTTON_INVENTORY.create(block, mapping, blockModelGenerators.modelOutput);
        blockModelGenerators.registerSimpleItemModel(block, inventory);
    }

    private void sign(WoodSet woodSet) {
		Block sign = woodSet.sign().value();
        Block wallSign = woodSet.wallSign().get();
        TextureMapping mapping = new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(sign)).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(woodSet.planks().get()));
        //? if >26.1 {
        /*MultiVariant standingRot0 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(sign, "_rot_0"), mapping, blockModelGenerators.modelOutput));
        MultiVariant standingRot1 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(sign, "_rot_1"), mapping, blockModelGenerators.modelOutput));
        MultiVariant standingRot2 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(sign, "_rot_2"), mapping, blockModelGenerators.modelOutput));
        MultiVariant standingRot3 = BlockModelGenerators.plainVariant(ModelTemplates.SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(sign, "_rot_3"), mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSign(sign, standingRot0, standingRot1, standingRot2, standingRot3));
        MultiVariant wallModel = BlockModelGenerators.plainVariant(ModelTemplates.WALL_SIGN.create(wallSign, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(wallSign, wallModel).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT));
        *///?} else {
        
        MultiVariant model = BlockModelGenerators.plainVariant(ModelTemplates.PARTICLE_ONLY.create(sign, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(sign, model));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSign, model));
        //?}
        blockModelGenerators.registerSimpleFlatItemModel(sign.asItem());
    }

    private void hangingSign(WoodSet woodSet) {
        Block hangingSign = woodSet.hangingSign().value();
        Block wallSign = woodSet.hangingWallSign().get();
        TextureMapping mapping = new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(hangingSign)).put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(woodSet.planks().get()));
        //? if >26.1 {
        /*blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createHangingSign(hangingSign, BlockModelGenerators.plainVariant(ModelTemplates.HANGING_SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_0"), mapping, blockModelGenerators.modelOutput)), BlockModelGenerators.plainVariant(ModelTemplates.HANGING_SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_1"), mapping, blockModelGenerators.modelOutput)), BlockModelGenerators.plainVariant(ModelTemplates.HANGING_SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_2"), mapping, blockModelGenerators.modelOutput)), BlockModelGenerators.plainVariant(ModelTemplates.HANGING_SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_3"), mapping, blockModelGenerators.modelOutput)), BlockModelGenerators.plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_0"), mapping, blockModelGenerators.modelOutput)), BlockModelGenerators.plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_1"), mapping, blockModelGenerators.modelOutput)), BlockModelGenerators.plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_2"), mapping, blockModelGenerators.modelOutput)), BlockModelGenerators.plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_3"), mapping, blockModelGenerators.modelOutput))));
        MultiVariant wallModel = BlockModelGenerators.plainVariant(ModelTemplates.WALL_HANGING_SIGN.create(wallSign, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(wallSign, wallModel).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT));
        blockModelGenerators.registerSimpleFlatItemModel(hangingSign.asItem());
        *///?} else {
        blockModelGenerators.createHangingSign(woodSet.planks().get(), woodSet.hangingSign().get(), woodSet.hangingWallSign().get());
        //?}
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
//?}