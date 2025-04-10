package mastergeneral156.ctdmythos.items.mythos;

import mastergeneral156.ctdmythos.MythosReborn;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MythosItemStorage extends MythosItem {

    protected float xferRate;
    public MythosItemStorage(float maxMythos, float xferRate) {
        super(maxMythos);
        this.xferRate=xferRate;
    }

    @Override
    public void inventoryTick(ItemStack storageStack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(storageStack,level,entity,slot,selected);
        if (level.isClientSide) return;

        // Try collecting from nearby MythosItems
        if (entity instanceof Player player) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack otherStack = player.getInventory().getItem(i);

                if (otherStack != storageStack && otherStack.getItem() instanceof MythosItemGenerator otherItem) {
                    // Only drain if other item has charge and this storage has room
                    float extractable = otherItem.getCurrentMythos(otherStack);
                    if (extractable > 0F && this.getCurrentMythos(storageStack) < this.getMaxMythos(otherStack)) {
                        float extracted = otherItem.extractMythos(xferRate, otherStack); // adjust rate
                        this.receiveMythos(extracted, storageStack);
                    }
                }
            }
        }

        // Then, try feeding Mythos to others if this item has charge
        if (entity instanceof Player player && this.getCurrentMythos(storageStack) > 0F) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack otherStack = player.getInventory().getItem(i);

                if (otherStack != storageStack) {
                    Item otherItemInstance = otherStack.getItem();

                    if (otherItemInstance instanceof MythosItem && !(otherItemInstance instanceof MythosItemGenerator)) {
                        MythosItem otherItem = (MythosItem) otherItemInstance;

                        if (otherItem.getCurrentMythos(otherStack) < otherItem.getMaxMythos(otherStack)) {
                            float transferable = Math.min(xferRate, this.getCurrentMythos(storageStack));
                            float received = otherItem.receiveMythos(transferable, otherStack);
                            this.extractMythos(received, storageStack);
                        }
                    }
                }
            }
        }

    }
}

