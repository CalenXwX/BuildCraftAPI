/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL. Please check the contents
 * of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt */
package buildcraft.api.robots;

import buildcraft.api.core.EnumPipePart;
import net.minecraft.nbt.CompoundTag;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ResourceIdRequest extends ResourceIdBlock {

    private int slot;

    public ResourceIdRequest() {

    }

    public ResourceIdRequest(DockingStation station, int slot) {
        pos = station.index();
        side = EnumPipePart.fromFacing(station.side());
        this.slot = slot;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!super.equals(obj)) return false;
        ResourceIdRequest compareId = (ResourceIdRequest) obj;

        return slot == compareId.slot;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(super.hashCode()).append(slot).build();
    }

    @Override
    public void writeToNBT(CompoundTag nbt) {
        super.writeToNBT(nbt);

        nbt.putInt("localId", slot);
    }

    @Override
    protected void readFromNBT(CompoundTag nbt) {
        super.readFromNBT(nbt);

        slot = nbt.getInt("localId");
    }
}
