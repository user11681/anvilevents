package user11681.anvilevents.event.item;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ItemTooltipEvent extends ItemEvent {
    protected TooltipContext context;
    protected PlayerEntity player;
    protected List<Text> tooltip;

    public ItemTooltipEvent(final ItemStack itemStack, final PlayerEntity player, final TooltipContext context, final List<Text> tooltip) {
        super(itemStack);

        this.player = player;
        this.tooltip = tooltip;
        this.context = context;
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public void setItemStack(final ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public List<Text> getTooltip() {
        return this.tooltip;
    }

    public void setTooltip(final List<Text> tooltip) {
        this.tooltip = tooltip;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public void setPlayer(final PlayerEntity player) {
        this.player = player;
    }

    public TooltipContext getContext() {
        return this.context;
    }

    public void setContext(final TooltipContext context) {
        this.context = context;
    }
}
