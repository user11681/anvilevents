package user11681.anvilevents.mixin.world;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvil.event.EventInvoker;
import user11681.anvilevents.event.entity.player.PlayerChangedDimensionEvent;
import user11681.anvilevents.event.entity.player.PlayerConnectedEvent;
import user11681.anvilevents.event.entity.player.PlayerRespawnedEvent;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    @Inject(method = "onPlayerConnected", at = @At("HEAD"))
    protected void onOnPlayerConnected(final ServerPlayerEntity player, final CallbackInfo info) {
        EventInvoker.fire(new PlayerConnectedEvent(player, thiz()));
    }

    @Inject(method = "onPlayerChangeDimension", at = @At("HEAD"))
    protected void onOnPlayerChangeDimension(final ServerPlayerEntity player, final CallbackInfo info) {
        EventInvoker.fire(new PlayerChangedDimensionEvent(player, thiz()));
    }

    @Inject(method = "onPlayerRespawned", at = @At("HEAD"))
    protected void onOnPlayerRespawned(final ServerPlayerEntity player, final CallbackInfo info) {
        EventInvoker.fire(new PlayerRespawnedEvent(player, thiz()));
    }

    protected ServerWorld thiz() {
        return (ServerWorld) (Object) this;
    }
}
