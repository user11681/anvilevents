package user11681.anvilevents.event.client;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import user11681.anvilevents.event.entity.player.PlayerEvent;

@Environment(EnvType.CLIENT)
public class ItemTooltipEvent extends PlayerEvent {
    protected TooltipContext context;
    protected List<Text> tooltip;
    protected ItemStack itemStack;

    public ItemTooltipEvent(final PlayerEntity player, final ItemStack itemStack, final TooltipContext context, final List<Text> tooltip) {
        super(player);

        this.itemStack = itemStack;
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

    public TooltipContext getContext() {
        return this.context;
    }

    public void setContext(final TooltipContext context) {
        this.context = context;
    }
}
