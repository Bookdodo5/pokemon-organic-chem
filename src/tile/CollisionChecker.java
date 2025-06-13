package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class CollisionChecker {

	private static Set<Integer> walkableTiles;

	public CollisionChecker() throws NumberFormatException, IOException {
		walkableTiles = new HashSet<>();
		loadWalkableTiles();
	}

	private void loadWalkableTiles() throws NumberFormatException, IOException {
		InputStream is = getClass().getResourceAsStream("/tiles/walkable_tiles.txt");
		BufferedReader bReader = new BufferedReader(new InputStreamReader(is));

		String line;
		while ((line = bReader.readLine()) != null) {
			walkableTiles.add(Integer.valueOf(line.trim()));
		}
	}

	public boolean checkCollision(int x, int y, TileManager[] tileManagers) {
		for (TileManager tileManager : tileManagers) {
			if (!walkableTiles.contains(tileManager.layerTileNum[y][x] + 1)) return false;
			if (x < 0 || y < 0 || x >= tileManager.maxLayerCol || y >= tileManager.maxLayerRow) return false;
		}
		return true;
	}
}
