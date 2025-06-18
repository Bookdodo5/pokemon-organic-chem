package tile;

import assets.AssetManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import static main.Constants.*;

public class TileManager {

	protected int layerTileNum[][];
	private Tile tiles[];
	private String layerPath, tilePath;
	protected int maxLayerCol = 0;
	protected int maxLayerRow = 0;

	private static final Map<Integer, String[]> ANIMATED_TILE_PATHS = new HashMap<>();

	static {
		ANIMATED_TILE_PATHS.put(956, new String[] {
				"/animations/Flowers1.png", "5"
		});
	}

	public TileManager(String layerPath) {
		this.layerPath = layerPath;
		loadTile();
		try {
			loadLayer();
		} catch (IOException e) {
			System.err.println("Failed to load layer: " + layerPath);
		}
	}

	private void loadTile() {

		tilePath = "/tiles/" + layerPath.split("_")[0].split("/")[3] + ".png";
		// tileLayer = layerPath.split("_")[1];

		BufferedImage spriteSheet = AssetManager.loadImage(tilePath);

		if (spriteSheet == null) {
			System.err.println("Failed to load tile spritesheet: " + tilePath);
			return;
		}

		int tilesPerRow = spriteSheet.getWidth() / ORIGINAL_TILE_SIZE;
		int tilesPerCol = spriteSheet.getHeight() / ORIGINAL_TILE_SIZE;
		int totalTiles = tilesPerRow * tilesPerCol;
		this.tiles = new Tile[totalTiles];

		for (int y = 0; y < tilesPerCol; y++) {
			for (int x = 0; x < tilesPerRow; x++) {

				int tileID = y * tilesPerRow + x;

				if (tileID == 0) {
					tiles[tileID] = new Tile(null);
					continue;
				}

				String[] animatedTilePath = ANIMATED_TILE_PATHS.get(tileID);
				if (animatedTilePath != null) {
					BufferedImage tileImage = AssetManager.loadImage(animatedTilePath[0]);
					int totalFrame = Integer.parseInt(animatedTilePath[1]);
					tiles[tileID] = new AnimatedTile(tileImage, totalFrame);
					continue;
				}

				BufferedImage tileImage = AssetManager.getSprite(spriteSheet, x * ORIGINAL_TILE_SIZE,
						y * ORIGINAL_TILE_SIZE, ORIGINAL_TILE_SIZE, ORIGINAL_TILE_SIZE);

				tiles[y * tilesPerRow + x] = new Tile(tileImage);
			}
		}
	}

	private void loadLayer() throws IOException {
		InputStream is = getClass().getResourceAsStream(layerPath);
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

	public void drawLayer(Graphics2D g2, int cameraX, int cameraY) {

		for (int row = 0; row < maxLayerRow; row++) {
			for (int col = 0; col < maxLayerCol; col++) {

				int mapX = col * ORIGINAL_TILE_SIZE;
				int mapY = row * ORIGINAL_TILE_SIZE;
				int screenX = mapX - cameraX;
				int screenY = mapY - cameraY;

				if (screenX + ORIGINAL_TILE_SIZE < 0 || screenX > SCREEN_WIDTH ||
                screenY + ORIGINAL_TILE_SIZE < 0 || screenY > SCREEN_HEIGHT) {
					continue;
				}

				tiles[layerTileNum[row][col]].draw(g2, screenX, screenY);
			}
		}
	}

}
