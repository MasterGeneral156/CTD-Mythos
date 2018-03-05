package com.themastergeneral.ctdmythos.common.effects;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EffectUtils {
	public static void generateEntityAroundPlayer(Entity entityIn, World worldIn, Entity particle)
	{
		for (float degrees = 0.0F; degrees < 360.0F; degrees += 20.0F)
        {
            double angle = Math.toRadians(degrees);

            double vx = entityIn.posX* Math.cos(angle) -entityIn.posZ * Math.sin(angle);
            double vz = entityIn.posX * Math.sin(angle) + entityIn.posZ * Math.cos(angle);
            
            particle.setPosition(vx, entityIn.posY, vz);
            
        }
	}
}
