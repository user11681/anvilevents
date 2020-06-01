package user11681.anvilevents.mixin.mixin.client;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvilevents.event.client.gui.RenderTooltipEvent;
import user11681.anvilevents.event.client.gui.screen.RenderStackTooltipEvent;

@Environment(EnvType.CLIENT)
@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Shadow
    protected abstract void renderTooltip(final ItemStack stack, final int x, final int y);

    protected final Screen thiz = (Screen) (Object) this;

    private static boolean allTooltips = true;
    private static boolean stackTooltips = true;

    @Inject(method = "renderTooltip(Ljava/util/List;II)V", at = @At("HEAD"), cancellable = true)
    protected void preRenderTooltip(final List<String> tooltip, final int x, final int y, final CallbackInfo info) {
        if (allTooltips) {
            final RenderTooltipEvent event = new RenderTooltipEvent.Pre(thiz, tooltip, x, y).fire();

            if (!event.isFail()) {
                allTooltips = false;
                event.getScreen().renderTooltip(event.getTooltip(), event.getX(), event.getY());
                allTooltips = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "renderTooltip(Ljava/util/List;II)V", at = @At("RETURN"))
    protected void postRenderTooltip(final List<String> tooltip, final int x, final int y, final CallbackInfo info) {
        new RenderTooltipEvent.Post(thiz, tooltip, x, y).fire();
    }

    @Inject(method = "renderTooltip(Lnet/minecraft/item/ItemStack;II)V", at = @At("HEAD"), cancellable = true)
    protected void preRenderStackTooltip(final ItemStack stack, final int x, final int y, final CallbackInfo info) {
        if (stackTooltips) {
            final RenderStackTooltipEvent event = new RenderStackTooltipEvent.Pre(thiz, stack, x, y).fire();

            if (!event.isFail()) {
                stackTooltips = false;
                //noinspection ConstantConditions
                ((ScreenMixin) (Object) event.getScreen()).renderTooltip(event.getStack(), event.getX(), event.getY());
                stackTooltips = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "renderTooltip(Lnet/minecraft/item/ItemStack;II)V", at = @At("RETURN"))
    protected void postRenderStackTooltip(final ItemStack stack, final int x, final int y, final CallbackInfo info) {
        new RenderStackTooltipEvent.Post(thiz, stack, x, y).fire();
    }
}
