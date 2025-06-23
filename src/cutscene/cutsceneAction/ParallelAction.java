package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import cutscene.InputCutsceneAction;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;

public class ParallelAction implements InputCutsceneAction {

    private final CutsceneAction[] actions;
    private boolean isFinished;

    public ParallelAction(CutsceneAction... actions) {
        this.actions = actions;
    }

    @Override
    public void start() {
        isFinished = false;
        for (CutsceneAction action : actions) {
            action.start();
        }
    }

    @Override
    public void update() {
        isFinished = true;
        for (CutsceneAction action : actions) {
            if (!action.isFinished()) action.update();
            isFinished = isFinished && action.isFinished();
        }
    }

    @Override
    public void end() {
        for (CutsceneAction action : actions) {
            if (!action.isFinished()) action.end();
        }
    }

    @Override
    public void reset() {
        isFinished = false;
        for (CutsceneAction action : actions) {
            if (!action.isFinished()) action.reset();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        for (CutsceneAction action : actions) {
            if (!action.isFinished()) action.draw(g2);
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void keyTapped(KeyBindingHandler keyHandler) {
        for (CutsceneAction action : actions) {
            boolean canAcceptInput = action instanceof InputCutsceneAction;
            if (canAcceptInput) {
                ((InputCutsceneAction) action).keyTapped(keyHandler);
            }
        }
    }

    @Override
    public void keyPressed(KeyBindingHandler keyHandler) {
        for (CutsceneAction action : actions) {
            boolean canAcceptInput = action instanceof InputCutsceneAction;
            if (canAcceptInput) {
                ((InputCutsceneAction) action).keyPressed(keyHandler);
            }
        }
    }

    @Override
    public void keyReleased(Keys key) {
        for (CutsceneAction action : actions) {
            boolean canAcceptInput = action instanceof InputCutsceneAction;
            if (canAcceptInput) {
                ((InputCutsceneAction) action).keyReleased(key);
            }
        }
    }

    }
