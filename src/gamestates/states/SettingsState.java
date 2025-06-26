package gamestates.states;

import assets.SoundManager;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;
import static main.Constants.SCALE;
import static main.Constants.SCREEN_HEIGHT;
import static main.Constants.SCREEN_WIDTH;
import main.GameContentManager;
import menu.Option;
import menu.OptionRenderer;
import menu.Settings;
import ui.BoxStyle;
import ui.TextStyle;

public class SettingsState extends GameState {

	private static final int BACK_OPTION_INDEX = 0;
	private static final int TEXT_SPEED_INDEX = 1;
	private static final int SFX_VOLUME_INDEX = 2;
	private static final int MUSIC_VOLUME_INDEX = 3;

	private final OverworldState overworldState;
	private final TitleState titleState;
	private final Option[] settingsOptions;
	private GameStates prevState = GameStates.TITLE;

	private TextStyle optionTextStyle = TextStyle.getOptionStyle().build();
	private BoxStyle optionBoxStyle = BoxStyle.getOptionStyle().build();
	private OptionRenderer settingsRenderer;

	private int selectionIndex = 0;
	private boolean waitForInput = false;

	public SettingsState(StateManager stateManager, KeyBindingHandler keyHandler, GameContentManager gameContentManager) {
		super(stateManager, keyHandler, gameContentManager);
		this.overworldState = (OverworldState) StateManager.states.get(GameStates.OVERWORLD);
		this.titleState = (TitleState) StateManager.states.get(GameStates.TITLE);
		this.settingsOptions = initializeOptions();
		this.settingsRenderer = new OptionRenderer(settingsOptions, optionBoxStyle, optionTextStyle);
	}

	private Option[] initializeOptions() {
		return new Option[]{
			new Option("Back") {
				@Override
				public void execute() { stateManager.setState(prevState); }
			},
			new Option(getTextSpeedString()) {
				@Override
				public void execute() {}
			},
			new Option(getSfxVolumeString()) {
				@Override
				public void execute() {}
			},
			new Option(getMusicVolumeString()) {
				@Override
				public void execute() {}
			}
		};
	}

	private String getTextSpeedString() { return "Text Speed : " + Settings.getInstance().getTextSpeed(); }

	private String volumeToString(double volume) { return String.format("Volume : %.0f%%", volume * 100); }

	private String getSfxVolumeString() { return "SFX " + volumeToString(Settings.getInstance().getSfxVolume()); }

	private String getMusicVolumeString() { return "Music " + volumeToString(Settings.getInstance().getMusicVolume()); }

	private boolean adjustSettings(int direction) {
		boolean valueChanged = false;
		switch (selectionIndex) {
			case TEXT_SPEED_INDEX -> valueChanged = handleTextSpeed(direction);
			case SFX_VOLUME_INDEX -> valueChanged = handleSfxVolume(direction);
			case MUSIC_VOLUME_INDEX -> valueChanged = handleMusicVolume(direction);
			default -> {}
		}
		return valueChanged;
	}

	private void updateOptionText(int index, String text) {
		settingsOptions[index].setText(text);
		settingsRenderer.updateSettingsOptionText(index, text);
	}

	private float adjustVolume(float currentVolume, int direction) {
		return Math.max(0, Math.min(1, currentVolume + direction * 0.01f));
	}

	private boolean handleTextSpeed(int direction) {
		List<Settings.TextSpeed> speeds = Arrays.asList(Settings.TextSpeed.values());
		int currentIndex = speeds.indexOf(Settings.getInstance().getTextSpeed());
		int newIndex = (currentIndex + direction + speeds.size()) % speeds.size();

		if (newIndex != currentIndex) {
			Settings.TextSpeed newTextSpeed = speeds.get(newIndex);
			Settings.getInstance().setTextSpeed(newTextSpeed);
			String newText = getTextSpeedString();
			updateOptionText(TEXT_SPEED_INDEX, newText);
			return true;
		}

		return false;
	}

	private boolean handleSfxVolume(int direction) {
		float currentVolume = Settings.getInstance().getSfxVolume();
		float newVolume = adjustVolume(currentVolume, direction);

		if (newVolume != currentVolume) {
			Settings.getInstance().setSfxVolume(newVolume);
			updateOptionText(SFX_VOLUME_INDEX, getSfxVolumeString());
			SoundManager.getSfxplayer().setVolume(SoundManager.getSfxplayer().volumeToDB(newVolume));
			return true;
		}
		return false;
	}

	private boolean handleMusicVolume(int direction) {
		float currentVolume = Settings.getInstance().getMusicVolume();
		float newVolume = adjustVolume(currentVolume, direction);

		if (newVolume != currentVolume) {
			Settings.getInstance().setMusicVolume(newVolume);
			updateOptionText(MUSIC_VOLUME_INDEX, getMusicVolumeString());
			SoundManager.getMusicplayer().setVolume(SoundManager.getMusicplayer().volumeToDB(newVolume));
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		if (titleState != null && prevState == GameStates.TITLE) {
			titleState.update();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		if (overworldState != null && prevState == GameStates.PAUSING) {
			overworldState.draw(g2);
			g2.setColor(new Color(0, 0, 0, 128));
			g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		}

		if (titleState != null && prevState == GameStates.TITLE) {
			titleState.draw(g2);
		}

		int titleOffset = (int) (SCREEN_HEIGHT / (8 * SCALE));
		if (prevState == GameStates.TITLE) settingsRenderer.renderOptionsCenter(g2, titleOffset);
		if (prevState == GameStates.PAUSING) settingsRenderer.renderOptionsCenter(g2, 0);
	}

	@Override
	public void keyTapped() {
		boolean isSuccessfulKeypress = true;

		switch (keyHandler.getCurrentKey()) {
			case ESCAPE -> stateManager.setState(prevState);
			case UP -> {
				selectionIndex = (selectionIndex + settingsOptions.length - 1) % settingsOptions.length;
				settingsRenderer.setSelectionIndex(selectionIndex);
			}
			case DOWN -> {
				selectionIndex = (selectionIndex + 1) % settingsOptions.length;
				settingsRenderer.setSelectionIndex(selectionIndex);
			}
			case INTERACT -> {
				settingsOptions[selectionIndex].execute();
				if (selectionIndex != BACK_OPTION_INDEX || prevState == GameStates.TITLE) isSuccessfulKeypress = false;
			}
			default -> isSuccessfulKeypress = false;
		}

		if (isSuccessfulKeypress) SoundManager.getSfxplayer().playSE("GameCursor");
	}

	@Override
	public void keyPressed() {

		if (!waitForInput) return;

		boolean isSuccessfulKeypress = true;

		switch (keyHandler.getCurrentKey()) {
			case LEFT -> {
				if (!adjustSettings(-1)) isSuccessfulKeypress = false;
			}
			case RIGHT -> {
				if (!adjustSettings(1)) isSuccessfulKeypress = false;
			}
			default -> isSuccessfulKeypress = false;
		}

		if (isSuccessfulKeypress) SoundManager.getSfxplayer().playSE("GameCursor");
	}

	@Override
	public void keyReleased(Keys key) {
		waitForInput = !keyHandler.pressingKey(Keys.ESCAPE);
	}

	@Override
	public void onEnter(GameStates prevState) {
		this.prevState = prevState;
		waitForInput = false;
		if (prevState == GameStates.TITLE) {
			optionTextStyle = TextStyle.getTitleMenuStyle().build();
			optionBoxStyle = BoxStyle.getTitleMenuStyle().build();
			settingsRenderer = new OptionRenderer(settingsOptions, optionBoxStyle, optionTextStyle);
			SoundManager.getSfxplayer().playSE("GUIMenuOpen");
		}
		else {
			optionTextStyle = TextStyle.getOptionStyle().build();
			optionBoxStyle = BoxStyle.getOptionStyle().build();
			settingsRenderer = new OptionRenderer(settingsOptions, optionBoxStyle, optionTextStyle);
		}
	}

	@Override
	public void onExit(GameStates nextState) {
		if (nextState == GameStates.TITLE) {
			SoundManager.getSfxplayer().playSE("GUIMenuClose");
		}
	}
}
