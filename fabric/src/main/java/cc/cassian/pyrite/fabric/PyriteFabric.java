package cc.cassian.pyrite.fabric;

import cc.cassian.pyrite.Pyrite;
import cc.cassian.pyrite.compat.ChestsCompat;
import cc.cassian.pyrite.compat.FarmersDelightCompat;
import cc.cassian.pyrite.functions.ModHelpers;
import cc.cassian.pyrite.registry.fabric.BlockCreatorImpl;
import cc.cassian.pyrite.functions.fabric.FabricHelpers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerBlockEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import static cc.cassian.pyrite.Pyrite.MOD_ID;

public class PyriteFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pyrite.init();
        BlockCreatorImpl.register();
        FabricHelpers.registerFuelBlocks();
        ServerLifecycleEvents.SERVER_STARTING.register(minecraftServer -> {
            if (FabricLoader.getInstance().isModLoaded("lolmcv"))
                ChestsCompat.registerToBlockEntity();
            if (FabricLoader.getInstance().isModLoaded("farmersdelight"))
                FarmersDelightCompat.registerToBlockEntity();
        });
        UseBlockCallback.EVENT.register((ModHelpers::updateTorchColour));
        if (Pyrite.CONFIG.oddities) {
            ResourceManagerHelper.registerBuiltinResourcePack(
                ModHelpers.locate("pyrite_oddities"),
                FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
                ResourcePackActivationType.DEFAULT_ENABLED);
        }
        if (Pyrite.CONFIG.azalea) {
            ResourceManagerHelper.registerBuiltinResourcePack(
                    ModHelpers.locate("pyrite_azalea"),
                    FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(),
                    ResourcePackActivationType.DEFAULT_ENABLED);
        }

    }
}