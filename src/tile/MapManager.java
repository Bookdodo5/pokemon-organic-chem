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

        // Starting towns and routes
        initializeMap(
            "porbitalTown", 
            "essentials", 
            "Motorcycle", 
            0, 0
        );
        initializeMap(
            "route1",
            "essentials",
            "Bicycle",
            1664, -1024
        );
        initializeMap(
            "methanopolis",
            "essentials",
            "BattleTrainer",
            1344, -2816
        );
        initializeMap(
            "route2",
            "essentials",
            "Bicycle",
            3136, -2496
        );
        initializeMap(
            "hallogue_town",
            "essentials",
            "Motorcycle",
            5440, -2496
        );
        initializeMap(
            "route3",
            "essentials",
            "Bicycle",
            1344, -5120
        );
        initializeMap(
            "pyrrole_town",
            "essentials",
            "Motorcycle",
            64, -5728
        );
        
        /* Major cities and locations
        initializeMap(
            "alkenystra",
            "essentials",
            "BattleGymLeader",
            -4032, -5920
        );
        initializeMap(
            "cyclenchyma",
            "essentials",
            "BattleGymLeader",
            -4112, -1840
        );
        initializeMap(
            "hydroziva",
            "essentials",
            "BattleGymLeader",
            -7776, -8032
        );
        initializeMap(
            "incisiona",
            "essentials",
            "BattleTrainer",
            -7440, -112
        );
        initializeMap(
            "tresyneca",
            "essentials",
            "BattleGymLeader",
            -10992, -2384
        );
        initializeMap(
            "x-liminera",
            "essentials",
            "BattleTrainer",
            -10960, -5712
        );
        initializeMap(
            "azo_village",
            "essentials",
            "Motorcycle",
            -14224, -240
        );
        
        // Coastal and special areas
        initializeMap(
            "saponis_anomalocaris",
            "essentials",
            "BattleTrainer",
            -8080, 3184
        );
        initializeMap(
            "fisher_fjord",
            "essentials",
            "Bicycle",
            -4672, 5248
        );
        initializeMap(
            "port_oxton",
            "essentials",
            "Motorcycle",
            -1872, 3056
        );
        initializeMap(
            "oxidation_observatory",
            "essentials",
            "BattleTrainer",
            1840, 2736
        );
        initializeMap(
            "the_crucible",
            "essentials",
            "BattleGymLeader",
            656, 6096
        );
        
        // Northern regions
        initializeMap(
            "phenol_falls",
            "essentials",
            "Bicycle",
            -7856, -11440
        );
        initializeMap(
            "harmony_complex",
            "essentials",
            "BattleTrainer",
            -11056, -13072
        );
        initializeMap(
            "the_six_rings",
            "essentials",
            "BattleGymLeader",
            -11280, -16496
        );
        initializeMap(
            "polymer_research_institute",
            "essentials",
            "BattleTrainer",
            -8112, -18256
        );
        initializeMap(
            "nose_town",
            "essentials",
            "Motorcycle",
            -13424, -19984
        );
        */

        currentMap = maps.get("methanopolis");
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

    private void initializeMap(String mapName, String tilesetName, String music, int globalX, int globalY) {
        TileManager ground = new TileManager(
            "/data/maps/" + mapName + "/ground.txt",
            tilesetName
        );
        TileManager decoration = new TileManager(
            "/data/maps/" + mapName + "/decoration.txt",
            tilesetName
        );
        TileManager obstacle = new TileManager(
            "/data/maps/" + mapName + "/obstacle.txt",
            tilesetName
        );
        TileManager air = new TileManager(
            "/data/maps/" + mapName + "/air.txt", 
            tilesetName
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
            if(!newMapData.getMusic().equals(currentMap.getMusic())) {
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
        return transitions.get(transitionKey);
    }

}
