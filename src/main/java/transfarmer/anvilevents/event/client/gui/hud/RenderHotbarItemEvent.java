package transfarmer.anvilevents.event.client.gui.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class RenderHotbarItemEvent extends RenderHUDElementEvent {
    protected int x;
    protected int y;
    protected float tickDelta;
    protected PlayerEntity player;
    protected ItemStack itemStack;

    public RenderHotbarItemEvent(final InGameHud hud, final int x, final int y, final float tickDelta,
                                 final PlayerEntity player, final ItemStack itemStack) {
        super(hud, Element.HOTBAR_ITEM);
        this.x = x;
        this.y = y;
        this.tickDelta = tickDelta;
        this.player = player;
        this.itemStack = itemStack;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public float getTickDelta() {
        return tickDelta;
    }

    public void setTickDelta(final float tickDelta) {
        this.tickDelta = tickDelta;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(final PlayerEntity player) {
        this.player = player;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(final ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
