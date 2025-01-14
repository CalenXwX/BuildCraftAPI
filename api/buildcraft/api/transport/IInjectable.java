package buildcraft.api.transport;

import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;


/** Interface for pipes to implement if they can accept items in a similar fashion to BC pipes. */
public interface IInjectable {
    /** Tests to see if this pipe can accept items from the given direction. Useless to call this if you are going to
     * call {@link #injectItem(ItemStack, boolean, Direction, DyeColor, double)} straight after. */
    boolean canInjectItems(Direction from);

    /** Offers an ItemStack for addition to the pipe. Will be rejected if the pipe doesn't accept items from that side.
     * This should never be called on the client side! Implementors are free to throw an exception if be called on the
     * client side.
     *
     * @param stack ItemStack offered for addition. Do not manipulate this!
     * @param doAdd If false no actual addition should take place. Implementors should simulate.
     * @param from Orientation the ItemStack is offered from.
     * @param color The color of the item to be added to the pipe, or null for no color.
     * @param speed The speed of the item to be added (in blocks per tick) or {@code <=0} if a default should be used.
     * @return The left over stack that was not accepted. */
    @Nonnull
    ItemStack injectItem(@Nonnull ItemStack stack, boolean doAdd, Direction from, DyeColor color, double speed);
}
