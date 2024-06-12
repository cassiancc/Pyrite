package cc.cassian.pyrite.functions.architectury;

import cc.cassian.pyrite.blocks.*;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Objects;

import static cc.cassian.pyrite.Pyrite.modID;
import static cc.cassian.pyrite.functions.architectury.ArchitecturyHelpers.*;

public class CommonRegistry {
    static RegistrySupplier<Block> creativeTabIcon;

    //Deferred registry entries
    public static final DeferredRegister<Block> pyriteBlocks = DeferredRegister.create(modID, Registry.BLOCK_KEY);
    public static final DeferredRegister<Item> pyriteItems = DeferredRegister.create(modID, Registry.ITEM_KEY);

    //Add most Pyrite blocks.
    public static void registerPyriteBlock(String blockID, String blockType, AbstractBlock.Settings blockSettings) {
        RegistrySupplier<Block> newBlock;
        int power;
        if (blockID.contains("redstone")) {
            power = 15;
        }
        else {
            power = 0;
        }

        switch (blockType.toLowerCase()) {
            case "block":
                newBlock = pyriteBlocks.register(blockID, () -> new ModBlock(blockSettings, power));
                break;
            case "crafting":
                newBlock = pyriteBlocks.register(blockID, () -> new ModCraftingTable(blockSettings));
                if (!(blockID.contains("crimson") || blockID.contains("warped"))) {
                    fuel.put(newBlock, 300);
                }
                break;
            case "ladder":
                newBlock = pyriteBlocks.register(blockID, () -> new LadderBlock(blockSettings));
                addTransparentBlock(newBlock);
                break;
            case "carpet":
                newBlock = pyriteBlocks.register(blockID, () -> new ModCarpet(blockSettings));
                break;
            case "slab":
                newBlock = pyriteBlocks.register(blockID, () -> new ModSlab(blockSettings, power));
                break;
            case "wall":
                newBlock = pyriteBlocks.register(blockID, () -> new ModWall(blockSettings, power));
                break;
            case "fence":
                newBlock = pyriteBlocks.register(blockID, () -> new FenceBlock(blockSettings));
                break;
            case "fence_gate":
                newBlock = pyriteBlocks.register(blockID, () -> new FenceGateBlock(blockSettings));
                break;
            case "log":
                newBlock = pyriteBlocks.register(blockID, () -> new ModPillar(blockSettings, power));
                break;
            case "torch":
                newBlock = pyriteBlocks.register(blockID, () -> new ModTorch(blockSettings, ParticleTypes.FLAME));
                break;
            case "facing":
                newBlock = pyriteBlocks.register(blockID, () -> new ModFacingBlock(blockSettings, power));
                break;
            case "bars", "glass_pane":
                newBlock = pyriteBlocks.register(blockID, () -> new ModPane(blockSettings, power));
                addTransparentBlock(newBlock);
                break;
            case "tinted_glass_pane":
                newBlock = pyriteBlocks.register(blockID, () -> new ModPane(blockSettings, power));
                addTranslucentBlock(newBlock);
                break;
            case "glass":
                newBlock = pyriteBlocks.register(blockID, () -> new ModGlass(blockSettings));
                addTransparentBlock(newBlock);
                break;
            case "tinted_glass":
                newBlock = pyriteBlocks.register(blockID, () -> new ModGlass(blockSettings));
                break;
            case "gravel":
                newBlock = pyriteBlocks.register(blockID, () -> new GravelBlock(blockSettings));
                break;
            case "flower":
                newBlock = pyriteBlocks.register(blockID, () -> new FlowerBlock(StatusEffects.NIGHT_VISION, 5, blockSettings));
                addTransparentBlock(newBlock);
                break;
            case "door":
                newBlock = pyriteBlocks.register(blockID, () -> new DoorBlock(blockSettings.nonOpaque()));
                addTransparentBlock(newBlock);
                break;
            case "trapdoor":
                newBlock = pyriteBlocks.register(blockID, () -> new TrapdoorBlock(blockSettings.nonOpaque()));
                addTransparentBlock(newBlock);
                break;
            case "button":
                newBlock = pyriteBlocks.register(blockID, () -> new ModWoodenButton(blockSettings));
                break;
            case "pressure_plate":
                newBlock = pyriteBlocks.register(blockID, () -> new ModPressurePlate(blockSettings));
                break;
            default:
                System.out.println(blockID + "created as a generic block, block provided" + blockType);
                newBlock = pyriteBlocks.register(blockID, () -> new Block(blockSettings));
                break;
        }
        addBlockItem(newBlock);
        if (blockID.contains("grass")) {
            addGrassBlock(newBlock);
        }
        else if (blockID.equals("cobblestone_bricks")) {
            creativeTabIcon = newBlock;
        }

    }


    //Add blocks with particles - torches/torch levers
    public static void registerPyriteBlock(String blockID, String blockType, AbstractBlock.Settings blockSettings, ParticleEffect particle) {
        RegistrySupplier<Block> newBlock;
        if (Objects.equals(blockType, "torch")) {
            newBlock = pyriteBlocks.register(blockID, () -> new ModTorch(blockSettings.nonOpaque(), particle));
        }
        else {
            newBlock = pyriteBlocks.register(blockID, () -> new TorchLever(blockSettings.nonOpaque(), particle));
        }
        addBlockItem(newBlock);
        addTransparentBlock(newBlock);
    }

    //Add Pyrite Stair blocks.
    public static void registerPyriteBlock(String blockID, Block copyBlock, AbstractBlock.Settings blockSettings) {
        RegistrySupplier<Block> newBlock = pyriteBlocks.register(blockID, () -> new ModStairs(copyBlock.getDefaultState(), blockSettings));
        addBlockItem(newBlock);
        if (blockID.contains("grass")) {
            addGrassBlock(newBlock);
        }
    }
    public static final ItemGroup PYRITE_GROUP = CreativeTabRegistry.create(
            Identifier.of("pyrite", "group"), // Tab ID
            () -> new ItemStack(creativeTabIcon.get()) // Icon
    );
    //Create and add Pyrite items.
    public static void registerPyriteItem(String itemID) {
        pyriteItems.register(itemID, () -> (new Item(new Item.Settings().group(PYRITE_GROUP))));
    }

    public static void register() {
        pyriteBlocks.register();
        pyriteItems.register();
    }

    public static void addBlockItem(RegistrySupplier<Block> newBlock) {
        pyriteItems.register(newBlock.getId(), () -> new BlockItem(newBlock.get(), new Item.Settings().group(PYRITE_GROUP)));
    }
}