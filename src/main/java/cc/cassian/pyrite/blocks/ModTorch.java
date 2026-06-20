package cc.cassian.pyrite.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class ModTorch extends ModWallMounted {
    private final ParticleOptions particle;

    public ModTorch(Properties settings, ParticleOptions particle) {
        super(settings);
        this.particle = particle;
    }

    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double xPlus;
        double yPlus;
        double zPlus;
        switch (state.getValue(FACE)) {
            case FLOOR:
                xPlus = (double)pos.getX() + 0.5;
                yPlus = (double)pos.getY() + 0.65;
                zPlus = (double)pos.getZ() + 0.5;
                break;
            case WALL:
                switch (state.getValue(FACING)) {
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
