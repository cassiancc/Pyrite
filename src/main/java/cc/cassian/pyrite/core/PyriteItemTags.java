package cc.cassian.pyrite.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteItemTags {
    public static final TagKey<Item> HIDDEN_FROM_RECIPE_VIEWERS = tagKey("c", "hidden_from_recipe_viewers");
    public static final TagKey<Item> DYES = tagKey("c", "dyes");
    public static final TagKey<Item> SHIELDS = tagKey("c", "tools/shield");
    public static final TagKey<Item> ODDITIES = tagKey("oddities");
    public static final TagKey<Item> CHESTS = tagKey("chests");
    public static final TagKey<Item> FENCES = tagKey("fences");
    public static final TagKey<Item> BOATS = tagKey("boats");
    public static final TagKey<Item> CHEST_BOATS = tagKey("chest_boats");
    // Dyes
    public static final TagKey<Item> DRAGON_DYES = tagKey("c", "dyes/dragon");
    public static final TagKey<Item> GLOW_DYES = tagKey("c", "dyes/glow");
    public static final TagKey<Item> HONEY_DYES = tagKey("c", "dyes/honey");
    public static final TagKey<Item> NOSTALGIA_DYES = tagKey("c", "dyes/nostalgia");
    public static final TagKey<Item> POISONOUS_DYES = tagKey("c", "dyes/poisonous");
    public static final TagKey<Item> ROSE_DYES = tagKey("c", "dyes/rose");
    public static final TagKey<Item> STAR_DYES = tagKey("c", "dyes/star");


	public static TagKey<Item> tagKey(String id) {
        return tagKey(MOD_ID, id);
    }

    public static TagKey<Item> tagKey(String namespace, String id) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, id));
    }
}
