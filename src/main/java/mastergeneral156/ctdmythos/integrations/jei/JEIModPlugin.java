package mastergeneral156.ctdmythos.integrations.jei;

import com.themastergeneral.ctdcore.helpers.ModUtils;
import mastergeneral156.ctdmythos.blocks.BlockConstants;
import mastergeneral156.ctdmythos.items.ItemRegistry;
import mastergeneral156.ctdmythos.recipes.AltarRecipe;
import mastergeneral156.ctdmythos.recipes.RecipeRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import java.util.List;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    private static final ResourceLocation ID = new ResourceLocation("ctdmythos", "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new AltarRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Minecraft mc = Minecraft.getInstance();
        Level level = mc.level;

        if (level != null) {
            RecipeManager recipeManager = level.getRecipeManager();
            List<AltarRecipe> recipes = recipeManager.getAllRecipesFor(RecipeRegistry.ALTAR_TYPE.get());
            registration.addRecipes(JEIRecipeTypes.ALTAR, recipes);
            registration.addIngredientInfo(ItemRegistry.ore_crystal_fire.get(), ModUtils.displayTranslation("jei.ctdmthos.ore_fire"));
            registration.addIngredientInfo(ItemRegistry.ore_crystal_grief.get(), ModUtils.displayTranslation("jei.ctdmthos.ore_grief"));
            registration.addIngredientInfo(ItemRegistry.ore_crystal_oath.get(), ModUtils.displayTranslation("jei.ctdmthos.ore_oath"));
            registration.addIngredientInfo(ItemRegistry.ore_crystal_memory.get(), ModUtils.displayTranslation("jei.ctdmthos.ore_memory"));
            registration.addIngredientInfo(ItemRegistry.ore_crystal_woe.get(), ModUtils.displayTranslation("jei.ctdmthos.ore_woe"));
        }
    }

    @Override
    public void registerRecipeCatalysts(@Nonnull final IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(BlockConstants.mythos_altar), JEIRecipeTypes.ALTAR);
        registration.addRecipeCatalyst(new ItemStack(BlockConstants.mythos_pedestal), JEIRecipeTypes.ALTAR);

    }
}

