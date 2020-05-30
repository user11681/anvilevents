package user11681.anvilevents.mixin.entity.player;

import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvil.Anvil;
import user11681.anvilevents.duck.entity.player.PlayerInventoryDuck;
import user11681.anvilevents.event.entity.player.PlayerDropInventoryEvent;
import user11681.anvilevents.event.entity.player.PlayerTickEvent;
import user11681.anvilevents.mixin.entity.LivingEntityMixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntityMixin {
    @Shadow
    protected abstract void dropInventory();

    protected boolean drop = true;

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
