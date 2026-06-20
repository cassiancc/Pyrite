package cc.cassian.pyrite.entity;


import cc.cassian.pyrite.entity.backport.PyriteBoat;
import cc.cassian.pyrite.entity.backport.PyriteChestBoat;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import cc.cassian.pyrite.Pyrite;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import java.util.LinkedHashMap;
import java.util.function.Supplier;


import java.util.ArrayList;


public class ModEntities {

	public static LinkedHashMap<String, EntityType<PyriteBoat>> BOATS = new LinkedHashMap<>();
	public static LinkedHashMap<String, EntityType<PyriteChestBoat>> CHEST_BOATS = new LinkedHashMap<>();

	private static EntityType.EntityFactory<PyriteBoat> boatFactory(Supplier<Item> boatItemGetter) {
		return (entityType, level) -> new PyriteBoat(entityType, level, boatItemGetter);
	}

	private static EntityType.EntityFactory<PyriteChestBoat> chestBoatFactory(Supplier<Item> boatItemGetter) {
		return (entityType, level) -> new PyriteChestBoat(entityType, level, boatItemGetter);
	}

	public static EntityType<PyriteBoat> registerBoat(String id, Supplier<Item> boatItemGetter) {
		EntityType<PyriteBoat> boatEntityType = register("%s_boat".formatted(id), EntityType.Builder.of(boatFactory(boatItemGetter), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
		BOATS.put(id, boatEntityType);
		return boatEntityType;
	}

	public static EntityType<PyriteChestBoat> registerChestBoat(String id, Supplier<Item> boatItemGetter) {
		EntityType<PyriteChestBoat> boatEntityType = register("%s_chest_boat".formatted(id), EntityType.Builder.of(chestBoatFactory(boatItemGetter), MobCategory.MISC).sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
		CHEST_BOATS.put(id, boatEntityType);
		return boatEntityType;
	}

	private static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder) {
		var resourceKey = ResourceKey.create(Registries.ENTITY_TYPE, Pyrite.of(key));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceKey, builder.build(key));
	}
}
