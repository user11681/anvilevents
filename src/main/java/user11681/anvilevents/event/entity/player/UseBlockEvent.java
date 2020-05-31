package user11681.anvilevents.event.entity.player;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class UseBlockEvent extends PlayerEvent {
    private final World world;

    protected BlockState state;
    protected Hand hand;
    protected BlockHitResult hitResult;

    public UseBlockEvent(final PlayerEntity player, final BlockState state, final World world, final Hand hand, final BlockHitResult hitResult) {
        super(player);

        this.state = state;
        this.world = world;
        this.hand = hand;
        this.hitResult = hitResult;
    }

    public BlockState getState() {
        return state;
    }

    public void setState(final BlockState state) {
        this.state = state;
    }

    public World getWorld() {
        return world;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(final Hand hand) {
        this.hand = hand;
    }

    public BlockHitResult getHitResult() {
        return hitResult;
    }

    public void setHitResult(final BlockHitResult hitResult) {
        this.hitResult = hitResult;
    }

    public ItemStack getItemStack() {
        return this.player.getStackInHand(this.hand);
    }
}
