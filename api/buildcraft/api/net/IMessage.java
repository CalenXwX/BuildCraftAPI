package buildcraft.api.net;

import buildcraft.api.core.BCLog;
import net.minecraft.network.PacketBuffer;

public interface IMessage {
    void fromBytes(PacketBuffer buf);

    void toBytes(PacketBuffer buf);

    public static <MSG extends IMessage> MSG staticFromBytes(Class<MSG> clazz, PacketBuffer buf) {
        try {
            MSG message = clazz.newInstance();
            message.fromBytes(buf);
            return message;
        } catch (Exception e) {
            BCLog.logger.error(e);
            return null;
        }
    }
}
