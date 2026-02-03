package cc.cassian.pyrite.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteBlockTags {
    public static final TagKey<Block> CHESTS = tagKey("chests");

    public static TagKey<Block> tagKey(String id) {
        return tagKey(MOD_ID, id);
    }

    public static TagKey<Block> tagKey(String namespace, String id) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(namespace, id));
    }
}
