package tile;

import assets.AssetManager;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static main.Constants.*;

public class TilesetManager {

    private static final Map<String, Tileset> tilesets = new HashMap<>();
    private static final Map<Integer, String[]> ANIMATED_TILE_PATHS = new HashMap<>();
	private static final Set<Integer> GRASS_TILE_PATHS = new HashSet<>();

    static {
		ANIMATED_TILE_PATHS.put(956, new String[] {
				"/animations/Flowers1.png", "5"
		});
		GRASS_TILE_PATHS.add(6);
	}

    public static Tileset getTileset(String name) {
        if(!tilesets.containsKey(name)) {
            loadTileset(name);
        }
        return tilesets.get(name);
    }

    private static void loadTileset(String tilesetName) {
		String tilePath = "/tiles/" + tilesetName + ".png";
		BufferedImage spriteSheet = AssetManager.loadImage(tilePath);

		if (spriteSheet == null) {
			System.err.println("Failed to load tile spritesheet: " + tilePath);
			return;
		}

		int tilesPerRow = spriteSheet.getWidth() / ORIGINAL_TILE_SIZE;
		int tilesPerCol = spriteSheet.getHeight() / ORIGINAL_TILE_SIZE;
		int totalTiles = tilesPerRow * tilesPerCol;
		Tile[] tiles = new Tile[totalTiles];

		for (int y = 0; y < tilesPerCol; y++) {
			for (int x = 0; x < tilesPerRow; x++) {
				int tileID = y * tilesPerRow + x;

				if (tileID == 0) {
					tiles[tileID] = new Tile(null, tileID);
					continue;
				}

				BufferedImage tileImage = AssetManager.getSprite(spriteSheet, x * ORIGINAL_TILE_SIZE,
						y * ORIGINAL_TILE_SIZE, ORIGINAL_TILE_SIZE, ORIGINAL_TILE_SIZE);
				
				if (ANIMATED_TILE_PATHS.containsKey(tileID)) {
					String[] animatedTilePath = ANIMATED_TILE_PATHS.get(tileID);
					tileImage = AssetManager.loadImage(animatedTilePath[0]);
					int totalFrame = Integer.parseInt(animatedTilePath[1]);
					tiles[tileID] = new TileAnimated(tileImage, tileID, totalFrame);
				} else if (GRASS_TILE_PATHS.contains(tileID)) {
					tiles[tileID] = new TileGrass(tileImage, tileID);
				} else {
                    tiles[y * tilesPerRow + x] = new Tile(tileImage, tileID);
                }
			}
		}
        Tileset tileset = new Tileset(tilesetName, tiles, tilesPerRow, tilesPerCol);
        tilesets.put(tilesetName, tileset);
	}
}
