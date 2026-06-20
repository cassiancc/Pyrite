package cc.cassian.pyrite.entity.backport;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class PyriteChestBoat extends ChestBoat {
	private final Supplier<Item> dropItem;

	public PyriteChestBoat(final EntityType<? extends PyriteChestBoat> type, final Level level, final Supplier<Item> dropItem) {
		super(type, level);
		this.dropItem = dropItem;
		this.blocksBuilding = true;
	}

	@Override
	public final Item getDropItem() {
		return this.dropItem.get();
	}

	@Override
	public final ItemStack getPickResult() {
		return new ItemStack(this.dropItem.get());
	}

	protected MovementEmission getMovementEmission() {
		return MovementEmission.EVENTS;
	}

}
