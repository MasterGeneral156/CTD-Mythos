package com.themastergeneral.ctdmythos.common.items.misc;

import java.util.List;

import javax.annotation.Nullable;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.common.items.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseFood extends ItemFood implements ItemModelProvider
{

    protected String name; // Name of the item.

    public BaseFood(String name, int amount, float saturation,
            boolean isWolfFood)
    {
        super(amount, saturation, isWolfFood);
        this.name = name;
        this.setRegistryName(name);
        this.setCreativeTab(CTDMythos.creativeTab);
        this.setUnlocalizedName(name);
    }

    @Override
    public void registerItemModel(Item item)
    {
        CTDCore.proxy.registerItemRenderer(CTDMythos.MODID, this, 0, name);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn,
            EntityPlayer player)
    {
        // Clear effects if eating the rye loaf.
        if (stack.getItem() == ModItems.revitalizing_rye)
        {
            player.clearActivePotions();
            int currentMythos = player.getEntityData().getInteger("mythos");
            int newMythos = currentMythos + 15;
            player.getEntityData().setInteger("mythos", newMythos);
        }
    }
    
 // Add tooltip on client side.
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
            List<String> tooltip, ITooltipFlag flagIn)
    {
    	if (stack.getItem() == ModItems.revitalizing_rye)
    		tooltip.add("Clears status effects. Increases Mythos.");
    }

}
