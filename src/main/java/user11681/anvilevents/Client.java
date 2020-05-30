package user11681.anvilevents;

import java.util.Arrays;
import java.util.Collection;
import user11681.anvil.entrypoint.ClientEventInitializer;
import user11681.anvil.event.Event;
import user11681.anvilevents.event.client.ClientTickEvent;
import user11681.anvilevents.event.client.ItemTooltipEvent;
import user11681.anvilevents.event.client.gui.RenderTooltipEvent;
import user11681.anvilevents.event.client.gui.hud.RenderCrosshairEvent;
import user11681.anvilevents.event.client.gui.hud.RenderExperienceBarEvent;
import user11681.anvilevents.event.client.gui.hud.RenderHeldTooltipEvent;
import user11681.anvilevents.event.client.gui.hud.RenderHotbarEvent;
import user11681.anvilevents.event.client.gui.hud.RenderHotbarItemEvent;
import user11681.anvilevents.event.client.gui.hud.RenderHudEvent;
import user11681.anvilevents.event.client.gui.hud.RenderJumpBarEvent;
import user11681.anvilevents.event.client.gui.hud.RenderMountHealthEvent;
import user11681.anvilevents.event.client.gui.hud.RenderPortalOverlayEvent;
import user11681.anvilevents.event.client.gui.hud.RenderPumpkinOverlayEvent;
import user11681.anvilevents.event.client.gui.hud.RenderScoreboardSidebarEvent;
import user11681.anvilevents.event.client.gui.hud.RenderStatusBarsEvent;
import user11681.anvilevents.event.client.gui.hud.RenderStatusEffectsEvent;
import user11681.anvilevents.event.client.gui.hud.RenderTextBackgroundEvent;
import user11681.anvilevents.event.client.gui.hud.RenderVignetteEvent;
import user11681.anvilevents.event.client.mouse.CursorPosEvent;
import user11681.anvilevents.event.client.mouse.MouseButtonEvent;
import user11681.anvilevents.event.client.mouse.MouseScrollEvent;

public class Client implements ClientEventInitializer {
    @Override
    public Collection<Class<? extends Event>> get() {;
        return Arrays.asList(
                RenderCrosshairEvent.class,
                RenderExperienceBarEvent.class,
                RenderHeldTooltipEvent.class,
                RenderHotbarEvent.class,
                RenderHotbarItemEvent.class,
                RenderHudEvent.class,
                RenderJumpBarEvent.class,
                RenderMountHealthEvent.class,
                RenderPortalOverlayEvent.class,
                RenderPumpkinOverlayEvent.class,
                RenderScoreboardSidebarEvent.class,
                RenderStatusBarsEvent.class,
                RenderStatusEffectsEvent.class,
                RenderTextBackgroundEvent.class,
                RenderVignetteEvent.class,

                RenderTooltipEvent.Pre.class,
                RenderTooltipEvent.Post.class,

                ItemTooltipEvent.class,

                CursorPosEvent.class,
                MouseButtonEvent.class,
                MouseScrollEvent.class,

                ClientTickEvent.Pre.class,
                ClientTickEvent.Post.class
        );
    }
}
