package cc.cassian.pyrite.config;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;

public class ModConfig extends WrappedConfig {
   @Comment("Enable the Oddities - removed features from Pyrite")
   public boolean oddities = true;
   @Comment("Enable Pyrite Azalea Trees and their wood set.")
   public boolean azalea = true;
   @Comment("Remove disabled content from EMI")
   public boolean removeDisabledContentFromEMI = true;
   @Comment("Add a tooltip on disabled items.")
   public boolean disabledContentTooltip;
}