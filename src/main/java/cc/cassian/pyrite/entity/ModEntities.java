package cc.cassian.pyrite.entity;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.function.Supplier;

public class ModEntities {

	public static LinkedHashMap<String, EntityType<Boat>> BOATS = new LinkedHashMap<>();

	private static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> boatItemGetter) {
		return (entityType, level) -> new Boat(entityType, level, boatItemGetter);
	}

	public static EntityType<Boat> registerBoat(String id, Supplier<Item> boatItemGetter) {
		EntityType<Boat> boatEntityType = register("%s_boat".formatted(id), EntityType.Builder.of(boatFactory(boatItemGetter), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
		BOATS.put(id, boatEntityType);
		return boatEntityType;
	}

	private static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder) {
		var resourceKey = ResourceKey.create(Registries.ENTITY_TYPE, Pyrite.of(key));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceKey, builder.build(resourceKey));
	}

}
