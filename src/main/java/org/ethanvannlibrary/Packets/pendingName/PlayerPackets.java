package org.ethanvannlibrary.Packets.pendingName;


import lombok.SneakyThrows;
import net.runelite.api.Player;
import net.runelite.api.widgets.Widget;
import org.ethanvannlibrary.Packets.PacketDef;
import org.ethanvannlibrary.Packets.PacketReflection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class PlayerPackets extends PacketReflectionDependent {

    public PlayerPackets(PacketReflection packetReflection) {
        super(packetReflection);
    }

    @SneakyThrows
    public void queuePlayerAction(int actionFieldNo, int playerIndex, boolean ctrlDown) {
        int ctrl = ctrlDown ? 1 : 0;
        switch (actionFieldNo) {
            case 1:
                packetReflection.sendPacket(PacketDef.getOpPlayer1(packetReflection), playerIndex, ctrl);
                break;
            case 2:
                packetReflection.sendPacket(PacketDef.getOpPlayer2(packetReflection), playerIndex, ctrl);
                break;
            case 3:
                packetReflection.sendPacket(PacketDef.getOpPlayer3(packetReflection), playerIndex, ctrl);
                break;
            case 4:
                packetReflection.sendPacket(PacketDef.getOpPlayer4(packetReflection), playerIndex, ctrl);
                break;
            case 5:
                packetReflection.sendPacket(PacketDef.getOpPlayer5(packetReflection), playerIndex, ctrl);
                break;
            case 6:
                packetReflection.sendPacket(PacketDef.getOpPlayer6(packetReflection), playerIndex, ctrl);
                break;
            case 7:
                packetReflection.sendPacket(PacketDef.getOpPlayer7(packetReflection), playerIndex, ctrl);
                break;
            case 8:
                packetReflection.sendPacket(PacketDef.getOpPlayer8(packetReflection), playerIndex, ctrl);
                break;
        }
    }

    @SneakyThrows
    public void queuePlayerAction(Player player, String... actionlist) {
        List<String> actions = Arrays.stream(client.getPlayerOptions()).collect(Collectors.toList());
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i) == null)
                continue;
            actions.set(i, actions.get(i).toLowerCase());
        }
        int num = -1;
        for (String action : actions) {
            for (String action2 : actionlist) {
                if (action != null && action.equalsIgnoreCase(action2)) {
                    num = actions.indexOf(action.toLowerCase()) + 1;
                }
            }
        }

        if (num < 1 || num > 10) {
            return;
        }
        queuePlayerAction(num, player.getId(), false);
    }

    public void queueWidgetOnPlayer(int playerIndex, int sourceItemId, int sourceSlot, int sourceWidgetId,
                                    boolean ctrlDown) {
        int ctrl = ctrlDown ? 1 : 0;
        packetReflection.sendPacket(PacketDef.getOpPlayerT(packetReflection), playerIndex, sourceItemId, sourceSlot, sourceWidgetId, ctrl);
    }

    public void queueWidgetOnPlayer(Player player, Widget widget) {
        queueWidgetOnPlayer(player.getId(), widget.getItemId(), widget.getIndex(), widget.getId(), false);
    }
}
