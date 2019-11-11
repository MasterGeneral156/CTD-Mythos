package com.themastergeneral.ctdmythos.common.items.wands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

import com.mojang.realmsclient.dto.PlayerInfo;
import com.themastergeneral.ctdmythos.common.config.ModConfig;
import com.themastergeneral.ctdmythos.common.items.ModItems;

public class WandItemTeleport extends WandItemBase
{

    public WandItemTeleport(String name)
    {
        super(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn,
            EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack offhand = playerIn.getHeldItemOffhand();
        ItemStack mainhand = playerIn.getHeldItemMainhand();
        if (mainhand.getItem() == ModItems.teleport_wand)
        {
        	if (worldIn.provider.isSurfaceWorld())
        	{
	            mainhand.damageItem(1, playerIn);
	            playerIn.getCooldownTracker().setCooldown(this, 100);
	            BlockPos blockpos = worldIn.provider.getRandomizedSpawnPoint();
	            playerIn.setPositionAndUpdate(blockpos.getX(), blockpos.getY(),
	                    blockpos.getZ());
        	}
        	else
        	{
        		playerIn.sendStatusMessage(new TextComponentTranslation(
                        "info.wand.teleport.fail"),true);
        	}
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS,
                playerIn.getHeldItem(handIn));
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }

}
