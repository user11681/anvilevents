package user11681.anvilevents.mixin.mixin.i18n;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.event.i18n.TranslationEvent;
import user11681.anvilevents.mixin.Flags;

@Environment(EnvType.CLIENT)
@Mixin(I18n.class)
public abstract class I18nMixin {
    @Shadow
    public static String translate(final String key, final Object... args) {
        return null;
    }

    /**
     * This injection adds a hook in {@link I18n#translate(String, Object...)} to {@link TranslationEvent}
     * for modification of translation results.
     */
    @Inject(method = "translate(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", at = @At("RETURN"), cancellable = true)
    private static void translate(final String key, final Object[] args, final CallbackInfoReturnable<String> info) {
        if (Flags.translate) {
            final TranslationEvent event = new TranslationEvent(info.getReturnValue(), key, args).fire();

            switch (event.result) {
                case PASS:
                    Flags.translate = false;
                    info.setReturnValue(translate(event.getKey(), event.getArgs().toArray()));
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
