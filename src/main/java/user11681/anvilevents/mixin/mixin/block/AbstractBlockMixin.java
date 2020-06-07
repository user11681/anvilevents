package user11681.anvilevents.mixin.mixin.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.event.entity.player.BlockBreakSpeedEvent;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    protected final AbstractBlock self = (AbstractBlock) (Object) this;

    protected boolean delta = true;

    @Inject(method = "calcBlockBreakingDelta(Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F", at = @At("HEAD"), cancellable = true)
    protected void onCalcBlockBreakingDelta(final BlockState state, final PlayerEntity player, final BlockView world, final BlockPos pos, final CallbackInfoReturnable<Float> info) {
        if (this.delta) {
            this.delta = false;
            final BlockBreakSpeedEvent event = new BlockBreakSpeedEvent(self, state, player, world, pos, self.calcBlockBreakingDelta(state, player, world, pos)).fire();

            if (event.isFail()) {
                info.setReturnValue(0F);
            } else {
                if (event.isAccepted()) {
                    info.setReturnValue(event.getSpeed());
                } else {
                    info.setReturnValue(event.getState().calcBlockBreakingDelta(event.getPlayer(), world, event.getBlockPos()));
                }
            }

            this.delta = true;
        }
    }
}
