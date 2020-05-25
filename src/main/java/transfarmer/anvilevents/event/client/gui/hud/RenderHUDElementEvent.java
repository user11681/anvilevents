package transfarmer.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;
import transfarmer.anvil.event.Event;

@Environment(EnvType.CLIENT)
public abstract class RenderHUDElementEvent extends Event {
    protected final InGameHud hud;
    protected final Element element;

    public RenderHUDElementEvent(final InGameHud hud, final Element element) {
        this.hud = hud;
        this.element = element;
    }

    public InGameHud getHud() {
        return hud;
    }

    public Element getElement() {
        return element;
    }

    public static class Element {
        protected static int nextID = 0;

        public static final Element TEXT_BACKGROUND = new Element(nextID++);
        public static final Element CROSSHAIR = new Element(nextID++);
        public static final Element EXPERIENCE_BAR = new Element(nextID++);
        public static final Element HELD_TOOLTIP = new Element(nextID++);
        public static final Element HOTBAR = new Element(nextID++);
        public static final Element HOTBAR_ITEM= new Element(nextID++);
        public static final Element JUMP_BAR = new Element(nextID++);
        public static final Element MOUNT_HEALTH = new Element(nextID++);
        public static final Element PORTAL = new Element(nextID++);
        public static final Element PUMPKIN = new Element(nextID++);
        public static final Element SCOREBOARD_SIDEBAR = new Element(nextID++);
        public static final Element STATUS_BARS = new Element(nextID++);
        public static final Element STATUS_EFFECTS = new Element(nextID++);
        public static final Element VIGNETTE = new Element(nextID++);

        protected final int id;

        public Element(final int id) {
            this.id = id;
        }

        @Override
        public boolean equals(final Object other) {
            return other instanceof Element && ((Element) other).id == this.id;
        }
    }
}
