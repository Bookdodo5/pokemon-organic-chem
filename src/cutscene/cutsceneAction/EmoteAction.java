package cutscene.cutsceneAction;

import assets.AssetManager;
import cutscene.CutsceneAction;
import cutscene.Emotes;
import entity.Human;
import gamestates.CameraManager;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EmoteAction implements CutsceneAction {
    
    private final Human targetHuman;
    private final CameraManager cameraManager;
    private final int displayDuration;
    private int currentTimer;
    private boolean isFinished;
    private final BufferedImage emoteImage;

    private final int EMOTE_SIZE = 192;

    public EmoteAction(Human targetHuman, int displayDuration, Emotes emote, CameraManager cameraManager) {
        this.targetHuman = targetHuman;
        this.cameraManager = cameraManager;
        this.displayDuration = displayDuration;
        this.isFinished = false;
        this.currentTimer = 0;

        String emotePath = "/animations/emotions.png";
        int emoteIndex = emote.getValue();  
        int emoteColumn = emoteIndex % 5;
        int emoteRow = emoteIndex / 5;

        BufferedImage emoteSpriteSheet = AssetManager.loadImage(emotePath);
        this.emoteImage = emoteSpriteSheet.getSubimage(
            emoteColumn * EMOTE_SIZE, emoteRow * EMOTE_SIZE,
            EMOTE_SIZE, EMOTE_SIZE
        );
    }

    @Override
    public void start() {
        currentTimer = 0;
        isFinished = false;
    }

    @Override
    public void update() {
        currentTimer++;
        if (currentTimer >= displayDuration) {
            isFinished = true;
        }
    }

    @Override
    public void end() {
    }

    @Override
    public void reset() {
        currentTimer = 0;
        isFinished = false;
    }

    @Override
    public void draw(Graphics2D g2) {
        if(targetHuman == null) return;

        int cameraX = cameraManager.getCameraX();
        int cameraY = cameraManager.getCameraY();
        int x = (int) (targetHuman.getX() - cameraX - EMOTE_SIZE / 2) + targetHuman.getSpriteWidth() / 2;
        int y = (int) (targetHuman.getY() - cameraY - EMOTE_SIZE / 2) - targetHuman.getSpriteHeight() / 2;

        g2.drawImage(emoteImage, x, y, null);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

}
