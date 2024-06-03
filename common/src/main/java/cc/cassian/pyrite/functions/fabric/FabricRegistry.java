package cc.cassian.pyrite.functions.fabric;

import cc.cassian.pyrite.blocks.*;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static cc.cassian.pyrite.Pyrite.modID;
import static cc.cassian.pyrite.functions.fabric.FabricCommonHelpers.*;

public class FabricRegistry {

    //Deferred registry entries
    public static ArrayList<Block> pyriteBlocks = new ArrayList<>();
    public static ArrayList<Item> pyriteItems = new ArrayList<>();
    static ArrayList<String> pyriteBlockIDs = new ArrayList<>();
    static ArrayList<String> pyriteItemIDs = new ArrayList<>();



    //Add most Pyrite blocks.
    public static void registerPyriteBlock(String blockID, String blockType, AbstractBlock.Settings blockSettings) {
        pyriteBlockIDs.add(blockID);
        int power;
        if (blockID.contains("redstone")) {
            power = 15;
        }
        else {
            power = 0;
        }
        switch (blockType.toLowerCase()) {
            case "block":
                pyriteBlocks.add(new ModBlock(blockSettings, power));
                break;
            case "crafting":
                pyriteBlocks.add(new ModCraftingTable(blockSettings));
                if (!(blockID.contains("crimson") || blockID.contains("warped"))) {
                    fuel.put(getLastBlock(), 300);
                }
                break;
            case "carpet":
                pyriteBlocks.add(new ModCarpet(blockSettings));
                break;
            case "slab":
                pyriteBlocks.add(new ModSlab(blockSettings, power));
                break;
            case "wall":
                pyriteBlocks.add(new ModWall(blockSettings, power));
                break;
            case "fence":
                pyriteBlocks.add(new FenceBlock(blockSettings));
                break;
            case "log":
                pyriteBlocks.add(new ModPillar(blockSettings, power));
                break;
            case "torch":
                pyriteBlocks.add(new ModTorch(blockSettings));
                break;
            case "facing":
                pyriteBlocks.add(new ModFacingBlock(blockSettings, power));
                break;
            case "bars", "glass_pane":
                pyriteBlocks.add(new ModPane(blockSettings, power));
                addTransparentBlock();
                break;
            case "glass":
                pyriteBlocks.add(new ModGlass(blockSettings));
                addTransparentBlock();
                break;
            case "gravel":
                pyriteBlocks.add(new GravelBlock(blockSettings));
                break;
            case "flower":
                pyriteBlocks.add(new FlowerBlock(StatusEffects.NIGHT_VISION, 5, blockSettings));
                addTransparentBlock();
                break;
            default:
                System.out.println(blockID + "created as a generic block, block provided" + blockType);
                pyriteBlocks.add(new Block(blockSettings));
                break;
        }
        if (blockID.contains("grass")) {
            addGrassBlock();
        }

    }


    //Add Pyrite blocks that require Wood Types - Fence gates.
    public static void registerPyriteBlock(String blockID, AbstractBlock.Settings blockSettings, WoodType type) {
        pyriteBlockIDs.add(blockID);
        pyriteBlocks.add(new FenceGateBlock(blockSettings, type));
    }

    //Add Pyrite blocks that require Block Sets.
    public static void registerPyriteBlock(String blockID, String blockType, AbstractBlock.Settings blockSettings, BlockSetType type) {
        pyriteBlockIDs.add(blockID);
        switch (blockType) {
            case "door":
                pyriteBlocks.add(new DoorBlock(blockSettings.nonOpaque(), type));
                addTransparentBlock();
                break;
            case "trapdoor":
                pyriteBlocks.add(new TrapdoorBlock(blockSettings.nonOpaque(), type));
                addTransparentBlock();
                break;
            case "button":
                pyriteBlocks.add(new ModWoodenButton(blockSettings, type));
                break;
            case "pressure_plate":
                pyriteBlocks.add(new ModPressurePlate(blockSettings, type));
                break;
            default:
                System.out.println(blockID + "created as a generic block.");
                pyriteBlocks.add(new Block(blockSettings));
                break;
        }
    }

    //Add Torch Levers
    public static void registerPyriteBlock(String blockID, String blockType, AbstractBlock.Settings blockSettings, ParticleEffect particle) {
        pyriteBlocks.add(new TorchLever(blockSettings, particle));
        pyriteBlockIDs.add(blockID);
        addTransparentBlock();
    }

    //Add Pyrite Stair blocks.
    public static void registerPyriteBlock(String blockID, Block copyBlock, AbstractBlock.Settings blockSettings) {
        pyriteBlockIDs.add(blockID);
        pyriteBlocks.add(new ModStairs(copyBlock.getDefaultState(), blockSettings));
        if (blockID.contains("grass")) {
            addGrassBlock();
        }
    }

    //Create and add Pyrite items.
    public static void registerPyriteItem(String itemID) {
        pyriteItems.add(new Item(new Item.Settings()));
        pyriteItemIDs.add(itemID);
    }

    public static void register() {
        //Register blocks and block items.
        for (int x = 0; x < pyriteBlockIDs.size(); x++) {
            Registry.register(Registries.BLOCK, Identifier.of(modID, pyriteBlockIDs.get(x)), pyriteBlocks.get(x));
            Registry.register(Registries.ITEM, Identifier.of(modID, pyriteBlockIDs.get(x)), new BlockItem(pyriteBlocks.get(x), new Item.Settings()));
        }
        //Registers items.
        for (int x = 0; x < pyriteItemIDs.size(); x++) {
            Registry.register(Registries.ITEM, Identifier.of(modID, pyriteItemIDs.get(x)), pyriteItems.get(x));
        }
        //Registers the Pyrite item group.
//        Registry.register(Registries.ITEM_GROUP, new Identifier(modID, "pyrite_group"), PYRITE_GROUP);
    }
}
