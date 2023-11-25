package org.ethanvannlibrary.Packets.pendingName;

import lombok.SneakyThrows;
import net.runelite.api.widgets.Widget;
import org.ethanvannlibrary.Collections.ETileItem;
import org.ethanvannlibrary.Packets.PacketDef;
import org.ethanvannlibrary.Packets.PacketReflection;

public class TileItemPackets extends PacketReflectionDependent {
    public TileItemPackets(PacketReflection packetReflection) {
        super(packetReflection);
    }

    @SneakyThrows
    public void queueTileItemAction(int actionFieldNo, int objectId, int worldPointX, int worldPointY, boolean ctrlDown) {
        int ctrl = ctrlDown ? 1 : 0;
        switch (actionFieldNo) {
            case 1:
                packetReflection.sendPacket(PacketDef.getOpObj1(packetReflection), objectId, worldPointX, worldPointY, ctrl);
                break;
            case 2:
                packetReflection.sendPacket(PacketDef.getOpObj2(packetReflection), objectId, worldPointX, worldPointY, ctrl);
                break;
            case 3:
                packetReflection.sendPacket(PacketDef.getOpObj3(packetReflection), objectId, worldPointX, worldPointY, ctrl);
                break;
            case 4:
                packetReflection.sendPacket(PacketDef.getOpObj4(packetReflection), objectId, worldPointX, worldPointY, ctrl);
                break;
            case 5:
                packetReflection.sendPacket(PacketDef.getOpObj5(packetReflection), objectId, worldPointX, worldPointY, ctrl);
                break;
        }
    }

    public void queueWidgetOnTileItem(int objectId, int worldPointX, int worldPointY, int sourceSlot, int sourceItemId, int sourceWidgetId, boolean ctrlDown) {
        int ctrl = ctrlDown ? 1 : 0;
        packetReflection.sendPacket(PacketDef.getOpObjT(packetReflection), objectId, worldPointX, worldPointY, sourceSlot, sourceItemId, sourceWidgetId, ctrl);
    }

    public void queueTileItemAction(ETileItem item, boolean ctrlDown) {
        queueTileItemAction(3, item.getTileItem().getId(), item.getLocation().getX(), item.getLocation().getY(), ctrlDown);
    }

    public void queueWidgetOnTileItem(ETileItem item, Widget w, boolean ctrlDown) {
        queueWidgetOnTileItem(item.getTileItem().getId(), item.getLocation().getX(), item.getLocation().getY(), w.getIndex(), w.getItemId(), w.getId(), ctrlDown);
    }
}
