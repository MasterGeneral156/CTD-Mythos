package mastergeneral156.ctdmythos.recipes;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "ctdmythos");
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, "ctdmythos");

    public static final RegistryObject<RecipeSerializer<?>> ALTAR_SERIALIZER = RECIPE_SERIALIZER.register("altar", AltarRecipe.Serializer::new);

    public static final RegistryObject<RecipeType> ALTAR_TYPE = RECIPE_TYPES.register("altar", AltarRecipeType::new);
}
