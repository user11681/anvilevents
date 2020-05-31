package user11681.anvilevents.mixin.block;

import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvil.Anvil;
import user11681.anvilevents.event.block.BlockDropEvent;
import user11681.anvilevents.event.entity.EntityLandEvent;
import user11681.anvilevents.event.entity.player.BlockBreakSpeedEvent;

@Mixin(Block.class)
public abstract class BlockMixin {
    private static BlockDropEvent event;

    protected boolean land = true;
    protected boolean delta = true;

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;)Ljava/util/List;",
            at = @At("RETURN"),
            cancellable = true)
    private static void onGetDroppedStacks(final BlockState state, final ServerWorld world, final BlockPos pos, final BlockEntity blockEntity, final CallbackInfoReturnable<List<ItemStack>> info) {
        if (event == null) {
            event = Anvil.fire(new BlockDropEvent(state, world, pos, blockEntity, null, null, info.getReturnValue()));

            if (event.isFail()) {
                info.setReturnValue(Collections.emptyList());
            } else if (event.isPass()) {
                info.setReturnValue(Block.getDroppedStacks(event.getState(), event.getWorld(), event.getPos(), event.getBlockEntity()));
            } else {
                info.setReturnValue(event.getDroppedStacks());
            }

            event = null;
        }
    }

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
            at = @At("RETURN"),
            cancellable = true)
    private static void onGetDroppedStacksEntity(final BlockState state, final ServerWorld world, final BlockPos pos, final BlockEntity blockEntity, final Entity entity, final ItemStack stack, final CallbackInfoReturnable<List<ItemStack>> info) {
        if (event == null) {
            event = Anvil.fire(new BlockDropEvent(state, world, pos, blockEntity, entity, stack, info.getReturnValue()));

            if (event.isFail()) {
                info.setReturnValue(Collections.emptyList());
            } else if (event.isPass()) {
                info.setReturnValue(Block.getDroppedStacks(event.getState(), event.getWorld(), event.getPos(), event.getBlockEntity(), event.getMiner(), event.getTool()));
            } else {
                info.setReturnValue(event.getDroppedStacks());
            }

            event = null;
        }
    }

    @Inject(method = "onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V", at = @At("HEAD"), cancellable = true)
    protected void onOnLandedUpon(final World world, final BlockPos position, final Entity entity, final float distance, final CallbackInfo info) {
        if (this.land) {
            final EntityLandEvent event = Anvil.fire(new EntityLandEvent(entity, thiz(), world, position, distance));

            if (event.getResult() != ActionResult.FAIL) {
                this.land = false;
                event.getBlock().onLandedUpon(event.getWorld(), event.getPosition(), event.getEntity(), event.getDistance());
                this.land = true;
            }

            info.cancel();
        }
    }

    @Inject(method = "calcBlockBreakingDelta(Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F", at = @At("HEAD"), cancellable = true)
    protected void onCalcBlockBreakingDelta(final BlockState state, final PlayerEntity player, final BlockView world, final BlockPos pos, final CallbackInfoReturnable<Float> info) {
        if (this.delta) {
            this.delta = false;
            final BlockBreakSpeedEvent event = Anvil.fire(new BlockBreakSpeedEvent(thiz(), state, player, world, pos, thiz().calcBlockBreakingDelta(state, player, world, pos)));

            if (event.isFail()) {
                info.setReturnValue(0F);
            } else {
                if (event.isAccepted()) {
                    info.setReturnValue(event.getSpeed());
                } else {
                    info.setReturnValue(event.getState().calcBlockBreakingDelta(event.getPlayer(), world, event.getBlockPos()));
                }
            }

            this.delta = true;
        }
    }

    protected Block thiz() {
        return (Block) (Object) this;
    }
}
