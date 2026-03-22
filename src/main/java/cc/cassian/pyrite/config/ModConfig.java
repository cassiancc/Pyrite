package cc.cassian.pyrite.config;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.DisplayName;

public class ModConfig extends WrappedConfig {
   @Comment("Add items to vanilla item groups.")
   public boolean addToVanillaItemGroups = true;
   @Comment("Enable the Oddities - removed features from Pyrite")
   public boolean oddities = false;
   @DisplayName("Azalea Wood Set")
   @Comment("Enable Pyrite Azalea Trees and their wood set.")
   public boolean azalea = true;
   @DisplayName("Mushroom Wood Sets")
   @Comment("Enable Pyrite Tall Mushrooms and their wood set.")
   public boolean mushrooms = true;
   @DisplayName("Variant Crafting Tables")
   @Comment("Enable variant Crafting Tables for Pyrite dyed wood and vanilla wood types.")
   public boolean crafting_tables = true;
   @Comment("Add a tooltip on disabled items.")
   public boolean disabledContentTooltip = true;

}