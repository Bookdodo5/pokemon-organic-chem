package tile;

public class CollisionChecker {

	public CollisionChecker() {
	}

	public boolean checkTileCollision(int x, int y, TileManager obstacleLayer) {
		if(x < 0 || y < 0 || x >= obstacleLayer.maxLayerCol || y >= obstacleLayer.maxLayerRow) {
			return true;
		}
		boolean isWalkable = obstacleLayer.layerTileNum[y][x] == 0;
		return isWalkable;
	}

	public boolean checkCollision(int x, int y, MapManager mapManager) {
		TileManager obstacleLayer = mapManager.getCurrentLayers()[2];

		if(x>=0 && y>=0 && x<obstacleLayer.maxLayerCol && y<obstacleLayer.maxLayerRow) {
			return checkTileCollision(x, y, obstacleLayer);
		}

		int globalX = x + mapManager.getGlobalX();
		int globalY = y + mapManager.getGlobalY();
		
		for(MapData map : mapManager.getVisibleMaps()) {
			if(globalX >= map.getGlobalX() && globalX < map.getGlobalX() + map.getWidth() &&
			   globalY >= map.getGlobalY() && globalY < map.getGlobalY() + map.getHeight()) {
				int localX = globalX - map.getGlobalX();
				int localY = globalY - map.getGlobalY();
				return checkTileCollision(localX, localY, map.getLayers()[2]);
			}
		}
		return true;
	}
}