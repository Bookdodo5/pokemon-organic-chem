package tile;

import java.util.HashMap;
import java.util.Map;

public class MapManager {
	private final Map<String, TileManager[]> maps;
	private final Map<String, String> musics;
	private final Map<String, TransitionPoint> transitions;
	String currentMapID;
	String currentMapMusic;
	TileManager[] currentMapLayers;

	public MapManager() {
		maps = new HashMap<>();
		musics = new HashMap<>();
		transitions = new HashMap<>();

		initializeMap("outside", "Lab");
		initializeMap("outside2", "Route2");

		initializeTransition(5, 5, "outside", 9, 9, "outside2");

		currentMapID = "outside";
		currentMapMusic = "Lab";
		currentMapLayers = maps.get(currentMapID);
	}

	private void initializeTransition(int fromX, int fromY, String mapFrom, int toX, int toY, String mapTo) {
		transitions.put(getTransitionKey(fromX, fromY, mapFrom),
				new TransitionPoint(fromX, fromY, mapFrom, toX, toY, mapTo));
	}

	private String getTransitionKey(int x, int y, String mapFrom) { return x + " " + y + " " + mapFrom; }

	private void initializeMap(String mapName, String music) {
		maps.put(mapName, new TileManager[]{
			new TileManager("/maps/" + mapName + "_GROUND.txt"),
			new TileManager("/maps/" + mapName + "_DECORATION.txt"),
			new TileManager("/maps/" + mapName + "_OBSTACLE.txt"),
			new TileManager("/maps/" + mapName + "_AIR.txt"),
		});
		musics.put(mapName, music);
	}

	public TileManager[] getCurrentLayers() { return currentMapLayers; }
	
	public String getCurrentMapID() { return currentMapID; }

	public String getCurrentMusic() { return currentMapMusic; }

	public void setCurrentMap(String newMap) {
		if (maps.containsKey(newMap)) {
			currentMapID = newMap;
			currentMapMusic = musics.get(newMap);
			currentMapLayers = maps.get(currentMapID);
		}
	}

	public TransitionPoint checkTransition(int playerX, int playerY) {

		if (!transitions.containsKey(getTransitionKey(playerX, playerY, currentMapID))) return null;
		return transitions.get(getTransitionKey(playerX, playerY, currentMapID));
	}

}
