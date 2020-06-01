package user11681.anvilevents.event.client.gui.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;

public abstract class RenderStackTooltipEvent extends ScreenRenderEvent {
    protected ItemStack stack;

    public RenderStackTooltipEvent(final Screen screen, final ItemStack stack, final int x, final int y) {
        super(screen, x, y);

        this.stack = stack;
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public void setStack(final ItemStack stack) {
        this.stack = stack;
    }

    public static class Pre extends RenderStackTooltipEvent {
        public Pre(final Screen screen, final ItemStack stack, final int x, final int y) {
            super(screen, stack, x, y);
        }
    }

    public static class Post extends RenderStackTooltipEvent {
        public Post(final Screen screen, final ItemStack stack, final int x, final int y) {
            super(screen, stack, x, y);
        }
    }
}
