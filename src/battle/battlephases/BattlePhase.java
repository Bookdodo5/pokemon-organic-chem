package battle.battlephases;

import input.KeyBindingHandler;
import java.awt.Graphics2D;

public abstract class BattlePhase {

	protected final KeyBindingHandler keyHandler;
	protected final PhaseManager phaseManager;
	
	public BattlePhase(PhaseManager phaseManager, KeyBindingHandler keyHandler) {
		this.phaseManager = phaseManager;
		this.keyHandler = keyHandler;
	}

	public abstract void update();

	public abstract void draw(Graphics2D g2);

	public abstract void keyTapped();

	public abstract void onEnter();

	public abstract void onExit();
}