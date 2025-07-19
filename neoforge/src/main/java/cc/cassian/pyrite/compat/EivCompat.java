package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.core.PyriteTags;
import cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl;
import de.crafty.eiv.common.api.recipe.ItemView;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

public class EivCompat {

    public static void hideStacks() {
        for (DeferredHolder<Block, ? extends Block> block : BlockCreatorImpl.BLOCKS.getEntries()) {
            if (!block.get().asItem().getDefaultStack().isIn(PyriteTags.ENABLED)) {
                ItemView.excludeItem(block.get().asItem());
            }
        }
        for (DeferredHolder<Item, ? extends Item> block : BlockCreatorImpl.ITEMS.getEntries()) {
            if (!block.get().getDefaultStack().isIn(PyriteTags.ENABLED)) {
                ItemView.excludeItem(block.get());
            }
        }
    }
}
