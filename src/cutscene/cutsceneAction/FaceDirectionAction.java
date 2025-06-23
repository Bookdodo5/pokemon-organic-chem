package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import entity.FacingDirections;
import entity.Human;
import java.awt.Graphics2D;

public class FaceDirectionAction implements CutsceneAction {

    private final Human targetHuman;
    private final FacingDirections direction;
    private final Human directionHuman;
    private boolean isFinished;

    public FaceDirectionAction(Human targetHuman, FacingDirections direction) {
        this.targetHuman = targetHuman;
        this.direction = direction;
        this.directionHuman = null;
        this.isFinished = false;
    }

    public FaceDirectionAction(Human targetHuman, Human directionHuman) {
        this.targetHuman = targetHuman;
        this.direction = null;
        this.directionHuman = directionHuman;
        this.isFinished = false;
    }

    @Override
    public void start() {
        FacingDirections targetDirection = calculateTargetDirection();
        targetHuman.setFacingDirection(targetDirection);
        isFinished = true;
    }

    private FacingDirections calculateTargetDirection() {
        if (directionHuman == null) return direction;
        return determineDirectionTowardsTarget();
    }

    private FacingDirections determineDirectionTowardsTarget() {
        int deltaX = targetHuman.getMapX() - directionHuman.getMapX();
        int deltaY = targetHuman.getMapY() - directionHuman.getMapY();
        
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            return deltaX > 0 ? FacingDirections.LEFT : FacingDirections.RIGHT;
        } else {
            return deltaY > 0 ? FacingDirections.UP : FacingDirections.DOWN;
        }
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
