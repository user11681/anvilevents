package user11681.anvilevents.mixin.item;

import com.sun.istack.internal.Nullable;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import transfarmer.anvil.event.EventInvoker;
import user11681.anvilevents.event.item.ItemTooltipEvent;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract List<Text> getTooltip(@Nullable final PlayerEntity player, final TooltipContext context);

    protected boolean tooltip = true;

    @Environment(EnvType.CLIENT)
    @Inject(method = "getTooltip(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/client/item/TooltipContext;)Ljava/util/List;", at = @At("RETURN"), cancellable = true)
    protected void getTooltip(final PlayerEntity player, final TooltipContext context, final CallbackInfoReturnable<List<Text>> info) {
        if (this.tooltip) {
            final ItemTooltipEvent event = EventInvoker.fire(new ItemTooltipEvent(thiz(), player, context, info.getReturnValue()));

            if (event.getResult() == ActionResult.FAIL) {
                info.setReturnValue(new ArrayList<>());
            } else {
                this.tooltip = false;
                info.setReturnValue(event.getItemStack().getTooltip(event.getPlayer(), event.getContext()));
                this.tooltip = true;
            }
        }
    }

    protected ItemStack thiz() {
        return (ItemStack) (Object) this;
    }
}
