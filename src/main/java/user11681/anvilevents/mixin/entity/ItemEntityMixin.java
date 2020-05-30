package user11681.anvilevents.mixin.entity;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import user11681.anvil.Anvil;
import user11681.anvilevents.event.entity.player.ItemPickupEvent;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    protected boolean collide = true;

    @Inject(method = "onPlayerCollision(Lnet/minecraft/entity/player/PlayerEntity;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;insertStack(Lnet/minecraft/item/ItemStack;)Z"),
            cancellable = true)
    protected void onOnPlayerCollision(final PlayerEntity player, final CallbackInfo info) {
        if (this.collide) {
            final ItemPickupEvent event = Anvil.fire(new ItemPickupEvent(player, thiz()));

            if (!event.isFail()) {
                this.collide = false;
                event.getItemEntity().onPlayerCollision(event.getPlayer());
                this.collide = true;
            }

            info.cancel();
        }
    }

    protected ItemEntity thiz() {
        return (ItemEntity) (Object) this;
    }
}
