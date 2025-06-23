package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static main.Constants.*;

public class TileManager {

	private Tileset tileset;
	protected int layerTileNum[][];
	private String layerPath;
	protected int maxLayerCol = 0;
	protected int maxLayerRow = 0;

	public TileManager(String layerPath, String tilesetName) {
		this.layerPath = layerPath;
		loadTileset(tilesetName);
		try {
			loadLayer();
		} catch (IOException e) {
			System.err.println("Failed to load layer: " + layerPath);
		}
	}

	private void loadTileset(String tilesetName) {
		tileset = TilesetManager.getTileset(tilesetName);
		if (tileset == null) {
			System.err.println("Failed to load tileset: " + tilesetName);
		}
	}

	private void loadLayer() throws IOException {
		InputStream is = getClass().getResourceAsStream(layerPath);
		System.out.println(layerPath + " " + is);
		BufferedReader bReader = new BufferedReader(new InputStreamReader(is));

		java.util.List<String[]> lines = new java.util.ArrayList<>();
		String line;

		while ((line = bReader.readLine()) != null) { lines.add(line.split(",")); }

		maxLayerRow = lines.size();
		maxLayerCol = lines.get(0).length;

		layerTileNum = new int[maxLayerRow][maxLayerCol];

		for (int row = 0; row < maxLayerRow; row++) {
			String[] numbers = lines.get(row);
			for (int col = 0; col < maxLayerCol; col++) {
				layerTileNum[row][col] = Integer.parseInt(numbers[col].trim()) - 1;
				if (layerTileNum[row][col] < 0) layerTileNum[row][col] = 0;
			}
		}
	}

	public int getMaxLayerCol() { return maxLayerCol; }
	public int getMaxLayerRow() { return maxLayerRow; }

	public Tileset getTileset() { return tileset; }

	public void drawLayer(Graphics2D g2, int cameraX, int cameraY) {

		int startCol = Math.max(0, cameraX / ORIGINAL_TILE_SIZE);
		int endCol = Math.min(maxLayerCol, (cameraX + SCREEN_WIDTH) / ORIGINAL_TILE_SIZE + 1);
		int startRow = Math.max(0, cameraY / ORIGINAL_TILE_SIZE);
		int endRow = Math.min(maxLayerRow, (cameraY + SCREEN_HEIGHT) / ORIGINAL_TILE_SIZE + 1);

		for (int row = startRow; row < endRow; row++) {
			for (int col = startCol; col < endCol; col++) {

				int mapX = col * ORIGINAL_TILE_SIZE;
				int mapY = row * ORIGINAL_TILE_SIZE;
				int screenX = mapX - cameraX;
				int screenY = mapY - cameraY;

				tileset.getTile(layerTileNum[row][col]).draw(g2, screenX, screenY);
			}
		}
	}

	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= maxLayerCol || y >= maxLayerRow) {
			return tileset.getTile(0);
		}
		return tileset.getTile(layerTileNum[y][x]);
	}
}
