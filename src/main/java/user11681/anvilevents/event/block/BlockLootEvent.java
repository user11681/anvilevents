package user11681.anvilevents.event.block;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;

public class BlockLootEvent extends BlockDropEvent {
    protected Block block;
    protected LootContext.Builder builder;

    public BlockLootEvent(final Block block, final BlockState state, final LootContext.Builder builder, final List<ItemStack> droppedStacks) {
        super(state, droppedStacks);

        this.block = block;
        this.builder = builder;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(final Block block) {
        this.block = block;
    }

    public LootContext.Builder getBuilder() {
        return builder;
    }

    public void setBuilder(final LootContext.Builder builder) {
        this.builder = builder;
    }
}
