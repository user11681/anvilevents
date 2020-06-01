package user11681.anvilevents;

import java.util.Collection;
import java.util.Collections;
import user11681.anvil.entrypoint.CommonListenerInitializer;
import user11681.anvil.event.Listener;
import user11681.anvilevents.event.entity.EntityLandEvent;

public class Listeners implements CommonListenerInitializer {
    @Listener
    public static void listen(final EntityLandEvent event) {
        event.setFail();
    }

    @Override
    public Collection<Class<?>> get() {
        return Collections.singleton(this.getClass());
    }
}
