package gamestates.states;

import assets.SoundManager;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import static main.Constants.*;
import main.GameContentManager;
import menu.Option;
import menu.OptionRenderer;
import ui.BoxStyle;
import ui.TextStyle;

public class PausingState extends GameState {

	private final OverworldState overworldState;
	private final OptionRenderer pausingRenderer;
	private final Option[] pausingOptions;

	private final TextStyle optionTextStyle = TextStyle.getOptionStyle().build();
	private final BoxStyle optionBoxStyle = BoxStyle.getOptionStyle().build();

	private int selectionIndex = 0;

	public PausingState(StateManager stateManager, KeyBindingHandler keyHandler, GameContentManager gameContentManager) {
		super(stateManager, keyHandler, gameContentManager);
		this.overworldState = (OverworldState) StateManager.states.get(GameStates.OVERWORLD);
		this.pausingOptions = initializeOptions();
		this.pausingRenderer = new OptionRenderer(pausingOptions, optionBoxStyle, optionTextStyle);
	}

	private Option[] initializeOptions() {
		return new Option[]{
			new Option("Resume") {
				@Override
				public void execute() { stateManager.setState(GameStates.OVERWORLD); }
			},
			new Option("Settings") {
				@Override
				public void execute() { stateManager.setState(GameStates.SETTINGS); }
			},
			new Option("Exit to Title") {
				@Override
				public void execute() {
					stateManager.transitionToState(GameStates.TITLE, true, "", () -> {
					});
					SoundManager.getMusicplayer().stop(true);
				}
			},
			new Option("Exit") {
				@Override
				public void execute() { System.exit(0); }
			}
		};
	}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics2D g2) {
		if (overworldState != null) {
			overworldState.draw(g2);
			g2.setColor(new Color(0, 0, 0, 128));
			g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		}

		AffineTransform originalTransform = g2.getTransform();
		g2.scale(SCALE, SCALE);

		pausingRenderer.renderOptionsCenter(g2, 0);

		g2.setTransform(originalTransform);
	}

	@Override
	public void onEnter(GameStates prevState) {
		if (prevState == GameStates.OVERWORLD) {
			SoundManager.getSfxplayer().playSE("GUIMenuOpen");
		}
	}

	@Override
	public void keyPressed() {}

	@Override
	public void keyReleased(Keys key) {}

	@Override
	public void onExit(GameStates nextState) {
		if (nextState == GameStates.OVERWORLD) {
			SoundManager.getSfxplayer().playSE("GUIMenuClose");
		}
	}

	@Override
	public void keyTapped() {
		boolean isSuccessfulKeypress = true;

		switch (keyHandler.getCurrentKey()) {
			case ESCAPE -> {
				stateManager.setState(GameStates.OVERWORLD);
				isSuccessfulKeypress = false;
			}
			case UP -> {
				selectionIndex = (selectionIndex + pausingOptions.length - 1) % pausingOptions.length;
				pausingRenderer.setSelectionIndex(selectionIndex);
			}
			case DOWN -> {
				selectionIndex = (selectionIndex + 1) % pausingOptions.length;
				pausingRenderer.setSelectionIndex(selectionIndex);
			}
			case INTERACT -> pausingOptions[selectionIndex].execute();
			default -> isSuccessfulKeypress = false;
		}

		if (isSuccessfulKeypress) SoundManager.getSfxplayer().playSE("GameCursor");

	}

}
