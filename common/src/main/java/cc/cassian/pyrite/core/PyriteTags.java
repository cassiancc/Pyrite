package cc.cassian.pyrite.core;

import dev.emi.emi.api.EmiRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteTags {
    public static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = itemTagKey("c", "hidden_from_recipe_viewers");
    public static final TagKey<Item> DYES = itemTagKey("c", "dyes");
    public static final TagKey<Item> SHIELDS = itemTagKey("c", "tools/shield");
    public static final TagKey<Item> ENABLED = itemTagKey( "enabled");
    public static final TagKey<Item> ODDITIES = itemTagKey("oddities");


    public static TagKey<Item> itemTagKey(String id) {
        return itemTagKey(MOD_ID, id);
    }

    public static TagKey<Item> itemTagKey(String namespace, String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(namespace, id));
    }
}
