package transfarmer.anvilevents;

import net.minecraft.util.ActionResult;
import transfarmer.anvil.event.Listener;
import transfarmer.anvilevents.event.TranslationEvent;

public class Listeners {
    @Listener
    public static void onTranslation(final TranslationEvent event) {
        event.setResult(ActionResult.FAIL);
    }
}
