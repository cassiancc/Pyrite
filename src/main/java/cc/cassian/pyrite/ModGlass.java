<<<<<<<< HEAD:src/main/java/com/pyrite/blocks/ModGlass.java
package com.pyrite.blocks;
========
package cc.cassian.pyrite;
>>>>>>>> 1.19.2:src/main/java/cc/cassian/pyrite/ModGlass.java

import net.minecraft.block.GlassBlock;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class ModGlass extends GlassBlock {
    public ModGlass() {
<<<<<<<< HEAD:src/main/java/com/pyrite/blocks/ModGlass.java
        super(Settings.of(Material.GLASS).nonOpaque().strength(2.0f)); }
    }
========
        super(Settings.of(Material.GLASS).nonOpaque().strength(2.0f).sounds(BlockSoundGroup.GLASS)); }
}
>>>>>>>> 1.19.2:src/main/java/cc/cassian/pyrite/ModGlass.java
