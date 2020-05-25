package transfarmer.anvilevents.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.ActionResult;
import transfarmer.anvil.event.Anvil;
import transfarmer.anvil.event.Listener;
import transfarmer.anvilevents.event.client.gui.hud.RenderHUDElementEvent;
import transfarmer.anvilevents.event.client.gui.hud.RenderHUDElementEvent.Element;

@Anvil
public class Listeners {
    public static int count;

    @Environment(EnvType.CLIENT)
    @Listener
    public static void onRender(final RenderHUDElementEvent event) {
        event.setResult(event.getElement() == Element.HOTBAR && (count = (count + 1) % 400) < 300 ? ActionResult.FAIL : ActionResult.SUCCESS);
    }
}
