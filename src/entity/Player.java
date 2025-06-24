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

	public Player(int positionX, int positionY, KeyBindingHandler keyHandler) {
		super(positionX, positionY);
		this.keyHandler = keyHandler;
		this.spriteSheet = AssetManager.loadImage("/player/trainer_SUPERNERD.png");
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
			setMapX(Math.max(0,Math.min(getMapX(), mapManager.getWidth() - 1)));
			setMapY(Math.max(0, Math.min(getMapY(), mapManager.getHeight() - 1)));
			return;
		}
		
		int nextMapX = playerGlobalX - nextMap.getGlobalX();
		int nextMapY = playerGlobalY - nextMap.getGlobalY();
		mapManager.setCurrentMap(nextMap.getMapName());
		setMapX(nextMapX);
		setMapY(nextMapY);
	}
	
	@Override
	protected void handleIdle(List<Entity> humans, MapManager mapManager) {
		checkWalkAcrossMap(mapManager);
		canMove = false;
		if (acceptInput) handleMovementInput(humans, mapManager);

		if (canMove) setMoving();
		else {
			spriteIndex = 0;
			setTarget((int) x, (int) y);
		}
	}
}