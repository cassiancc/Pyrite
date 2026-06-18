package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.entity.ModEntities;
import cc.cassian.pyrite.entries.ItemEntry;
import cc.cassian.pyrite.registry.BlockCreator;
import cc.cassian.pyrite.util.ModLists;
import cc.cassian.pyrite.util.sets.WoodSet;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class PyriteLangProvider extends FabricLanguageProvider {
    public PyriteLangProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(packOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registries, TranslationBuilder lang) {
        try {
            Optional<Path> path = packOutput.getModContainer().findPath("assets/pyrite/lang/en_us.base.json");
            if (path.isPresent()) {
                lang.add(path.get());
            } else {
                throw new RuntimeException("The existing language file could not be found in assets!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (String dye : ModLists.DYES) {
            name(lang, dye + "_wool_slab");
            name(lang, dye + "_wool_stairs");
        }
        for (WoodSet woodSet : BlockCreator.WOOD_SETS) {
            name(lang, woodSet.boat());
            name(lang, woodSet.chestBoat());
        }
        ModEntities.BOATS.forEach((name, entityType)->{
            lang.add(entityType, name(name + "_boat"));
        });
        ModEntities.CHEST_BOATS.forEach((name, entityType)->{
            lang.add(entityType, name(name + "_chest_boat"));
        });
    }

	private void name(TranslationBuilder lang, ItemEntry<Item> entry) {
        lang.add(entry.asItem(), name(entry.getPath()));
	}

    private void name(TranslationBuilder lang, String name) {
        lang.add(getBlock(Pyrite.of(name)), name(name));
    }

    private static String name(String path) {
        return WordUtils.capitalizeFully(path.replace("_", " "));
    }

    private Block getBlock(Identifier id) {
        return BuiltInRegistries.BLOCK.getOrThrow(ResourceKey.create(Registries.BLOCK, id)).value();
    }
}
