package entity;

import assets.AssetManager;
import assets.SoundManager;
import input.KeyBindingHandler;
import java.util.List;
import tile.MapManager;
import tile.TileManager;

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

	private void handleMovementInput(TileManager[] tileManagers, List<Entity> humans, MapManager mapManager) {

		switch (keyHandler.getCurrentKey()) {
			case UP -> setFacingDirection(FacingDirections.UP);
			case DOWN -> setFacingDirection(FacingDirections.DOWN);
			case LEFT -> setFacingDirection(FacingDirections.LEFT);
			case RIGHT -> setFacingDirection(FacingDirections.RIGHT);
			default -> {}
		}

		if (keyHandler.movementKeyPressed()) {
			canMove = checkCollision(x, y, tileManagers, humans, mapManager);
			if (!canMove) SoundManager.getSfxplayer().playSE("PlayerBump");
		}
	}
	
	@Override
	protected void handleIdle(TileManager[] tileManagers, List<Entity> humans, MapManager mapManager) {
		canMove = false;
		if (acceptInput) handleMovementInput(tileManagers, humans, mapManager);

		if (canMove) setMoving();
		else spriteIndex = 0;
	}

	@Override
	protected void handleIdle(TileManager[] tileManagers, List<Entity> humans) {
		handleIdle(tileManagers, humans, null);
	}
}