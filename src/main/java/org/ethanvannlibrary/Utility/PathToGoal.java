package org.ethanvannlibrary.Utility;

import lombok.SneakyThrows;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Node;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.RuneLite;

import java.util.*;

public class PathToGoal {
    static Client client = RuneLite.getInjector().getInstance(Client.class);
    public static final int[][] directionsMap = {
            {-2, 0},
            {0, 2},
            {2, 0},
            {0, -2},
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {-1, -1},
            {-1, 1},
            {1, -1},
            {-2, 2},
            {-2, -2},
            {2, 2},
            {2, -2},
            {-2, -1},
            {-2, 1},
            {-1, -2},
            {-1, 2},
            {1, -2},
            {1, 2},
            {2, -1},
            {2, 1}
    };

    @SneakyThrows
    public static ArrayList<WorldPoint> getPath(HashSet<WorldPoint> goal, ArrayList<List<WorldPoint>> paths,
                                                HashSet<WorldPoint> impassible, HashSet<WorldPoint> dangerous,
                                                HashSet<WorldPoint> walkable, HashSet<WorldPoint> walked) {
        Queue<List<WorldPoint>> queue = new LinkedList<>(paths);
        if (queue.isEmpty()) {
            queue.add(List.of(client.getLocalPlayer().getWorldLocation()));
        }
        ArrayDeque<Node> nodeQueue = new ArrayDeque<>();
        if (Collections.disjoint(walkable, goal)) {
            return null;
        }
        while (!queue.isEmpty()) {
            List<WorldPoint> path = queue.poll();
            for (int[] direction : directionsMap) {
                int x = direction[0];
                int y = direction[1];
                if (x == 0 && y == 0) {
                    continue;
                }
                if (path.size() == 0) {
                    continue;
                }
                WorldPoint point = path.get(path.size() - 1).dy(y).dx(x);
                WorldPoint originalPoint = path.get(path.size() - 1);
                if (!walkable.contains(point) || impassible.contains(point) || dangerous.contains(point)) {
//                        						System.out.println("rejecting 1");
                    continue;
                }
                if (walked.contains(point)) {
                    continue;
                }

                //far movements
                //Far West
                if (x == -2 && y == 0) {
                    if (farWObstructed(originalPoint, impassible, walkable)) {
                        continue;
                    }
                }
                //Far East
                if (x == 2 && y == 0) {
                    if (farEObstructed(originalPoint, impassible, walkable)) {
                        continue;
                    }
                }
                //Far South
                if (x == 0 && y == -2) {
                    if (farSObstructed(originalPoint, impassible, walkable)) {
                        continue;
                    }
                }
                //Far North
                if (x == 0 && y == 2) {
                    if (farNObstructed(originalPoint, impassible, walkable)) {
                        continue;
                    }
                }
                //far movements
                //L movement in here so i dont get lost in the saauce down there
                if (Math.abs(x) + Math.abs(y) == 3) {
                    //North east
                    if (x == 1 && y == 2) {
                        if (northEastLObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //East north
                    if (x == 2 && y == 1) {
                        if (eastNorthLObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //East south
                    if (x == 2 && y == -1) {
                        if (eastSouthLObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //South east
                    if (x == 1 && y == -2) {
                        if (southEastLObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //South west
                    if (x == -1 && y == -2) {
                        if (southWestLObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //West south
                    if (x == -2 && y == -1) {
                        if (westSouthLObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //West north
                    if (x == -2 && y == 1) {
                        if (westNorthLObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //North west
                    if (x == -1 && y == 2) {
                        if (northWestLObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                } else {
                    //One tile movement

                    //diagonal SE
                    if (x == 1 && y == -1) {
                        if (seObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }

                    }
                    //diagonal NE
                    if (x == 1 && y == 1) {
                        if (neObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //diagonal NW
                    if (x == -1 && y == 1) {
                        if (nwObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //diagonal SW
                    if (x == -1 && y == -1) {
                        if (swObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }

                    //Two tile movement

                    //Diagonal SW
                    if (x == -2 && y == -2) {
                        if (farSWObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //Diagonal NW
                    if (x == -2 && y == 2) {
                        if (farNWObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //Diagonal SE
                    if (x == 2 && y == -2) {
                        if (farSEObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                    //Diagonal NE
                    if (x == 2 && y == 2) {
                        if (farNEObstructed(originalPoint, impassible, walkable)) {
                            continue;
                        }
                    }
                }
                ArrayList<WorldPoint> newPath = new ArrayList<>(path);
                //					System.out.println("adding: "+counter);
                //					counter++;
                newPath.add(point);
                walked.add(point);
                if (goal.contains(point)) {
                    return newPath;
                }
                queue.add(newPath);
            }
        }
        return null;
    }

    public static ArrayList<WorldPoint> getPath(WorldPoint goal, HashSet<WorldPoint> dangerous) {
        ArrayList<List<WorldPoint>> paths = new ArrayList<>();
        paths.add(List.of(client.getLocalPlayer().getWorldLocation()));
        HashSet<WorldPoint> walkableTiles = new HashSet<>(LocationUtilities.reachableTiles());
        HashSet<WorldPoint> impassibleTiles = new HashSet<>(LocationUtilities.sceneWorldPoints());
        impassibleTiles.removeIf(walkableTiles::contains);
        HashSet<WorldPoint> goalSet = new HashSet<>();
        goalSet.add(goal);
        return getPath(goalSet, paths, impassibleTiles, dangerous, new HashSet<>(LocationUtilities.reachableTiles()), new HashSet<>());
    }

    public static ArrayList<WorldPoint> getPath(HashSet<WorldPoint> goalSet, HashSet<WorldPoint> dangerous) {
        ArrayList<List<WorldPoint>> paths = new ArrayList<>();
        paths.add(List.of(client.getLocalPlayer().getWorldLocation()));
        HashSet<WorldPoint> walkableTiles = new HashSet<>(LocationUtilities.reachableTiles());
        HashSet<WorldPoint> impassibleTiles = new HashSet<>(LocationUtilities.sceneWorldPoints());
        impassibleTiles.removeIf(walkableTiles::contains);
        return getPath(goalSet, paths, impassibleTiles, dangerous, new HashSet<>(LocationUtilities.reachableTiles()), new HashSet<>());
    }

    public static ArrayList<WorldPoint> getPath(WorldPoint goal, HashSet<WorldPoint> dangerous, HashSet<WorldPoint> impassible) {

        ArrayList<List<WorldPoint>> paths = new ArrayList<>();
        paths.add(List.of(client.getLocalPlayer().getWorldLocation()));
        HashSet<WorldPoint> goalSet = new HashSet<>();
        goalSet.add(goal);
        return getPath(goalSet, paths, impassible, dangerous, new HashSet<>(LocationUtilities.reachableTiles()), new HashSet<>());
    }

    public static ArrayList<WorldPoint> getPath(WorldPoint goal, HashSet<WorldPoint> walkable, HashSet<WorldPoint> dangerous, HashSet<WorldPoint> impassible) {
        ArrayList<List<WorldPoint>> paths = new ArrayList<>();
        paths.add(List.of(client.getLocalPlayer().getWorldLocation()));
        HashSet<WorldPoint> goalSet = new HashSet<>();
        goalSet.add(goal);
        return getPath(goalSet, paths, impassible, dangerous, walkable, new HashSet<>());
    }

    static boolean nwObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(-1).dy(0)) || !walkable.contains(starting.dx(-1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(0).dy(1)) || !walkable.contains(starting.dx(0).dy(1));
    }

    public static void sendClientMessage(String message) {
        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", message, null);
    }

    static boolean neObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(1).dy(0)) || !walkable.contains(starting.dx(1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(0).dy(1)) || !walkable.contains(starting.dx(0).dy(1));
    }

    //L movement of 2 north one east, as opposed to 2 east one north (what should we call this difference?)
    static boolean northEastLObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(1).dy(1)) || !walkable.contains(starting.dx(1).dy(1))) {
            return true;
        }
        if (impassible.contains(starting.dx(0).dy(1)) || !walkable.contains(starting.dx(0).dy(1))) {
            return true;
        }
        return impassible.contains(starting.dx(0).dy(2)) || !walkable.contains(starting.dx(0).dy(2));
    }

    //L movement of 2 east one North, as opposed to 2 north one east
    static boolean eastNorthLObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(1).dy(1)) || !walkable.contains(starting.dx(1).dy(1))) {
            return true;
        }
        if (impassible.contains(starting.dx(1).dy(0)) || !walkable.contains(starting.dx(1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(2).dy(0)) || !walkable.contains(starting.dx(2).dy(0));
    }

    static boolean eastSouthLObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(1).dy(-1)) || !walkable.contains(starting.dx(1).dy(-1))) {
            return true;
        }
        if (impassible.contains(starting.dx(1).dy(0)) || !walkable.contains(starting.dx(1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(2).dy(0)) || !walkable.contains(starting.dx(2).dy(0));
    }

    static boolean southEastLObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(1).dy(-1)) || !walkable.contains(starting.dx(1).dy(-1))) {
            return true;
        }
        if (impassible.contains(starting.dx(0).dy(-1)) || !walkable.contains(starting.dx(0).dy(-1))) {
            return true;
        }
        return impassible.contains(starting.dx(0).dy(-2)) || !walkable.contains(starting.dx(0).dy(-2));
    }

    static boolean southWestLObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(-1).dy(-1)) || !walkable.contains(starting.dx(-1).dy(-1))) {
            return true;
        }
        if (impassible.contains(starting.dx(0).dy(-1)) || !walkable.contains(starting.dx(0).dy(-1))) {
            return true;
        }
        return impassible.contains(starting.dx(0).dy(-2)) || !walkable.contains(starting.dx(0).dy(-2));
    }

    static boolean westSouthLObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(-1).dy(-1)) || !walkable.contains(starting.dx(-1).dy(-1))) {
            return true;
        }
        if (impassible.contains(starting.dx(-1).dy(0)) || !walkable.contains(starting.dx(-1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(-2).dy(0)) || !walkable.contains(starting.dx(-2).dy(0));
    }

    static boolean westNorthLObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(-1).dy(1)) || !walkable.contains(starting.dx(-1).dy(1))) {
            return true;
        }
        if (impassible.contains(starting.dx(-1).dy(0)) || !walkable.contains(starting.dx(-1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(-2).dy(0)) || !walkable.contains(starting.dx(-2).dy(0));
    }

    static boolean northWestLObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(-1).dy(1)) || !walkable.contains(starting.dx(-1).dy(1))) {
            return true;
        }
        if (impassible.contains(starting.dx(0).dy(1)) || !walkable.contains(starting.dx(0).dy(1))) {
            return true;
        }
        return impassible.contains(starting.dx(0).dy(2)) || !walkable.contains(starting.dx(0).dy(2));
    }

    static boolean seObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(1).dy(0)) || !walkable.contains(starting.dx(1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(0).dy(-1)) || !walkable.contains(starting.dx(0).dy(-1));
    }

    static boolean swObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(-1).dy(0)) || !walkable.contains(starting.dx(-1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(0).dy(-1)) || !walkable.contains(starting.dx(0).dy(-1));
    }

    static boolean farNObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        return impassible.contains(starting.dx(0).dy(1)) || !walkable.contains(starting.dx(0).dy(1));
    }

    static boolean farSObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        return impassible.contains(starting.dx(0).dy(-1)) || !walkable.contains(starting.dx(0).dy(-1));
    }

    static boolean farEObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        return impassible.contains(starting.dx(1).dy(0)) || !walkable.contains(starting.dx(1).dy(0));
    }

    static boolean farWObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        return impassible.contains(starting.dx(-1).dy(0)) || !walkable.contains(starting.dx(-1).dy(0));
    }

    static boolean farSWObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(-1).dy(-2)) || !walkable.contains(starting.dx(-1).dy(-2))) {
            return true;
        }
        if (impassible.contains(starting.dx(-2).dy(-1)) || !walkable.contains(starting.dx(-2).dy(-1))) {
            return true;
        }
        if (impassible.contains(starting.dx(0).dy(-1)) || !walkable.contains(starting.dx(0).dy(-1))) {
            return true;
        }
        if (impassible.contains(starting.dx(-1).dy(0)) || !walkable.contains(starting.dx(-1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(-1).dy(-1)) || !walkable.contains(starting.dx(-1).dy(-1));
    }

    static boolean farNWObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(-1).dy(2)) || !walkable.contains(starting.dx(-1).dy(2))) {
            return true;
        }
        if (impassible.contains(starting.dx(-2).dy(1)) || !walkable.contains(starting.dx(-2).dy(1))) {
            return true;
        }
        if (impassible.contains(starting.dx(0).dy(1)) || !walkable.contains(starting.dx(0).dy(1))) {
            return true;
        }
        if (impassible.contains(starting.dx(-1).dy(0)) || !walkable.contains(starting.dx(-1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(-1).dy(1)) || !walkable.contains(starting.dx(-1).dy(1));
    }

    static boolean farNEObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(1).dy(2)) || !walkable.contains(starting.dx(1).dy(2))) {
            return true;
        }
        if (impassible.contains(starting.dx(2).dy(1)) || !walkable.contains(starting.dx(2).dy(1))) {
            return true;
        }
        if (impassible.contains(starting.dx(0).dy(1)) || !walkable.contains(starting.dx(0).dy(1))) {
            return true;
        }
        if (impassible.contains(starting.dx(1).dy(0)) || !walkable.contains(starting.dx(1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(1).dy(1)) || !walkable.contains(starting.dx(1).dy(1));
    }

    static boolean farSEObstructed(WorldPoint starting, HashSet<WorldPoint> impassible, HashSet<WorldPoint> walkable) {
        if (impassible.contains(starting.dx(1).dy(-2)) || !walkable.contains(starting.dx(1).dy(-2))) {
            return true;
        }
        if (impassible.contains(starting.dx(2).dy(-1)) || !walkable.contains(starting.dx(2).dy(-1))) {
            return true;
        }
        if (impassible.contains(starting.dx(0).dy(-1)) || !walkable.contains(starting.dx(0).dy(-1))) {
            return true;
        }
        if (impassible.contains(starting.dx(1).dy(0)) || !walkable.contains(starting.dx(1).dy(0))) {
            return true;
        }
        return impassible.contains(starting.dx(1).dy(-1)) || !walkable.contains(starting.dx(1).dy(-1));
    }
}
