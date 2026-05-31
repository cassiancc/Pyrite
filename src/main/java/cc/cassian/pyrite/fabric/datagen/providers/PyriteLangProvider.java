package cc.cassian.pyrite.fabric.datagen.providers;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.functions.ModLists;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String dye : ModLists.DYES) {
            String woolSlab = dye + "_wool_slab";
            lang.add(getBlock(Pyrite.of(woolSlab)), WordUtils.capitalizeFully(woolSlab.replace("_", " ")));
            String woolStairs = dye + "_wool_stairs";
            lang.add(getBlock(Pyrite.of(woolStairs)), WordUtils.capitalizeFully(woolStairs.replace("_", " ")));
        }
    }

    private Block getBlock(Identifier id) {
        return BuiltInRegistries.BLOCK.getOrThrow(ResourceKey.create(Registries.BLOCK, id)).value();
    }
}
