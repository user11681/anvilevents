package user11681.anvilevents.mixin.entity.player;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvil.Anvil;
import user11681.anvilevents.duck.entity.player.PlayerInventoryDuck;
import user11681.anvilevents.event.entity.player.BreakSpeedEvent;
import user11681.anvilevents.event.entity.player.PlayerDropInventoryEvent;
import user11681.anvilevents.event.entity.player.PlayerTickEvent;
import user11681.anvilevents.mixin.entity.LivingEntityMixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntityMixin {
    @Shadow
    protected abstract void dropInventory();

    protected boolean drop = true;
    protected boolean speed = true;

    @Inject(method = "dropInventory()V", at = @At("HEAD"), cancellable = true)
    protected void onDropInventroy(final CallbackInfo info) {
        if (this.drop) {
            final PlayerDropInventoryEvent event = Anvil.fire(new PlayerDropInventoryEvent(thiz(), ((PlayerInventoryDuck) thiz().inventory).getCombinedInventory()));

            if (!event.isFail()) {
                this.drop = false;
                //noinspection ConstantConditions
                ((PlayerEntityMixin) (Object) event.getPlayer()).dropInventory();
                this.drop = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F", at = @At("RETURN"), cancellable = true)
    protected void onGetBlockBreakingSpeed(final BlockState block, final CallbackInfoReturnable<Float> info) {
        if (this.speed) {
            final BreakSpeedEvent event = Anvil.fire(new BreakSpeedEvent(thiz(), block, info.getReturnValue()));

            if (event.isFail()) {
                info.setReturnValue(0F);
            } else {
                this.speed = false;

                if (event.isAccepted()) {
                    info.setReturnValue(event.getSpeed());
                } else {
                    info.setReturnValue(event.getPlayer().getBlockBreakingSpeed(event.getState()));
                }

                this.speed = true;
            }
        }
    }

    @Override
    @Inject(method = "tick()V", at = @At("HEAD"))
    protected void preTick(final CallbackInfo info) {
        Anvil.fire(new PlayerTickEvent.Pre(thiz()));
    }

    @Override
    @Inject(method = "tick()V", at = @At("RETURN"))
    protected void postTick(final CallbackInfo info) {
        Anvil.fire(new PlayerTickEvent.Post(thiz()));
    }

    protected PlayerEntity thiz() {
        return (PlayerEntity) (Object) this;
    }
}
