package com.pyrite;
import com.pyrite.blocks.ModGlass;
import com.pyrite.entity.Grebe;
import com.terraformersmc.terraform.wood.block.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.*;
import com.pyrite.blocks.ModStairs;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class Pyrite implements ModInitializer {
    public static final ItemGroup PYRITE = FabricItemGroupBuilder.build(
            new Identifier("pyrite"),
            () -> new ItemStack(Pyrite.PYRITE_ORE)
    );

    public static final EntityType<Grebe> GREBE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("pyrite", "grebe"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, Grebe::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );
    public static final Item GREBE_SPAWN_EGG = new SpawnEggItem(GREBE, 	3551793, 9847342, new Item.Settings().group(PYRITE));

    //CHARRED WOOD
    public static final Block STRIPPED_CHARRED_STEM = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_CRIMSON_STEM));
    public static final Block STRIPPED_CHARRED_HYPHAE = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_CRIMSON_HYPHAE));
    public static final Block CHARRED_STEM = new StrippableLogBlock(() -> STRIPPED_CHARRED_STEM, MapColor.BLACK, FabricBlockSettings.copyOf(Blocks.CRIMSON_STEM));
    public static final Block CHARRED_HYPHAE = new StrippableLogBlock(() -> STRIPPED_CHARRED_HYPHAE, MapColor.BLACK, FabricBlockSettings.copyOf(Blocks.CRIMSON_HYPHAE));


    public static final Block CHARRED_PLANKS = new Block(FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.0f));
    public static final Block CHARRED_STAIRS = new ModStairs(Pyrite.CHARRED_PLANKS.getDefaultState(),FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.0f));

    public static final Block CHARRED_SLAB = new SlabBlock(FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.0f));

    public static final Block CHARRED_TRAPDOOR = new TerraformTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_TRAPDOOR));
    public static final Block CHARRED_DOOR = new TerraformDoorBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_DOOR));
    public static final Block CHARRED_BUTTON = new TerraformButtonBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_BUTTON));
    public static final Block CHARRED_PRESSURE_PLATE = new TerraformPressurePlateBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_PRESSURE_PLATE));
    public static final Block CHARRED_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FENCE_GATE));
    public static final Block CHARRED_FENCE = new FenceBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FENCE));





    //ASTRAL WOOD
    public static final Block STRIPPED_ASTRAL_STEM = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_CRIMSON_STEM));
    public static final Block STRIPPED_ASTRAL_HYPHAE = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_CRIMSON_HYPHAE));
    public static final Block ASTRAL_STEM =  new StrippableLogBlock(() -> STRIPPED_ASTRAL_STEM, MapColor.BLUE, FabricBlockSettings.copyOf(Blocks.WARPED_STEM));
    public static final Block ASTRAL_HYPHAE =  new StrippableLogBlock(() -> STRIPPED_ASTRAL_HYPHAE, MapColor.BLUE, FabricBlockSettings.copyOf(Blocks.CRIMSON_STEM));


    public static final Block ASTRAL_PLANKS = new Block(FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.0f));
    public static final Block ASTRAL_STAIRS = new ModStairs(Pyrite.ASTRAL_PLANKS.getDefaultState(),FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.0f));
    public static final Block ASTRAL_SLAB = new SlabBlock(FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.0f));

    public static final Block ASTRAL_TRAPDOOR = new TerraformTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_TRAPDOOR));
    public static final Block ASTRAL_DOOR = new TerraformDoorBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_DOOR));
    public static final Block ASTRAL_BUTTON = new TerraformButtonBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_BUTTON));
    public static final Block ASTRAL_PRESSURE_PLATE = new TerraformPressurePlateBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_PRESSURE_PLATE));
    public static final Block ASTRAL_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FENCE_GATE));
    public static final Block ASTRAL_FENCE = new FenceBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FENCE));

    public static final Item UMBRAL_CHARCOAL = new Item(new FabricItemSettings().group(Pyrite.PYRITE));
    public static final Block UMBRAL_TORCH = new TorchBlock(FabricBlockSettings.copyOf(Blocks.TORCH), ParticleTypes.SMOKE);
    public static final Block UMBRAL_CAMPFIRE = new CampfireBlock(true, 1, FabricBlockSettings.copyOf(Blocks.CAMPFIRE));
    public static final Block UMBRAL_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN));


    public static final Item ASTRAL_CHARCOAL = new Item(new FabricItemSettings().group(Pyrite.PYRITE));
    public static final Block ASTRAL_TORCH = new TorchBlock(FabricBlockSettings.copyOf(Blocks.TORCH), ParticleTypes.SMOKE);
    public static final Block ASTRAL_CAMPFIRE = new CampfireBlock(true, 1, FabricBlockSettings.copyOf(Blocks.CAMPFIRE));
    public static final Block ASTRAL_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN));


    public static final Item DAL_CHARCOAL = new Item(new FabricItemSettings().group(Pyrite.PYRITE));
    public static final Block DAL_CAMPFIRE = new CampfireBlock(true, 1, FabricBlockSettings.copyOf(Blocks.CAMPFIRE));
    public static final Block DAL_TORCH = new TorchBlock(FabricBlockSettings.copyOf(Blocks.TORCH), ParticleTypes.SMOKE);
    public static final Block DAL_LANTERN = new LanternBlock(FabricBlockSettings.copyOf(Blocks.LANTERN));

    //UMBRAL WOOD
    public static final Block STRIPPED_UMBRAL_STEM = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_CRIMSON_STEM));
    public static final Block STRIPPED_UMBRAL_HYPHAE = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_CRIMSON_HYPHAE));
    public static final Block UMBRAL_STEM =  new StrippableLogBlock(() -> STRIPPED_UMBRAL_STEM, MapColor.BROWN, FabricBlockSettings.copyOf(Blocks.CRIMSON_STEM));
    public static final Block UMBRAL_HYPHAE =  new StrippableLogBlock(() -> STRIPPED_UMBRAL_HYPHAE, MapColor.BROWN, FabricBlockSettings.copyOf(Blocks.CRIMSON_STEM));

    public static final Block UMBRAL_PLANKS = new Block(FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.0f));
    public static final Block UMBRAL_STAIRS = new ModStairs(Pyrite.ASTRAL_PLANKS.getDefaultState(),FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.0f));
    public static final Block UMBRAL_SLAB = new SlabBlock(FabricBlockSettings.of(Material.NETHER_WOOD).strength(3.0f));

    public static final Block UMBRAL_DOOR = new TerraformDoorBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_DOOR));
    public static final Block UMBRAL_TRAPDOOR = new TerraformTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_TRAPDOOR));
    public static final Block UMBRAL_BUTTON = new TerraformButtonBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_BUTTON));
    public static final Block UMBRAL_PRESSURE_PLATE = new TerraformPressurePlateBlock(FabricBlockSettings.copyOf(Blocks.CRIMSON_PRESSURE_PLATE));
    public static final Block UMBRAL_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FENCE_GATE));
    public static final Block UMBRAL_FENCE = new FenceBlock(FabricBlockSettings.copyOf(Blocks.WARPED_FENCE));

    //STRIPPED OAK
    public static final Block STRIPPED_ORIGIN_OAK_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG));
    public static final Block STRIPPED_ORIGIN_OAK_WOOD = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD));
    public static final Block ORIGIN_OAK_LOG =  new StrippableLogBlock(() -> STRIPPED_ORIGIN_OAK_LOG, MapColor.BLUE, FabricBlockSettings.copyOf(Blocks.OAK_LOG));
    public static final Block ORIGIN_OAK_WOOD =  new StrippableLogBlock(() -> STRIPPED_ORIGIN_OAK_WOOD, MapColor.BLUE, FabricBlockSettings.copyOf(Blocks.OAK_WOOD));
    public static final Block STRIPPED_OAK_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS));
    public static final Block STRIPPED_OAK_STAIRS = new ModStairs(Pyrite.STRIPPED_OAK_PLANKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.OAK_STAIRS));
    public static final Block STRIPPED_OAK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB));
    public static final Block STRIPPED_OAK_DOOR = new TerraformDoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR));
    public static final Block STRIPPED_OAK_TRAPDOOR = new TerraformTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR));
    public static final Block STRIPPED_OAK_BUTTON = new TerraformButtonBlock(FabricBlockSettings.copyOf(Blocks.OAK_BUTTON));
    public static final Block STRIPPED_OAK_PRESSURE_PLATE = new TerraformPressurePlateBlock(FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE));
    public static final Block STRIPPED_OAK_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE));
    public static final Block STRIPPED_OAK_FENCE = new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE));

    //STRIPPED OAK
    public static final Block STRIPPED_DAL_LOG = new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_DARK_OAK_LOG));
    public static final Block STRIPPED_DAL_WOOD = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD));
    public static final Block DAL_LOG =  new StrippableLogBlock(() -> STRIPPED_DAL_LOG, MapColor.BROWN, FabricBlockSettings.copyOf(Blocks.DARK_OAK_LOG));
    public static final Block DAL_WOOD =  new StrippableLogBlock(() -> STRIPPED_DAL_WOOD, MapColor.GREEN, FabricBlockSettings.copyOf(Blocks.DARK_OAK_WOOD));
    public static final Block DAL_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.DARK_OAK_PLANKS));
    public static final Block DAL_STAIRS = new ModStairs(Pyrite.DAL_PLANKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.DARK_OAK_STAIRS));
    public static final Block DAL_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_SLAB));


    public static final Block DAL_DOOR = new TerraformDoorBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_DOOR));
    public static final Block DAL_TRAPDOOR = new TerraformTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_TRAPDOOR));
    public static final Block DAL_BUTTON = new TerraformButtonBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_BUTTON));
    public static final Block DAL_PRESSURE_PLATE = new TerraformPressurePlateBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_PRESSURE_PLATE));
    public static final Block DAL_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_FENCE_GATE));
    public static final Block DAL_FENCE = new FenceBlock(FabricBlockSettings.copyOf(Blocks.DARK_OAK_FENCE));


    //MANGROVE
    public static final Block STRIPPED_MANGROVE_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_ACACIA_LOG));
    public static final Block STRIPPED_MANGROVE_WOOD = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_ACACIA_WOOD));
    public static final Block MANGROVE_LOG =  new StrippableLogBlock(() -> STRIPPED_MANGROVE_LOG, MapColor.BROWN, FabricBlockSettings.copyOf(Blocks.CRIMSON_STEM));
    public static final Block MANGROVE_WOOD =  new StrippableLogBlock(() -> STRIPPED_MANGROVE_WOOD, MapColor.RED, FabricBlockSettings.copyOf(Blocks.ACACIA_LOG));
    public static final Block MANGROVE_PLANKS = new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_PLANKS));
    public static final Block MANGROVE_STAIRS = new ModStairs(Pyrite.MANGROVE_PLANKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.OAK_STAIRS));
    public static final Block MANGROVE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_SLAB));


    public static final Block MANGROVE_DOOR = new TerraformDoorBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_DOOR));
    public static final Block MANGROVE_TRAPDOOR = new TerraformTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_TRAPDOOR));
    public static final Block MANGROVE_BUTTON = new TerraformButtonBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_BUTTON));
    public static final Block MANGROVE_PRESSURE_PLATE = new TerraformPressurePlateBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_PRESSURE_PLATE));
    public static final Block MANGROVE_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_FENCE_GATE));
    public static final Block MANGROVE_FENCE = new FenceBlock(FabricBlockSettings.copyOf(Blocks.ACACIA_FENCE));



    //FRAMED GLASS
    public static final Block FRAMED_GLASS = new ModGlass();
    public static final Block FRAMED_GLASS_PANE = new PaneBlock(FabricBlockSettings.of(Material.GLASS).nonOpaque().strength(2.0f));

    public static final Block PYRITE_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));


    //CRACKED COBBLESTONE
    public static final Block CRACKED_COBBLESTONE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block CRACKED_COBBLESTONE_STAIRS = new ModStairs(Pyrite.CRACKED_COBBLESTONE.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block CRACKED_COBBLESTONE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block CRACKED_COBBLESTONE_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.COBBLESTONE_WALL));


    //COBBLESTONE BRICKS

    public static final Block COBBLESTONE_BRICKS = new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS));
    public static final Block COBBLESTONE_BRICK_STAIRS = new ModStairs(Pyrite.COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.copyOf(Blocks.STONE_BRICK_STAIRS));
    public static final Block COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_SLAB));
    public static final Block COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.STONE_BRICK_WALL));

    public static final Block MOSSY_COBBLESTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = new ModStairs(Pyrite.MOSSY_COBBLESTONE_BRICKS.getDefaultState(),FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block MOSSY_COBBLESTONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f));
    public static final Block MOSSY_COBBLESTONE_BRICK_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICK_WALL));



    @Override
    //MOD INITIALIZES
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(GREBE, Grebe.createMobAttributes());
        Registry.register(Registry.ITEM, new Identifier("pyrite", "grebe_spawn_egg"), GREBE_SPAWN_EGG);
        //pyrite BLOCKS
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cracked_cobblestone"), CRACKED_COBBLESTONE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cracked_cobblestone_stairs"), CRACKED_COBBLESTONE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cracked_cobblestone_slab"), CRACKED_COBBLESTONE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cracked_cobblestone_wall"), CRACKED_COBBLESTONE_WALL);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "pyrite_ore"), PYRITE_ORE);


        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cobblestone_bricks"), COBBLESTONE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cobblestone_brick_stairs"), COBBLESTONE_BRICK_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cobblestone_brick_slab"), COBBLESTONE_BRICK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "cobblestone_brick_wall"), COBBLESTONE_BRICK_WALL);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mossy_cobblestone_bricks"), MOSSY_COBBLESTONE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mossy_cobblestone_brick_stairs"), MOSSY_COBBLESTONE_BRICK_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mossy_cobblestone_brick_slab"), MOSSY_COBBLESTONE_BRICK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mossy_cobblestone_brick_wall"), MOSSY_COBBLESTONE_BRICK_WALL);


        Registry.register(Registry.BLOCK, new Identifier("pyrite", "framed_glass"), FRAMED_GLASS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "framed_glass_pane"), FRAMED_GLASS_PANE);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_torch"), UMBRAL_TORCH);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_campfire"), UMBRAL_CAMPFIRE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_lantern"), UMBRAL_LANTERN);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_torch"), ASTRAL_TORCH);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_campfire"), ASTRAL_CAMPFIRE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_lantern"), ASTRAL_LANTERN);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_torch"), DAL_TORCH);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_campfire"), DAL_CAMPFIRE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_lantern"), DAL_LANTERN);


        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_stem"), CHARRED_STEM);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_hyphae"), CHARRED_HYPHAE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_charred_stem"), STRIPPED_CHARRED_STEM);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_charred_hyphae"), STRIPPED_CHARRED_HYPHAE);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_planks"), CHARRED_PLANKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_stairs"), CHARRED_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_slab"), CHARRED_SLAB);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_trapdoor"), CHARRED_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_door"), CHARRED_DOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_button"), CHARRED_BUTTON);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_pressure_plate"), CHARRED_PRESSURE_PLATE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_fence"), CHARRED_FENCE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "charred_fence_gate"), CHARRED_FENCE_GATE);



        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_stem"), ASTRAL_STEM);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_hyphae"), ASTRAL_HYPHAE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_astral_stem"), STRIPPED_ASTRAL_STEM);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_astral_hyphae"), STRIPPED_ASTRAL_HYPHAE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_planks"), ASTRAL_PLANKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_stairs"), ASTRAL_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_slab"), ASTRAL_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_door"), ASTRAL_DOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_trapdoor"), ASTRAL_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_button"), ASTRAL_BUTTON);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_pressure_plate"), ASTRAL_PRESSURE_PLATE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_fence"), ASTRAL_FENCE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "astral_fence_gate"), ASTRAL_FENCE_GATE);


        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_stem"), UMBRAL_STEM);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_hyphae"), UMBRAL_HYPHAE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_umbral_stem"), STRIPPED_UMBRAL_STEM);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_umbral_hyphae"), STRIPPED_UMBRAL_HYPHAE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_planks"), UMBRAL_PLANKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_stairs"), UMBRAL_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_slab"), UMBRAL_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_door"), UMBRAL_DOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_trapdoor"), UMBRAL_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_button"), UMBRAL_BUTTON);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_pressure_plate"), UMBRAL_PRESSURE_PLATE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_fence"), UMBRAL_FENCE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "umbral_fence_gate"), UMBRAL_FENCE_GATE);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_log"), DAL_LOG);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_wood"), DAL_WOOD);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_dal_log"), STRIPPED_DAL_LOG);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_dal_wood"), STRIPPED_DAL_WOOD);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_planks"), DAL_PLANKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_stairs"), DAL_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_slab"), DAL_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_door"), DAL_DOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_trapdoor"), DAL_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_button"), DAL_BUTTON);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_pressure_plate"), DAL_PRESSURE_PLATE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_fence"), DAL_FENCE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "dal_fence_gate"), DAL_FENCE_GATE);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_log"), ORIGIN_OAK_LOG);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_wood"), ORIGIN_OAK_WOOD);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_origin_oak_log"), STRIPPED_ORIGIN_OAK_LOG);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_origin_oak_wood"), STRIPPED_ORIGIN_OAK_WOOD);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_planks"), STRIPPED_OAK_PLANKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_stairs"), STRIPPED_OAK_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_slab"), STRIPPED_OAK_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_door"), STRIPPED_OAK_DOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_trapdoor"), STRIPPED_OAK_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_button"), STRIPPED_OAK_BUTTON);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_pressure_plate"), STRIPPED_OAK_PRESSURE_PLATE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_fence"), STRIPPED_OAK_FENCE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "origin_oak_fence_gate"), STRIPPED_OAK_FENCE_GATE);

        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_mangrove_log"), STRIPPED_MANGROVE_LOG);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "stripped_mangrove_wood"), STRIPPED_MANGROVE_WOOD);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_log"), MANGROVE_LOG);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_wood"), MANGROVE_WOOD);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_planks"), MANGROVE_PLANKS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_stairs"), MANGROVE_STAIRS);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_slab"), MANGROVE_SLAB);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_door"), MANGROVE_DOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_trapdoor"), MANGROVE_TRAPDOOR);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_button"), MANGROVE_BUTTON);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_pressure_plate"), MANGROVE_PRESSURE_PLATE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_fence"), MANGROVE_FENCE);
        Registry.register(Registry.BLOCK, new Identifier("pyrite", "mangrove_fence_gate"), MANGROVE_FENCE_GATE);





        //pyrite BLOCKITEMS


        Registry.register(Registry.ITEM, new Identifier("pyrite", "cracked_cobblestone"), new BlockItem(CRACKED_COBBLESTONE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "cracked_cobblestone_stairs"), new BlockItem(CRACKED_COBBLESTONE_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "cracked_cobblestone_slab"), new BlockItem(CRACKED_COBBLESTONE_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "pyrite_ore"), new BlockItem(PYRITE_ORE, new FabricItemSettings().group(Pyrite.PYRITE)));


        Registry.register(Registry.ITEM, new Identifier("pyrite", "cobblestone_bricks"), new BlockItem(COBBLESTONE_BRICKS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "cobblestone_brick_stairs"), new BlockItem(COBBLESTONE_BRICK_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "cobblestone_brick_slab"), new BlockItem(COBBLESTONE_BRICK_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "mossy_cobblestone_bricks"), new BlockItem(MOSSY_COBBLESTONE_BRICKS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mossy_cobblestone_brick_stairs"), new BlockItem(MOSSY_COBBLESTONE_BRICK_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mossy_cobblestone_brick_slab"), new BlockItem(MOSSY_COBBLESTONE_BRICK_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));



        Registry.register(Registry.ITEM, new Identifier("pyrite", "framed_glass"), new BlockItem(FRAMED_GLASS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "framed_glass_pane"), new BlockItem(FRAMED_GLASS_PANE, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_stem"), new BlockItem(CHARRED_STEM, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_stem"), new BlockItem(ASTRAL_STEM, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_stem"), new BlockItem(UMBRAL_STEM, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_log"), new BlockItem(DAL_LOG, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_log"), new BlockItem(MANGROVE_LOG, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_log"), new BlockItem(ORIGIN_OAK_LOG, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_hyphae"), new BlockItem(CHARRED_HYPHAE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_hyphae"), new BlockItem(ASTRAL_HYPHAE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_hyphae"), new BlockItem(UMBRAL_HYPHAE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_wood"), new BlockItem(DAL_WOOD, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_wood"), new BlockItem(MANGROVE_WOOD, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_wood"), new BlockItem(ORIGIN_OAK_WOOD, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_charred_stem"), new BlockItem(STRIPPED_CHARRED_STEM, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_astral_stem"), new BlockItem(STRIPPED_ASTRAL_STEM, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_umbral_stem"), new BlockItem(STRIPPED_UMBRAL_STEM, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_mangrove_log"), new BlockItem(STRIPPED_MANGROVE_LOG, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_dal_log"), new BlockItem(STRIPPED_DAL_LOG, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_origin_oak_log"), new BlockItem(STRIPPED_ORIGIN_OAK_LOG, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_charred_hyphae"), new BlockItem(STRIPPED_CHARRED_HYPHAE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_astral_hyphae"), new BlockItem(STRIPPED_ASTRAL_HYPHAE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_umbral_hyphae"), new BlockItem(STRIPPED_UMBRAL_HYPHAE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_mangrove_wood"), new BlockItem(STRIPPED_MANGROVE_WOOD, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_dal_wood"), new BlockItem(STRIPPED_DAL_WOOD, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "stripped_origin_oak_wood"), new BlockItem(STRIPPED_ORIGIN_OAK_WOOD, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_planks"), new BlockItem(CHARRED_PLANKS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_planks"), new BlockItem(ASTRAL_PLANKS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_planks"), new BlockItem(UMBRAL_PLANKS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_planks"), new BlockItem(DAL_PLANKS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_planks"), new BlockItem(MANGROVE_PLANKS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_planks"), new BlockItem(STRIPPED_OAK_PLANKS, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_stairs"), new BlockItem(CHARRED_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_stairs"), new BlockItem(ASTRAL_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_stairs"), new BlockItem(UMBRAL_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_stairs"), new BlockItem(DAL_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_stairs"), new BlockItem(MANGROVE_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_stairs"), new BlockItem(STRIPPED_OAK_STAIRS, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_slab"), new BlockItem(CHARRED_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_slab"), new BlockItem(ASTRAL_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_slab"), new BlockItem(UMBRAL_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_slab"), new BlockItem(DAL_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_slab"), new BlockItem(MANGROVE_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_slab"), new BlockItem(STRIPPED_OAK_SLAB, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_door"), new BlockItem(CHARRED_DOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_door"), new BlockItem(ASTRAL_DOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_door"), new BlockItem(UMBRAL_DOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_door"), new BlockItem(DAL_DOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_door"), new BlockItem(MANGROVE_DOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_door"), new BlockItem(STRIPPED_OAK_DOOR, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_trapdoor"), new BlockItem(CHARRED_TRAPDOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_trapdoor"), new BlockItem(ASTRAL_TRAPDOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_trapdoor"), new BlockItem(UMBRAL_TRAPDOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_trapdoor"), new BlockItem(DAL_TRAPDOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_trapdoor"), new BlockItem(MANGROVE_TRAPDOOR, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_trapdoor"), new BlockItem(STRIPPED_OAK_TRAPDOOR, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_button"), new BlockItem(CHARRED_BUTTON, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_button"), new BlockItem(ASTRAL_BUTTON, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_button"), new BlockItem(UMBRAL_BUTTON, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_button"), new BlockItem(DAL_BUTTON, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_button"), new BlockItem(MANGROVE_BUTTON, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_button"), new BlockItem(STRIPPED_OAK_BUTTON, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_pressure_plate"), new BlockItem(CHARRED_PRESSURE_PLATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_pressure_plate"), new BlockItem(ASTRAL_PRESSURE_PLATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_pressure_plate"), new BlockItem(UMBRAL_PRESSURE_PLATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_pressure_plate"), new BlockItem(DAL_PRESSURE_PLATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_pressure_plate"), new BlockItem(MANGROVE_PRESSURE_PLATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_pressure_plate"), new BlockItem(STRIPPED_OAK_PRESSURE_PLATE, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_fence"), new BlockItem(CHARRED_FENCE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_fence"), new BlockItem(ASTRAL_FENCE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_fence"), new BlockItem(UMBRAL_FENCE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_fence"), new BlockItem(DAL_FENCE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_fence"), new BlockItem(MANGROVE_FENCE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_fence"), new BlockItem(STRIPPED_OAK_FENCE, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "charred_fence_gate"), new BlockItem(CHARRED_FENCE_GATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_fence_gate"), new BlockItem(ASTRAL_FENCE_GATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_fence_gate"), new BlockItem(UMBRAL_FENCE_GATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_fence_gate"), new BlockItem(DAL_FENCE_GATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "mangrove_fence_gate"), new BlockItem(MANGROVE_FENCE_GATE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "origin_oak_fence_gate"), new BlockItem(STRIPPED_OAK_FENCE_GATE, new FabricItemSettings().group(Pyrite.PYRITE)));


        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_charcoal"), ASTRAL_CHARCOAL);
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_charcoal"), UMBRAL_CHARCOAL);
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_charcoal"), DAL_CHARCOAL);

        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_torch"), new BlockItem(ASTRAL_TORCH, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_torch"), new BlockItem(UMBRAL_TORCH, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_torch"), new BlockItem(DAL_TORCH, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_campfire"), new BlockItem(ASTRAL_CAMPFIRE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_campfire"), new BlockItem(UMBRAL_CAMPFIRE, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_campfire"), new BlockItem(DAL_CAMPFIRE, new FabricItemSettings().group(Pyrite.PYRITE)));

        Registry.register(Registry.ITEM, new Identifier("pyrite", "astral_lantern"), new BlockItem(ASTRAL_LANTERN, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "umbral_lantern"), new BlockItem(UMBRAL_LANTERN, new FabricItemSettings().group(Pyrite.PYRITE)));
        Registry.register(Registry.ITEM, new Identifier("pyrite", "dal_lantern"), new BlockItem(DAL_LANTERN, new FabricItemSettings().group(Pyrite.PYRITE)));




    }
}
