package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import entity.AIMode;
import entity.FacingDirections;
import entity.Human;
import entity.NPC;
import java.awt.Graphics2D;

public class MovementCutsceneAction implements CutsceneAction {
	
	private final Human targetHuman;
	private final int targetX;
	private final int targetY;
	private final boolean isNPC;
	private boolean isFinished;

	public MovementCutsceneAction(Human targetHuman, int targetX, int targetY) {
		this.targetHuman = targetHuman;
		this.targetX = targetX;
		this.targetY = targetY;
		this.isNPC = targetHuman instanceof NPC;
		this.isFinished = false;
	}

	@Override
	public void start() {
		if(isNPC) {
			NPC npc = (NPC) targetHuman;
			npc.setAIMode(AIMode.STILL);
		}
	}

	@Override
	public void update() {
		int currentX = targetHuman.getMapX();
		int currentY = targetHuman.getMapY();

		if(targetHuman.isIdle() && currentX == targetX && currentY == targetY) {
			end();
			return;
		}

		if(targetHuman.isIdle()) {
			if(currentX < targetX) {
				targetHuman.currentDirection = FacingDirections.RIGHT;
				targetHuman.setMoving();
			} else if(currentX > targetX) {
				targetHuman.currentDirection = FacingDirections.LEFT;
				targetHuman.setMoving();
			} else if(currentY < targetY) {
				targetHuman.currentDirection = FacingDirections.DOWN;
				targetHuman.setMoving();
			} else if(currentY > targetY) {
				targetHuman.currentDirection = FacingDirections.UP;
				targetHuman.setMoving();
			}
		}
	}

	@Override
	public void end() {
		targetHuman.setMapX(targetX);
		targetHuman.setMapY(targetY);
		
		targetHuman.targetX = (int) targetHuman.getX();
		targetHuman.targetY = (int) targetHuman.getY();
		
		targetHuman.setIdle();

		if(isNPC) {
			NPC npc = (NPC) targetHuman;
			npc.setOriginalPosition(targetX, targetY);
		}
		
		isFinished = true;
	}

	@Override
	public void draw(Graphics2D g2) { }

	@Override
	public boolean isFinished() { return isFinished; }

	@Override
	public void reset() {
		isFinished = false;
	}

}
