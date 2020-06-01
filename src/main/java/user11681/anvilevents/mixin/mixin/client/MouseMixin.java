package user11681.anvilevents.mixin.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvilevents.event.client.mouse.CursorPosEvent;
import user11681.anvilevents.event.client.mouse.MouseButtonEvent;
import user11681.anvilevents.event.client.mouse.MouseScrollEvent;

@Environment(EnvType.CLIENT)
@Mixin(Mouse.class)
public abstract class MouseMixin {
    @Shadow
    private double x;
    @Shadow
    private double y;
    @Shadow
    protected abstract void onCursorPos(long window, double x, double y);

    @Shadow
    protected abstract void onMouseButton(long window, int button, int action, int mods);

    @Shadow
    protected abstract void onMouseScroll(long window, double dX, double dY);

    protected final Mouse thiz = (Mouse) (Object) this;

    protected boolean pos = true;
    protected boolean button = true;
    protected boolean scroll = true;

    @Inject(method = "onCursorPos(JDD)V", at = @At("HEAD"), cancellable = true)
    protected void onOnCursorPos(final long window, final double x, final double y, final CallbackInfo info) {
        if (this.pos) {
            final CursorPosEvent event = new CursorPosEvent(thiz, window, x, y).fire();

            if (!event.isFail()) {
                this.pos = false;
                this.onCursorPos(event.getWindow(), event.getX(), event.getY());
                this.pos = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "onMouseButton(JIII)V", at = @At("HEAD"), cancellable = true)
    protected void onOnMouseButton(final long window, final int button, final int action, final int mods, final CallbackInfo info) {
        if (this.button) {
            final MouseButtonEvent event = new MouseButtonEvent(thiz, window, this.x, this.y, button, action, mods).fire();

            if (!event.isFail()) {
                this.x = event.getX();
                this.y = event.getY();

                this.button = false;
                this.onMouseButton(event.getWindow(), event.getButton(), event.getAction(), event.getMods());
                this.button = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "onMouseScroll(JDD)V", at = @At("HEAD"), cancellable = true)
    protected void onOnMouseScroll(final long window, final double dX, final double dY, final CallbackInfo info) {
        if (this.scroll) {
            final MouseScrollEvent event = new MouseScrollEvent(thiz, window, this.x, this.y, dX, dY).fire();

            if (!event.isFail()) {
                this.x = event.getX();
                this.y = event.getY();

                this.scroll = false;
                this.onMouseScroll(event.getWindow(), event.getDX(), event.getDY());
                this.scroll = true;
            }

            info.cancel();
        }
    }
}
