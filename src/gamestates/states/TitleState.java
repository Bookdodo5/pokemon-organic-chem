package gamestates.states;

import assets.SoundManager;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
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

	public TitleState(StateManager stateManager, KeyBindingHandler keyHandler, GameContentManager gameContentManager) {
		super(stateManager, keyHandler, gameContentManager);
		initializeMenu();
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
					// TODO: Implement menu
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
		// Handle menu selection updates if needed
	}

	@Override
	public void draw(Graphics2D g2) {
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
}