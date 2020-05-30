package user11681.anvilevents.mixin.i18n;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvil.Anvil;
import user11681.anvilevents.event.i18n.TranslationEvent;

@Mixin(Language.class)
public abstract class LanguageMixin {
    @Shadow
    protected abstract String getTranslation(final String key);

    /**
     * This injection adds a hook in {@link Language#translate} to {@link TranslationEvent}
     * for modification of translation results.
     */
    @Inject(method = "translate", at = @At("RETURN"), cancellable = true)
    public synchronized void translate(final String key, final CallbackInfoReturnable<String> info) {
        final TranslationEvent event = Anvil.fire(new TranslationEvent(this.getTranslation(key), key));
        final ActionResult result = event.getResult();

        switch (result) {
            case SUCCESS:
            case CONSUME:
                info.setReturnValue(event.getValue());
                break;
            case FAIL:
                info.setReturnValue(key);
                break;
            default:
                info.setReturnValue(this.getTranslation(event.getKey()));
        }
    }
}
