package user11681.anvilevents.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvil.event.EventInvoker;
import user11681.anvilevents.event.client.ClientTickEvent;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void preTick(final CallbackInfo info) {
        EventInvoker.fire(new ClientTickEvent.Pre(thiz()));
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void postTick(final CallbackInfo info) {
        EventInvoker.fire(new ClientTickEvent.Post(thiz()));
    }

    protected MinecraftClient thiz() {
        return (MinecraftClient) (Object) this;
    }
}
