package cc.cassian.pyrite.compat;

import cc.cassian.pyrite.core.PyriteTags;
import cc.cassian.pyrite.registry.fabric.BlockCreatorImpl;
import de.crafty.eiv.common.api.recipe.ItemView;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class EivCompat {

    public static void hideStacks() {
        for (Block block : BlockCreatorImpl.BLOCKS.values()) {
            if (!block.asItem().getDefaultStack().isIn(PyriteTags.ENABLED)) {
                ItemView.excludeItem(block.asItem());
            }
        }
        for (Block block : BlockCreatorImpl.ITEMLESS_BLOCKS.values()) {
            if (!block.asItem().getDefaultStack().isIn(PyriteTags.ENABLED)) {
                ItemView.excludeItem(block.asItem());
            }
        }
        for (Item item : BlockCreatorImpl.ITEMS.values()) {
            if (!item.getDefaultStack().isIn(PyriteTags.ENABLED)) {
                ItemView.excludeItem(item);
            }
        }
    }
}
