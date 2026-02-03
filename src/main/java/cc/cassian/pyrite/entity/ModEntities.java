package cc.cassian.pyrite.entity;


import net.minecraft.resources.ResourceKey;

//? if >1.21.2 {
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
//?}



import java.util.ArrayList;


public class ModEntities {

//? if >1.21.2 {


	public static LinkedHashMap<String, EntityType<Boat>> BOATS = new LinkedHashMap<>();
	public static LinkedHashMap<String, EntityType<ChestBoat>> CHEST_BOATS = new LinkedHashMap<>();

	private static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> boatItemGetter) {
		return (entityType, level) -> new Boat(entityType, level, boatItemGetter);
	}

	private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(Supplier<Item> boatItemGetter) {
		return (entityType, level) -> new ChestBoat(entityType, level, boatItemGetter);
	}

	public static EntityType<Boat> registerBoat(String id, Supplier<Item> boatItemGetter) {
		EntityType<Boat> boatEntityType = register("%s_boat".formatted(id), EntityType.Builder.of(boatFactory(boatItemGetter), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
		BOATS.put(id, boatEntityType);
		return boatEntityType;
	}

	public static EntityType<ChestBoat> registerChestBoat(String id, Supplier<Item> boatItemGetter) {
		EntityType<ChestBoat> boatEntityType = register("%s_chest_boat".formatted(id), EntityType.Builder.of(chestBoatFactory(boatItemGetter), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
		CHEST_BOATS.put(id, boatEntityType);
		return boatEntityType;
	}

	private static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder) {
		var resourceKey = ResourceKey.create(Registries.ENTITY_TYPE, Pyrite.of(key));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceKey, builder.build(resourceKey));
	}
//?} else {
/*public static ArrayList<ResourceKey<?>> BOATS = new ArrayList<>();

	*///?}
}
