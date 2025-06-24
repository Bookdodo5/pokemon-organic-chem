package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import static main.Constants.ORIGINAL_TILE_SIZE;

public class Tile {
	protected BufferedImage tileImage;
	protected List<CollisionTypes> collisionTypes;
	protected int tileID;
	protected boolean isGrass;
	
	public Tile(BufferedImage tileImage, int tileID) {
		this.tileImage = tileImage;
		this.tileID = tileID;
		this.collisionTypes = new ArrayList<>();
		this.isGrass = false;
	}
	
	public void addCollisionType(CollisionTypes collisionType) {
		this.collisionTypes.add(collisionType);
	}

	public void setIsGrass(boolean isGrass) {
		this.isGrass = isGrass;
	}

	public boolean isGrass() {
		return isGrass;
	}

	public int getTileID() {
		return tileID;
	}

	public List<CollisionTypes> getCollisionTypes() {
		return collisionTypes;
	}
	
	public void draw(Graphics2D g2, int screenX, int screenY) {
		if(tileImage == null) return;
		g2.drawImage(tileImage, screenX, screenY, ORIGINAL_TILE_SIZE,
				ORIGINAL_TILE_SIZE, null);
	}
}
