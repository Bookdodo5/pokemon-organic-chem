package cutscene.cutsceneAction;

import assets.AssetManager;
import assets.SoundManager;
import cutscene.InputCutsceneAction;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import main.Constants;
import ui.BoxRenderer;
import ui.BoxStyle;


public class ImageBoxAction implements InputCutsceneAction {

	private final BoxStyle boxStyle;
	private final BoxRenderer boxRenderer;
	private int imageWidth, imageHeight;
	private boolean isFinished;

	private final int IMAGE_MARGIN = 10;
	private final int BOX_MARGIN = 50;

	BufferedImage image;

	public ImageBoxAction(String imagePath) {
		this.boxStyle = BoxStyle.getDialogueStyle().build();
		this.boxRenderer = new BoxRenderer(boxStyle);
		this.isFinished = false;
		this.image = AssetManager.loadImage(imagePath);
	}

	@Override
	public void start() {
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		SoundManager.getSfxplayer().playSE("GameCursor");
	}

	@Override
	public void update() {
	 }

	@Override
	public void end() {
		isFinished = true;
		SoundManager.getSfxplayer().playSE("GameCursor");
	 }

	@Override
	public void draw(Graphics2D g2) {
		int boxWidth = (int) (Constants.SCREEN_WIDTH / Constants.SCALE - 2 * BOX_MARGIN);
		int boxHeight = (int) (Constants.SCREEN_HEIGHT / Constants.SCALE - 2 * BOX_MARGIN);
		boxRenderer.renderBox(g2, BOX_MARGIN, BOX_MARGIN, boxWidth, boxHeight);

		int availableWidth = boxWidth - (IMAGE_MARGIN * 2) - (boxStyle.getBorderThickness() * 2);
		int availableHeight = boxHeight - (IMAGE_MARGIN * 2) - (boxStyle.getBorderThickness() * 2);
		double imageAspectRatio = (double) imageWidth / imageHeight;
		double availableAspectRatio = (double) availableWidth / availableHeight;

		int scaledWidth, scaledHeight;
		if (imageAspectRatio > availableAspectRatio) {
			scaledWidth = availableWidth;
			scaledHeight = (int) (scaledWidth / imageAspectRatio);
		} else {
			scaledHeight = availableHeight;
			scaledWidth = (int) (scaledHeight * imageAspectRatio);
		}

		int imageX = BOX_MARGIN + (boxWidth - scaledWidth) / 2;
		int imageY = BOX_MARGIN + (boxHeight - scaledHeight) / 2;

		g2.drawImage(image, imageX, imageY, scaledWidth, scaledHeight, null);
	 }

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void reset() {
		isFinished = false;
	}

	@Override
	public void keyTapped(KeyBindingHandler keyHandler) {
		if(keyHandler.getCurrentKey() == Keys.INTERACT) {
			end();
		}
	}

	@Override
	public void keyPressed(KeyBindingHandler keyHandler) {
	}
	
	@Override
	public void keyReleased(Keys key) {
	}
	
}
