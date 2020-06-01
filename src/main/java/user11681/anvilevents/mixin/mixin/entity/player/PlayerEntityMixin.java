package user11681.anvilevents.mixin.mixin.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvilevents.mixin.duck.entity.player.PlayerInventoryDuck;
import user11681.anvilevents.event.entity.player.PlayerDropInventoryEvent;
import user11681.anvilevents.event.entity.player.PlayerTickEvent;
import user11681.anvilevents.mixin.mixin.entity.LivingEntityMixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntityMixin {
    @Shadow
    protected abstract void dropInventory();

    protected final PlayerEntity thiz = (PlayerEntity) (Object) this;

    protected boolean drop = true;

    @Inject(method = "dropInventory()V", at = @At("HEAD"), cancellable = true)
    protected void onDropInventroy(final CallbackInfo info) {
        if (this.drop) {
            final PlayerDropInventoryEvent event = new PlayerDropInventoryEvent(thiz, ((PlayerInventoryDuck) thiz.inventory).getCombinedInventory()).fire();

            if (!event.isFail()) {
                this.drop = false;
                //noinspection ConstantConditions
                ((PlayerEntityMixin) (Object) event.getPlayer()).dropInventory();
                this.drop = true;
            }

            info.cancel();
        }
    }

    @Override
    @Inject(method = "tick()V", at = @At("HEAD"), cancellable = true)
    protected void preTick(final CallbackInfo info) {
        if (new PlayerTickEvent.Pre(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Override
    @Inject(method = "tick()V", at = @At("RETURN"))
    protected void postTick(final CallbackInfo info) {
        new PlayerTickEvent.Post(thiz).fire();
    }
}
