package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import java.awt.Color;
import java.awt.Graphics2D;
import main.Constants;

public class FadeAction implements CutsceneAction {

    private final int fadeDuration;
    private final int targetAlpha;
    private final int startAlpha;
    private int currentAlpha;
    private int currentTimer;
    private boolean isFinished;

    public FadeAction(int fadeDuration, int startAlpha, int targetAlpha) {
        this.fadeDuration = fadeDuration;
        this.targetAlpha = targetAlpha;
        this.startAlpha = startAlpha;
        this.currentAlpha = startAlpha;
        this.currentTimer = 0;
        this.isFinished = false;
    }

    @Override
    public void start() {
        currentTimer = 0;
    }

    @Override
    public void update() {
        currentTimer++;
        if (currentTimer >= fadeDuration) {
            currentAlpha = targetAlpha;
            isFinished = true;
        }
        int alphaChange = (targetAlpha - startAlpha) / fadeDuration;
        currentAlpha += alphaChange;
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

}
