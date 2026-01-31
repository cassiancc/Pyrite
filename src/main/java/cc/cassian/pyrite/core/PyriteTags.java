package cc.cassian.pyrite.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteTags {
    public static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = itemTagKey("c", "hidden_from_recipe_viewers");
    public static final TagKey<Item> DYES = itemTagKey("c", "dyes");
    public static final TagKey<Item> SHIELDS = itemTagKey("c", "tools/shield");
    public static final TagKey<Item> ENABLED = itemTagKey( "enabled");
    public static final TagKey<Item> ODDITIES = itemTagKey("oddities");
    public static final TagKey<Block> CHESTS = blockTagKey("chests");


    public static TagKey<Block> blockTagKey(String id) {
        return blockTagKey(MOD_ID, id);
    }

    public static TagKey<Block> blockTagKey(String namespace, String id) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(namespace, id));
    }

    public static TagKey<Item> itemTagKey(String id) {
        return itemTagKey(MOD_ID, id);
    }

    public static TagKey<Item> itemTagKey(String namespace, String id) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(namespace, id));
    }
}
