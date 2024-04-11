package buildcraft.api.core;

import buildcraft.api.items.IMapLocation.MapLocationType;
import net.minecraft.core.BlockPos;

import java.util.List;

/** To be implemented by TileEntities able to provide a path on the world, typically BuildCraft path markers. */
public interface IPathProvider
{
    /** @return The completed path. This should loop back onto itself (The last position is the same as the first
     *         position) if you are {@link MapLocationType#PATH_REPEATING} */
    List<BlockPos> getPath();

    /** Remove from the world all objects used to define the path. */
    void removeFromWorld();
}
