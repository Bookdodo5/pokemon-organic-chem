package cutscene.cutsceneAction;

import cutscene.InputCutsceneAction;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Color;
import java.awt.Graphics2D;
import main.Constants;

public class FadeAction implements InputCutsceneAction {

    private final int duration;
    private final int target;
    private final int alphaChange;
    private int currentAlpha;
    private int currentTimer;
    private boolean isFinished;
    private final int persistence;
    
    public FadeAction(int duration, int start, int target, int persistence) {
        this.duration = duration;
        this.target = target;
        this.alphaChange = (target - start) / duration;
        this.currentAlpha = start;
        this.currentTimer = 0;
        this.isFinished = false;
        this.persistence = persistence;
    }

    public FadeAction(int duration, int start, int target) {
        this(duration, start, target, 0);
    }

    @Override
    public void start() {
        currentTimer = 0;
    }

    @Override
    public void update() {
        currentTimer++;
        currentAlpha += alphaChange;
        if (currentTimer >= duration) {
            currentAlpha = target;
        }
        if(persistence == -1) return;
        if(currentTimer >= persistence + duration) {
            isFinished = true;
        }
    }

    @Override
    public void end() {
    }

    @Override
    public void reset() {
        currentAlpha = 0;
        currentTimer = 0;
        isFinished = false;
    }

    @Override
    public void draw(Graphics2D g2) {
        if(currentAlpha <= 0) return;
        g2.setColor(new Color(0, 0, 0, currentAlpha));
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void keyReleased(Keys key) {
    }

    @Override
    public void keyTapped(KeyBindingHandler keyHandler) {
        if(persistence != -1) return;
        if(currentTimer < duration) return;
        if(keyHandler.getCurrentKey() == Keys.INTERACT) {
            isFinished = true;
            end();
        }
    }

    @Override
    public void keyPressed(KeyBindingHandler keyHandler) {
    }

}
