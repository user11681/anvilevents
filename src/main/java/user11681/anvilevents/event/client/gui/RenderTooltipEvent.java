package user11681.anvilevents.event.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import user11681.anvil.event.Event;

import java.util.List;

@Environment(EnvType.CLIENT)
public abstract class RenderTooltipEvent extends Event {
    protected Screen screen;
    protected List<String> tooltip;
    protected int x;
    protected int y;

    public RenderTooltipEvent(final Screen screen, final List<String> tooltip, final int x, final int y) {
        this.screen = screen;
        this.tooltip = tooltip;
        this.x = x;
        this.y = y;
    }

    public List<String> getTooltip() {
        return tooltip;
    }

    public void setTooltip(final List<String> tooltip) {
        this.tooltip = tooltip;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(final Screen screen) {
        this.screen = screen;
    }

    public static class Pre extends RenderTooltipEvent {
        public Pre(final Screen screen, final List<String> tooltip, final int x, final int y) {
            super(screen, tooltip, x, y);
        }
    }

    public static class Post extends RenderTooltipEvent {
        public Post(final Screen screen, final List<String> tooltip, final int x, final int y) {
            super(screen, tooltip, x, y);
        }
    }
}
