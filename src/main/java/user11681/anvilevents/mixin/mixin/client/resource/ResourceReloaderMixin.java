package user11681.anvilevents.mixin.mixin.client.resource;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.event.client.LoadResourcesEvent;

@Environment(EnvType.CLIENT)
@Mixin(ResourceReloader.class)
public class ResourceReloaderMixin {
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "method_18366(Ljava/util/List;)Lnet/minecraft/util/Unit;", at = @At(value = "RETURN"))
    private static void onReloadResources(final List<ResourcePack> resourcePacks, final CallbackInfoReturnable<Unit> info) {
        new LoadResourcesEvent.Launch().fire();
    }
}
