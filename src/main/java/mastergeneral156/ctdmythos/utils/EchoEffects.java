package mastergeneral156.ctdmythos.utils;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class EchoEffects {

    private static final double RADIUS = 6.0;

    public static void castFirePulse(Level level, Player player) {
        level.playSound(null, player.blockPosition(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1.0f, 1.0f);
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.FLAME,
                    player.getX(), player.getY() + 1, player.getZ(),
                    40, 1, 1, 1, 0.1);
        }

        for (LivingEntity target : getNearbyEnemies(level, player)) {
            target.setSecondsOnFire(4);
        }
    }

    public static void castWoePulse(Level level, Player player) {
        level.playSound(null, player.blockPosition(), SoundEvents.ILLUSIONER_PREPARE_BLINDNESS, SoundSource.PLAYERS, 1.0f, 0.7f);

        for (LivingEntity target : getNearbyEnemies(level, player)) {
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0)); // 10s
        }
    }

    public static void castGriefPulse(Level level, Player player) {
        level.playSound(null, player.blockPosition(), SoundEvents.AMBIENT_CAVE.get(), SoundSource.PLAYERS, 1.0f, 0.7f);

        for (LivingEntity target : getNearbyEnemies(level, player)) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 160, 1)); // 8s
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0)); // 5s
        }
    }

    public static void castOathPulse(Level level, Player player) {
        level.playSound(null, player.blockPosition(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 1.0f, 1.5f);

        for (LivingEntity entity : getNearbyAllies(level, player)) {
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0)); // 15s Strength I
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300, 0));
        }
    }

    public static void castMemoryPulse(Level level, Player player) {
        level.playSound(null, player.blockPosition(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0f, 1.2f);

        for (LivingEntity target : getNearbyEnemies(level, player)) {
            if (target.isInvisible()) {
                target.removeEffect(MobEffects.INVISIBILITY);
            }
        }

        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    player.getX(), player.getY() + 1, player.getZ(),
                    30, 1, 1, 1, 0.2);
        }
    }

    // Utility methods to get nearby entities
    private static List<LivingEntity> getNearbyEnemies(Level level, Player player) {
        AABB area = new AABB(player.blockPosition()).inflate(RADIUS);
        return level.getEntitiesOfClass(LivingEntity.class, area,
                e -> e != player && e.isAlive() && !e.isAlliedTo(player));
    }

    private static List<LivingEntity> getNearbyAllies(Level level, Player player) {
        AABB area = new AABB(player.blockPosition()).inflate(RADIUS);
        return level.getEntitiesOfClass(LivingEntity.class, area,
                e -> e.isAlive() && (e == player || e.isAlliedTo(player)));
    }
}
