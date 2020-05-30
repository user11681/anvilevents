package user11681.anvilevents;

import java.util.Arrays;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import user11681.anvil.entrypoint.EventInitializer;
import user11681.anvil.event.Event;
import user11681.anvilevents.event.block.BlockDropEvent;
import user11681.anvilevents.event.client.ClientTickEvent;
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
import user11681.anvilevents.event.entity.EnderTeleportEvent;
import user11681.anvilevents.event.entity.EntityDamageEvent;
import user11681.anvilevents.event.entity.EntityLandEvent;
import user11681.anvilevents.event.entity.living.LivingCollisionEvent;
import user11681.anvilevents.event.entity.living.LivingDeathEvent;
import user11681.anvilevents.event.entity.living.LivingDropExperienceEvent;
import user11681.anvilevents.event.entity.living.LivingKnockbackEvent;
import user11681.anvilevents.event.entity.living.LivingTickEvent;
import user11681.anvilevents.event.entity.player.BreakSpeedEvent;
import user11681.anvilevents.event.entity.player.ItemPickupEvent;
import user11681.anvilevents.event.entity.player.PlayerChangedDimensionEvent;
import user11681.anvilevents.event.entity.player.PlayerConnectedEvent;
import user11681.anvilevents.event.entity.player.PlayerCopyEvent;
import user11681.anvilevents.event.entity.player.PlayerDropInventoryEvent;
import user11681.anvilevents.event.entity.player.PlayerRespawnedEvent;
import user11681.anvilevents.event.entity.player.PlayerTickEvent;
import user11681.anvilevents.event.entity.player.UseBlockEvent;
import user11681.anvilevents.event.i18n.TranslationEvent;
import user11681.anvilevents.event.item.ItemEvent;
import user11681.anvilevents.event.item.ItemTooltipEvent;
import user11681.anvilevents.event.item.UseItemEvent;

public class Main implements EventInitializer {
    public static final String MOD_ID = "anvilevents";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public Collection<Class<? extends Event>> get() {
        return Arrays.asList(
                BlockDropEvent.class,

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

                CursorPosEvent.class,
                MouseButtonEvent.class,
                MouseScrollEvent.class,

                ClientTickEvent.Pre.class,
                ClientTickEvent.Post.class,

                LivingCollisionEvent.class,
                LivingDeathEvent.class,
                LivingDropExperienceEvent.class,
                LivingKnockbackEvent.class,
                LivingTickEvent.Pre.class,
                LivingTickEvent.Post.class,

                BreakSpeedEvent.class,
                ItemPickupEvent.class,
                PlayerChangedDimensionEvent.class,
                PlayerConnectedEvent.class,
                PlayerCopyEvent.class,
                PlayerDropInventoryEvent.class,
                PlayerRespawnedEvent.class,
                PlayerTickEvent.Pre.class,
                PlayerTickEvent.Post.class,

                UseBlockEvent.class,

                EnderTeleportEvent.class,
                EntityDamageEvent.Pre.class,
                EntityDamageEvent.Post.class,
                EntityLandEvent.class,

                TranslationEvent.class,

                ItemEvent.class,
                ItemTooltipEvent.class,
                UseItemEvent.class
        );
    }
}
