//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.entries.BlockEntry;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.fabric.datagen.PyriteModelTemplates;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.registry.PyriteItemGroups;
import cc.cassian.pyrite.util.ModHelpers;
import cc.cassian.pyrite.util.ModLists;
import cc.cassian.pyrite.util.sets.BrickSet;
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
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NullMarked;

import static cc.cassian.pyrite.fabric.datagen.PyriteDataGeneratorUtil.*;
import static cc.cassian.pyrite.registry.BlockCreator.BRICK_SETS;
import static cc.cassian.pyrite.registry.BlockCreator.RESOURCE_BLOCK_SETS;
import static cc.cassian.pyrite.util.ModHelpers.getBlock;
import static cc.cassian.pyrite.util.ModHelpers.getBlockEntry;
import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

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

        for (Block wall : ModLists.VANILLA_WALLS) {
            Identifier wallId = wall.properties().blockId().identifier();
            Identifier id = Pyrite.of(wallId.getPath() + "_gate");
            Block wallGate = getBlock(id);
            Block base = getBlock(wallId.withPath(block -> block.replace("_wall", "").replace("brick", "bricks").replace("tile", "tiles")));
            wallGate(wallGate, base);
        }

        for (ResourceBlockSet set : getResourceBlockSets()) {
            wallGate(set.cutBlocks().wallGate().get(), set.cutBlocks().block().get());
            wallGate(set.smoothBlocks().wallGate().get(), set.smoothBlocks().block().get());
        }

        for (BrickSet set : BRICK_SETS) {
            wallGate(set.wallGate().get(), set.base().get());
        }
    }

    public void wallGate(final Block block, Block texture) {
        TextureMapping mapping = getTextureMapping(texture);
        var open = plainVariant(PyriteModelTemplates.WALL_GATE_OPEN.create(block, mapping, blockModelGenerators.modelOutput));
        var closed = plainVariant(PyriteModelTemplates.WALL_GATE_CLOSED.create(block, mapping, blockModelGenerators.modelOutput));
        var openWall = plainVariant(PyriteModelTemplates.WALL_GATE_WALL_OPEN.create(block, mapping, blockModelGenerators.modelOutput));
        var closedWall = plainVariant(PyriteModelTemplates.WALL_GATE_WALL_CLOSED.create(block, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate(block, open, closed, openWall, closedWall, false));
    }

    public void pressurePlate(final Block block, final Block texture) {
        TextureMapping mapping = getTextureMapping(texture);
        var off = plainVariant(ModelTemplates.PRESSURE_PLATE_UP.create(block, mapping, blockModelGenerators.modelOutput));
        var on = plainVariant(ModelTemplates.PRESSURE_PLATE_DOWN.create(block, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(block, off, on));
    }

    private void button(final Block block, final Block texture) {
        TextureMapping mapping = getTextureMapping(texture);
        var normal = plainVariant(ModelTemplates.BUTTON.create(block, mapping, blockModelGenerators.modelOutput));
        var pressed = plainVariant(ModelTemplates.BUTTON_PRESSED.create(block, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton(block, normal, pressed));
        Identifier inventory = ModelTemplates.BUTTON_INVENTORY.create(block, mapping, blockModelGenerators.modelOutput);
        blockModelGenerators.registerSimpleItemModel(block, inventory);
    }

    private void sign(WoodSet woodSet) {
		Block sign = woodSet.sign().value();
        Block wallSign = woodSet.wallSign().get();
        TextureMapping mapping = getTextureMapping(sign, woodSet.planks().get());
        //? if >26.1 {
        /*MultiVariant standingRot0 = plainVariant(ModelTemplates.SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(sign, "_rot_0"), mapping, blockModelGenerators.modelOutput));
        MultiVariant standingRot1 = plainVariant(ModelTemplates.SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(sign, "_rot_1"), mapping, blockModelGenerators.modelOutput));
        MultiVariant standingRot2 = plainVariant(ModelTemplates.SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(sign, "_rot_2"), mapping, blockModelGenerators.modelOutput));
        MultiVariant standingRot3 = plainVariant(ModelTemplates.SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(sign, "_rot_3"), mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSign(sign, standingRot0, standingRot1, standingRot2, standingRot3));
        MultiVariant wallModel = plainVariant(ModelTemplates.WALL_SIGN.create(wallSign, mapping, blockModelGenerators.modelOutput));
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
        TextureMapping mapping = getTextureMapping(hangingSign, woodSet.planks().get());
        //? if >26.1 {
        /*blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createHangingSign(hangingSign, plainVariant(ModelTemplates.HANGING_SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_0"), mapping, blockModelGenerators.modelOutput)), plainVariant(ModelTemplates.HANGING_SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_1"), mapping, blockModelGenerators.modelOutput)), plainVariant(ModelTemplates.HANGING_SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_2"), mapping, blockModelGenerators.modelOutput)), plainVariant(ModelTemplates.HANGING_SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_3"), mapping, blockModelGenerators.modelOutput)), plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_0"), mapping, blockModelGenerators.modelOutput)), plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_1"), mapping, blockModelGenerators.modelOutput)), plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_2"), mapping, blockModelGenerators.modelOutput)), plainVariant(ModelTemplates.ATTACHED_HANGING_SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_3"), mapping, blockModelGenerators.modelOutput))));
        MultiVariant wallModel = plainVariant(ModelTemplates.WALL_HANGING_SIGN.create(wallSign, mapping, blockModelGenerators.modelOutput));
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(wallSign, wallModel).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT));
        blockModelGenerators.registerSimpleFlatItemModel(hangingSign.asItem());
        *///?} else {
        blockModelGenerators.createHangingSign(woodSet.planks().get(), woodSet.hangingSign().get(), woodSet.hangingWallSign().get());
        //?}
    }

    public void stairs(final BlockEntry<Block> stairs, BlockEntry<Block> baseBlock) {
        var inner = plainVariant(this.getOrCreateModel(ModelTemplates.STAIRS_INNER, stairs.get(), baseBlock.get()));
        var straight = this.getOrCreateModel(ModelTemplates.STAIRS_STRAIGHT, stairs.get(), baseBlock.get());
        var outer = plainVariant(this.getOrCreateModel(ModelTemplates.STAIRS_OUTER, stairs.get(), baseBlock.get()));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs.get(), inner, plainVariant(straight), outer));
        blockModelGenerators.registerSimpleItemModel(stairs.get(), straight);
    }

    public void slab(final BlockEntry<Block> slab, BlockEntry<Block> baseBlock) {
        var bottom = this.getOrCreateModel(ModelTemplates.SLAB_BOTTOM, slab.get(), baseBlock.get());
        var top = plainVariant(this.getOrCreateModel(ModelTemplates.SLAB_TOP, slab.get(), baseBlock.get()));
        blockModelGenerators.blockStateOutput
                .accept(BlockModelGenerators.createSlab(slab.get(), plainVariant(bottom), top, BlockModelGenerators.variant(new Variant(baseBlock.getId().withPrefix("block/")))));
        blockModelGenerators.registerSimpleItemModel(slab.get(), bottom);
    }

    private Identifier getOrCreateModel(final ModelTemplate modelTemplate, final Block block, Block baseBlock) {
        return modelTemplate.create(block, getMapping(baseBlock), blockModelGenerators.modelOutput);
    }

    private TextureMapping getMapping(Block baseBlock) {
        return TexturedModel.CUBE.get(baseBlock).getMapping();
    }

    private static TextureMapping getTextureMapping(Block texture) {
        return getTextureMapping(texture, texture);
    }

    private static TextureMapping getTextureMapping(Block all, Block particle) {
        return new TextureMapping().put(TextureSlot.ALL, getBlockTexture(all)).put(TextureSlot.PARTICLE, getBlockTexture(particle));
    }

    public static Material getBlockTexture(final Block block) {
        Identifier id = BuiltInRegistries.BLOCK.getKey(block);
        if (id.equals(Identifier.withDefaultNamespace("smooth_quartz"))) {
            id = id.withPath("quartz_block_bottom");
        }
        if (id.getPath().contains("waxed")) {
            id = id.withPath(p->p.replace("waxed_", ""));
        }
        return new Material(id.withPrefix("block/"));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        for (ItemEntry<Item> boat : PyriteItemGroups.BOATS) {
            itemModelGenerators.generateFlatItem(boat.asItem(), ModelTemplates.FLAT_ITEM);
        }
    }
}
//?}