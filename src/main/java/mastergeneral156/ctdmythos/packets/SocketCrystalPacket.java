package mastergeneral156.ctdmythos.packets;

import mastergeneral156.ctdmythos.items.mythos.socketable.MythosCrystalSocketItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class SocketCrystalPacket {
    private final int itemSlot;
    private final String crystalId;

    public SocketCrystalPacket(int itemSlot, String crystalId) {
        this.itemSlot = itemSlot;
        this.crystalId = crystalId;
    }

    public static void encode(SocketCrystalPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.itemSlot);
        buf.writeUtf(msg.crystalId);
    }

    public static SocketCrystalPacket decode(FriendlyByteBuf buf) {
        return new SocketCrystalPacket(buf.readInt(), buf.readUtf());
    }

    public static void handle(SocketCrystalPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            ItemStack socketStack = player.getInventory().getItem(msg.itemSlot);
            if (!(socketStack.getItem() instanceof MythosCrystalSocketItem)) return;

            // Get the item to socket
            Item crystalItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(msg.crystalId));
            if (crystalItem == null) return;

            // Check player inventory for that crystal
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack invStack = player.getInventory().getItem(i);
                if (invStack.getItem() == crystalItem && MythosCrystalSocketItem.isCrystal(invStack)) {
                    // Set crystal
                    MythosCrystalSocketItem.setSocketedCrystal(socketStack, crystalItem);

                    // Consume 1 from inventory
                    invStack.shrink(1);

                    break;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

}

