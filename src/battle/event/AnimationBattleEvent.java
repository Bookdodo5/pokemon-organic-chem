package battle.event;

import assets.AnimationManager;
import java.awt.Graphics2D;
import main.Constants;
import static main.Constants.SCALE;

public class AnimationBattleEvent extends BattleEvent {

    private AnimationManager animationManager;
    private final String animationPath;
    private final int x, y;
    private final double scale;
    private final int animationSpeed;
    private int animationCounter;

    public AnimationBattleEvent(String animationPath, AnimationPosition animationPosition, double scale, int animationSpeed) {
        this.animationManager = new AnimationManager();
        animationManager.loadAnimation(animationPath);
        int availableHeight = (int) (Constants.SCREEN_HEIGHT / SCALE - 110 - 45);
        int availableWidth = (int) (Constants.SCREEN_WIDTH / SCALE);
        switch (animationPosition) {
            case BOARD -> {
                this.x = (availableWidth - animationManager.getFrameSize()) / 2;
                this.y = (availableHeight - animationManager.getFrameSize()) / 2 + 45;
            }
            case PLAYER -> {
                this.x = 110 - animationManager.getFrameSize() / 2;
                this.y = 210 - animationManager.getFrameSize() / 2;
            }
            case OPPONENT -> {
                this.x = (int) (availableWidth - 110 - animationManager.getFrameSize() / 2);
                this.y = 100 - animationManager.getFrameSize() / 2;
            }
            default -> {
                this.x = 0;
                this.y = 0;
            }
        }
        this.animationPath = animationPath;
        this.scale = scale;
        this.animationCounter = 0;
        this.animationSpeed = animationSpeed;
    }

    @Override
    public void start() {
        animationManager.loadAnimation(animationPath);
    }

    @Override
    public void update() {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            animationCounter = 0;
            animationManager.nextFrame();
        }

        if (animationManager.isFinished()) {
            end();
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        animationManager.draw(g2, x, y, scale);
    }

}
