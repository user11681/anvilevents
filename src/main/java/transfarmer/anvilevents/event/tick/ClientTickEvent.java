package transfarmer.anvilevents.event.tick;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ClientTickEvent extends TickEvent {
    protected final MinecraftClient client;

    public ClientTickEvent(final MinecraftClient client) {
        this.client = client;
    }

    public MinecraftClient getClient() {
        return this.client;
    }
}
