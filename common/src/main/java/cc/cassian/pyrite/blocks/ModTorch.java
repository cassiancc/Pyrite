package cc.cassian.pyrite.blocks;

import net.minecraft.block.*;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ModTorch extends ModWallMounted {
    private final ParticleEffect particle;

    public ModTorch(Settings settings, ParticleEffect particle) {
        super(settings);
        this.particle = particle;
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double xPlus;
        double yPlus;
        double zPlus;
        switch (state.get(FACE)) {
            case FLOOR:
                xPlus = (double)pos.getX() + 0.5;
                yPlus = (double)pos.getY() + 0.65;
                zPlus = (double)pos.getZ() + 0.5;
                break;
            case WALL:
                switch (state.get(FACING)) {
                    case EAST:
                        xPlus = (double)pos.getX() + 0.3;
                        yPlus = (double)pos.getY() + 0.9;
                        zPlus = (double)pos.getZ() + 0.5;
                        break;
                    case WEST:
                        xPlus = (double)pos.getX() + 0.7;
                        yPlus = (double)pos.getY() + 0.9;
                        zPlus = (double)pos.getZ() + 0.5;
                        break;
                    case SOUTH:
                        xPlus = (double)pos.getX() + 0.5;
                        yPlus = (double)pos.getY() + 0.9;
                        zPlus = (double)pos.getZ() + 0.25;
                        break;
                    default:
                        xPlus = (double)pos.getX() + 0.5;
                        yPlus = (double)pos.getY() + 0.9;
                        zPlus = (double)pos.getZ() +.8;
                        break;
                }
                break;
            //Ceiling
            default:
                xPlus = (double)pos.getX() + 0.5;
                yPlus = (double)pos.getY() + 0.4;
                zPlus = (double)pos.getZ() + 0.5;
                break;
        }

        world.addParticle(ParticleTypes.SMOKE, xPlus, yPlus, zPlus, 0.0, 0.0, 0.0);
        world.addParticle(particle, xPlus, yPlus, zPlus, 0.0, 0.0, 0.0);
    }
}
