package cc.cassian.pyrite.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TorchLever extends LeverBlock {
    private final ParticleOptions particle;

    public TorchLever(Properties settings, ParticleOptions particle) {
        super(settings);
        this.particle = particle;
    }
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {

        double xPlus;
        double yPlus;
        double zPlus;
        switch (state.getValue(FACE)) {
            case FLOOR:
                if (state.getValue(POWERED))  {
                    zPlus = switch (state.getValue(FACING)) {
                        case WEST -> {
                            xPlus = (double) pos.getX() + 0.1;
                            yield (double) pos.getZ() + 0.5;
                        }
                        case EAST -> {
                            xPlus = (double) pos.getX() + 0.9;
                            yield (double) pos.getZ() + 0.5;
                        }
                        case NORTH -> {
                            xPlus = (double) pos.getX() + 0.4;
                            yield (double) pos.getZ() + 0.05;
                        }
                        default -> {
                            xPlus = (double) pos.getX() + 0.5;
                            yield (double) pos.getZ() + .9;
                        }
                    };
                    yPlus = (double)pos.getY() + 0.55;

                }
            else {
                xPlus = (double)pos.getX() + 0.5;
                yPlus = (double)pos.getY() + 0.65;
                zPlus = (double)pos.getZ() + 0.5;
                }
                break;
            case WALL:
                if (state.getValue(POWERED)) {
                    zPlus = switch (state.getValue(FACING)) {
                        case EAST -> {
                            xPlus = (double) pos.getX() + 0.3;
                            yield (double) pos.getZ() + 0.5;
                        }
                        case WEST -> {
                            xPlus = (double) pos.getX() + 0.7;
                            yield (double) pos.getZ() + 0.5;
                        }
                        case SOUTH -> {
                            xPlus = (double) pos.getX() + 0.5;
                            yield (double) pos.getZ() + 0.35;
                        }
                        default -> {
                            xPlus = (double) pos.getX() + 0.5;
                            yield (double) pos.getZ() + .6;
                        }
                    };
                    yPlus = (double)pos.getY() + 0.1;

                }
                else {
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
                }
                break;
            //Ceiling
            default:
                if (state.getValue(POWERED))  {
                    zPlus = switch (state.getValue(FACING)) {
                        case WEST -> {
                            xPlus = (double) pos.getX() + 0.1;
                            yield (double) pos.getZ() + 0.5;
                        }
                        case EAST -> {
                            xPlus = (double) pos.getX() + 0.9;
                            yield (double) pos.getZ() + 0.5;
                        }
                        case NORTH -> {
                            xPlus = (double) pos.getX() + 0.4;
                            yield (double) pos.getZ() + 0.05;
                        }
                        default -> {
                            xPlus = (double) pos.getX() + 0.5;
                            yield (double) pos.getZ() + .9;
                        }
                    };
                    yPlus = (double)pos.getY() + 0.55;

                }
                else {
                    xPlus = (double)pos.getX() + 0.5;
                    yPlus = (double)pos.getY() + 0.4;
                    zPlus = (double)pos.getZ() + 0.5;
                }
                break;
        }



        world.addParticle(ParticleTypes.SMOKE, xPlus, yPlus, zPlus, 0.0, 0.0, 0.0);
        world.addParticle(particle, xPlus, yPlus, zPlus, 0.0, 0.0, 0.0);
    }
}
