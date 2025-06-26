package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import entity.Human;
import entity.NPC;
import gamestates.states.OverworldState;
import java.awt.Graphics2D;

public class TeleportAction implements CutsceneAction {

    private final Human targetHuman;
    private final int targetX;
    private final int targetY;
    private final String targetMap;
    private final boolean isNPC;
    private final OverworldState overworldState;
    private boolean isFinished;

    public TeleportAction(Human targetHuman, int targetX, int targetY, String targetMap, OverworldState overworldState) {
        this.targetHuman = targetHuman;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetMap = targetMap;
        this.isNPC = targetHuman instanceof NPC;
        this.isFinished = false;
        this.overworldState = overworldState;
    }

    @Override
    public void start() {
        if(isNPC) {
            ((NPC) targetHuman).setMap(targetMap);
            targetHuman.setMapX(targetX);
            targetHuman.setMapY(targetY);
        } else {
            overworldState.setMap(targetX, targetY, targetMap);
        }
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
