package cc.cassian.pyrite.forge;

import cc.cassian.pyrite.Pyrite;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import static cc.cassian.pyrite.Pyrite.*;

@Mod(Pyrite.MOD_ID)
public class PyriteForge {
    public PyriteForge() {
        System.out.println("LOADED");
        Pyrite.init();


    }

    @SubscribeEvent
    public void register(RegisterEvent event) {
        System.out.println("REGISTRY");

        event.register(ForgeRegistries.Keys.BLOCKS,
                helper -> {
                    //Register blocks and block items.
                    for (int x = 0; x < pyriteBlockIDs.size(); x++) {
                        helper.register(new ResourceLocation(MOD_ID, pyriteBlockIDs.get(x)), pyriteBlocks.get(x));

                    }
                }
        );
        event.register(ForgeRegistries.Keys.ITEMS,
                helper -> {
                    //Register blocks and block items.
                    for (int x = 0; x < pyriteBlockIDs.size(); x++) {
                        helper.register(new ResourceLocation(MOD_ID, pyriteBlockIDs.get(x)), new BlockItem(pyriteBlocks.get(x), new Item.Properties()));
                    }
                    for (int x = 0; x < pyriteItemIDs.size(); x++) {
                        helper.register(new ResourceLocation(MOD_ID, pyriteItemIDs.get(x)), pyriteItems.get(x));
                    }
                }
        );
    }
}

