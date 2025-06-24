package entity;

import assets.AnimationManager;
import assets.AssetManager;
import assets.SoundManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import main.Constants;
import static main.Constants.*;
import tile.CollisionChecker;
import tile.MapManager;
import tile.TileGrass;
import tile.TileManager;

public abstract class Human extends Entity {

	// Movement
	private int targetX, targetY;
	protected int idleWalkingCounter = 0;
	protected boolean canMove = false;
	protected BufferedImage spriteSheet;

	private FacingDirections currentDirection = FacingDirections.DOWN;
	private MovementStates currentMovementState = MovementStates.IDLE;
	private final CollisionChecker collisionChecker = new CollisionChecker();

	private final AnimationManager grassAnimationManager;

	public Human(int positionX, int positionY) {

		this.spriteWidth = ORIGINAL_TILE_SIZE;
		this.spriteHeight = (int) (ORIGINAL_TILE_SIZE * 1.5);
		this.x = positionX * Constants.ORIGINAL_TILE_SIZE;
		this.y = positionY * Constants.ORIGINAL_TILE_SIZE;
		this.targetX = (int) x;
		this.targetY = (int) y;
		this.currentMovementState = MovementStates.IDLE;
		this.currentDirection = FacingDirections.DOWN;

		this.grassAnimationManager = new AnimationManager();
	}

	public FacingDirections getCurrentDirection() {
		return currentDirection;
	}

	public MovementStates getCurrentMovementState() {
		return currentMovementState;
	}

	public void setFacingDirection(FacingDirections direction) {
		currentDirection = direction;
	}

	public void setTarget(int x, int y) {
		targetX = x;
		targetY = y;
	}

	public int getTargetX() {
		return targetX;
	}

	public int getTargetY() {
		return targetY;
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

	protected boolean checkCollision(double x, double y, List<Entity> humans, MapManager mapManager) {
		int checkX = getMapX() + currentDirection.getX();
		int checkY = getMapY() + currentDirection.getY();

		boolean tileResult = collisionChecker.checkCollision(checkX, checkY, mapManager);
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
		targetX = (int) x + ORIGINAL_TILE_SIZE * currentDirection.getX();
		targetY = (int) y + ORIGINAL_TILE_SIZE * currentDirection.getY();
		currentMovementState = MovementStates.MOVING;
	}

	public void setIdleWalking() {
		idleWalkingCounter = 18;
		currentMovementState = MovementStates.IDLE_WALKING;
	}

	protected abstract void handleIdle(List<Entity> humans, MapManager mapManager);

	private void handleMoving() {
		x += currentDirection.getX() * SPEED;
		y += currentDirection.getY() * SPEED;

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

	public void update(MapManager mapManager, List<Entity> humans) {
		MovementStates prevState = currentMovementState;
		
		switch (currentMovementState) {
			case IDLE -> handleIdle(humans, mapManager);
			case MOVING -> handleMoving();
			case IDLE_WALKING -> handleIdleWalking();
		}

		boolean isPlayer = this instanceof Player;
		boolean isOnGrass = isOnGrass(mapManager);
		boolean isIdle = currentMovementState == MovementStates.IDLE;
		boolean wasMoving = prevState == MovementStates.MOVING;

		if (isPlayer && isOnGrass && wasMoving && isIdle) {
			SoundManager.getSfxplayer().playSE("Grass");
			grassAnimationManager.loadAnimation("grass");
		}

		if (!grassAnimationManager.isFinished()) {
			grassAnimationManager.update();
		}
	}

	public void update(List<Entity> humans, MapManager mapManager) {
		MovementStates prevState = currentMovementState;
		
		switch (currentMovementState) {
			case IDLE -> handleIdle(humans, mapManager);
			case MOVING -> handleMoving();
			case IDLE_WALKING -> handleIdleWalking();
		}

		boolean isOnGrass = isOnGrass(mapManager);
		boolean isIdle = currentMovementState == MovementStates.IDLE;
		boolean wasMoving = prevState == MovementStates.MOVING;

		if (isOnGrass && wasMoving && isIdle) {
			SoundManager.getSfxplayer().playSE("Grass");
			grassAnimationManager.loadAnimation("grass");
		}

		if (!grassAnimationManager.isFinished()) {
			grassAnimationManager.update();
		}
	}

	@Override
	public void draw(Graphics2D g2, int cameraX, int cameraY, MapManager mapManager) {
		boolean isOnGrass = isOnGrass(mapManager);
		int drawX = (int) (x - cameraX);
		int drawY = (int) (y - cameraY - (int) ((getSpriteHeight() * SCALE) / 6));
		
		java.awt.Shape originalClip = g2.getClip();
		if (isOnGrass && Math.abs(x-targetX) < 20 && Math.abs(y-targetY) < 20) {
			g2.setClip(drawX, drawY, getSpriteWidth(), getSpriteHeight() - 10);
		}
		g2.drawImage(getSpriteAnimation(spriteSheet), drawX, drawY, getSpriteWidth(), getSpriteHeight(),
			null);
		g2.setClip(originalClip);

		if(!grassAnimationManager.isFinished()) {
			int grassDrawX = drawX + getSpriteWidth() / 2;
			int grassDrawY = drawY + getSpriteHeight() / 2 + 10;
			grassAnimationManager.drawCenter(g2, grassDrawX, grassDrawY, 2);
		}
	}

	private boolean isOnGrass(MapManager mapManager) {
		TileManager decorationLayer = mapManager.getCurrentLayers()[1];
		boolean humanOnGrass = decorationLayer.getTile(getMapX(), getMapY()) instanceof TileGrass;
		boolean targetOnGrass = decorationLayer.getTile(targetX/ORIGINAL_TILE_SIZE, targetY/ORIGINAL_TILE_SIZE) instanceof TileGrass;
		return humanOnGrass && targetOnGrass;
	}
}
