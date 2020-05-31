package user11681.anvilevents;

import java.util.Arrays;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import user11681.anvil.entrypoint.CommonEventInitializer;
import user11681.anvil.event.Event;
import user11681.anvilevents.event.block.BlockDropEvent;
import user11681.anvilevents.event.entity.EnderTeleportEvent;
import user11681.anvilevents.event.entity.EntityDamageEvent;
import user11681.anvilevents.event.entity.EntityLandEvent;
import user11681.anvilevents.event.entity.EntityTickEvent;
import user11681.anvilevents.event.entity.living.LivingCollisionEvent;
import user11681.anvilevents.event.entity.living.LivingDeathEvent;
import user11681.anvilevents.event.entity.living.LivingDropExperienceEvent;
import user11681.anvilevents.event.entity.living.LivingKnockbackEvent;
import user11681.anvilevents.event.entity.living.LivingTickEvent;
import user11681.anvilevents.event.entity.player.BlockBreakSpeedEvent;
import user11681.anvilevents.event.entity.player.ItemPickupEvent;
import user11681.anvilevents.event.entity.player.PlayerChangedDimensionEvent;
import user11681.anvilevents.event.entity.player.PlayerConnectedEvent;
import user11681.anvilevents.event.entity.player.PlayerCopyEvent;
import user11681.anvilevents.event.entity.player.PlayerDropInventoryEvent;
import user11681.anvilevents.event.entity.player.PlayerRespawnedEvent;
import user11681.anvilevents.event.entity.player.PlayerTickEvent;
import user11681.anvilevents.event.entity.player.UseBlockEvent;
import user11681.anvilevents.event.i18n.TranslationEvent;
import user11681.anvilevents.event.item.UseItemEvent;

public class Main implements CommonEventInitializer {
    public static final String MOD_ID = "anvilevents";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public Collection<Class<? extends Event>> get() {
        return Arrays.asList(
                BlockDropEvent.class,

                EnderTeleportEvent.class,
                EntityDamageEvent.Pre.class,
                EntityDamageEvent.Post.class,
                EntityLandEvent.class,
                EntityTickEvent.Pre.class,
                EntityTickEvent.Post.class,

                LivingCollisionEvent.class,
                LivingDeathEvent.class,
                LivingDropExperienceEvent.class,
                LivingKnockbackEvent.class,
                LivingTickEvent.Pre.class,
                LivingTickEvent.Post.class,

                BlockBreakSpeedEvent.class,
                ItemPickupEvent.class,
                PlayerChangedDimensionEvent.class,
                PlayerConnectedEvent.class,
                PlayerCopyEvent.class,
                PlayerDropInventoryEvent.class,
                PlayerRespawnedEvent.class,
                PlayerTickEvent.Pre.class,
                PlayerTickEvent.Post.class,
                UseBlockEvent.class,

                TranslationEvent.class,

                UseItemEvent.class
        );
    }
}
