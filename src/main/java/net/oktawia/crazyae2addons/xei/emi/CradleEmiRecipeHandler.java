package net.oktawia.crazyae2addons.xei.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.handler.StandardRecipeHandler;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

import java.util.List;

public class CradleEmiRecipeHandler implements StandardRecipeHandler<AbstractContainerMenu> {
    @Override
    public List<Slot> getInputSources(AbstractContainerMenu handler) {
        return handler.slots;
    }

    @Override
    public List<Slot> getCraftingSlots(AbstractContainerMenu handler) {
        return handler.slots;
    }

    @Override
    public boolean supportsRecipe(EmiRecipe recipe) {
        return recipe instanceof CradleEmiRecipe;
    }
}