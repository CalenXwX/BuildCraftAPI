package buildcraft.api.transport.pipe;

import buildcraft.api.mj.IMjPassiveProvider;
import net.minecraft.core.Direction;

public interface IFlowPower {
    /** Makes this pipe reconfigure itself, possibly due to the addition of new modules. */
    void reconfigure();

    /** Attempts to extract power from the {@link IMjPassiveProvider} connected to this pipe on the given side.
     * 
     * @param maxPower The Maximum amount of power that can be extracted.
     * @param from The side (of this pipe) to take power from.
     * @return The amount of power extracted. */
    long tryExtractPower(long maxPower, Direction from);
}
