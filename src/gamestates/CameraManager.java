package gamestates;

import entity.Human;
import static main.Constants.SCALE;
import static main.Constants.SCREEN_HEIGHT;
import static main.Constants.SCREEN_WIDTH;

public class CameraManager {
	private int cameraX, cameraY;
	private Human focusPoint;
	private boolean isFollowingHuman;

	public CameraManager(int cameraX, int cameraY) {
		this.cameraX = cameraX;
		this.cameraY = cameraY;
		this.focusPoint = null;
		this.isFollowingHuman = false;
	}

	public CameraManager(Human focusPoint) {
		this.cameraX = (int) focusPoint.getX() - ((int) ((double) SCREEN_WIDTH / SCALE) - focusPoint.getSpriteWidth()) / 2;
		this.cameraY = (int) focusPoint.getY() - ((int) ((double) SCREEN_HEIGHT / SCALE) - focusPoint.getSpriteHeight()) / 2;
		this.focusPoint = focusPoint;
		this.isFollowingHuman = true;
	}

	public void setFocusPoint(Human focusPoint) {
		this.focusPoint = focusPoint;
		this.isFollowingHuman = true;
	}

	public void resetFocusPoint() {
		this.isFollowingHuman = false;
	}

	public void update() {
		if (isFollowingHuman) {
			cameraX = (int) focusPoint.getX() - ((int) ((double) SCREEN_WIDTH / SCALE) - focusPoint.getSpriteWidth()) / 2;
			cameraY = (int) focusPoint.getY() - ((int) ((double) SCREEN_HEIGHT / SCALE) - focusPoint.getSpriteHeight()) / 2;
		}
	}

	public void update(int x, int y) {
		cameraX = x;
		cameraY = y;
	}

	public void move(int x, int y) {
		cameraX += x;
		cameraY += y;
	}

	public int getCameraX() {
		return cameraX;
	}

	public int getCameraY() {
		return cameraY;
	}
}
