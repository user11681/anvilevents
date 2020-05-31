package user11681.anvilevents.mixin.client;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvilevents.event.client.gui.RenderTooltipEvent;

@Environment(EnvType.CLIENT)
@Mixin(Screen.class)
public abstract class ScreenMixin {
    protected boolean available = true;

    @Inject(method = "renderTooltip(Ljava/util/List;II)V", at = @At("HEAD"), cancellable = true)
    protected void preRenderTooltip(final List<String> tooltip, final int x, final int y, final CallbackInfo info) {
        if (this.available) {
            final RenderTooltipEvent event = new RenderTooltipEvent.Pre(thiz(), tooltip, x, y).fire();

            if (!event.isFail()) {
                this.available = false;
                event.getScreen().renderTooltip(event.getTooltip(), event.getX(), event.getY());
                this.available = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "renderTooltip(Ljava/util/List;II)V", at = @At("RETURN"))
    protected void postRenderTooltip(final List<String> tooltip, final int x, final int y, final CallbackInfo info) {
        new RenderTooltipEvent.Post(thiz(), tooltip, x, y).fire();
    }

    protected Screen thiz() {
        return (Screen) (Object) this;
    }
}
