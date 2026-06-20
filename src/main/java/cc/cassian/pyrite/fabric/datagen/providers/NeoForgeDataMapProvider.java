//? fabric {
package cc.cassian.pyrite.fabric.datagen.providers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import cc.cassian.pyrite.fabric.FabricPlatformImpl;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;

/// There's something deeply funny about using Fabric's data generator for NeoForge data.
public class NeoForgeDataMapProvider implements DataProvider {
    private final FabricDataOutput output;

    public NeoForgeDataMapProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        this.output = output;
    }

    private static final List<Consumer<BiConsumer<String, JsonElement>>> SUBMITTERS = List.of(
            NeoForgeDataMapProvider::compostables,
            NeoForgeDataMapProvider::strippables,
            NeoForgeDataMapProvider::oxidizables,
            NeoForgeDataMapProvider::waxables
    );

    private static void compostables(BiConsumer<String, JsonElement> consumer) {
        /*
        ModCompostable.register();
        Codec<Float> chance = ExtraCodecs.POSITIVE_FLOAT.fieldOf("chance").codec();
        Codec<Map<Identifier, Float>> mapCodec = Codec.unboundedMap(Identifier.CODEC, chance).fieldOf("values").codec();
        consumer.accept("item/compostables", mapCodec.encodeStart(JsonOps.INSTANCE, ModCompostable.COMPOSTABLES).getOrThrow());

         */
    }


    private static void strippables(BiConsumer<String, JsonElement> consumer) {
        Codec<Identifier> value = Identifier.CODEC.fieldOf("stripped_block").codec();
        Codec<Map<Identifier, Identifier>> mapCodec = Codec.unboundedMap(Identifier.CODEC, value).fieldOf("values").codec();
        consumer.accept("block/strippables", mapCodec.encodeStart(JsonOps.INSTANCE, FabricPlatformImpl.STRIPPABLES).getOrThrow());
    }

    private static void oxidizables(BiConsumer<String, JsonElement> consumer) {
        Codec<Identifier> value = Identifier.CODEC.fieldOf("next_oxidation_stage").codec();
        Codec<Map<Identifier, Identifier>> mapCodec = Codec.unboundedMap(Identifier.CODEC, value).fieldOf("values").codec();
        consumer.accept("block/oxidizables", mapCodec.encodeStart(JsonOps.INSTANCE, FabricPlatformImpl.OXIDIZABLES).getOrThrow());
    }

    private static void waxables(BiConsumer<String, JsonElement> consumer) {
        Codec<Identifier> value = Identifier.CODEC.fieldOf("waxed").codec();
        Codec<Map<Identifier, Identifier>> mapCodec = Codec.unboundedMap(Identifier.CODEC, value).fieldOf("values").codec();
        consumer.accept("block/waxables", mapCodec.encodeStart(JsonOps.INSTANCE, FabricPlatformImpl.WAXABLES).getOrThrow());
    }

    private static void collect(BiConsumer<String, JsonElement> consumer) {
        for (final var submitter : SUBMITTERS) {
            submitter.accept(consumer);
        }
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        final var elements = new HashMap<String, JsonElement>();

        collect((name, elem) -> {
            if (elements.put(name, elem) != null) {
                throw new IllegalArgumentException("An element with name " + name + " has already been added.");
            }
        });

        final var paths = this.output.createPathProvider(PackOutput.Target.DATA_PACK, "data_maps");

        return CompletableFuture.allOf(
                elements.entrySet().stream().map(x ->
                                DataProvider.saveStable(
                                        cache,
                                        x.getValue(),
                                        paths.json(Identifier.fromNamespaceAndPath("neoforge", x.getKey()))
                                )
                        )
                        .toArray(CompletableFuture[]::new)
        );
    }

    @Override
    public String getName() {
        return "Pyrite Data Maps";
    }
}
//?}