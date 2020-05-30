package user11681.anvilevents.mixin.i18n;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvil.Anvil;
import user11681.anvilevents.event.i18n.TranslationEvent;

@Environment(EnvType.CLIENT)
@Mixin(I18n.class)
public abstract class I18nMixin {
    @Shadow
    private static TranslationStorage storage;

    /**
     * This injection adds a hook in {@link I18n#translate(String, Object...)} to {@link TranslationEvent}
     * for modification of translation results.
     */
    @Inject(method = "translate", at = @At("RETURN"), cancellable = true)
    private static void translate(final String key, final Object[] args, final CallbackInfoReturnable<String> info) {
        final TranslationEvent event = Anvil.fire(new TranslationEvent(storage.translate(key, args), key, args));
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
                info.setReturnValue(storage.translate(event.getKey(), event.getArgs().toArray()));
        }
    }
}
