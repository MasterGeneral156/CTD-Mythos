package mastergeneral156.ctdmythos.items.mythos.socketable;

import mastergeneral156.ctdmythos.utils.EchoEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class SolsticeLegionStaffItem extends MythosCrystalSocketItem {

    public SolsticeLegionStaffItem(float maxSize) {
        super(maxSize);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        super.use(level, player, hand);

        ItemStack stack = player.getItemInHand(hand);
        Item socketed = getSocketedCrystal(stack);

        if (socketed == null) {
            player.displayClientMessage(Component.literal("The staff is inert..."), true);
            return InteractionResultHolder.fail(stack);
        }

        // Basic Mythos Pool check (you can customize this)
        if (getCurrentMythos(stack) < 64F){
            player.displayClientMessage(Component.literal("The staff thirsts for Mythos."), true);
            return InteractionResultHolder.fail(stack);
        }
        if (!level.isClientSide) {
            switch (Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(socketed)).getPath()) {
                case "crystal_fire" -> EchoEffects.castFirePulse(level, player);
                case "crystal_woe" -> EchoEffects.castWoePulse(level, player);
                case "crystal_grief" -> EchoEffects.castGriefPulse(level, player);
                case "crystal_oath" -> EchoEffects.castOathPulse(level, player);
                case "crystal_memory" -> EchoEffects.castMemoryPulse(level, player);
                default -> player.displayClientMessage(Component.literal("Nothing happens..."), true);
            }

            extractMythos(16F, stack);
        }
        return InteractionResultHolder.success(stack);
    }
}
