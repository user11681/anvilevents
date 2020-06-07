package user11681.anvilevents.mixin.mixin.block;

import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import user11681.anvilevents.event.block.BlockDropEvent;

@Mixin(Block.class)
public abstract class BlockMixin {
    private static BlockDropEvent event;

    protected final Block thiz = (Block) (Object) this;

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;)Ljava/util/List;",
            at = @At("RETURN"),
            cancellable = true)
    private static void onGetDroppedStacks(final BlockState state, final ServerWorld world, final BlockPos pos, final BlockEntity blockEntity, final CallbackInfoReturnable<List<ItemStack>> info) {
        if (event == null) {
            event = new BlockDropEvent(state, world, pos, blockEntity, null, null, info.getReturnValue()).fire();

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
            event = new BlockDropEvent(state, world, pos, blockEntity, entity, stack, info.getReturnValue()).fire();

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
}
