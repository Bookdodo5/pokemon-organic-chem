package tile;

import assets.SoundManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static main.Constants.MAX_SCREEN_COL;
import static main.Constants.MAX_SCREEN_ROW;
import static main.Constants.ORIGINAL_TILE_SIZE;
import main.D;

public class MapManager {

    private final Map<String, MapData> maps;
    private final Map<String, TransitionPoint> transitions;
    private final Set<MapData> visibleMaps;
    private MapData currentMap;
    private final Object visibleMapsLock = new Object();

    public MapManager() {
        D.d("Creating MapManager...");
        maps = new HashMap<>();
        transitions = new HashMap<>();
        visibleMaps = new HashSet<>();

        initializeAll();

        currentMap = maps.get("porbital_town__house1_f2");
        D.d("Initial current map set to:", currentMap != null ? currentMap.getMapName() : "null");
        updateVisibleMaps(0, 0);
	}

    private void initializeAll() {
        D.d("Initializing all maps and transitions...");
        MapInitializer.initializeMaps(this);
        TransitionInitializer.initializeTransitions(this);
        D.d("Total maps loaded:", maps.size());
        D.d("Total transitions loaded:", transitions.size());
    }

    protected void initializeTransition(int x1, int y1, String map1, int x2, int y2, String map2) {
        String transitionKey = getTransitionKey(x1, y1, map1);
        D.d("Adding transition:", transitionKey, "->", map2 + "(" + x2 + "," + y2 + ")");
        transitions.put(transitionKey, new TransitionPoint(x1, y1, map1, x2, y2, map2));
    }

    private String getTransitionKey(int x, int y, String mapFrom) {
        return x + " " + y + " " + mapFrom;
    }

    protected void initializeMap(String mapName, String tilesetName, String music, int globalX, int globalY) {
        D.d("Initializing map:", mapName, "with tileset:", tilesetName);
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
        D.d("Map initialized successfully:", mapName, "size:", ground.getMaxLayerCol() + "x" + ground.getMaxLayerRow());
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
            String oldMapName = currentMap != null ? currentMap.getMapName() : "null";
            D.d("Map transition:", oldMapName, "->", newMap, "music:", newMapData.getMusic());
            
            if(!newMapData.getMusic().equals(currentMap.getMusic())) {
                D.d("Music change detected, playing:", newMapData.getMusic());
                SoundManager.getMusicplayer().play(newMapData.getMusic());
            }
            currentMap = newMapData;
        } else {
            D.d("WARNING: Attempted to set map to unknown map:", newMap);
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
        D.d("Transition found at", playerX + "," + playerY, "in", getCurrentMapID(), "->", transition.getMapTo());
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
}
