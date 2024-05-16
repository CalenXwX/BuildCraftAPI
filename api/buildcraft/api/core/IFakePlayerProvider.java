/** Copyright (c) 2011-2015, SpaceToad and the BuildCraft Team http://www.mod-buildcraft.com
 *
 * BuildCraft is distributed under the terms of the Minecraft Mod Public License 1.0, or MMPL. Please check the contents
 * of the license located in http://www.mod-buildcraft.com/MMPL-1.0.txt */
package buildcraft.api.core;

import com.mojang.authlib.GameProfile;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.FakePlayer;

public interface IFakePlayerProvider {
    /**
     * Returns the generic buildcraft fake player. Note that you shouldn't use this anymore, as you should store the
     * UUID of the real player who created the block or entity that calls this.
     */
    @Deprecated
    FakePlayer getBuildCraftPlayer(ServerWorld world);

    /**
     * @param world
     * @param profile The owner's profile.
     * @return A fake player that can be used IN THE CURRENT METHOD CONTEXT ONLY! This will cause problems if this
     * player is left around as it holds a reference to the world object.
     */
    FakePlayer getFakePlayer(ServerWorld world, GameProfile profile);

    /**
     * @param world
     * @param profile The owner's profile.
     * @param pos
     * @return A fake player that can be used IN THE CURRENT METHOD CONTEXT ONLY! This will cause problems if this
     * player is left around as it holds a reference to the world object.
     */
    FakePlayer getFakePlayer(ServerWorld world, GameProfile profile, BlockPos pos);
}
