package user11681.anvilevents.mixin.mixin.item;

import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.event.client.ItemTooltipEvent;

@Environment(EnvType.CLIENT)
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    protected final ItemStack thiz = (ItemStack) (Object) this;

    protected boolean tooltip = true;

    @Environment(EnvType.CLIENT)
    @Inject(method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    protected void getTooltip(final PlayerEntity player, final TooltipContext context, final CallbackInfoReturnable<List<Text>> info) {
        if (this.tooltip) {
            final ItemTooltipEvent event = new ItemTooltipEvent(player, thiz, context, info.getReturnValue()).fire();

            switch (event.result) {
                case PASS:
                    this.tooltip = false;
                    info.setReturnValue(event.getItemStack().getTooltip(event.getPlayer(), event.getContext()));
                    this.tooltip = true;
                    break;
                case FAIL:
                    info.setReturnValue(new ArrayList<>());
                    break;
                default:
                    info.setReturnValue(event.getTooltip());
            }
        }
    }
}
