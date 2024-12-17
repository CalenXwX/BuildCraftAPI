/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL. Please check the contents
 * of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt */
package buildcraft.api.fuels;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public interface ISolidCoolant extends ICoolant {
    FluidStack getFluidFromSolidCoolant(ItemStack stack);

    float getMultiplier();

    ItemStack getSolid();
}
