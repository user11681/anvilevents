package user11681.anvilevents.mixin.mixin.world;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvilevents.event.entity.player.PlayerChangedDimensionEvent;
import user11681.anvilevents.event.entity.player.PlayerConnectedEvent;
import user11681.anvilevents.event.entity.player.PlayerRespawnedEvent;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
    protected final ServerWorld thiz = (ServerWorld) (Object) this;

    @Inject(method = "onPlayerConnected", at = @At("HEAD"))
    protected void onOnPlayerConnected(final ServerPlayerEntity player, final CallbackInfo info) {
        new PlayerConnectedEvent(player, thiz).fire();
    }

    @Inject(method = "onPlayerChangeDimension", at = @At("HEAD"))
    protected void onOnPlayerChangeDimension(final ServerPlayerEntity player, final CallbackInfo info) {
        new PlayerChangedDimensionEvent(player, thiz).fire();
    }

    @Inject(method = "onPlayerRespawned", at = @At("HEAD"))
    protected void onOnPlayerRespawned(final ServerPlayerEntity player, final CallbackInfo info) {
        new PlayerRespawnedEvent(player, thiz).fire();
    }
}
