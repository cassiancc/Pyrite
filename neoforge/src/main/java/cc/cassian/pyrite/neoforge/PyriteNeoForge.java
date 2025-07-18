package cc.cassian.pyrite.neoforge;


import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.config.ModConfig;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.registry.neoforge.BlockCreatorImpl;
import cc.cassian.pyrite.neoforge.client.PyriteNeoForgeClient;
import cc.cassian.pyrite.registry.neoforge.PyriteItemGroupsImpl;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;


@Mod(Pyrite.MOD_ID)
public final class PyriteNeoForge {
    public PyriteNeoForge(IEventBus eventBus, ModContainer container) {
        // Run our common setup.
        Pyrite.init();
        // Run our client setup.
        if (FMLEnvironment.dist.equals(Dist.CLIENT))
            PyriteNeoForgeClient.init(eventBus);
        // Run NeoForged specific setup.
        BlockCreatorImpl.register(eventBus);
        eventBus.addListener(BlockCreatorImpl::addSupportedBlocks);
        eventBus.addListener(BlockCreatorImpl::commonSetup);
        eventBus.addListener(PyriteItemGroupsImpl::buildContents);
        eventBus.addListener(PyriteNeoForge::onUseWithItem);
        eventBus.addListener(PyriteNeoForge::addOddities);

    }

    private static void onUseWithItem(UseItemOnBlockEvent event) {
        ActionResult actionResult = ModHelpers.updateTorchColour(event.getItemStack(), event.getPlayer(), event.getLevel(), event.getPos());
        if (actionResult.equals(ActionResult.SUCCESS)) event.cancelWithResult(ItemActionResult.SUCCESS);
    }

    private static void addOddities(AddPackFindersEvent event) {
        if (Pyrite.CONFIG.oddities) {
            event.addPackFinders(ModHelpers.locate("resourcepacks/pyrite_oddities"), ResourceType.SERVER_DATA, Text.literal("Pyrite Oddities"), ResourcePackSource.BUILTIN, true, ResourcePackProfile.InsertionPosition.TOP);
        }
    }
}
