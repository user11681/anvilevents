package user11681.anvilevents.mixin.mixin.i18n;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.event.i18n.TranslationEvent;
import user11681.anvilevents.mixin.Flags;

@Mixin(TranslationStorage.class)
public abstract class TranslationStorageMixin {
    @Shadow
    public abstract String get(final String string);

    /**
     * This injection adds a hook in {@link Language#get} to {@link TranslationEvent}.
     */
    @Inject(method = "get(Ljava/lang/String;)Ljava/lang/String;", at = @At("RETURN"), cancellable = true)
    protected void get(final String key, final CallbackInfoReturnable<String> info) {
        if (Flags.translate) {
            final TranslationEvent event = new TranslationEvent(info.getReturnValue(), key).fire();

            switch (event.result) {
                case PASS:
                    Flags.translate = false;
                    info.setReturnValue(this.get(event.getKey()));
                    Flags.translate = true;
                    break;
                case FAIL:
                    info.setReturnValue(key);
                    break;
                default:
                    info.setReturnValue(event.getValue());
            }
        }
    }
}
