package org.ethanvannlibrary.Utility;

import net.runelite.api.Client;
import net.runelite.client.RuneLite;

import static net.runelite.api.Varbits.QUICK_PRAYER;

public class PrayerUtilities {
    static Client client = RuneLite.getInjector().getInstance(Client.class);

    public static boolean isQuickPrayerActive(QuickPrayer prayer) {
        return (client.getVarbitValue(4102) & (int) Math.pow(2, prayer.getIndex())) == Math.pow(2, prayer.getIndex());
    }

    public static boolean isQuickPrayerEnabled() {
        return client.getVarbitValue(QUICK_PRAYER) == 1;
    }
}
