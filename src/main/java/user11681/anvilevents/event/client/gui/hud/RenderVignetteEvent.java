package user11681.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public class RenderVignetteEvent extends RenderHudElementEvent {
    protected Entity entity;

    public RenderVignetteEvent(final InGameHud hud, final Entity entity) {
        super(hud, Element.VIGNETTE);

        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(final Entity entity) {
        this.entity = entity;
    }
}
