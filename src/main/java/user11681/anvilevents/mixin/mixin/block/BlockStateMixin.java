package user11681.anvilevents.mixin.mixin.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.event.entity.player.UseBlockEvent;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class BlockStateMixin {
    protected boolean use = true;

    @Inject(method = "onUse(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;",
            at = @At("HEAD"),
            cancellable = true)
    protected void onUse(final World world, final PlayerEntity player, final Hand hand, final BlockHitResult hitResult, final CallbackInfoReturnable<ActionResult> info) {
        if (this.use) {
            final UseBlockEvent event = new UseBlockEvent(player, (BlockState) (Object) this, world, hand, hitResult).fire();
            final ActionResult result = event.result;

            if (event.isFail()) {
                info.setReturnValue(result);
            } else {
                this.use = false;
                final ActionResult returnValue = event.getState().onUse(world, event.getPlayer(), event.getHand(), event.getHitResult());
                this.use = true;

                if (event.isPass()) {
                    info.setReturnValue(returnValue);
                } else {
                    info.setReturnValue(result);
                }
            }
        }
    }
}
