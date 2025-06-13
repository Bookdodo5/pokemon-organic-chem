package main;

public final class Constants {

	public static final int ORIGINAL_TILE_SIZE = 32;
	public static final double SCALE = 2;

	public static final int TILE_SIZE = (int) (ORIGINAL_TILE_SIZE * SCALE);
	public static final int MAX_SCREEN_COL = 16;
	public static final int MAX_SCREEN_ROW = 12;
	public static final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL;
	public static final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;
}
