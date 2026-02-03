package cc.cassian.pyrite.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteItemTags {
    public static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = tagKey("c", "hidden_from_recipe_viewers");
    public static final TagKey<Item> DYES = tagKey("c", "dyes");
    public static final TagKey<Item> SHIELDS = tagKey("c", "tools/shield");
    public static final TagKey<Item> ENABLED = tagKey( "enabled");
    public static final TagKey<Item> ODDITIES = tagKey("oddities");
    public static final TagKey<Item> CHESTS = tagKey("chests");

    public static TagKey<Item> tagKey(String id) {
        return tagKey(MOD_ID, id);
    }

    public static TagKey<Item> tagKey(String namespace, String id) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(namespace, id));
    }
}
