package tile;

public class Tileset {

    private final String name;
    private final Tile[] tiles; 
    private final int tilesPerRow;
    private final int tilesPerCol;

    public Tileset(String name, Tile[] tiles, int tilesPerRow, int tilesPerCol) {
        this.name = name;
        this.tiles = tiles;
        this.tilesPerRow = tilesPerRow;
        this.tilesPerCol = tilesPerCol;
    }

    public Tile getTile(int tileID) {
        if(tileID < 0 || tileID >= tiles.length) return tiles[0];
        return tiles[tileID];
    }

    public int getTilesPerRow() {
        return tilesPerRow;
    }

    public int getTilesPerCol() {
        return tilesPerCol;
    }

    public String getName() {
        return name;
    }
}
