package entity;

import java.awt.Graphics2D;
import main.Constants;
import tile.TileManager;

public abstract class Entity {
	protected double x, y;
	protected int spriteWidth, spriteHeight;
	protected static final double SPEED = 2.4;
	protected int animationCounter = 0;
	protected static final int ANIMATION_SPEED = 8;
	protected int spriteIndex = 0;

	public int getMapX() { return (int) (x / Constants.ORIGINAL_TILE_SIZE); }

	public int getMapY() { return (int) (y / Constants.ORIGINAL_TILE_SIZE); }

	public double getX() { return x; }

	public double getY() { return y; }

	protected void setX(double x) { this.x = x; }

	protected void setY(double y) { this.y = y; }

	public void setMapX(int x) { this.x = x * Constants.ORIGINAL_TILE_SIZE; }

	public void setMapY(int y) { this.y = y * Constants.ORIGINAL_TILE_SIZE; }

	public int getSpriteWidth() { return spriteWidth; }

	public int getSpriteHeight() { return spriteHeight; }

	public void setSpriteWidth(int spriteWidth) { this.spriteWidth = spriteWidth; }

	public void setSpriteHeight(int spriteHeight) { this.spriteHeight = spriteHeight; }

	public void draw(Graphics2D g2, int cameraX, int cameraY, TileManager[] tileManagers) {}
}
