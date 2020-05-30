package user11681.anvilevents.event.entity.player;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import user11681.anvilevents.event.block.BlockEvent;

public class BlockBreakSpeedEvent extends BlockEvent {
    protected final BlockView world;

    protected BlockState state;
    protected PlayerEntity player;
    protected BlockPos blockPos;
    protected float speed;

    public BlockBreakSpeedEvent(final Block block, final BlockState state, final PlayerEntity player, final BlockView world, final BlockPos blockPos, final float speed) {
        super(block);

        this.world = world;

        this.state = state;
        this.player = player;
        this.blockPos = blockPos;
        this.speed = speed;
    }

    public BlockState getState() {
        return this.state;
    }

    public void setState(final BlockState state) {
        this.state = state;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public void setPlayer(final PlayerEntity player) {
        this.player = player;
    }

    public BlockView getWorld() {
        return this.world;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public void setBlockPos(final BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(final float speed) {
        this.speed = speed;
        this.setConsume();
    }
}
