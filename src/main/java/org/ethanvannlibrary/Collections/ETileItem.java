package org.ethanvannlibrary.Collections;

import net.runelite.api.TileItem;
import net.runelite.api.coords.WorldPoint;
import org.ethanvannlibrary.Packets.PacketReflection;

public class ETileItem {
    public WorldPoint location;
    public TileItem tileItem;

    public ETileItem(WorldPoint worldLocation, TileItem tileItem) {
        this.location = worldLocation;
        this.tileItem = tileItem;
    }

    public WorldPoint getLocation() {
        return location;
    }

    public TileItem getTileItem() {
        return tileItem;
    }

    public void interact(PacketReflection pr, boolean ctrlDown) {
        pr.mousePackets.queueClickPacket();
        pr.tileItemPackets.queueTileItemAction(this, ctrlDown);
    }
}
