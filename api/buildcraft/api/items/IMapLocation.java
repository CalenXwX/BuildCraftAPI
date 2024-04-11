package buildcraft.api.items;

import buildcraft.api.core.IBox;
import buildcraft.api.core.IZone;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

/** Created by asie on 2/28/15. */
public interface IMapLocation extends INamedItem {
    enum MapLocationType {
        CLEAN,
        SPOT,
        AREA,
        PATH,
        ZONE,
        /** Like PATH but repeats around in a loop. */
        PATH_REPEATING;

        public final int meta = ordinal();

        public static MapLocationType getFromStack(@Nonnull ItemStack stack) {
            int dam = stack.getDamageValue();
            if (dam < 0 || dam >= values().length) {
                return MapLocationType.CLEAN;
            }
            return values()[dam];
        }

        public void setToStack(@Nonnull ItemStack stack) {
            stack.setDamageValue(meta);
        }
    }

    /** This function can be used for SPOT types.
     * 
     * @param stack
     * @return The point representing the map location. */
    BlockPos getPoint(@Nonnull ItemStack stack);

    /** This function can be used for SPOT and AREA types.
     * 
     * @param stack
     * @return The box representing the map location. */
    IBox getBox(@Nonnull ItemStack stack);

    /** This function can be used for SPOT, AREA and ZONE types. The PATH type needs to be handled separately.
     * 
     * @param stack
     * @return An IZone representing the map location - also an instance of IBox for SPOT and AREA types. */
    IZone getZone(@Nonnull ItemStack stack);

    /** This function can be used for SPOT and PATH types.
     * 
     * @param stack
     * @return A list of BlockPoses representing the path the Map Location stores. */
    List<BlockPos> getPath(@Nonnull ItemStack stack);

    /** This function can be used for SPOT types only.
     * 
     * @param stack
     * @return The side of the spot. */
    Direction getPointSide(@Nonnull ItemStack stack);
}
