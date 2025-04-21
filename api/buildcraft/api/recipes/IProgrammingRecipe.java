package buildcraft.api.recipes;

import buildcraft.api.BCModules;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

// public interface IProgrammingRecipe
public interface IProgrammingRecipe extends IRecipe<IInventory> {
    public static final ResourceLocation TYPE_ID = new ResourceLocation(BCModules.SILICON.getModId(), "programming");

    public static final IRecipeType<IProgrammingRecipe> TYPE = IRecipeType.register(TYPE_ID.toString());

    @Override
    default IRecipeType<IProgrammingRecipe> getType() {
        return TYPE;
    }

    ResourceLocation getId();

//    /** Get a list (size at least width*height) of ItemStacks representing options.
//     *
//     * @param width The width of the Programming Table panel.
//     * @param height The height of the Programming Table panel.
//     * @return */
//    List<ItemStack> getOptions(int width, int height);

    /** Get the energy cost of a given option ItemStack.
     *
     * @return */
    // int getEnergyCost(ItemStack option);
    long getEnergyCost();

    /** @param input The input stack.
     * @return Whether this recipe applies to the given input stack. */
    boolean canCraft(ItemStack input);

    /** Craft the input ItemStack with the given option into an output ItemStack.
     *
     * @param input
     * @return The output ItemStack. */
    // ItemStack craft(ItemStack input, ItemStack option);
    ItemStack craft(ItemStack input);

    IngredientStack getInput();

    ItemStack getOutput();

    // Recipe

    @Override
    public default boolean matches(IInventory inv, World world) {
        return false;
    }

    @Override
    default ItemStack assemble(IInventory inv) {
        return ItemStack.EMPTY;
    }

    @Override
    public default boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    default ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public default boolean isSpecial() {
        return true;
    }
}
