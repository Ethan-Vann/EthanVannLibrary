package org.ethanvannlibrary.Packets.pendingName;

import lombok.SneakyThrows;
import net.runelite.api.widgets.Widget;
import net.runelite.client.util.Text;
import org.ethanvannlibrary.Packets.PacketDef;
import org.ethanvannlibrary.Packets.PacketReflection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WidgetPackets extends PacketReflectionDependent {
    public WidgetPackets(PacketReflection packetReflection) {
        super(packetReflection);
    }

    @SneakyThrows
    public void queueWidgetActionPacket(int actionFieldNo, int widgetId, int itemId, int childId) {
        switch (actionFieldNo) {
            case 1:
                packetReflection.sendPacket(PacketDef.getIfButton1(packetReflection), widgetId, childId, itemId);
                break;
            case 2:
                packetReflection.sendPacket(PacketDef.getIfButton2(packetReflection), widgetId, childId, itemId);
                break;
            case 3:
                packetReflection.sendPacket(PacketDef.getIfButton3(packetReflection), widgetId, childId, itemId);
                break;
            case 4:
                packetReflection.sendPacket(PacketDef.getIfButton4(packetReflection), widgetId, childId, itemId);
                break;
            case 5:
                packetReflection.sendPacket(PacketDef.getIfButton5(packetReflection), widgetId, childId, itemId);
                break;
            case 6:
                packetReflection.sendPacket(PacketDef.getIfButton6(packetReflection), widgetId, childId, itemId);
                break;
            case 7:
                packetReflection.sendPacket(PacketDef.getIfButton7(packetReflection), widgetId, childId, itemId);
                break;
            case 8:
                packetReflection.sendPacket(PacketDef.getIfButton8(packetReflection), widgetId, childId, itemId);
                break;
            case 9:
                packetReflection.sendPacket(PacketDef.getIfButton9(packetReflection), widgetId, childId, itemId);
                break;
            case 10:
                packetReflection.sendPacket(PacketDef.getIfButton10(packetReflection), widgetId, childId, itemId);
                break;
        }
        System.out.println("sent widget action packet");
    }

    @SneakyThrows
    public void queueWidgetAction(Widget widget, String... actionlist) {
        if (widget == null) {
            System.out.println("call to queueWidgetAction with null widget");
            return;
        }
        List<String> actions = Arrays.stream(widget.getActions()).collect(Collectors.toList());
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i) == null)
                continue;
            actions.set(i, actions.get(i).toLowerCase());
        }
        int num = -1;
        for (String action : actions) {
            for (String action2 : actionlist) {
                if (action != null && Text.removeTags(action).equalsIgnoreCase(action2)) {
                    num = actions.indexOf(action.toLowerCase()) + 1;
                }
            }
        }

        if (num < 1 || num > 10) {
            return;
        }
        queueWidgetActionPacket(num, widget.getId(), widget.getItemId(), widget.getIndex());
    }

    public void queueWidgetOnWidget(Widget srcWidget, Widget destWidget) {
        queueWidgetOnWidget(srcWidget.getId(), srcWidget.getIndex(), srcWidget.getItemId(), destWidget.getId(), destWidget.getIndex(), destWidget.getItemId());
    }

    public void queueWidgetOnWidget(int sourceWidgetId, int sourceSlot, int sourceItemId,
                                    int destinationWidgetId, int destinationSlot, int destinationItemId) {
        packetReflection.sendPacket(PacketDef.getIfButtonT(packetReflection), sourceWidgetId, sourceSlot, sourceItemId, destinationWidgetId,
                destinationSlot, destinationItemId);
    }

    public void queueResumePause(int widgetId, int childId) {
        packetReflection.sendPacket(PacketDef.getResumePausebutton(packetReflection), widgetId, childId);
    }

}