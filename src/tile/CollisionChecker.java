package tile;

public class CollisionChecker {

	public CollisionChecker() {
	}

	public boolean checkCollision(int x, int y, TileManager[] tileManagers) {
		if(x < 0 || y < 0 || x >= tileManagers[0].maxLayerCol || y >= tileManagers[0].maxLayerRow) return true;
		if(tileManagers[2].layerTileNum[y][x] == 0) return true;
		if(tileManagers[2].layerTileNum[y][x] == 1) return false;
		return false;
	}

	public boolean checkCollisionAcrossMaps(int x, int y, TileManager[] currentTileManagers, MapManager mapManager) {
		// If within current map bounds, use normal collision check
		if(x >= 0 && y >= 0 && x < currentTileManagers[0].maxLayerCol && y < currentTileManagers[0].maxLayerRow) {
			return checkCollision(x, y, currentTileManagers);
		}
		
		// If outside current map bounds, check collision on destination map
		if(mapManager == null) return true;
		
		int globalX = x + mapManager.getGlobalX();
		int globalY = y + mapManager.getGlobalY();
		
		System.out.println("Cross-map check: local(" + x + "," + y + ") -> global(" + globalX + "," + globalY + ")");
		
		// Find map containing the target coordinates
		for(MapData map : mapManager.getVisibleMaps()) {
			if(globalX >= map.getGlobalX() && globalX < map.getGlobalX() + map.getWidth() &&
			   globalY >= map.getGlobalY() && globalY < map.getGlobalY() + map.getHeight()) {
				
				int localX = globalX - map.getGlobalX();
				int localY = globalY - map.getGlobalY();
				boolean result = checkCollision(localX, localY, map.getLayers());
				System.out.println("Found map " + map.getMapName() + ": local(" + localX + "," + localY + ") collision=" + result);
				return result;
			}
		}
		
		System.out.println("No map found for global(" + globalX + "," + globalY + ") - blocking movement");
		return true; // Block movement if no map found
	}
}