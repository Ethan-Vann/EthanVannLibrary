package org.ethanvannlibrary.Collections;

import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.GameTick;
import net.runelite.client.RuneLite;
import net.runelite.client.eventbus.Subscribe;
import org.ethanvannlibrary.Collections.query.PlayerQuery;

import java.util.ArrayList;
import java.util.List;

public class Players {
    static List<Player> players = new ArrayList<>();
    static Client client = RuneLite.getInjector().getInstance(Client.class);
    private static int lastUpdateTick = 0;
    public static PlayerQuery search() {
        if(lastUpdateTick < client.getTickCount()) {
            lastUpdateTick = client.getTickCount();
            players.clear();
            for (Player player : client.getPlayers()) {
                if (player == null)
                    continue;
                players.add(player);
            }
        }
        return new PlayerQuery(players);
    }
}
