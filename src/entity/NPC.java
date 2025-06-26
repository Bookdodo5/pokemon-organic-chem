package entity;

import assets.AssetManager;
import java.util.List;
import java.util.Random;	
import java.util.concurrent.Callable;
import tile.MapManager;

public class NPC extends Human {
	private final String id;
	private final Random random;
	private String map;
	private int moveCounter;
	private int movementInterval;

	private int originalX, originalY;
	private final int range;
	private final NPCPath path;
	private final int dx, dy;

	private AIMode aiMode;

	private NPC(String id, int positionX, int positionY, String map, NPCSprites sprite, AIMode aiMode, NPCPath path, int dx, int dy, int range) {
		super(positionX, positionY);
		this.id = id;
		this.originalX = positionX;
		this.originalY = positionY;
		this.map = map;
		this.random = new Random();
		this.moveCounter = 0;
		this.movementInterval = 60;
		this.spriteSheet = AssetManager.loadImage("/player/" + sprite.getSpriteFileName());
		this.aiMode = aiMode;
		this.path = path;
		this.dx = dx;
		this.dy = dy;
		this.range = range;
	}

	public static class Builder {
		private final String id;
		private int positionX = 0;
		private int positionY = 0;
		private String map = null;
		private final NPCSprites sprite;

		private int range = 0;
		private NPCPath path = null;
		private int dx = 0;
		private int dy = 0;
		private AIMode aiMode = AIMode.STILL;

		public Builder(String id, NPCSprites sprite) {
			this.id = id;
			this.sprite = sprite;
		}

		public Builder position(int x, int y, String map) {
			this.positionX = x;
			this.positionY = y;
			this.map = map;
			return this;
		}

		public Builder circle(int range) {
			this.range = range;
			this.aiMode = AIMode.CIRCLE_WANDER;
			return this;
		}

		public Builder rect(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
			this.aiMode = AIMode.RECTANGULAR_WANDER;
			return this;
		}

		public Builder path(NPCPath path) {
			this.path = path;
			this.aiMode = AIMode.PATH_FOLLOWING;
			return this;
		}

		public NPC build() {
			if(map == null) {
				System.err.println("Map must be set");
				return null;
			}
			NPC npc = new NPC(id, positionX, positionY, map, sprite, aiMode, path, dx, dy, range);
			return npc;
		}
	}

	private boolean shouldMove() {
		moveCounter++;
		if (moveCounter < movementInterval) return false;
		
		moveCounter = 0;
		movementInterval = random.nextInt(240);
		return true;
	}

	private void handleWander(MapManager mapManager, List<Entity> humans, Callable<Boolean> outOfRange) {
		canMove = false;

		int direction = random.nextInt(4);
		switch (direction) {
			case 0 -> setFacingDirection(FacingDirections.UP);
			case 1 -> setFacingDirection(FacingDirections.DOWN);
			case 2 -> setFacingDirection(FacingDirections.LEFT);
			case 3 -> setFacingDirection(FacingDirections.RIGHT);
			default -> {}
		}
		try {
			canMove = checkCollision(x, y, humans, mapManager) && outOfRange.call();
		} catch (Exception e) {
			canMove = false;
		}
		if (canMove) setMoving();
	}
	
	private void handleRectangularWander(MapManager mapManager, List<Entity> humans) {
		handleWander(mapManager, humans, this::checkOutOfRangeRect);
	}
	private void handleCircleWander(MapManager mapManager, List<Entity> humans) {
		handleWander(mapManager, humans, this::checkOutOfRangeCircle);
	}

	private void handlePathFollowing(MapManager mapManager, List<Entity> humans) {
		canMove = false;

		NPCPath.Point targetPoint = path.getNextPoint();
		
		int deltaX = targetPoint.x - getMapX();
		int deltaY = targetPoint.y - getMapY();
		
		if (Math.abs(deltaX) > Math.abs(deltaY)) {
			setFacingDirection(deltaX > 0 ? FacingDirections.RIGHT : FacingDirections.LEFT);
		} else {
			setFacingDirection(deltaY > 0 ? FacingDirections.DOWN : FacingDirections.UP);
		}
		
		canMove = checkCollision(x, y, humans, mapManager);
		if (canMove) setMoving();
	}


	private boolean checkOutOfRangeCircle() {
		int checkX = getMapX() + getCurrentDirection().getX();
		int checkY = getMapY() + getCurrentDirection().getY();
		double distX = Math.pow(checkX - originalX, 2);
		double distY = Math.pow(checkY - originalY, 2);
		return Math.sqrt(distX + distY) <= range;
	}

	private boolean checkOutOfRangeRect() {
		int checkX = getMapX() + getCurrentDirection().getX();
		int checkY = getMapY() + getCurrentDirection().getY();
		return checkX >= originalX - dx && checkX <= originalX + dx && checkY >= originalY - dy && checkY <= originalY + dy;
	}

	@Override
	protected void handleIdle(List<Entity> humans, MapManager mapManager) {
		if (!shouldMove()) {
			spriteIndex = 0;
			return;
		}
		switch (aiMode) {
			case CIRCLE_WANDER -> handleCircleWander(mapManager, humans);
			case STILL -> {}
			case RECTANGULAR_WANDER -> handleRectangularWander(mapManager, humans);
			case PATH_FOLLOWING -> handlePathFollowing(mapManager, humans);
			default ->
				throw new IllegalArgumentException("Unexpected value: " + aiMode);
		}
	}

	public String getMap() { return map; }

	public AIMode getAIMode() { return aiMode; }

	public void setAIMode(AIMode mode) { this.aiMode = mode; }

	public String getId() { return id; }

	public void setMap(String map) { this.map = map; }

	public void setOriginalPosition(int x, int y) {
		originalX = x;
		originalY = y;
	}
}
