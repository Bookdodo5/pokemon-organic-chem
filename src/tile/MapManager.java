package tile;

import assets.SoundManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static main.Constants.MAX_SCREEN_COL;
import static main.Constants.MAX_SCREEN_ROW;
import static main.Constants.ORIGINAL_TILE_SIZE;

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
        currentMap = new MapData("", "", new TileManager[0], 0, 0, 0, 0);
        initializeAll();
	}

    private void initializeAll() {
        MapInitializer.initializeMaps(this);
        TransitionInitializer.initializeTransitions(this);
    }

    protected void initializeTransition(int x1, int y1, String map1, int x2, int y2, String map2) {
        String transitionKey = getTransitionKey(x1, y1, map1);
        transitions.put(transitionKey, new TransitionPoint(x1, y1, map1, x2, y2, map2));
    }

    private String getTransitionKey(int x, int y, String mapFrom) {
        return x + " " + y + " " + mapFrom;
    }

    protected void initializeMap(String mapName, String tilesetName, String music, int globalX, int globalY) {
        TileManager ground = new TileManager(
            "/data/maps/" + mapName + "/ground.txt", tilesetName
        );
        TileManager decoration = new TileManager(
            "/data/maps/" + mapName + "/decoration.txt", tilesetName
        );
        TileManager obstacle = new TileManager(
            "/data/maps/" + mapName + "/obstacle.txt", tilesetName
        );
        TileManager air = new TileManager(
            "/data/maps/" + mapName + "/air.txt",  tilesetName
        );

        MapData mapData = new MapData(
                mapName, music,
                new TileManager[]{ground, decoration, obstacle, air},
                globalX / ORIGINAL_TILE_SIZE, globalY / ORIGINAL_TILE_SIZE,
                ground.getMaxLayerCol(), ground.getMaxLayerRow()
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
            String currentMusic = currentMap == null ? "" : currentMap.getMusic();
            
            if(!currentMusic.equals("") && !newMapData.getMusic().equals(currentMusic)) {
                SoundManager.getMusicplayer().play(newMapData.getMusic());
            }
            currentMap = newMapData;
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
        TransitionPoint transition = transitions.get(transitionKey);
        return transition;
    }

    public MapData findMap(int globalX, int globalY) {
		for(MapData map : visibleMaps) {
			if(globalX >= map.getGlobalX() &&
				globalX < map.getGlobalX() + map.getWidth() &&
				globalY >= map.getGlobalY() &&
				globalY < map.getGlobalY() + map.getHeight()) {
				return map;
			}
		}
		return null;
	}

    public double findGlobalX(String mapName, double x) { 
        return maps.get(mapName).getGlobalX() + x;
    }

    public double findGlobalY(String mapName, double y) { 
        return maps.get(mapName).getGlobalY() + y;
    }
}
