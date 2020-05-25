package transfarmer.anvilevents.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import transfarmer.anvil.event.EventInvoker;
import transfarmer.anvilevents.event.tick.ClientTickEvent;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(final CallbackInfo info) {
        EventInvoker.fire(new ClientTickEvent((MinecraftClient) (Object) this));
    }
}
