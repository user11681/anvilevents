package user11681.anvilevents.mixin.i18n;

import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.event.i18n.TranslationEvent;

@Mixin(Language.class)
public abstract class LanguageMixin {
    @Shadow
    protected abstract String getTranslation(final String key);

    /**
     * This injection adds a hook in {@link Language#translate} to {@link TranslationEvent}
     * for modification of translation results.
     */
    @Inject(method = "translate(Ljava/lang/String;)Ljava/lang/String;", at = @At("RETURN"), cancellable = true)
    protected synchronized void translate(final String key, final CallbackInfoReturnable<String> info) {
        final TranslationEvent event = new TranslationEvent(info.getReturnValue(), key).fire();

        switch (event.getResult()) {
            case PASS:
                info.setReturnValue(this.getTranslation(event.getKey()));
                break;
            case FAIL:
                info.setReturnValue(key);
                break;
            default:
                info.setReturnValue(event.getValue());
        }
    }
}
