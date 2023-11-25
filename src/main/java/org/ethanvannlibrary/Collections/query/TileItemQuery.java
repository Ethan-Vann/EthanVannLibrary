package org.ethanvannlibrary.Collections.query;

import lombok.SneakyThrows;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.RuneLite;
import net.runelite.client.game.ItemManager;
import net.runelite.client.util.Text;
import net.runelite.client.util.WildcardMatcher;
import org.ethanvannlibrary.Collections.ETileItem;
import org.ethanvannlibrary.Utility.HelperClass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TileItemQuery {
    public List<ETileItem> tileItems;
    static Client client = RuneLite.getInjector().getInstance(Client.class);
    static ItemManager itemManager = RuneLite.getInjector().getInstance(ItemManager.class);

    public TileItemQuery(List<ETileItem> tileItems) {
        this.tileItems = new ArrayList<ETileItem>(tileItems);
    }

    public TileItemQuery withId(int id) {
        tileItems = this.tileItems.stream().filter(tileItem -> tileItem.tileItem.getId() == id).collect(Collectors.toList());
        return this;
    }

    @SneakyThrows
    public TileItemQuery withName(String name) {
        tileItems = tileItems.stream().filter(tileItem ->
        {
            try {
                return HelperClass.itemDefs.get(tileItem.tileItem.getId()).getName().equals(name);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        return this;
    }

    public TileItemQuery nameContains(String name) {
        tileItems = tileItems.stream().filter(tileItem ->
        {
            try {
                return HelperClass.itemDefs.get(tileItem.tileItem.getId()).getName().contains(name);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        return this;
    }

    public TileItemQuery filter(Predicate<? super ETileItem> predicate) {
        tileItems = tileItems.stream().filter(predicate).collect(Collectors.toList());
        return this;
    }

    public TileItemQuery onlyNoted() {
        tileItems = tileItems.stream().filter(this::isNoted).collect(Collectors.toList());
        return this;
    }

    public TileItemQuery onlyUnnoted() {
        tileItems = tileItems.stream().filter(item -> !isNoted(item)).collect(Collectors.toList());
        return this;
    }

    public TileItemQuery matchesWildCardNoCase(String input) {
        tileItems =
                tileItems.stream().
                        filter(item ->
                        {
                            try {
                                return WildcardMatcher.matches(input.toLowerCase(),
                                        Text.removeTags(HelperClass.itemDefs.get(item.tileItem.getId()).getName().toLowerCase()));
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                        }).
                        collect(Collectors.toList());
        return this;
    }

    public TileItemQuery eachItemAboveXValue(int value) {
        tileItems = tileItems.stream().filter(item -> itemManager.getItemPrice(item.getTileItem().getId()) > value).collect(Collectors.toList());
        return this;
    }

    public TileItemQuery stackAboveXValue(int value) {
        tileItems = tileItems.stream().filter(item -> item.getTileItem().getQuantity() * itemManager.getItemPrice(item.getTileItem().getId()) > value).collect(Collectors.toList());
        return this;
    }

    //the 3 methods below are ugly af and might not work
    public TileItemQuery doesNotMatchWildCardNoCase(String input) {
        tileItems =
                tileItems.stream().
                        filter(item ->
                        {
                            try {
                                return !WildcardMatcher.matches(input.toLowerCase(),
                                        Text.removeTags(HelperClass.itemDefs.get(item.tileItem.getId()).getName().toLowerCase()));
                            } catch (ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                        }).
                        collect(Collectors.toList());
        return this;
    }

    public TileItemQuery itemsMatchingWildcardsNoCase(String... input) {
        List<ETileItem> tileItemsTemp = new ArrayList<>();
        for (String s : input) {
            tileItemsTemp.addAll(tileItems.stream().
                    filter(item ->
                    {
                        try {
                            return WildcardMatcher.matches(s.toLowerCase(),
                                    Text.removeTags(HelperClass.itemDefs.get(item.tileItem.getId()).getName().toLowerCase()));
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }).
                    collect(Collectors.toList()));
        }
        tileItems = tileItemsTemp;
        return this;
    }

    public TileItemQuery itemsExcludingMatchingWildcardsNoCase(String... input) {
        List<ETileItem> tileItemsTemp = new ArrayList<>();
        for (String s : input) {
            tileItemsTemp.addAll(tileItems.stream().
                    filter(item ->
                    {
                        try {
                            return WildcardMatcher.matches(s.toLowerCase(),
                                    Text.removeTags(HelperClass.itemDefs.get(item.tileItem.getId()).getName().toLowerCase()));
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }).
                    collect(Collectors.toList()));
        }
        tileItems.removeAll(tileItemsTemp);
        return this;
    }


    public boolean empty() {
        return tileItems.size() == 0;
    }

    public List<ETileItem> result() {
        return tileItems;
    }

    public Optional<ETileItem> first() {
        if (tileItems.size() == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(tileItems.get(0));
    }

    public TileItemQuery withinDistance(int distance) {
        tileItems = tileItems.stream().filter(tileItem -> tileItem.getLocation().distanceTo(client.getLocalPlayer().getWorldLocation()) <= distance).collect(Collectors.toList());
        return this;
    }

    public TileItemQuery withinDistanceToPoint(int distance, WorldPoint point) {
        tileItems = tileItems.stream().filter(tileItem -> tileItem.getLocation().distanceTo(point) <= distance).collect(Collectors.toList());
        return this;
    }

    public Optional<ETileItem> nearestToPlayer() {
        return nearestToPoint(client.getLocalPlayer().getWorldLocation());
    }

    public Optional<ETileItem> nearestToPoint(WorldPoint point) {
        if (tileItems.size() == 0) {
            return Optional.empty();
        }
        return tileItems.stream().min(Comparator.comparingInt(tileItem -> tileItem.location.distanceTo(point)));
    }

    @SneakyThrows
    public boolean isNoted(ETileItem item) {
        ItemComposition itemComposition = HelperClass.itemDefs.get(item.tileItem.getId());
        return itemComposition.getNote() != -1;
    }
}