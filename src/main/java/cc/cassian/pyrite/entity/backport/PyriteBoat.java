package cc.cassian.pyrite.entity.backport;

import java.util.function.Supplier;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PyriteBoat extends Boat {
	private final Supplier<Item> dropItem;

	public PyriteBoat(final EntityType<? extends PyriteBoat> type, final Level level, final Supplier<Item> dropItem) {
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

	protected Entity.MovementEmission getMovementEmission() {
		return MovementEmission.EVENTS;
	}

}
