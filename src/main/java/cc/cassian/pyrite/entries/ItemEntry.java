package cc.cassian.pyrite.entries;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemEntry<T extends Item> implements PyriteEntry {

    private final ResourceLocation id;
    private final T raw;

    public ItemEntry(ResourceLocation id, T raw) {
        this.id = id;
        this.raw = raw;
    }

    public ItemEntry(String itemID, T item) {
        this.id = Pyrite.of(itemID);
        this.raw = item;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public Item asItem() {
        return raw.asItem();
    }

    public T get() {
        return this.raw;
    }

    public T value() {
        return get();
    }

    public boolean isIn(ItemStack heldItem) {
        return heldItem.is(this.raw.asItem());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}