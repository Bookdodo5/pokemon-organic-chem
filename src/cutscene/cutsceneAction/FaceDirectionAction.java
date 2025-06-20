package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import entity.FacingDirections;
import entity.Human;
import java.awt.Graphics2D;

public class FaceDirectionAction implements CutsceneAction {

    private final Human targetHuman;
	private final FacingDirections direction;
	private boolean isFinished;

    public FaceDirectionAction(Human targetHuman, FacingDirections direction) {
        this.targetHuman = targetHuman;
        this.direction = direction;
		this.isFinished = false;
    }

    @Override
    public void start() {
        targetHuman.setFacingDirection(direction);
		isFinished = true;
    }

    @Override
    public void update() {
    }

    @Override
    public void end() {
    }

    @Override
    public void reset() {
		isFinished = false;
    }

    @Override
    public void draw(Graphics2D g2) {
    }

    @Override
    public boolean isFinished() {
		return isFinished;
    }
}
