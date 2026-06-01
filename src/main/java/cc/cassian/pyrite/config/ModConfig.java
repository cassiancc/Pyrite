package cc.cassian.pyrite.config;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.DisplayName;

public class ModConfig extends WrappedConfig {

   @DisplayName("Amethyst Block Set")
   @Comment("Completes the block set for Amethyst.")
   public boolean amethyst = true;

   @DisplayName("Azalea Wood Set")
   @Comment("Enable Pyrite Azalea Trees and their wood set.")
   public boolean azalea = true;

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

   @DisplayName("Emerald Block Set")
   @Comment("Completes the block set for Emerald.")
   public boolean emerald = true;

   @DisplayName("Framed Glass")
   @Comment("Adds a decorative block set made of Iron and Glass.")
   public boolean framed_glass = true;

   @DisplayName("Gold Block Set")
   @Comment("Completes the block set for Gold.")
   public boolean gold = true;

   @DisplayName("Iron Block Set")
   @Comment("Completes the block set for Iron.")
   public boolean iron = true;

   @DisplayName("Variant Ladders")
   @Comment("Enable variant Ladders for Pyrite dyed wood.")
   public boolean ladders = true;

   @DisplayName("Lapis Block Set")
   @Comment("Completes the block set for Lapis Lazuli.")
   public boolean lapis = true;

   @DisplayName("Mushroom Wood Sets")
   @Comment("Enable Pyrite Tall Mushrooms and their wood set.")
   public boolean mushrooms = true;

   @DisplayName("Netherite Block Set")
   @Comment("Completes the block set for Netherite.")
   public boolean netherite = true;

   @Comment("Enable the Oddities - removed features from Pyrite")
   public boolean oddities = false;

   @DisplayName("Quartz Block Set")
   @Comment("Completes the block set for Quartz.")
   public boolean quartz = true;

   @DisplayName("Redstone Block Set")
   @Comment("Completes the block set for Redstone.")
   public boolean redstone = true;

   @DisplayName("Torch Levers")
   @Comment("Enable Pyrite Torch Levers.")
   public boolean torch_levers = true;

   @Comment("Enable Wall Gates, a decorative block like fence gates, but for walls.")
   public boolean wall_gates = true;

   @Comment("Enable Pyrite's Backport of Wool Stairs and Slabs.")
   public boolean wool_stairs_and_slabs = true;

   @Comment("Add items to vanilla item groups.")
   public boolean addToVanillaItemGroups = true;

   @Comment("Add a tooltip on disabled items.")
   public boolean disabledContentTooltip = true;

}