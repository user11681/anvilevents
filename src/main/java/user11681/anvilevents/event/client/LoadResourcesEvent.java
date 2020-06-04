package user11681.anvilevents.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import user11681.anvil.event.AnvilEvent;

public abstract class LoadResourcesEvent extends AnvilEvent {
    public static class Launch extends LoadResourcesEvent {}

    @Environment(EnvType.CLIENT)
    public static class Reload extends LoadResourcesEvent {}
}
