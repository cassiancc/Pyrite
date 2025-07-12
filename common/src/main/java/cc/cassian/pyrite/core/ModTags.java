package cc.cassian.pyrite.core;

import net.minecraft.item.Item;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class ModTags {
    public static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = itemTagKey("c", "hidden_from_recipe_viewers");

    public static TagKey<Item> itemTagKey(String id) {
        return itemTagKey(MOD_ID, id);
    }

    public static TagKey<Item> itemTagKey(String namespace, String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(namespace, id));
    }
}
