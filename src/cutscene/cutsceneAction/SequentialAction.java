package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import cutscene.InputCutsceneAction;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;

public class SequentialAction implements InputCutsceneAction {

    private final CutsceneAction[] actions;
    private int currentActionIndex;
    private boolean isFinished;

    public SequentialAction(CutsceneAction... actions) {
        this.actions = actions;
    }

    @Override
    public void start() {
        isFinished = false;
        currentActionIndex = 0;
        actions[currentActionIndex].start();
    }

    @Override
    public void update() {
        if(currentActionIndex < 0 || currentActionIndex >= actions.length) return;
        actions[currentActionIndex].update();
        if (actions[currentActionIndex].isFinished()) {
            actions[currentActionIndex].end();
            nextAction();
            if(isFinished) return;
            if(actions[currentActionIndex].isFinished()) update();
        }
    }

    private void nextAction() {
        currentActionIndex++;
        if(currentActionIndex < actions.length) actions[currentActionIndex].start();
        else isFinished = true;
    }

    @Override
    public void end() {
        if(isFinished) return;
        actions[currentActionIndex].end();
    }

    @Override
    public void reset() {
        isFinished = false;
        currentActionIndex = 0;
        for (CutsceneAction action : actions) {
            action.reset();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        actions[currentActionIndex].draw(g2);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void keyTapped(KeyBindingHandler keyHandler) {
        if(isFinished) return;
        boolean canAcceptInput = actions[currentActionIndex] instanceof InputCutsceneAction;
        if (canAcceptInput) {
            ((InputCutsceneAction) actions[currentActionIndex]).keyTapped(keyHandler);
        }
    }

    @Override
    public void keyPressed(KeyBindingHandler keyHandler) {
        if(isFinished) return;
        boolean canAcceptInput = actions[currentActionIndex] instanceof InputCutsceneAction;
        if (canAcceptInput) {
            ((InputCutsceneAction) actions[currentActionIndex]).keyPressed(keyHandler);
        }
    }

    @Override
    public void keyReleased(Keys key) {
        if(isFinished) return;
        boolean canAcceptInput = actions[currentActionIndex] instanceof InputCutsceneAction;
        if (canAcceptInput) {
            ((InputCutsceneAction) actions[currentActionIndex]).keyReleased(key);
        }
    }
}
