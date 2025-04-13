package mastergeneral156.ctdmythos.integrations.jei;

import mastergeneral156.ctdmythos.recipes.AltarRecipe;
import mezz.jei.api.recipe.RecipeType;

public class JEIRecipeTypes {
    public static final RecipeType<AltarRecipe> ALTAR =
            RecipeType.create(
                    "ctdmythos", "altar", AltarRecipe.class);
}
