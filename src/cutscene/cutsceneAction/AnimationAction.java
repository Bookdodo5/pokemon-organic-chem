package cutscene.cutsceneAction;

import assets.AnimationManager;
import cutscene.CutsceneAction;
import gamestates.CameraManager;
import java.awt.Graphics2D;
import static main.Constants.ORIGINAL_TILE_SIZE;

public class AnimationAction implements CutsceneAction {

    private final AnimationManager animationManager;
    private final String animation;
    private final int x;
    private final int y;
    private final double scale;
    private final int animationSpeed;
    private final CameraManager cameraManager;
    private boolean isFinished;
    private int animationCounter;

    public AnimationAction(String animation, int x, int y, double scale) {
        this.animationManager = new AnimationManager();
        this.animation = animation;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.cameraManager = null;
        this.isFinished = false;
        this.animationCounter = 0;
        this.animationSpeed = 4;
    }

    public AnimationAction(String animation, int x, int y, double scale, CameraManager cameraManager) {
        this.animationManager = new AnimationManager();
        this.animation = animation;
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.cameraManager = cameraManager;
        this.isFinished = false;
        this.animationCounter = 0;
        this.animationSpeed = 4;
    }

    @Override
    public void start() {
        animationManager.loadAnimation(animation);
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
    public void end() {
        isFinished = true;
    }

    @Override
    public void draw(Graphics2D g2) {
        int drawX = x;
        int drawY = y;

        if (cameraManager != null) {
            int worldX = x * ORIGINAL_TILE_SIZE;
            int worldY = y * ORIGINAL_TILE_SIZE;
			int frameSize = (int) (animationManager.getFrameSize() * scale);

            drawX = worldX - cameraManager.getCameraX() - frameSize / 2 + ORIGINAL_TILE_SIZE / 2;
            drawY = worldY - cameraManager.getCameraY() - frameSize / 2 + ORIGINAL_TILE_SIZE / 2;
        }
        animationManager.draw(g2, drawX, drawY, scale);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void reset() {
        isFinished = false;
        animationCounter = 0;
    }

}
