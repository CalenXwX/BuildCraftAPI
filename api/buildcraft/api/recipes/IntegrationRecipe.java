package buildcraft.api.recipes;

import buildcraft.api.BCModules;
import com.google.common.collect.ImmutableList;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public abstract class IntegrationRecipe implements IRecipe<IInventory> {
    public static final ResourceLocation TYPE_ID = new ResourceLocation(BCModules.SILICON.getModId(), "integration");

    public static final IRecipeType<IntegrationRecipe> TYPE = IRecipeType.register(TYPE_ID.toString());

    public final ResourceLocation name;

    private final long energyCost;
    private final int maxExpansionCount;

    public IntegrationRecipe(ResourceLocation name, long energyCost, int maxExpansionCount) {
        this.name = name;
        this.energyCost = energyCost;
        this.maxExpansionCount = maxExpansionCount;
    }

    /**
     * Determines the output of this recipe
     * @param target the stack in the middle to integrate the components into
     * @param toIntegrate All available stacks to integrate (not all have to be used up in this recipe)
     * @return The output to produce based on the inputs provided or an empty stack if the recipe isn't valid
     */
    public abstract ItemStack getOutput(@Nonnull ItemStack target, NonNullList<ItemStack> toIntegrate);

    // Calen 1.18.2
    public abstract ItemStack getExampleOutput();

    /**
     * Determines the components to use when crafting finishes
     * @return The components to use up
     */
    // public abstract ImmutableList<IngredientStack> getRequirements(@Nonnull ItemStack output);
    public abstract ImmutableList<IngredientStack> getRequirements();

    /**
     * Determines the amount of MJ required to integrate
     * @return The powercost in microjoules
     */
    // public abstract long getRequiredMicroJoules(ItemStack output);
    public final long getRequiredMicroJoules() {
        return energyCost;
    }

    public abstract IngredientStack getCenterStack();

    public int getMaxExpansionCount() {
        return maxExpansionCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IntegrationRecipe that = (IntegrationRecipe) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    // Recipe

    @Override
    public boolean matches(IInventory inv, World world) {
        return false;
    }

    @Override
    public ItemStack assemble(IInventory inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public IRecipeType<IntegrationRecipe> getType() {
        return TYPE;
    }
}
