package mastergeneral156.ctdmythos;

import mastergeneral156.ctdmythos.packets.EjectCrystalPacket;
import mastergeneral156.ctdmythos.packets.SocketCrystalPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MythosNetworkManager {
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("ctdmythos", "main"),
            () -> "1.0",
            s -> true,
            s -> true
    );

    public static void registerMessages() {
        int id = 0;
        INSTANCE.registerMessage(id++, SocketCrystalPacket.class, SocketCrystalPacket::encode, SocketCrystalPacket::decode, SocketCrystalPacket::handle);
        INSTANCE.registerMessage(id++, EjectCrystalPacket.class, EjectCrystalPacket::encode, EjectCrystalPacket::decode, EjectCrystalPacket::handle);

    }
}
