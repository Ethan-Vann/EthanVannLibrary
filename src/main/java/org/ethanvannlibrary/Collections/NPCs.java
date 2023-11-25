package org.ethanvannlibrary.Collections;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.GameTick;
import net.runelite.client.RuneLite;
import net.runelite.client.eventbus.Subscribe;
import org.ethanvannlibrary.Collections.query.NPCQuery;

import java.util.ArrayList;
import java.util.List;

public class NPCs {
    static Client client = RuneLite.getInjector().getInstance(Client.class);
    private static final List<NPC> npcList = new ArrayList<>();
    private static int lastUpdateTick = 0;
    public static NPCQuery search() {
        npcList.clear();
        if(lastUpdateTick < client.getTickCount()) {
            lastUpdateTick = client.getTickCount();
            for (NPC npc : client.getNpcs()) {
                if (npc == null)
                    continue;
                if (npc.getId() == -1)
                    continue;
                npcList.add(npc);
            }
        }
        return new NPCQuery(npcList);
    }
}
