package user11681.anvilevents.event.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import transfarmer.anvil.event.Event;

@Environment(EnvType.CLIENT)
public abstract class ClientTickEvent extends Event {
    protected final MinecraftClient client;

    public ClientTickEvent(final MinecraftClient client) {
        this.client = client;
    }

    public MinecraftClient getClient() {
        return this.client;
    }

    @Environment(EnvType.CLIENT)
    public static class Pre extends ClientTickEvent {
        public Pre(final MinecraftClient client) {
            super(client);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Post extends ClientTickEvent {
        public Post(final MinecraftClient client) {
            super(client);
        }
    }
}
