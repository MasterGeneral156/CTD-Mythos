package com.themastergeneral.ctdmythos.common.items.tools;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import com.themastergeneral.ctdcore.CTDCore;
import com.themastergeneral.ctdcore.client.ItemModelProvider;
import com.themastergeneral.ctdmythos.CTDMythos;
import com.themastergeneral.ctdmythos.client.sound.ModSounds;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class MythosSwordBase extends ItemSword implements ItemModelProvider
{
    protected String name; // Name of the item.

    public MythosSwordBase(ToolMaterial material, String name, int damage)
    {
        super(material);
        this.name = name;
        this.setRegistryName(name);
        this.setCreativeTab(CTDMythos.creativeTab);
        this.setUnlocalizedName(name);
        this.setMaxDamage(damage);
    }

    @Override
    public void registerItemModel(Item item)
    {
        CTDCore.proxy.registerItemRenderer(CTDMythos.MODID, this, 0, name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn,
            EntityPlayer playerIn, EnumHand handIn)
    {
        /*ItemStack offhand = playerIn.getHeldItemOffhand();
        ItemStack mainhand = playerIn.getHeldItemMainhand();
        // Crystal oath + book in offhand to get a tome of XP
        if (mainhand.getItem() == ModItems.gladius_sword)
        {
        	if (checkMythos(getMythos(playerIn), ModConfig.mythosCostSword))
            {
        		removeMythos(playerIn, ModConfig.mythosCostSword);
	            if (!worldIn.isRemote)
	            {
	                playerIn.addPotionEffect(new PotionEffect(
	                        MobEffects.RESISTANCE, 200, 4, true, false));
	                playerIn.getCooldownTracker().setCooldown(mainhand.getItem(),
	                        500);
	            }
            }
        	else
        	{
        		playerIn.sendStatusMessage(new TextComponentTranslation(
    	                "You need at least " + ModConfig.mythosCostSword + " mythos to be boosted."),true);
        	}
        }*/
        return new ActionResult<ItemStack>(EnumActionResult.PASS,
                playerIn.getHeldItem(handIn));
    }
    public int getMythos(EntityPlayer playerIn)
	{
		return playerIn.getEntityData().getInteger("mythos");
	}
	
	public void setMythos(EntityPlayer playerIn, int mythos)
	{
		playerIn.getEntityData().setInteger("mythos", mythos);
	}
	
	public boolean checkMythos(int currentMythos, int required)
	{
		return currentMythos >= required;
	}
	
	public void removeMythos(EntityPlayer playerIn, int mythosChange)
	{
		int mythos = getMythos(playerIn);
		int newMythos = mythos - mythosChange;
		if (newMythos < 0)
			newMythos = 0;
		setMythos(playerIn, newMythos);
	}
	public void addMythos(EntityPlayer playerIn, int mythosChange)
	{
		int mythos = getMythos(playerIn);
		int newMythos = mythos + mythosChange;
		if (newMythos > ModConfig.mythosMaxStorage)
			newMythos = ModConfig.mythosMaxStorage;
		setMythos(playerIn, newMythos);
	}
}
