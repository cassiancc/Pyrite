package cc.cassian.pyrite.functions.architectury;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.ToIntFunction;


public class ArchitecturyHelpers {
    public static ArrayList<RegistrySupplier<Block>> transparentBlocks = new ArrayList<>();
    public static ArrayList<RegistrySupplier<Block>> translucentBlocks = new ArrayList<>();
    public static ArrayList<RegistrySupplier<Block>> grassBlocks = new ArrayList<>();
    public static HashMap<RegistrySupplier<Block>, Integer> fuel = new HashMap<>();

    public static void addTransparentBlock(RegistrySupplier<Block> newBlock) {
        transparentBlocks.add(newBlock);
    }
    public static void addTranslucentBlock(RegistrySupplier<Block> newBlock) {
        translucentBlocks.add(newBlock);
    }
    public static void addGrassBlock(RegistrySupplier<Block> newBlock) {
        grassBlocks.add(newBlock);
    }

}