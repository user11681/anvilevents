package user11681.anvilevents.mixin.entity.player;

import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvilevents.event.entity.player.PlayerCopyEvent;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntityMixin {
    protected boolean copy = true;

    @Inject(method = "requestTeleport(DDD)V", at = @At("HEAD"), cancellable = true)
    protected void onRequestTeleport(final double x, final double y, final double z, final CallbackInfo info) {
        super.onRequestTeleport(x, y, z, info);
    }

    @Inject(method = "copyFrom(Lnet/minecraft/server/network/ServerPlayerEntity;Z)V", at = @At("HEAD"), cancellable = true)
    protected void onCopyFrom(final ServerPlayerEntity oldPlayer, final boolean alive, final CallbackInfo info) {
        if (this.copy) {
            final PlayerCopyEvent event = new PlayerCopyEvent(thiz, oldPlayer, alive).fire();

            if (!event.isFail()) {
                this.copy = false;
                ((ServerPlayerEntity) event.getPlayer()).copyFrom(event.getOldPlayer(), event.isAlive());
                this.copy = true;
            }

            info.cancel();
        }
    }
}
