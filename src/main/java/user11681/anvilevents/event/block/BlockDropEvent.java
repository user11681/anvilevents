package user11681.anvilevents.event.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;

public class BlockDropEvent extends BlockEvent {
    protected BlockState state;
    protected LootContext.Builder builder;
    protected List<ItemStack> drops;

    public BlockDropEvent(final Block block, final BlockState state, final LootContext.Builder builder, final List<ItemStack> drops) {
        super(block);

        this.state = state;
        this.builder = builder;
        this.drops = drops;
    }

    public BlockState getState() {
        return state;
    }

    public void setState(final BlockState state) {
        this.state = state;
    }

    public LootContext.Builder getBuilder() {
        return this.builder;
    }

    public void setBuilder(final LootContext.Builder builder) {
        this.builder = builder;
    }

    public List<ItemStack> getDrops() {
        return this.drops;
    }

    public void setDrops(final List<ItemStack> drops) {
        this.drops = drops;

        this.setAccepted();
    }
}
