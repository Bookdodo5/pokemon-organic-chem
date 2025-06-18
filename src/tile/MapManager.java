package tile;

import assets.SoundManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static main.Constants.MAX_SCREEN_COL;
import static main.Constants.MAX_SCREEN_ROW;

public class MapManager {

    private final Map<String, MapData> maps;
    private final Map<String, TransitionPoint> transitions;
    private final Set<MapData> visibleMaps;
    private MapData currentMap;
    private final Object visibleMapsLock = new Object();

    public MapManager() {
        maps = new HashMap<>();
        transitions = new HashMap<>();
        visibleMaps = new HashSet<>();

        initializeMap("porbitalTown", "Motorcycle", 0, 0);
        initializeMap("route1", "Bicycle", 48, -32);

        currentMap = maps.get("porbitalTown");
        initializeTransition(8, 10, "porbitalTown", 9, 9, "route1");
		updateVisibleMaps(10, 12);
	}

    private void initializeTransition(int fromX, int fromY, String mapFrom, int toX, int toY, String mapTo) {
        transitions.put(getTransitionKey(fromX, fromY, mapFrom),
                new TransitionPoint(fromX, fromY, mapFrom, toX, toY, mapTo));
    }

    private String getTransitionKey(int x, int y, String mapFrom) {
        return x + " " + y + " " + mapFrom;
    }

    private void initializeMap(String mapName, String music, int globalX, int globalY) {
        TileManager ground = new TileManager("/data/maps/" + mapName + "/ground.txt");
        TileManager decoration = new TileManager("/data/maps/" + mapName + "/decoration.txt");
        TileManager obstacle = new TileManager("/data/maps/" + mapName + "/obstacle.txt");
        TileManager air = new TileManager("/data/maps/" + mapName + "/air.txt");

        MapData mapData = new MapData(
                mapName, music,
                new TileManager[]{ground, decoration, obstacle, air},
                globalX, globalY, ground.getMaxLayerCol(), ground.getMaxLayerRow()
        );
        maps.put(mapName, mapData);
    }

    public TileManager[] getCurrentLayers() {
        return currentMap.getLayers();
    }

    public String getCurrentMapID() {
        return currentMap.getMapName();
    }

    public String getCurrentMusic() {
        return currentMap.getMusic();
    }

    public MapData getMap(String mapName) {
        return maps.get(mapName);
    }

	public int getWidth() {
		return currentMap.getWidth();
	}

	public int getHeight() {
		return currentMap.getHeight();
	}

    public Set<MapData> getVisibleMaps() {
        synchronized (visibleMapsLock) {
            return new HashSet<>(visibleMaps);
        }
    }

    public int getGlobalX() {
        return currentMap.getGlobalX();
    }

    public int getGlobalY() {
        return currentMap.getGlobalY();
    }

    public void setCurrentMap(String newMap) {
        if (maps.containsKey(newMap)) {
            MapData newMapData = maps.get(newMap);
            currentMap = newMapData;
            SoundManager.getMusicplayer().play(newMapData.getMusic());
        }
    }

    public final void updateVisibleMaps(int playerMapX, int playerMapY) {
        synchronized (visibleMapsLock) {
            int playerGlobalX = playerMapX + currentMap.getGlobalX();
            int playerGlobalY = playerMapY + currentMap.getGlobalY();
            int leftLimit = playerGlobalX - MAX_SCREEN_COL / 2 - 2;
            int rightLimit = playerGlobalX + MAX_SCREEN_COL / 2 + 2;
            int topLimit = playerGlobalY - MAX_SCREEN_ROW / 2 - 2;
            int bottomLimit = playerGlobalY + MAX_SCREEN_ROW / 2 + 2;

            Set<MapData> newVisibleMaps = new HashSet<>();
            for (MapData map : maps.values()) {
                if (map.getGlobalX() >= rightLimit
                        || map.getGlobalX() + map.getWidth() <= leftLimit
                        || map.getGlobalY() >= bottomLimit
                        || map.getGlobalY() + map.getHeight() <= topLimit) {
                    continue;
                }
                newVisibleMaps.add(map);
            }
            visibleMaps.clear();
            visibleMaps.addAll(newVisibleMaps);
        }
    }

    public TransitionPoint checkTransition(int playerX, int playerY) {
        String transitionKey = getTransitionKey(playerX, playerY, getCurrentMapID());
        if (!transitions.containsKey(transitionKey)) {
            return null;
        }
        return transitions.get(transitionKey);
    }

}
