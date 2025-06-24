package tile;

import assets.AssetManager;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static main.Constants.*;

public class TilesetManager {

    private static final Map<String, Tileset> tilesets = new HashMap<>();
    private static final Map<String, String[]> ANIMATED_TILE_PATHS = new HashMap<>();
	private static final Map<String, List<CollisionTypes>> COLLISION_TYPES = new HashMap<>();
	private static final Set<String> GRASS_TILE_PATHS = new HashSet<>();

    static {
		ANIMATED_TILE_PATHS.put("essentials_956", new String[] {
				"/animations/Flowers1.png", "5"
		});
		GRASS_TILE_PATHS.add("essentials_6");

		COLLISION_TYPES.put("essentials_810", List.of(CollisionTypes.JUMP_N));
		COLLISION_TYPES.put("essentials_814", List.of(CollisionTypes.JUMP_N));
		COLLISION_TYPES.put("essentials_828", List.of(CollisionTypes.JUMP_N));
		COLLISION_TYPES.put("essentials_818", List.of(CollisionTypes.JUMP_E));
		COLLISION_TYPES.put("essentials_819", List.of(CollisionTypes.JUMP_E));
		COLLISION_TYPES.put("essentials_826", List.of(CollisionTypes.JUMP_E));
		COLLISION_TYPES.put("essentials_821", List.of(CollisionTypes.JUMP_W));
		COLLISION_TYPES.put("essentials_822", List.of(CollisionTypes.JUMP_W));
		COLLISION_TYPES.put("essentials_830", List.of(CollisionTypes.JUMP_W));

		COLLISION_TYPES.put("essentials_890", List.of(CollisionTypes.JUMP_N));
		COLLISION_TYPES.put("essentials_894", List.of(CollisionTypes.JUMP_N));
		COLLISION_TYPES.put("essentials_908", List.of(CollisionTypes.JUMP_N));
		COLLISION_TYPES.put("essentials_898", List.of(CollisionTypes.JUMP_E));
		COLLISION_TYPES.put("essentials_899", List.of(CollisionTypes.JUMP_E));
		COLLISION_TYPES.put("essentials_906", List.of(CollisionTypes.JUMP_E));
		COLLISION_TYPES.put("essentials_901", List.of(CollisionTypes.JUMP_W));
		COLLISION_TYPES.put("essentials_902", List.of(CollisionTypes.JUMP_W));
		COLLISION_TYPES.put("essentials_910", List.of(CollisionTypes.JUMP_W));
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
				
				String tileKey = tilesetName + "_" + tileID;
				BufferedImage tileImage = AssetManager.getSprite(spriteSheet, x * ORIGINAL_TILE_SIZE,
						y * ORIGINAL_TILE_SIZE, ORIGINAL_TILE_SIZE, ORIGINAL_TILE_SIZE);
				
				if (ANIMATED_TILE_PATHS.containsKey(tileKey)) {
					String[] animatedTilePath = ANIMATED_TILE_PATHS.get(tileKey);
					tileImage = AssetManager.loadImage(animatedTilePath[0]);
					int totalFrame = Integer.parseInt(animatedTilePath[1]);
					tiles[tileID] = new TileAnimated(tileImage, tileID, totalFrame);
					continue;
				}
				tiles[tileID] = new Tile(tileImage, tileID);

				if (GRASS_TILE_PATHS.contains(tileKey)) {
					tiles[tileID].setIsGrass(true);
				}
				if (COLLISION_TYPES.containsKey(tileKey)) {
					for (CollisionTypes collisionType : COLLISION_TYPES.get(tileKey)) {
						tiles[tileID].addCollisionType(collisionType);
					}
				} else {
					tiles[tileID].addCollisionType(CollisionTypes.BLOCKED);
				}
			}
		}
        Tileset tileset = new Tileset(tilesetName, tiles, tilesPerRow, tilesPerCol);
        tilesets.put(tilesetName, tileset);
	}
}
