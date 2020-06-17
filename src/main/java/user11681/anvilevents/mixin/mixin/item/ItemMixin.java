package user11681.anvilevents.mixin.mixin.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.event.item.UseItemEvent;

@Mixin(Item.class)
public abstract class ItemMixin {
    private boolean use = true;

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    protected void onUse(final World world, final PlayerEntity user, final Hand hand, final CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
        if (this.use) {
            final UseItemEvent event = new UseItemEvent(user.getStackInHand(hand), world, user, hand).fire();
            final ActionResult result = event.result;
            final ItemStack itemStack = event.getItemStack();

            if (result == ActionResult.FAIL) {
                info.setReturnValue(new TypedActionResult<>(result, itemStack));
            } else {
                this.use = false;
                info.setReturnValue(itemStack.use(event.getWorld(), event.getUser(), event.getHand()));
                this.use = true;
            }
        }
    }
}
