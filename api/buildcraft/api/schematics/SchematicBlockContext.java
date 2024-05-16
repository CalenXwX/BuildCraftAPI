package buildcraft.api.schematics;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SchematicBlockContext {
    @Nonnull
    public final World world;
    @Nonnull
    public final BlockPos basePos;
    @Nonnull
    public final BlockPos pos;
    @Nonnull
    public final BlockState blockState;
    @Nonnull
    public final Block block;

    public SchematicBlockContext(@Nonnull World world,
                                 @Nonnull BlockPos basePos,
                                 @Nonnull BlockPos pos,
                                 @Nonnull BlockState blockState,
                                 @Nonnull Block block) {
        this.world = world;
        this.basePos = basePos;
        this.pos = pos;
        this.blockState = blockState;
        this.block = block;
    }
}
