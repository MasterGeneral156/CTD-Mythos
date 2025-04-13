package mastergeneral156.ctdmythos.packets;

import mastergeneral156.ctdmythos.items.mythos.MythosCrystalSocketItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EjectCrystalPacket {
    private final int itemSlot;

    public EjectCrystalPacket(int itemSlot) {
        this.itemSlot = itemSlot;
    }

    public static void encode(EjectCrystalPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.itemSlot);
    }

    public static EjectCrystalPacket decode(FriendlyByteBuf buf) {
        return new EjectCrystalPacket(buf.readInt());
    }

    public static void handle(EjectCrystalPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                ItemStack stack = player.getInventory().getItem(msg.itemSlot);
                if (stack.getItem() instanceof MythosCrystalSocketItem socket) {
                    MythosCrystalSocketItem.ejectCrystal(stack, player.level(), player);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

