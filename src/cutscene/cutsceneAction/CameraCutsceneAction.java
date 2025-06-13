package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import entity.Human;
import gamestates.CameraManager;
import java.awt.Graphics2D;

public class CameraCutsceneAction implements CutsceneAction {

	enum CameraMode {
		FOLLOW_HUMAN,
		MOVE,
		SET,
		SHAKE
	}

	private final CameraManager cameraManager;
	private final CameraMode cameraMode;
	private int moveX, moveY, setX, setY;
	private int originalX, originalY;
	private int time;
	private int timeCounter;
	private int shakeIntensity;
	private boolean isFinished;
	private Human focusPoint = null;

	public CameraCutsceneAction(CameraManager cameraManager, int moveX, int moveY, int time) {
		this.cameraManager = cameraManager;
		this.moveX = moveX;
		this.moveY = moveY;
		this.time = time;
		this.cameraMode = CameraMode.MOVE;
		this.isFinished = false;
		this.timeCounter = 0;
	}

	public CameraCutsceneAction(CameraManager cameraManager, Human focusPoint) {
		this.cameraManager = cameraManager;
		this.focusPoint = focusPoint;
		this.cameraMode = CameraMode.FOLLOW_HUMAN;
		this.isFinished = false;
	}

	public CameraCutsceneAction(CameraManager cameraManager, int setX, int setY) {
		this.cameraManager = cameraManager;
		this.setX = setX;
		this.setY = setY;
		this.cameraMode = CameraMode.SET;
		this.isFinished = false;
	}

	public CameraCutsceneAction(CameraManager cameraManager, int shakeDuration) {
		this.cameraManager = cameraManager;
		this.time = shakeDuration;
		this.shakeIntensity = 5;
		this.cameraMode = CameraMode.SHAKE;
		this.isFinished = false;
		this.timeCounter = 0;
	}

	@Override
	public void update() {
		switch (cameraMode) {
			case FOLLOW_HUMAN -> {
				cameraManager.update();
				end();
			}
			case MOVE -> {
				if (timeCounter >= time) {
					end();
					return;
				}
				int nextX = (int)(originalX + (moveX * timeCounter)/time);
				int nextY = (int)(originalY + (moveY * timeCounter)/time);
				cameraManager.update(nextX, nextY);
				timeCounter++;
			}
			case SET -> {
				cameraManager.update(setX, setY);
				end();
			}
			case SHAKE -> {
				if (timeCounter >= time) {
					cameraManager.update(originalX, originalY);
					end();
					return;
				}
				
				double fadeRatio = 1.0 - (double) timeCounter / time;
				double currentIntensity = shakeIntensity * fadeRatio;
				
				int shakeX = (int) ((Math.random() - 0.5) * 2 * currentIntensity);
				int shakeY = (int) ((Math.random() - 0.5) * 2 * currentIntensity);
				
				cameraManager.update(originalX + shakeX, originalY + shakeY);
				timeCounter++;
			}
		}
	}

	@Override
	public void start() {
		switch (cameraMode) {
			case FOLLOW_HUMAN -> {
				cameraManager.setFocusPoint(focusPoint);
			}
			case MOVE -> {
				originalX = cameraManager.getCameraX();
				originalY = cameraManager.getCameraY();
				cameraManager.resetFocusPoint();
			}
			case SET -> {
				cameraManager.resetFocusPoint();
			}
			case SHAKE -> {
				originalX = cameraManager.getCameraX();
				originalY = cameraManager.getCameraY();
			}
		}
	}

	@Override
	public void end() { isFinished = true; }

	@Override
	public void draw(Graphics2D g2) {}

	@Override
	public boolean isFinished() { return isFinished; }

	@Override
	public void reset() {
		isFinished = false;
		timeCounter = 0;
	}
}

