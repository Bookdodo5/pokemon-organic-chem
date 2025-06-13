package gamestates.states;

import assets.SoundManager;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import main.GameContentManager;

public class PokedexState extends GameState {

	public PokedexState(StateManager stateManager, KeyBindingHandler keyHandler, GameContentManager gameContentManager) {
		super(stateManager, keyHandler, gameContentManager);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnter(GameStates prevState) {
		SoundManager.getSfxplayer().playSE("GUIMenuOpen");
	}

	@Override
	public void keyPressed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(Keys key) {
		if (!keyHandler.pressingKey(Keys.P)) {
			stateManager.setState(GameStates.OVERWORLD);
		}
	}

	@Override
	public void onExit(GameStates nextState) {
		SoundManager.getSfxplayer().playSE("GUIMenuClose");
	}

	@Override
	public void keyTapped() {
		// TODO Auto-generated method stub

	}

}
