package org.ethanvannlibrary.Utility;

import lombok.SneakyThrows;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.RuneLite;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class LocationUtilities {
    static Client client = RuneLite.getInjector().getInstance(Client.class);

    public static boolean inRegion(int regionID) {
        List<Integer> mapRegions = Arrays.stream(client.getMapRegions()).boxed().collect(Collectors.toList());
        return mapRegions.contains(regionID);
    }

    public static WorldPoint playerPosition() {
        return client.getLocalPlayer().getWorldLocation();
    }

    @SneakyThrows
    public static int pathLength(NPC npc) {
        Field pathLength = npc.getClass().getSuperclass().getDeclaredField("dq");
        pathLength.setAccessible(true);
        int path = pathLength.getInt(npc) * -1742381503;
        pathLength.setAccessible(false);
        return path;
    }

    @SneakyThrows
    public static int pathLength(Player player) {
        Field pathLength = player.getClass().getSuperclass().getDeclaredField("dq");
        pathLength.setAccessible(true);
        int path = pathLength.getInt(player) * -1742381503;
        pathLength.setAccessible(false);
        return path;
    }

    @SneakyThrows
    public static HeadIcon getHeadIcon(NPC npc) {
        Method getHeadIconArrayMethod = null;
        for (Method declaredMethod : npc.getComposition().getClass().getDeclaredMethods()) {
            if (declaredMethod.getReturnType() == short[].class && declaredMethod.getParameterTypes().length == 0) {
                getHeadIconArrayMethod = declaredMethod;
                getHeadIconArrayMethod.setAccessible(true);
                short[] headIconArray = (short[]) getHeadIconArrayMethod.invoke(npc.getComposition());
                getHeadIconArrayMethod.setAccessible(false);
                if (headIconArray == null || headIconArray.length == 0) {
                    continue;
                }
                return HeadIcon.values()[headIconArray[0]];
            }
        }
        return null;
    }

    public static List<WorldPoint> sceneWorldPoints() {
        List<WorldPoint> allWorldPoints = new ArrayList<>();
        Scene scene = client.getScene();
        Tile[][][] tiles = scene.getTiles();
        int z = client.getPlane();
        for (int x = 0; x < 104; ++x) {
            for (int y = 0; y < 104; ++y) {
                Tile tile = tiles[z][x][y];
                if (tile == null) {
                    continue;
                }
                allWorldPoints.add(tile.getWorldLocation());
            }
        }
        return allWorldPoints;
    }

    public static List<WorldPoint> reachableTiles() {
        HashSet<Tile> retPoints = new HashSet<>();
        Tile[][] tiles = client.getScene().getTiles()[client.getPlane()];
        int[][] flags = client.getCollisionMaps()[client.getPlane()].getFlags();
        Tile firstPoint = tiles[client.getLocalPlayer().getWorldLocation().getX() - client.getBaseX()][client.getLocalPlayer().getWorldLocation().getY() - client.getBaseY()];
        Queue<Tile> queue = new LinkedList<>();
        queue.add(firstPoint);
        while (!queue.isEmpty()) {
            Tile tile = queue.poll();
            int x = tile.getSceneLocation().getX();
            int y = tile.getSceneLocation().getY();

            if (y > 0 && canMoveSouth(flags[x][y]) && canMoveTo(flags[x][y - 1]) && !retPoints.contains(tiles[x][y - 1])) {
                queue.add(tiles[x][y - 1]);
                retPoints.add(tiles[x][y - 1]);
            }
            if (y < 127 && canMoveNorth(flags[x][y]) && canMoveTo(flags[x][y + 1]) && !retPoints.contains(tiles[x][y + 1])) {
                queue.add(tiles[x][y + 1]);
                retPoints.add(tiles[x][y + 1]);
            }
            if (x > 0 && canMoveWest(flags[x][y]) && canMoveTo(flags[x - 1][y]) && !retPoints.contains(tiles[x - 1][y])) {
                queue.add(tiles[x - 1][y]);
                retPoints.add(tiles[x - 1][y]);
            }
            if (x < 127 && canMoveEast(flags[x][y]) && canMoveTo(flags[x + 1][y]) && !retPoints.contains(tiles[x + 1][y])) {
                queue.add(tiles[x + 1][y]);
                retPoints.add(tiles[x + 1][y]);
            }
        }
        return retPoints.stream().map(Tile::getWorldLocation).collect(Collectors.toList());
    }

    static boolean canMoveWest(int flag) {
        return (flag & CollisionDataFlag.BLOCK_MOVEMENT_WEST) != CollisionDataFlag.BLOCK_MOVEMENT_WEST;
    }

    static boolean canMoveEast(int flag) {
        return (flag & CollisionDataFlag.BLOCK_MOVEMENT_EAST) != CollisionDataFlag.BLOCK_MOVEMENT_EAST;
    }

    static boolean canMoveNorth(int flag) {
        return (flag & CollisionDataFlag.BLOCK_MOVEMENT_NORTH) != CollisionDataFlag.BLOCK_MOVEMENT_NORTH;
    }

    static boolean canMoveSouth(int flag) {
        return (flag & CollisionDataFlag.BLOCK_MOVEMENT_SOUTH) != CollisionDataFlag.BLOCK_MOVEMENT_SOUTH;
    }

    static boolean canMoveTo(int flag) {
        if ((flag & CollisionDataFlag.BLOCK_MOVEMENT_FULL) == CollisionDataFlag.BLOCK_MOVEMENT_FULL) {
            return false;
        }
        if ((flag & CollisionDataFlag.BLOCK_MOVEMENT_FLOOR) == CollisionDataFlag.BLOCK_MOVEMENT_FLOOR) {
            return false;
        }
        if ((flag & CollisionDataFlag.BLOCK_MOVEMENT_OBJECT) == CollisionDataFlag.BLOCK_MOVEMENT_OBJECT) {
            return false;
        }
        return (flag & CollisionDataFlag.BLOCK_MOVEMENT_FLOOR_DECORATION) != CollisionDataFlag.BLOCK_MOVEMENT_FLOOR_DECORATION;
    }

    public static List<WorldPoint> objectInteractableTiles(TileObject e) {
        Point p;
        int x = 1;
        int y = 1;
        if (e instanceof GameObject) {
            GameObject gameObject = (GameObject) e;
            x = gameObject.sizeX();
            y = gameObject.sizeY();
            p = gameObject.getSceneMinLocation();
        } else {
            p = new Point(e.getLocalLocation().getSceneX(), e.getLocalLocation().getSceneY());
        }
        LocalPoint lp = new LocalPoint(p.getX(), p.getY());
        WorldPoint location = WorldPoint.fromScene(client, lp.getX(), lp.getY(), e.getPlane());
        List<WorldPoint> objectArea = new WorldArea(location, x, y).toWorldPointList();
        ArrayList<WorldPoint> grownArea = new ArrayList<>(new WorldArea(location, x + 1, y + 1).toWorldPointList());
        int corner1 = 0;
        int corner2 = x - 1;
        int corner3 = (y * x) - x;
        int corner4 = (y * x) - 1;
        grownArea.remove(corner4);
        grownArea.remove(corner3);
        grownArea.remove(corner2);
        grownArea.remove(corner1);
        grownArea.removeAll(objectArea);
        return grownArea;
    }

    public static PathResult canPathToTile(WorldPoint destinationTile) {
        int z = client.getPlane();
        if (z != destinationTile.getPlane()) {
            return new PathResult(false, Integer.MAX_VALUE);
        }

        CollisionData[] collisionData = client.getCollisionMaps();
        if (collisionData == null) {
            return new PathResult(false, Integer.MAX_VALUE);
        }

        int[][] directions = new int[128][128];
        int[][] distances = new int[128][128];
        int[] bufferX = new int[4096];
        int[] bufferY = new int[4096];

        // Initialise directions and distances
        for (int i = 0; i < 128; ++i) {
            for (int j = 0; j < 128; ++j) {
                directions[i][j] = 0;
                distances[i][j] = Integer.MAX_VALUE;
            }
        }

        int pSX = client.getLocalPlayer().getLocalLocation().getSceneX();

        int pSY = client.getLocalPlayer().getLocalLocation().getSceneY();
        Point p1 = client.getScene().getTiles()[client.getPlane()][pSX][pSY].getSceneLocation();
        LocalPoint lp = LocalPoint.fromWorld(client, destinationTile);
        if (lp == null || !lp.isInScene()) {
            return new PathResult(false, Integer.MAX_VALUE);
        }
        Point p2 = new Point(lp.getSceneX(), lp.getSceneY());

        int middleX = p1.getX();
        int middleY = p1.getY();
        int currentX = middleX;
        int currentY = middleY;
        int offsetX = 64;
        int offsetY = 64;
        // Initialise directions and distances for starting tile
        directions[offsetX][offsetY] = 99;
        distances[offsetX][offsetY] = 0;
        int index1 = 0;
        bufferX[0] = currentX;
        int index2 = 1;
        bufferY[0] = currentY;
        int[][] collisionDataFlags = collisionData[z].getFlags();

        int currentDistance = Integer.MAX_VALUE;
        boolean isReachable = false;

        while (index1 != index2) {
            currentX = bufferX[index1];
            currentY = bufferY[index1];
            index1 = index1 + 1 & 4095;
            // currentX is for the local coordinate while currentMapX is for the index in the directions and distances arrays
            int currentMapX = currentX - middleX + offsetX;
            int currentMapY = currentY - middleY + offsetY;
            if ((currentX == p2.getX()) && (currentY == p2.getY())) {
                isReachable = true;
                break;
            }

            currentDistance = distances[currentMapX][currentMapY] + 1;
            if (currentMapX > 0 && directions[currentMapX - 1][currentMapY] == 0 && (collisionDataFlags[currentX - 1][currentY] & 19136776) == 0) {
                // Able to move 1 tile west
                bufferX[index2] = currentX - 1;
                bufferY[index2] = currentY;
                index2 = index2 + 1 & 4095;
                directions[currentMapX - 1][currentMapY] = 2;
                distances[currentMapX - 1][currentMapY] = currentDistance;
            }

            if (currentMapX < 127 && directions[currentMapX + 1][currentMapY] == 0 && (collisionDataFlags[currentX + 1][currentY] & 19136896) == 0) {
                // Able to move 1 tile east
                bufferX[index2] = currentX + 1;
                bufferY[index2] = currentY;
                index2 = index2 + 1 & 4095;
                directions[currentMapX + 1][currentMapY] = 8;
                distances[currentMapX + 1][currentMapY] = currentDistance;
            }

            if (currentMapY > 0 && directions[currentMapX][currentMapY - 1] == 0 && (collisionDataFlags[currentX][currentY - 1] & 19136770) == 0) {
                // Able to move 1 tile south
                bufferX[index2] = currentX;
                bufferY[index2] = currentY - 1;
                index2 = index2 + 1 & 4095;
                directions[currentMapX][currentMapY - 1] = 1;
                distances[currentMapX][currentMapY - 1] = currentDistance;
            }

            if (currentMapY < 127 && directions[currentMapX][currentMapY + 1] == 0 && (collisionDataFlags[currentX][currentY + 1] & 19136800) == 0) {
                // Able to move 1 tile north
                bufferX[index2] = currentX;
                bufferY[index2] = currentY + 1;
                index2 = index2 + 1 & 4095;
                directions[currentMapX][currentMapY + 1] = 4;
                distances[currentMapX][currentMapY + 1] = currentDistance;
            }

            if (currentMapX > 0 && currentMapY > 0 && directions[currentMapX - 1][currentMapY - 1] == 0 && (collisionDataFlags[currentX - 1][currentY - 1] & 19136782) == 0 && (collisionDataFlags[currentX - 1][currentY] & 19136776) == 0 && (collisionDataFlags[currentX][currentY - 1] & 19136770) == 0) {
                // Able to move 1 tile south-west
                bufferX[index2] = currentX - 1;
                bufferY[index2] = currentY - 1;
                index2 = index2 + 1 & 4095;
                directions[currentMapX - 1][currentMapY - 1] = 3;
                distances[currentMapX - 1][currentMapY - 1] = currentDistance;
            }

            if (currentMapX < 127 && currentMapY > 0 && directions[currentMapX + 1][currentMapY - 1] == 0 && (collisionDataFlags[currentX + 1][currentY - 1] & 19136899) == 0 && (collisionDataFlags[currentX + 1][currentY] & 19136896) == 0 && (collisionDataFlags[currentX][currentY - 1] & 19136770) == 0) {
                // Able to move 1 tile north-west
                bufferX[index2] = currentX + 1;
                bufferY[index2] = currentY - 1;
                index2 = index2 + 1 & 4095;
                directions[currentMapX + 1][currentMapY - 1] = 9;
                distances[currentMapX + 1][currentMapY - 1] = currentDistance;
            }

            if (currentMapX > 0 && currentMapY < 127 && directions[currentMapX - 1][currentMapY + 1] == 0 && (collisionDataFlags[currentX - 1][currentY + 1] & 19136824) == 0 && (collisionDataFlags[currentX - 1][currentY] & 19136776) == 0 && (collisionDataFlags[currentX][currentY + 1] & 19136800) == 0) {
                // Able to move 1 tile south-east
                bufferX[index2] = currentX - 1;
                bufferY[index2] = currentY + 1;
                index2 = index2 + 1 & 4095;
                directions[currentMapX - 1][currentMapY + 1] = 6;
                distances[currentMapX - 1][currentMapY + 1] = currentDistance;
            }

            if (currentMapX < 127 && currentMapY < 127 && directions[currentMapX + 1][currentMapY + 1] == 0 && (collisionDataFlags[currentX + 1][currentY + 1] & 19136992) == 0 && (collisionDataFlags[currentX + 1][currentY] & 19136896) == 0 && (collisionDataFlags[currentX][currentY + 1] & 19136800) == 0) {
                // Able to move 1 tile north-east
                bufferX[index2] = currentX + 1;
                bufferY[index2] = currentY + 1;
                index2 = index2 + 1 & 4095;
                directions[currentMapX + 1][currentMapY + 1] = 12;
                distances[currentMapX + 1][currentMapY + 1] = currentDistance;
            }
        }
        return new PathResult(isReachable, currentDistance);
    }

    public static class PathResult {
        private final boolean reachable;
        private final int distance;

        public PathResult(boolean reachable, int distance) {
            this.reachable = reachable;
            this.distance = distance;
        }

        public boolean isReachable() {
            return reachable;
        }

        public int getDistance() {
            return distance;
        }
    }
}
