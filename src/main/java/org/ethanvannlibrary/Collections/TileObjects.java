package org.ethanvannlibrary.Collections;

import net.runelite.api.*;
import net.runelite.api.events.GameTick;
import net.runelite.client.RuneLite;
import net.runelite.client.eventbus.Subscribe;
import org.ethanvannlibrary.Collections.query.TileObjectQuery;

import java.util.ArrayList;
import java.util.List;

public class TileObjects {
    static Client client = RuneLite.getInjector().getInstance(Client.class);
    static List<TileObject> tileObjects = new ArrayList<>();
    final static int lastUpdateTick = 0;
    public static TileObjectQuery search() {
        if(lastUpdateTick < client.getTickCount()){
            tileObjects.clear();
            for (Tile[] tiles : client.getScene().getTiles()[client.getPlane()]) {
                if (tiles == null) {
                    continue;
                }
                for (Tile tile : tiles) {
                    if (tile == null) {
                        continue;
                    }
                    for (GameObject gameObject : tile.getGameObjects()) {
                        if (gameObject == null) {
                            continue;
                        }
                        if (gameObject.getId() == -1) {
                            continue;
                        }
                        tileObjects.add(gameObject);
                    }
                    if (tile.getGroundObject() != null) {
                        if (tile.getGroundObject().getId() == -1) {
                            continue;
                        }
                        tileObjects.add(tile.getGroundObject());
                    }
                    if (tile.getWallObject() != null) {
                        if (tile.getWallObject().getId() == -1) {
                            continue;
                        }
                        tileObjects.add(tile.getWallObject());
                    }
                    if (tile.getDecorativeObject() != null) {
                        if (tile.getDecorativeObject().getId() == -1) {
                            continue;
                        }
                        tileObjects.add(tile.getDecorativeObject());
                    }
                }
            }
        }
        return new TileObjectQuery(tileObjects);
    }
}
