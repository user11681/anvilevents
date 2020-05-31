package user11681.anvilevents.event.block;

import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class EntityMineDropEvent extends BlockDropEvent {
    protected ServerWorld world;
    protected BlockPos pos;
    protected BlockEntity blockEntity;
    protected Entity entity;
    protected ItemStack tool;

    public EntityMineDropEvent(final BlockState state, final ServerWorld world, final BlockPos pos, final BlockEntity blockEntity, final Entity entity, final ItemStack tool, final List<ItemStack> droppedStacks) {
        super(state, droppedStacks);

        this.world = world;
        this.pos = pos;
        this.blockEntity = blockEntity;
        this.entity = entity;
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

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(final Entity entity) {
        this.entity = entity;
    }

    public ItemStack getTool() {
        return tool;
    }

    public void setTool(final ItemStack tool) {
        this.tool = tool;
    }
}
