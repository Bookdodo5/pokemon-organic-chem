package assets;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import main.Constants;

public class AnimationManager {

    private static final Map<String, int[]> ANIMATION_MAP = new HashMap<>();

    static {
        //animationName -> (framesPerRow, framesPerCol, frameSize, scaleFactor)
        ANIMATION_MAP.put("electric1", new int[]{5, 3, 192, 1});
        ANIMATION_MAP.put("fire4", new int[]{5, 3, 192, 1});
        ANIMATION_MAP.put("fire5", new int[]{5, 3, 192, 1});
        ANIMATION_MAP.put("water3", new int[]{5, 3, 192, 1});
        ANIMATION_MAP.put("poison4", new int[]{5, 3, 192, 1});
        ANIMATION_MAP.put("24", new int[]{14, 1, 64, 3});
        ANIMATION_MAP.put("26", new int[]{14, 1, 64, 3});
    }

    private int framesPerRow = 5;
    private int framesPerCol = 3;
    private int frameSize = 192;
    private int totalFrames = 15;
    private int scaleFactor = 1;

    private final BufferedImage[] frames;
    private int currentFrame = 0;

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

    public void draw(Graphics2D g2, int x, int y) {
        if (currentFrame >= totalFrames) {
            return;
        }
        g2.drawImage(frames[currentFrame], x, y, null);
    }

    public void draw(Graphics2D g2, int x, int y, double scale) {
        if (currentFrame >= totalFrames) {
            return;
        }
        AffineTransform originalTransform = g2.getTransform();
        g2.scale(Constants.SCALE, Constants.SCALE);

        int newSize = (int) (frameSize * scale * scaleFactor);
        g2.drawImage(frames[currentFrame], x, y, newSize, newSize, null);

        g2.setTransform(originalTransform);
    }

}
