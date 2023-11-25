package org.ethanvannlibrary.Collections;

import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.RuneLite;
import net.runelite.client.eventbus.Subscribe;
import org.ethanvannlibrary.Collections.query.ItemQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BankInventory {
    static Client client = RuneLite.getInjector().getInstance(Client.class);
    static List<Widget> bankInventoryItems = new ArrayList<>();
    static int lastUpdateTick = 0;

    public static ItemQuery search() {
        if (lastUpdateTick < client.getTickCount()) {
            BankInventory.bankInventoryItems =
                    Arrays.stream(client.getWidget(WidgetInfo.BANK_INVENTORY_ITEMS_CONTAINER).getDynamicChildren()).filter(Objects::nonNull).filter(x -> x.getItemId() != 6512 && x.getItemId() != -1).collect(Collectors.toList());
            lastUpdateTick = client.getTickCount();
        }
        return new ItemQuery(bankInventoryItems.stream().filter(Objects::nonNull).collect(Collectors.toList()));
    }
}
