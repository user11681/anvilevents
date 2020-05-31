package user11681.anvilevents.event.block;

import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

/**
 * This event is called when blocks are queried for their item drops.
 * {@link BlockDropEvent#miner}—and thus {@link BlockDropEvent#tool}—may be null.
 */
public class BlockDropEvent extends BlockEvent {
    protected ServerWorld world;
    protected BlockPos pos;
    protected BlockEntity blockEntity;
    protected Entity miner;
    protected ItemStack tool;
    protected List<ItemStack> droppedStacks;

    public BlockDropEvent(final BlockState state, final ServerWorld world, final BlockPos pos, final BlockEntity blockEntity, final Entity miner, final ItemStack tool, final List<ItemStack> droppedStacks) {
        super(state);

        this.world = world;
        this.pos = pos;
        this.blockEntity = blockEntity;
        this.miner = miner;
        this.tool = tool;
        this.droppedStacks = droppedStacks;
    }

    public Entity getMiner() {
        return miner;
    }

    public void setMiner(final Entity miner) {
        this.miner = miner;
    }

    public ItemStack getTool() {
        return tool;
    }

    public void setTool(final ItemStack tool) {
        this.tool = tool;
    }

    public ServerWorld getWorld() {
        return world;
    }

    public void setWorld(final ServerWorld world) {
        this.world = world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(final BlockPos pos) {
        this.pos = pos;
    }

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    public void setBlockEntity(final BlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }

    public List<ItemStack> getDroppedStacks() {
        return droppedStacks;
    }

    public void setDroppedStacks(final List<ItemStack> droppedStacks) {
        this.droppedStacks = droppedStacks;
        this.setAccepted();
    }
}
