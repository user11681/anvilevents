package user11681.anvilevents.event.client.gui;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import user11681.anvilevents.event.client.gui.screen.ScreenRenderEvent;

@Environment(EnvType.CLIENT)
public abstract class RenderTooltipEvent extends ScreenRenderEvent {
    protected List<String> tooltip;

    public RenderTooltipEvent(final Screen screen, final List<String> tooltip, final int x, final int y) {
        super(screen, x, y);

        this.tooltip = tooltip;
    }

    public List<String> getTooltip() {
        return this.tooltip;
    }

    public void setTooltip(final List<String> tooltip) {
        this.tooltip = tooltip;
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
