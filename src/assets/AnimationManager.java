package assets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimationManager {

    private static final Map<String, int[]> ANIMATION_MAP = new HashMap<>();

    static {
        //animationName -> (framesPerRow, framesPerCol, frameSize, scaleFactor, speed)
        ANIMATION_MAP.put("electric1", new int[]{5, 3, 192, 1, 4});
        ANIMATION_MAP.put("fire4", new int[]{5, 3, 192, 1, 4});
        ANIMATION_MAP.put("fire5", new int[]{5, 3, 192, 1, 4});
        ANIMATION_MAP.put("water3", new int[]{5, 3, 192, 1, 4});
        ANIMATION_MAP.put("poison4", new int[]{5, 3, 192, 1, 4});
        ANIMATION_MAP.put("24", new int[]{14, 1, 64, 3, 4});
        ANIMATION_MAP.put("26", new int[]{14, 1, 64, 3, 4});
        ANIMATION_MAP.put("grass", new int[]{3, 1, 192, 1, 6});
    }

    private int framesPerRow = 5;
    private int framesPerCol = 3;
    private int frameSize = 192;
    private int totalFrames = 15;
    private int scaleFactor = 1;
    private int animationSpeed = 4;

    private final BufferedImage[] frames;
    private int currentFrame = Integer.MAX_VALUE;

    private int animationCounter = 0;

    public AnimationManager() {
        frames = new BufferedImage[100];
    }

    public int getFrameSize() {
        return frameSize * scaleFactor;
    }

    public void loadAnimation(String animationPath) {

        framesPerRow = ANIMATION_MAP.get(animationPath)[0];
        framesPerCol = ANIMATION_MAP.get(animationPath)[1];
        frameSize = ANIMATION_MAP.get(animationPath)[2];
        scaleFactor = ANIMATION_MAP.get(animationPath)[3];
        animationSpeed = ANIMATION_MAP.get(animationPath)[4];
        totalFrames = framesPerRow * framesPerCol;

        String fullPath = "/animations/" + animationPath + ".png";
        BufferedImage spriteSheet = AssetManager.loadImage(fullPath);
        currentFrame = 0;

        if (spriteSheet == null || ANIMATION_MAP.get(animationPath) == null) {
            System.err.println("Failed to load animation: " + animationPath);
            return;
        }

        for (int y = 0; y < framesPerCol; y++) {
            for (int x = 0; x < framesPerRow; x++) {
                int frameIndex = y * framesPerRow + x;
                if (frameIndex >= totalFrames) {
                    break;
                }
                frames[frameIndex] = spriteSheet.getSubimage(x * frameSize, y * frameSize, frameSize, frameSize);
            }
        }
    }

    public BufferedImage getFrame(int index) {
        return frames[index];
    }

    public boolean isFinished() {
        return currentFrame >= totalFrames;
    }

    public void nextFrame() {
        currentFrame++;
    }

    public void update() {
        animationCounter++;
        if (animationCounter >= animationSpeed) {
            currentFrame++;
            animationCounter = 0;
        }
    }

    public void drawCenter(Graphics2D g2, int x, int y) {
        drawCenter(g2, x, y, 1);
    }

    public void drawCenter(Graphics2D g2, int x, int y, double scale) {
        int newX = x - (int) (frameSize * scaleFactor / 2);
        int newY = y - (int) (frameSize * scaleFactor / 2);
        g2.drawImage(frames[currentFrame], newX, newY, null);
    }

    public void draw(Graphics2D g2, int x, int y) {
        draw(g2, x, y, 1);
    }

    public void draw(Graphics2D g2, int x, int y, double scale) {
        if (currentFrame >= totalFrames) return;

        int newSize = (int) (frameSize * scale * scaleFactor);
        g2.drawRect(x, y, newSize, newSize);
        g2.drawImage(frames[currentFrame], x, y, newSize, newSize, null);
    }

}
