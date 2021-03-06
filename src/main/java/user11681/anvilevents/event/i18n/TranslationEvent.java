package user11681.anvilevents.event.i18n;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Language;
import user11681.anvil.event.AnvilEvent;

/**
 * This event is called when {@link I18n#translate} and {@link Language#translate} are called.
 * It allows listeners to modify translation key, arguments and return value of the method call.
 *
 * A {@link ActionResult#SUCCESS} {@link AnvilEvent#result} sets the return value to {@link TranslationEvent#value}
 * and cancels further processing.
 * A {@link ActionResult#CONSUME} {@link AnvilEvent#result} sets the return value to {@link TranslationEvent#value}
 * and does not cancel further processing.
 * A {@link ActionResult#PASS} {@link AnvilEvent#result} translates the new key and arguments
 * and does not cancel further processing.
 * A {@link ActionResult#FAIL} {@link AnvilEvent#result} sets the return value to {@link TranslationEvent#key}
 * and cancels further processing.
 */
public class TranslationEvent extends AnvilEvent {
    protected String value;
    protected String key;
    protected List<Object> args;

    public TranslationEvent(final String value, final String key, final Object... args) {
        super();

        this.key = key;
        this.args = new ArrayList<>(Arrays.asList(args));
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public List<Object> getArgs() {
        return this.args;
    }

    public void setArgs(final List<Object> args) {
        this.args = args;
    }

    /**
     * @return the return value of the translation
     */
    public String getValue() {
        return this.value;
    }

    public void setValue(final String value) {
        this.value = value;
        this.setAccepted();
    }
}
