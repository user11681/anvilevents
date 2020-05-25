package transfarmer.anvilevents.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Mouse;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import transfarmer.anvil.event.EventInvoker;
import transfarmer.anvilevents.duck.client.mouse.MouseDuck;
import transfarmer.anvilevents.event.client.mouse.CursorPosEvent;
import transfarmer.anvilevents.event.client.mouse.MouseButtonEvent;
import transfarmer.anvilevents.event.client.mouse.MouseScrollEvent;

@SuppressWarnings("UnresolvedMixinReference")
@Environment(EnvType.CLIENT)
@Mixin(Mouse.class)
@Implements(@Interface(iface = MouseDuck.class, prefix = "duck$"))
public abstract class MouseMixin implements MouseDuck {
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

    @Override
    public void onCursorPosDuck(final long window, final double x, final double y) {
        this.onCursorPos(window, x, y);
    }

    @Override
    public void onMouseButtonDuck(final long window, final int button, final int action, final int mods) {
        this.onMouseButton(window, button, action, mods);
    }

    @Override
    public void onMouseScrollDuck(final long window, final double dX, final double dY) {
        this.onMouseScroll(window, dX, dY);
    }

    @Redirect(method = "method_22689(JDD)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Mouse;onCursorPos(JDD)V"))
    public void setupOnCursorPos(final Mouse mouse, final long window, final double x, final double y) {
        final CursorPosEvent event = EventInvoker.fire(new CursorPosEvent(window, x, y));

        if (event.getResult() != ActionResult.FAIL) {
            ((MouseDuck) mouse).onCursorPosDuck(event.getWindow(), event.getX(), event.getY());
        }
    }

    @Redirect(method = "method_22686(JIII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Mouse;onMouseButton(JIII)V"))
    public void setupOnMouseButton(final Mouse mouse, final long window, final int button, final int action,
                                   final int mods) {
        final MouseButtonEvent event = EventInvoker.fire(new MouseButtonEvent(window, mouse.getX(), mouse.getY(), button, action, mods));

        if (event.getResult() != ActionResult.FAIL) {
            this.x = event.getX();
            this.y = event.getY();

            ((MouseDuck) mouse).onMouseButtonDuck(event.getWindow(), event.getButton(), event.getAction(), event.getMods());
        }
    }

    @Redirect(method = "method_22687(JDD)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Mouse;onMouseScroll(JDD)V"))
    public void setupOnMouseScroll(final Mouse mouse, final long window, final double dX, final double dY) {
        final MouseScrollEvent event = EventInvoker.fire(new MouseScrollEvent(window, mouse.getX(), mouse.getY(), dX, dY));

        if (event.getResult() != ActionResult.FAIL) {
            this.x = event.getX();
            this.y = event.getY();

            ((MouseDuck) mouse).onMouseScrollDuck(event.getWindow(), event.getDX(), event.getDY());
        }
    }
}
