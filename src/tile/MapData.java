package tile;

public class MapData {

    private final String mapName;
    private final String music;
    private final TileManager[] layers;
    private final int globalX;
    private final int globalY;
    private final int width;
    private final int height;

    public MapData(String mapName, String music, TileManager[] layers, int globalX, int globalY, int width, int height) {
        this.mapName = mapName;
        this.music = music;
        this.layers = layers;
        this.globalX = globalX;
        this.globalY = globalY;
        this.width = width;
        this.height = height;
    }

    public String getMapName() { return mapName; }
    public String getMusic() { return music; }
    public TileManager[] getLayers() { return layers; }
    public int getGlobalX() { return globalX; }
    public int getGlobalY() { return globalY; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
