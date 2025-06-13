package gamestates;

import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import main.GameContentManager;

public abstract class GameState {

	protected final KeyBindingHandler keyHandler;
	protected final StateManager stateManager;
	
	public GameState(StateManager stateManager, KeyBindingHandler keyHandler, GameContentManager gameContentManager) {
		this.stateManager = stateManager;
		this.keyHandler = keyHandler;
	}

	public abstract void update();

	public abstract void draw(Graphics2D g2);

	public abstract void keyTapped();

	public abstract void keyPressed();

	public abstract void keyReleased(Keys key);

	public abstract void onEnter(GameStates prevState);

	public abstract void onExit(GameStates nextState);
}
