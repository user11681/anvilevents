package user11681.anvilevents.event.entity.player;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import user11681.anvilevents.event.block.BlockEvent;

public class BlockBreakSpeedEvent extends BlockEvent {
    protected final BlockView world;

    protected AbstractBlock block;
    protected PlayerEntity player;
    protected BlockPos blockPos;
    protected float speed;

    public BlockBreakSpeedEvent(final AbstractBlock block, final BlockState state, final PlayerEntity player, final BlockView world, final BlockPos blockPos, final float speed) {
        super(state);

        this.world = world;
        this.block = block;
        this.player = player;
        this.blockPos = blockPos;
        this.speed = speed;
    }

    public BlockView getWorld() {
        return this.world;
    }

    public AbstractBlock getBlock() {
        return this.block;
    }

    public void setBlock(final Block block) {
        this.block = block;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public void setPlayer(final PlayerEntity player) {
        this.player = player;
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
        this.setAccepted();
    }
}
