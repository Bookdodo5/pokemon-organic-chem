package entity;

import assets.AssetManager;
import java.util.List;
import java.util.Random;
import tile.TileManager;

public class NPC extends Human {
	private final String id;
	private final String map;
	private final Random random;
	private int moveCounter;
	private int movementInterval;

	private int originalX, originalY;
	private final int range;

	private AIMode aiMode;

	public NPC(String id, int positionX, int positionY, int range, String map, String sprite, AIMode AImode) {
		super(positionX, positionY);
		this.id = id;
		this.originalX = positionX;
		this.originalY = positionY;
		this.range = range;
		this.map = map;
		this.random = new Random();
		this.moveCounter = 0;
		this.movementInterval = 60;
		this.spriteSheet = AssetManager.loadImage("/player/" + sprite + ".png");
		this.aiMode = AImode;
	}

	public void setOriginalPosition(int x, int y) {
		originalX = x;
		originalY = y;
	}

	private void handleWander(TileManager[] tileManagers, List<Entity> humans) {
		canMove = false;

		moveCounter++;
		if (moveCounter < movementInterval) return;

		moveCounter = 0;
		movementInterval = 60 + random.nextInt(120);

		int direction = random.nextInt(4);
		switch (direction) {
			case 0 -> currentDirection = FacingDirections.UP;
			case 1 -> currentDirection = FacingDirections.DOWN;
			case 2 -> currentDirection = FacingDirections.LEFT;
			case 3 -> currentDirection = FacingDirections.RIGHT;
			default -> {}
		}
		canMove = checkCollision(x, y, tileManagers, humans) && checkOutOfRange();

		if (canMove) setMoving();
		else spriteIndex = 0;
	}

	private boolean checkOutOfRange() {
		currentDirectionVector = DIRECTION_VECTORS.get(currentDirection);
		int checkX = getWorldX() + currentDirectionVector[0];
		int checkY = getWorldY() + currentDirectionVector[1];
		int distX = Math.abs(checkX - originalX);
		int distY = Math.abs(checkY - originalY);
		return distX + distY <= range;
	}

	@Override
	protected void handleIdle(TileManager[] tileManagers, List<Entity> humans) {
		switch (aiMode) {
			case WANDER -> handleWander(tileManagers, humans);
			case STILL -> {}
			default ->
				throw new IllegalArgumentException("Unexpected value: " + aiMode);
		}
	}

	public String getMap() { return map; }

	public AIMode getAIMode() { return aiMode; }

	public void setAIMode(AIMode mode) { this.aiMode = mode; }

	public String getId() { return id; }
}
