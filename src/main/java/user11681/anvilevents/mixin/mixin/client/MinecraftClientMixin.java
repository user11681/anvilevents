package user11681.anvilevents.mixin.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvilevents.event.client.ClientTickEvent;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    protected final MinecraftClient thiz = (MinecraftClient) (Object) this;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void preTick(final CallbackInfo info) {
        if (new ClientTickEvent.Pre(thiz).fire().isFail()) {
            info.cancel();
        }
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void postTick(final CallbackInfo info) {
        new ClientTickEvent.Post(thiz).fire();
    }
}
