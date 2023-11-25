package org.ethanvannlibrary.Packets.pendingName;

import lombok.SneakyThrows;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.api.widgets.Widget;
import org.ethanvannlibrary.Collections.query.NPCQuery;
import org.ethanvannlibrary.Packets.PacketDef;
import org.ethanvannlibrary.Packets.PacketReflection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NPCPackets extends PacketReflectionDependent {

    public NPCPackets(PacketReflection packetReflection) {
        super(packetReflection);
    }

    @SneakyThrows
    public void queueNPCAction(int actionFieldNo, int npcIndex, boolean ctrlDown) {
        int ctrl = ctrlDown ? 1 : 0;
        switch (actionFieldNo) {
            case 1:
                packetReflection.sendPacket(PacketDef.getOpNpc1(packetReflection), npcIndex, ctrl);
                break;
            case 2:
                packetReflection.sendPacket(PacketDef.getOpNpc2(packetReflection), npcIndex, ctrl);
                break;
            case 3:
                packetReflection.sendPacket(PacketDef.getOpNpc3(packetReflection), npcIndex, ctrl);
                break;
            case 4:
                packetReflection.sendPacket(PacketDef.getOpNpc4(packetReflection), npcIndex, ctrl);
                break;
            case 5:
                packetReflection.sendPacket(PacketDef.getOpNpc5(packetReflection), npcIndex, ctrl);
                break;
        }
    }

    @SneakyThrows
    public void queueNPCAction(NPC npc, String... actionList) {
        if (npc == null) {
            return;
        }
        NPCComposition comp = NPCQuery.getNPCComposition(npc);
        if (comp == null) {
            return;
        }
        if (comp.getActions() == null) {
            return;
        }
        List<String> actions = Arrays.stream(comp.getActions()).collect(Collectors.toList());
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i) == null)
                continue;
            actions.set(i, actions.get(i).toLowerCase());
        }
        int num = -1;
        for (String action : actions) {
            for (String action2 : actionList) {
                if (action != null && action.equalsIgnoreCase(action2)) {
                    num = actions.indexOf(action.toLowerCase()) + 1;
                }
            }
        }

        if (num < 1 || num > 10) {
            return;
        }
        queueNPCAction(num, npc.getIndex(), false);
    }

    public void queueWidgetOnNPC(int npcIndex, int sourceItemId, int sourceSlot, int sourceWidgetId,
                                 boolean ctrlDown) {
        int ctrl = ctrlDown ? 1 : 0;
        packetReflection.sendPacket(PacketDef.getOpNpcT(packetReflection), npcIndex, sourceItemId, sourceSlot, sourceWidgetId, ctrl);
    }

    public void queueWidgetOnNPC(NPC npc, Widget widget) {
        queueWidgetOnNPC(npc.getIndex(), widget.getItemId(), widget.getIndex(), widget.getId(), false);
    }
}
