package user11681.anvilevents.duck.entity;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public interface EntityDuck {
    void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition);
}
