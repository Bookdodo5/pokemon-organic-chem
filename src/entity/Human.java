package entity;

import assets.AssetManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import main.Constants;
import static main.Constants.*;
import tile.CollisionChecker;
import tile.TileManager;

public abstract class Human extends Entity {

	// Constants
	protected static final Map<FacingDirections, int[]> DIRECTION_VECTORS = Map.of(
			FacingDirections.UP, new int[]{
				0,
				-1
			},
			FacingDirections.DOWN, new int[]{
				0,
				1
			},
			FacingDirections.LEFT, new int[]{
				-1,
				0
			},
			FacingDirections.RIGHT, new int[]{
				1,
				0
			});

	// Movement
	public int targetX, targetY;
	protected int[] currentDirectionVector = DIRECTION_VECTORS.get(FacingDirections.DOWN);
	protected int idleWalkingCounter = 0;
	protected boolean canMove = false;
	protected BufferedImage spriteSheet;

	// State
	public FacingDirections currentDirection = FacingDirections.DOWN;
	public MovementStates currentMovementState = MovementStates.IDLE;
	private CollisionChecker collisionChecker;

	public Human(int positionX, int positionY) {

		this.spriteWidth = ORIGINAL_TILE_SIZE;
		this.spriteHeight = (int) (ORIGINAL_TILE_SIZE * 1.5);
		this.x = positionX * Constants.ORIGINAL_TILE_SIZE;
		this.y = positionY * Constants.ORIGINAL_TILE_SIZE;
		this.targetX = (int) x;
		this.targetY = (int) y;
		this.currentMovementState = MovementStates.IDLE;
		this.currentDirection = FacingDirections.DOWN;

		try {
			this.collisionChecker = new CollisionChecker();
		} catch (IOException e) {
			System.err.println("Failed to initialize collision checker: " + e.getMessage());
		}
	}

	public void setFacingDirection(FacingDirections direction) {
		currentDirection = direction;
	}

	protected void updateAnimation() {
		animationCounter++;
		if (animationCounter >= ANIMATION_SPEED) {
			spriteIndex = (spriteIndex + 1) % 4;
			animationCounter = 0;
		}
	}

	public BufferedImage getSpriteAnimation(BufferedImage spriteSheet) {
		int frameLocation = spriteIndex * spriteWidth;
		int directionLocation = 0;

		switch (currentDirection) {
			case DOWN -> directionLocation = 0 * spriteHeight;
			case LEFT -> directionLocation = 1 * spriteHeight;
			case RIGHT -> directionLocation = 2 * spriteHeight;
			case UP -> directionLocation = 3 * spriteHeight;
		}

		return AssetManager.getSprite(spriteSheet, frameLocation, directionLocation, spriteWidth, spriteHeight);
	}

	protected boolean checkCollision(double x, double y, TileManager[] tileManagers, List<Entity> humans) {
		currentDirectionVector = DIRECTION_VECTORS.get(currentDirection);
		int checkX = getMapX() + currentDirectionVector[0];
		int checkY = getMapY() + currentDirectionVector[1];

		boolean tileResult = collisionChecker.checkCollision(checkX, checkY, tileManagers);
		boolean humanResult = humans.stream()
				.allMatch(human -> (human.getMapX() != checkX) || (human.getMapY() != checkY));
		boolean result = tileResult && humanResult;

		if (!result) setIdleWalking();
		return result;
	}

	public void setIdle() {
		x = targetX;
		y = targetY;
		currentMovementState = MovementStates.IDLE;
	}

	public void setMoving() {
		idleWalkingCounter = 0;
		currentDirectionVector = DIRECTION_VECTORS.get(currentDirection);
		targetX = (int) x + ORIGINAL_TILE_SIZE * currentDirectionVector[0];
		targetY = (int) y + ORIGINAL_TILE_SIZE * currentDirectionVector[1];
		currentMovementState = MovementStates.MOVING;
	}

	public void setIdleWalking() {
		idleWalkingCounter = 18;
		currentMovementState = MovementStates.IDLE_WALKING;
	}

	protected void handleIdle(TileManager[] tileManagers, List<Entity> humans) {

	};

	private void handleMoving() {
		x += currentDirectionVector[0] * SPEED;
		y += currentDirectionVector[1] * SPEED;

		if ((currentDirection == FacingDirections.UP && y <= targetY)
				|| (currentDirection == FacingDirections.DOWN && y >= targetY)
				|| (currentDirection == FacingDirections.LEFT && x <= targetX)
				|| (currentDirection == FacingDirections.RIGHT && x >= targetX)) {
			setIdle();
		}

		updateAnimation();
	}

	private void handleIdleWalking() {
		idleWalkingCounter--;
		if (idleWalkingCounter <= 0) setIdle();

		updateAnimation();
	}

	public boolean isIdle() { return currentMovementState == MovementStates.IDLE; }

	public void update(TileManager[] tileManagers, List<Entity> humans) {
		switch (currentMovementState) {
			case IDLE -> handleIdle(tileManagers, humans);
			case MOVING -> handleMoving();
			case IDLE_WALKING -> handleIdleWalking();
		}
	}

	@Override
	public void draw(Graphics2D g2, int cameraX, int cameraY) {
		int drawX = (int) (x - cameraX);
		int drawY = (int) (y - cameraY - (int) ((getSpriteHeight() * SCALE) / 6));
		g2.drawImage(getSpriteAnimation(spriteSheet), drawX, drawY, getSpriteWidth(), getSpriteHeight(),
				null);
	}
}
