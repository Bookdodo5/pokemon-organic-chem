package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import cutscene.InputCutsceneAction;
import gamestates.FlagManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import java.util.function.BooleanSupplier;

public class ConditionAction implements InputCutsceneAction {

    private final BooleanSupplier condition;
    private final CutsceneAction trueAction;
    private boolean isFinished;
    
    public ConditionAction(BooleanSupplier condition, CutsceneAction trueAction) {
        this.condition = condition;
        this.trueAction = trueAction;
        this.isFinished = false;
    }

    public ConditionAction(FlagManager flagManager, String flag, CutsceneAction trueAction) {
        this(() -> flagManager.hasFlag(flag), trueAction);
    }

    public ConditionAction(FlagManager flagManager, String flag, int expectedValue, CutsceneAction trueAction) {
        this(() -> flagManager.getFlag(flag) == expectedValue, trueAction);
    }

    @Override
    public void start() {
        isFinished = false;
        
        if (!condition.getAsBoolean()) {
            isFinished = true;
            trueAction.end();
            return;
        }
        
        trueAction.start();
    }

    @Override
    public void update() {
        if (condition.getAsBoolean() && !trueAction.isFinished()) {
            trueAction.update();
            isFinished = trueAction.isFinished();
        }
    }

    @Override
    public void end() {
        trueAction.end();
        isFinished = true;
    }

    @Override
    public void reset() {
        isFinished = false;
        trueAction.reset();
    }

    @Override
    public void draw(Graphics2D g2) {
        trueAction.draw(g2);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void keyTapped(KeyBindingHandler keyHandler) {
        if(isFinished) return;
        boolean canAcceptInput = trueAction instanceof InputCutsceneAction;
        if (canAcceptInput) {
            ((InputCutsceneAction) trueAction).keyTapped(keyHandler);
        }
    }

    @Override
    public void keyPressed(KeyBindingHandler keyHandler) {
        if(isFinished) return;
        boolean canAcceptInput = trueAction instanceof InputCutsceneAction;
        if (canAcceptInput) {
            ((InputCutsceneAction) trueAction).keyPressed(keyHandler);
        }
    }

    @Override
    public void keyReleased(Keys key) {
        if(isFinished) return;
        boolean canAcceptInput = trueAction instanceof InputCutsceneAction;
        if (canAcceptInput) {
            ((InputCutsceneAction) trueAction).keyReleased(key);
        }
    }
}
