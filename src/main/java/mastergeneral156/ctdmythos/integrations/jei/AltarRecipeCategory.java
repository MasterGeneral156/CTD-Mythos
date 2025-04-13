package mastergeneral156.ctdmythos.integrations.jei;

import mastergeneral156.ctdmythos.blocks.BlockRegistry;
import mastergeneral156.ctdmythos.recipes.AltarRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class AltarRecipeCategory implements IRecipeCategory<AltarRecipe> {
    public static final ResourceLocation UID = new ResourceLocation("ctdmythos", "altar");
    public static final ResourceLocation TEXTURE = new ResourceLocation("ctdmythos", "textures/gui/jei/altar.png");

    private final IDrawable background;
    private final IDrawable icon;

    public AltarRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 150, 66); // Adjust size to fit your GUI
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockRegistry.mythos_altar.get()));
    }

    @Override
    public RecipeType<AltarRecipe> getRecipeType() {
        return new RecipeType<>(UID, AltarRecipe.class);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.ctdmythos.altar");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, AltarRecipe recipe, IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 8, 24) // Catalyst slot
                .addIngredients(recipe.getCatalyst());

        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            int x = 30 + (i % 3) * 18;
            int y = 6 + (i / 3) * 18;
            builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                    .addIngredients(recipe.getIngredients().get(i));
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 120, 24)
                .addItemStack(recipe.getResultItem(null));
    }
}

