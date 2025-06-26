package gamestates.states;

import assets.AssetManager;
import assets.SoundManager;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;	
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
import static main.Constants.*;
import main.GameContentManager;
import menu.Option;
import menu.OptionRenderer;
import ui.BoxStyle;	
import ui.TextRenderer;
import ui.TextStyle;

public class TitleState extends GameState {

	private Option[] menuOptions;
	private OptionRenderer optionRenderer;
	private int selectionIndex = 0;

	private final TextStyle titleTextStyle = TextStyle.getTitleStyle().build();
	private final TextStyle menuTextStyle = TextStyle.getTitleMenuStyle().build();
	private final BoxStyle menuBoxStyle = BoxStyle.getTitleMenuStyle().build();

	private static final int SCALED_WIDTH = (int)(SCREEN_WIDTH / SCALE);
	private static final int SCALED_HEIGHT = (int)(SCREEN_HEIGHT / SCALE);

	private final FloatingMolecule[] floatingMolecules = new FloatingMolecule[30];
	private final String[] possibleSmiles = {
		"C",
		"C1CCCCC1",
		"CC=CC",
		"CC#CC",
		"C1=CCCCC1",
		"O=C1CCCCC1",
		"C1CCC(O)CC1",
		"CCl",
		"O=CCCCCC=O",
		"CCC#C",
		"CC=C(C)C",
		"c1ccccc1CCCCCCCCc1ccccc1",
		"CC(C)(O)CC",
		"C1CCC=CC1",
		"CCC=C",
		"O=C(O)CCCCC(=O)O"
	};

	public TitleState(StateManager stateManager, KeyBindingHandler keyHandler, GameContentManager gameContentManager) {
		super(stateManager, keyHandler, gameContentManager);
		initializeMenu();
		initializeFloatingMolecules();
	}

	private void initializeFloatingMolecules() {
		for (int i = 0; i < floatingMolecules.length; i++) {
			floatingMolecules[i] = new FloatingMolecule();
		}
	}

	private void initializeMenu() {
		menuOptions = new Option[]{
			new Option("Start Game") {
				@Override
				public void execute() {
					stateManager.transitionToState(GameStates.OVERWORLD, true, "Title", () -> {
					});
				}
			},
			new Option("Settings") {
				@Override
				public void execute() { stateManager.setState(GameStates.SETTINGS); }
			},
			new Option("Credits") {
				@Override
				public void execute() {
					stateManager.setState(GameStates.CREDIT);
					SoundManager.getSfxplayer().playSE("GUIMenuOpen");
				}
			},
			new Option("Exit") {
				@Override
				public void execute() { System.exit(0); }
			}
		};
		optionRenderer = new OptionRenderer(menuOptions, menuBoxStyle, menuTextStyle);
	}

	@Override
	public void update() {
		for (FloatingMolecule molecule : floatingMolecules) molecule.update();
	}

	@Override
	public void draw(Graphics2D g2) {
		for (FloatingMolecule molecule : floatingMolecules) molecule.draw(g2);
		TextRenderer titleRenderer = new TextRenderer(titleTextStyle);

		String title = "Pokemon Organic Chem";
		int titleWidth = g2.getFontMetrics(titleTextStyle.getFont()).stringWidth(title);
		int titleX = (int) (SCREEN_WIDTH / (2 * SCALE)) - titleWidth / 2;
		int titleY = (int) (SCREEN_HEIGHT / (4 * SCALE));

		titleRenderer.renderLine(g2, titleX, titleY, title);

		optionRenderer.renderOptionsCenter(g2, titleY / 2);
	}

	@Override
	public void onEnter(GameStates prevState) {
		if (!SoundManager.getMusicplayer().isPlaying("Title")) SoundManager.getMusicplayer().play("Title");
	}

	@Override
	public void keyPressed() {

	}

	@Override
	public void keyReleased(Keys key) {
		// Not needed for title state
	}

	@Override
	public void onExit(GameStates nextState) {
		// Do nothing
	}

	@Override
	public void keyTapped() {

		boolean isSuccessfulKeypress = true;

		switch (keyHandler.getCurrentKey()) {
			case UP -> {
				selectionIndex = (selectionIndex - 1 + menuOptions.length) % menuOptions.length;
				optionRenderer.setSelectionIndex(selectionIndex);
			}
			case DOWN -> {
				selectionIndex = (selectionIndex + 1) % menuOptions.length;
				optionRenderer.setSelectionIndex(selectionIndex);
			}
			case INTERACT -> {
				menuOptions[selectionIndex].execute();
			}
			default -> {
				isSuccessfulKeypress = false;
			}
		}

		if (isSuccessfulKeypress) SoundManager.getSfxplayer().playSE("GameCursor");
	}

	private class FloatingMolecule {
		private BufferedImage moleculeImage;
		private double x, y;
		private double velocityX, velocityY;
		private double angle;
		private double angularVelocity;
		private final static Random random = new Random();

		public FloatingMolecule() {
			reset();
		}

		public final void reset() {
			BufferedImage originalImage = AssetManager.loadImage(
				"/molecules/" + possibleSmiles[random.nextInt(possibleSmiles.length)] + ".png"
			);
			
			moleculeImage = convertToGreen(originalImage);
			
			switch (random.nextInt(4)) {
				case 0 -> { // Left edge
					x = -150;
					y = random.nextDouble() * SCALED_HEIGHT;
				}
				case 1 -> { // Right edge
					x = SCALED_WIDTH + 150;
					y = random.nextDouble() * SCALED_HEIGHT;
				}
				case 2 -> { // Top edge
					x = random.nextDouble() * SCALED_WIDTH;
					y = -150;
				}
				case 3 -> { // Bottom edge
					x = random.nextDouble() * SCALED_WIDTH;
					y = SCALED_HEIGHT + 150;
				}
			}
			
			velocityX = (random.nextDouble() * 0.8 - 0.4) * 2;
			velocityY = (random.nextDouble() * 0.8 - 0.4) * 2;
			if(Math.abs(velocityX) < 0.1) velocityX = 0.1;
			if(Math.abs(velocityY) < 0.1) velocityY = 0.1;
			angle = random.nextDouble() * 2 * Math.PI;
			angularVelocity = (random.nextDouble() * 0.006 - 0.003) * 2;
		}

		private BufferedImage convertToGreen(BufferedImage original) {
			if (original == null) return null;
			int width = original.getWidth();
			int height = original.getHeight();
			BufferedImage greenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			
			int red = random.nextInt(50) + 10;
			int green = random.nextInt(70) + 60;
			int blue = random.nextInt(50) + 10;

			for (int pixelY = 0; pixelY < height; pixelY++) {
				for (int pixelX = 0; pixelX < width; pixelX++) {
					int pixel = original.getRGB(pixelX, pixelY);
					int alpha = (pixel >> 24) & 0xFF;
					if (alpha > 0) {
						int newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
						greenImage.setRGB(pixelX, pixelY, newPixel);
					} else {
						greenImage.setRGB(pixelX, pixelY, 0x00000000);
					}
				}
			}
			
			return greenImage;
		}

		public void update() {
			x += velocityX;
			y += velocityY;
			angle += angularVelocity;

			if (x < -300 || x > SCALED_WIDTH + 300
				|| y < -300 || y > SCALED_HEIGHT + 300) {
				reset();
			}
		}

		public void draw(Graphics2D g2) {
			AffineTransform originalTransform = g2.getTransform();
		
			int moleculeWidth = moleculeImage.getWidth() / 4;
			int moleculeHeight = moleculeImage.getHeight() / 4;
			int centerX = (int)x + moleculeWidth / 2;
			int centerY = (int)y + moleculeHeight / 2;
			
			g2.translate(centerX, centerY);
			g2.rotate(angle);
			g2.translate(-centerX, -centerY);
			g2.drawImage(moleculeImage, (int)x, (int)y, moleculeWidth, moleculeHeight, null);
			
			g2.setTransform(originalTransform);
		}
	}
}