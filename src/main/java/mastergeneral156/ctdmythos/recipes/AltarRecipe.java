package mastergeneral156.ctdmythos.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;

import java.util.ArrayList;
import java.util.List;

public class AltarRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    private final Ingredient catalyst;
    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;
    private final int durationTicks;
    private final float mythosCost;

    public AltarRecipe(ResourceLocation id, Ingredient catalyst, NonNullList<Ingredient> ingredients, ItemStack result, int durationTicks, float mythosCost) {
        this.id = id;
        this.catalyst = catalyst;
        this.ingredients = ingredients;
        this.result = result;
        this.durationTicks = durationTicks;
        this.mythosCost = mythosCost;
    }

    @Override
    public boolean matches(Container container, Level level) {
        // Catalyst check
        if (!catalyst.test(container.getItem(0))) return false;

        // Collect input items (pedestal inputs start at index 1)
        List<ItemStack> inputStacks = new ArrayList<>();
        for (int i = 1; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                inputStacks.add(stack);
            }
        }

        // Check if all ingredients match (shapeless)
        List<Ingredient> requiredIngredients = new ArrayList<>(ingredients);

        for (Ingredient required : ingredients) {
            boolean matched = false;
            for (int i = 0; i < inputStacks.size(); i++) {
                if (required.test(inputStacks.get(i))) {
                    inputStacks.remove(i);
                    matched = true;
                    break;
                }
            }
            if (!matched) return false;
        }

        return true;
    }

    public boolean matches(ItemStack catalyst, List<ItemStack> inputs) {
        if (!this.catalyst.test(catalyst)) return false;

        // Match unordered ingredients (as long as all match)
        List<Ingredient> recipeIngredients = new ArrayList<>(this.ingredients);
        List<ItemStack> remaining = new ArrayList<>(inputs);

        for (Ingredient ingredient : recipeIngredients) {
            boolean matched = false;
            for (ItemStack stack : remaining) {
                if (ingredient.test(stack)) {
                    remaining.remove(stack);
                    matched = true;
                    break;
                }
            }
            if (!matched) return false; // Required ingredient not found
        }

        return true;
    }



    public ItemStack assemble(Container inv, RegistryAccess access) {
        return result.copy();
    }

    public int getCraftTime(Container inv, RegistryAccess access) {
        return durationTicks;
    }

    public int getMythosCost(Container inv, RegistryAccess access) {
        return durationTicks;
    }

    public ItemStack getResultItem(RegistryAccess access) {
        return result;
    }

    public ResourceLocation getId() {
        return id;
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.ALTAR_SERIALIZER.get();
    }

    public RecipeType<?> getType() {
        return RecipeRegistry.ALTAR_TYPE.get();
    }

    public Ingredient getCatalyst() {
        return catalyst;
    }

    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getDurationTicks() {
        return durationTicks;
    }

    public float getMythosCost() {
        return mythosCost;
    }

    public boolean isIncomplete() {
        return ingredients.stream().anyMatch(ForgeHooks::hasNoElements) || ForgeHooks.hasNoElements(catalyst);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    public ItemStack getResultItem() {
        return this.result.copy(); // Or just `result` if you're not modifying it
    }

    public int getProcessingTime() {
        return this.durationTicks;
    }


    public static class Serializer implements RecipeSerializer<AltarRecipe> {

        @Override
        public AltarRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient catalyst = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "catalyst"));

            NonNullList<Ingredient> ingredients = NonNullList.create();
            JsonArray inputs = GsonHelper.getAsJsonArray(json, "ingredients");
            for (JsonElement element : inputs) {
                ingredients.add(Ingredient.fromJson(element));
            }

            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            int duration = GsonHelper.getAsInt(json, "time", 200);
            float mythos = GsonHelper.getAsFloat(json, "mythos", 0f);

            return new AltarRecipe(id, catalyst, ingredients, result, duration, mythos);
        }

        @Override
        public AltarRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient catalyst = Ingredient.fromNetwork(buf);

            int count = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(count, Ingredient.EMPTY);
            for (int i = 0; i < count; i++) {
                ingredients.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack result = buf.readItem();
            int time = buf.readVarInt();
            float mythos = buf.readFloat();

            return new AltarRecipe(id, catalyst, ingredients, result, time, mythos);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, AltarRecipe recipe) {
            recipe.getCatalyst().toNetwork(buf);

            buf.writeVarInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }

            buf.writeItem(recipe.getResultItem(null));
            buf.writeVarInt(recipe.getDurationTicks());
            buf.writeFloat(recipe.getMythosCost());
        }
    }

}
