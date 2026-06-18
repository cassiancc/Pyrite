package cc.cassian.pyrite.config;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.DisplayName;

public class ModConfig extends WrappedConfig {

   @Comment("Enable a block set for Andesite Bricks.")
   public boolean andesite_bricks = true;

   @DisplayName("Amethyst Block Set")
   @Comment("Completes the block set for Amethyst.")
   public boolean amethyst = true;

   @DisplayName("Azalea Wood Set")
   @Comment("Enable Pyrite Azalea Trees and their wood set.")
   public boolean azalea = true;

   @DisplayName("Calcite Bricks")
   @Comment("Enable a block set for Calcite Bricks.")
   public boolean calcite_bricks = true;

   @DisplayName("Cobblestone Bricks")
   @Comment("Enable a block set for Cobblestone Bricks.")
   public boolean cobblestone_bricks = true;

   @DisplayName("Cobbled Deepslate Bricks")
   @Comment("Enable a block set for Cobbled Deepslate Bricks.")
   public boolean cobbled_deepslate_bricks = true;

   @DisplayName("Concrete Stairs and Slabs")
   @Comment("Enable Pyrite's Concrete Stairs and Slabs.")
   public boolean concrete_stairs_and_slabs = true;

   @DisplayName("Copper Block Set")
   @Comment("Completes the block set for Copper.")
   public boolean copper = true;

   @DisplayName("Variant Crafting Tables")
   @Comment("Enable variant Crafting Tables for Pyrite dyed wood and vanilla wood types.")
   public boolean crafting_tables = true;

   @DisplayName("Diamond Block Set")
   @Comment("Completes the block set for Diamond.")
   public boolean diamond = true;

   @DisplayName("Diorite Bricks")
   @Comment("Enable a block set for Diorite Bricks.")
   public boolean diorite_bricks = true;

   @DisplayName("Dyed Planks")
   @Comment("Adds dyed planks.")
   public boolean dyed_planks = true;

   @DisplayName("Emerald Block Set")
   @Comment("Completes the block set for Emerald.")
   public boolean emerald = true;

   @DisplayName("Framed Glass")
   @Comment("Adds a decorative block set made of Iron and Glass.")
   public boolean framed_glass = true;

   @DisplayName("Granite Bricks")
   @Comment("Enable a block set for Granite Bricks.")
   public boolean granite_bricks = true;

   @DisplayName("Gold Block Set")
   @Comment("Completes the block set for Gold.")
   public boolean gold = true;

   @DisplayName("Grass Turf")
   @Comment("Adds a block set for Grass Blocks.")
   public boolean grass_turf = true;

   @DisplayName("Iron Block Set")
   @Comment("Completes the block set for Iron.")
   public boolean iron = true;

   @DisplayName("Ladder Variants")
   @Comment("Enable variant Ladders for Pyrite dyed wood.")
   public boolean ladders = true;

   @Comment("Adds glowstone lamps and dyed lamps.")
   public boolean lamps = true;

   @DisplayName("Lapis Block Set")
   @Comment("Completes the block set for Lapis Lazuli.")
   public boolean lapis = true;

   @DisplayName("Mushroom Wood Sets")
   @Comment("Enable Pyrite Tall Mushrooms and their wood set.")
   public boolean mushrooms = true;

   @DisplayName("Mycelium Turf")
   @Comment("Adds a block set for Mycelium.")
   public boolean mycelium_turf = true;

   @DisplayName("Nether Brick Fence Gate")
   @Comment("Adds the missing Nether Brick Fence Gate.")
   public boolean nether_brick_fence_gate = true;

   @DisplayName("Netherite Block Set")
   @Comment("Completes the block set for Netherite.")
   public boolean netherite = true;

   @Comment("Enable the Oddities - removed features from Pyrite")
   public boolean oddities = false;

   @DisplayName("Quartz Block Set")
   @Comment("Completes the block set for Quartz.")
   public boolean quartz = true;

   @DisplayName("Path Turf")
   @Comment("Adds a block set for Dirt Paths.")
   public boolean path_turf = true;

   @DisplayName("Podzol Turf")
   @Comment("Adds a block set for Podzol.")
   public boolean podzol_turf = true;

   @DisplayName("Redstone Block Set")
   @Comment("Completes the block set for Redstone.")
   public boolean redstone = true;

   @DisplayName("Red Sandstone Bricks")
   @Comment("Enable a block set for Red Sandstone Bricks.")
   public boolean red_sandstone_bricks = true;

   @DisplayName("Sandstone Bricks")
   @Comment("Enable a block set for Sandstone Bricks.")
   public boolean sandstone_bricks = true;

   @DisplayName("Smooth Stone Bricks")
   @Comment("Enable a block set for Smooth Stone Bricks.")
   public boolean smooth_stone_bricks = true;

   @DisplayName("Smooth Stone Stairs")
   @Comment("Adds the missing Smooth Stone Stairs.")
   public boolean smooth_stone_stairs = true;

   @DisplayName("Terracotta Bricks")
   @Comment("Enable a block set for Terracotta Bricks.")
   public boolean terracotta_bricks = true;

   @DisplayName("Dyed Torches")
   @Comment("Enable Dyed Torches.")
   public boolean torches = true;

   @DisplayName("Torch Levers")
   @Comment("Enable Pyrite Torch Levers.")
   public boolean torch_levers = true;

   @DisplayName("Wall Gates")
   @Comment("Enable Wall Gates, a decorative block like fence gates, but for walls.")
   public boolean wall_gates = true;

   @DisplayName("Wool Stairs and Slabs")
   @Comment("Enable Pyrite's Backport of Wool Stairs and Slabs.")
   public boolean wool_stairs_and_slabs = true;

   @Comment("Add items to vanilla item groups.")
   public boolean addToVanillaItemGroups = true;

   @Comment("Add a tooltip on disabled items.")
   public boolean disabledContentTooltip = true;

}