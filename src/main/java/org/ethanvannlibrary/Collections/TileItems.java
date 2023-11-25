package org.ethanvannlibrary.Collections;


import net.runelite.api.Client;
import net.runelite.api.Tile;
import net.runelite.api.TileItem;
import net.runelite.client.RuneLite;
import org.ethanvannlibrary.Collections.query.TileItemQuery;

import java.util.ArrayList;
import java.util.List;

public class TileItems {
    public static List<ETileItem> tileItems = new ArrayList<>();
    static Client client = RuneLite.getInjector().getInstance(Client.class);
    static int lastUpdateTick = 0;
    public static TileItemQuery search() {
        if(lastUpdateTick < client.getTickCount()){
            tileItems.clear();
            TileObjects.tileObjects.clear();
            for (Tile[] tiles : client.getScene().getTiles()[client.getPlane()]) {
                if (tiles == null) {
                    continue;
                }
                for (Tile tile : tiles) {
                    if (tile == null) {
                        continue;
                    }
                    if (tile.getGroundItems() != null) {
                        for (TileItem groundItem : tile.getGroundItems()) {
                            if (groundItem == null) {
                                continue;
                            }
                            tileItems.add(new ETileItem(tile.getWorldLocation(), groundItem));
                        }
                    }
                }
            }
            lastUpdateTick = client.getTickCount();
        }
        return new TileItemQuery(tileItems);
    }
}
