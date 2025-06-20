package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import java.awt.Color;
import java.awt.Graphics2D;
import main.Constants;

public class FadeAction implements CutsceneAction {

    private final int fadeDuration;
    private final int targetAlpha;
    private final int alphaChange;
    private int currentAlpha;
    private int currentTimer;
    private boolean isFinished;
    private final int persistence;
    
    public FadeAction(int fadeDuration, int startAlpha, int targetAlpha, int persistence) {
        this.fadeDuration = fadeDuration;
        this.targetAlpha = targetAlpha;
        this.alphaChange = (targetAlpha - startAlpha) / fadeDuration;
        this.currentAlpha = startAlpha;
        this.currentTimer = 0;
        this.isFinished = false;
        this.persistence = persistence;
    }

    public FadeAction(int fadeDuration, int startAlpha, int targetAlpha) {
        this(fadeDuration, startAlpha, targetAlpha, 0);
    }

    @Override
    public void start() {
        currentTimer = 0;
    }

    @Override
    public void update() {
        currentTimer++;
        currentAlpha += alphaChange;
        if (currentTimer >= fadeDuration) {
            currentAlpha = targetAlpha;
        }
        if(currentTimer >= persistence + fadeDuration) {
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

}
