package cc.cassian.pyrite.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class PyriteBoatItem extends Item {
	public static final java.util.function.Predicate<Entity> CAN_BE_PICKED = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
	private final EntityType<? extends Boat> entityType;

	public PyriteBoatItem(final EntityType<? extends Boat> entityType, final Properties properties) {
		super(properties);
		this.entityType = entityType;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(final Level level, final Player player, final InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
		if (hitResult.getType() == HitResult.Type.MISS) {
			return InteractionResultHolder.pass(itemStack);
		} else {
			Vec3 viewVector = player.getViewVector(1.0F);
			double range = 5.0;
			List<Entity> entities = level.getEntities(
					player, player.getBoundingBox().expandTowards(viewVector.scale(range)).inflate(1.0), CAN_BE_PICKED
			);
			if (!entities.isEmpty()) {
				Vec3 from = player.getEyePosition();

				for (Entity entity : entities) {
					AABB bb = entity.getBoundingBox().inflate(entity.getPickRadius());
					if (bb.contains(from)) {
						return InteractionResultHolder.pass(itemStack);
					}
				}
			}

			if (hitResult.getType() == HitResult.Type.BLOCK) {
				Boat boat = this.getBoat(level, hitResult, itemStack, player);
				if (boat == null) {
					return InteractionResultHolder.fail(itemStack);
				} else {
					boat.setYRot(player.getYRot());
					if (!level.noCollision(boat, boat.getBoundingBox())) {
						return InteractionResultHolder.fail(itemStack);
					} else {
						if (!level.isClientSide()) {
							level.addFreshEntity(boat);
							level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
							itemStack.consume(1, player);
						}

						player.awardStat(Stats.ITEM_USED.get(this));
						return InteractionResultHolder.success(itemStack);
					}
				}
			} else {
				return InteractionResultHolder.pass(itemStack);
			}
		}
	}

	private @Nullable Boat getBoat(final Level level, final HitResult hitResult, final ItemStack itemStack, final Player player) {
		Boat boat = this.entityType.create(level);
		if (boat != null) {
			Vec3 location = hitResult.getLocation();
			boat.setPos(location.x, location.y, location.z);
			if (level instanceof ServerLevel serverLevel) {
				EntityType.createDefaultStackConfig(serverLevel, itemStack, player).accept(boat);
			}
		}

		return boat;
	}
}
