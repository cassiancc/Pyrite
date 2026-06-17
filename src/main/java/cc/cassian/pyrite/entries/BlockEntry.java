package cc.cassian.pyrite.entries;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockEntry<T extends Block> implements PyriteEntry {

    private final Identifier id;
    private final T raw;

    public BlockEntry(String id, T supplier) {
        this(Pyrite.of(id), supplier);
    }

    public BlockEntry(Identifier id, T raw) {
        this.id = id;
        this.raw = raw;
    }

    public BlockEntry(ResourceKey<Block> id, T raw) {
        this(id.identifier(), raw);
    }

    public BlockEntry(T vanillaBlock) {
        this(vanillaBlock.properties().blockIdOrThrow(), vanillaBlock);
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public Item asItem() {
        return raw.asItem();
    }

    public T get() {
        return this.raw;
    }

    public boolean isIn(ItemStack heldItem) {
        return heldItem.is(this.raw.asItem());
    }

    public @Nullable BlockState getDefaultState() {
        return raw.defaultBlockState();
    }

    public T getValue() {
        return raw;
    }

    public String getKey() {
        return id.getPath();
    }

    public ResourceKey<Block> resourceKey() {
        return ResourceKey.create(Registries.BLOCK, id);
    }

    public T value() {
        return get();
    }
}