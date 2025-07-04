package entity;

import assets.AssetManager;
import assets.SoundManager;
import input.KeyBindingHandler;
import java.util.List;
import tile.MapData;
import tile.MapManager;

public class Player extends Human {
	KeyBindingHandler keyHandler;

	private boolean acceptInput;
	private boolean needsMapTransition = false;
	private int transitionToX, transitionToY;
	private String transitionToMap;

	public Player(KeyBindingHandler keyHandler, MapManager mapManager) {
		super(0, 0, mapManager.getCurrentMapID());
		this.keyHandler = keyHandler;
		this.spriteSheet = AssetManager.loadImage("/player/trainer_RED.png");
		this.acceptInput = true;
	}

	public void setAcceptInput(boolean acceptInput) { this.acceptInput = acceptInput; }

	private void handleMovementInput(List<Entity> humans, MapManager mapManager) {

		switch (keyHandler.getCurrentKey()) {
			case UP -> setFacingDirection(FacingDirections.UP);
			case DOWN -> setFacingDirection(FacingDirections.DOWN);
			case LEFT -> setFacingDirection(FacingDirections.LEFT);
			case RIGHT -> setFacingDirection(FacingDirections.RIGHT);
			default -> {}
		}

		if (keyHandler.movementKeyPressed()) {
			canMove = checkCollision(x, y, humans, mapManager);
			if (!canMove) SoundManager.getSfxplayer().playSE("PlayerBump");
		}
	}

	private void checkWalkAcrossMap(MapManager mapManager) {
		if(getMapX() >= 0 && getMapX() < mapManager.getWidth() &&
			getMapY() >= 0 && getMapY() < mapManager.getHeight()) {
			return;
		}

		int playerGlobalX = getMapX() + mapManager.getGlobalX();
		int playerGlobalY = getMapY() + mapManager.getGlobalY();
		
		MapData nextMap = mapManager.findMap(playerGlobalX, playerGlobalY);
		if (nextMap == null) {
			setMapX(Math.max(0, Math.min(getMapX(), mapManager.getWidth() - 1)));
			setMapY(Math.max(0, Math.min(getMapY(), mapManager.getHeight() - 1)));
			return;
		}
		
		needsMapTransition = true;
		transitionToX = playerGlobalX - nextMap.getGlobalX();
		transitionToY = playerGlobalY - nextMap.getGlobalY();
		transitionToMap = nextMap.getMapName();
	}

	public boolean needsMapTransition() {
		return needsMapTransition;
	}

	public void clearMapTransition() {
		needsMapTransition = false;
	}

	public int getTransitionToX() { return transitionToX; }
	public int getTransitionToY() { return transitionToY; }
	public String getTransitionToMap() { return transitionToMap; }
	
	@Override
	protected void handleIdle(List<Entity> humans, MapManager mapManager) {
		checkWalkAcrossMap(mapManager);
		if(needsMapTransition) return;
		canMove = false;
		if (acceptInput) handleMovementInput(humans, mapManager);

		if (canMove) setMoving();
		else {
			spriteIndex = 0;
			setTarget((int) x, (int) y);
		}
	}
}