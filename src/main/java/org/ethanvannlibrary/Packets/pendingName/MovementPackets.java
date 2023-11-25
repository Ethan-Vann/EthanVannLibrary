package org.ethanvannlibrary.Packets.pendingName;

import net.runelite.api.coords.WorldPoint;
import org.ethanvannlibrary.Packets.PacketDef;
import org.ethanvannlibrary.Packets.PacketReflection;

public class MovementPackets extends PacketReflectionDependent {
    public MovementPackets(PacketReflection packetReflection) {
        super(packetReflection);
    }

    public void queueMovement(int worldPointX, int worldPointY, boolean ctrlDown) {
        int ctrl = ctrlDown ? 2 : 0;
        packetReflection.sendPacket(PacketDef.getMoveGameClick(packetReflection), worldPointX, worldPointY, ctrl, 5);
    }

    public void queueMovement(WorldPoint location) {
        queueMovement(location.getX(), location.getY(), false);
    }
}
