package user11681.anvilevents.event.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityLandEvent extends EntityEvent {
    private Block block;
    private World world;
    protected BlockPos position;
    protected float distance;

    public EntityLandEvent(final Entity entity, final Block block, final World world, final BlockPos position, final float distance) {
        super(entity);

        this.block = block;
        this.world = world;
        this.position = position;
        this.distance = distance;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(final Block block) {
        this.block = block;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(final World world) {
        this.world = world;
    }

    public BlockPos getPosition() {
        return position;
    }

    public void setPosition(final BlockPos position) {
        this.position = position;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(final float distance) {
        this.distance = distance;
    }
}
