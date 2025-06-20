package cutscene;

import java.awt.Graphics2D;

public class Cutscene {

	private final CutsceneAction[] cutsceneActions;
	private int actionIndex;
	private boolean isFinished = false;
	private final String[] yesFlags;
	private final String[] noFlags;

	public Cutscene(String[] yesFlags, String[] noFlags, CutsceneAction... actions) {
		cutsceneActions = actions;
		actionIndex = -1;
		this.yesFlags = yesFlags;
		this.noFlags = noFlags;
	}

	public void start() {
		actionIndex = 0;
		cutsceneActions[actionIndex].start();
	}

	public void update() {
		if(actionIndex < 0 || actionIndex >= cutsceneActions.length) return;
		cutsceneActions[actionIndex].update();
		if (cutsceneActions[actionIndex].isFinished()) {
			cutsceneActions[actionIndex].end();
			nextAction();
			if(isFinished) return;
			if(cutsceneActions[actionIndex].isFinished()) update();
		}
	}

	public void draw(Graphics2D g2) {
		if(actionIndex < 0 || actionIndex >= cutsceneActions.length) return;
		if (!cutsceneActions[actionIndex].isFinished())
			cutsceneActions[actionIndex].draw(g2);
	}

	private void nextAction() {
		if (actionIndex == cutsceneActions.length - 1) {
			isFinished = true;
			return;
		}
		actionIndex++;
		cutsceneActions[actionIndex].start();
	}

	public boolean isFinished() { return isFinished; }

	public CutsceneAction getCurrentAction() {
		if (actionIndex < 0 || actionIndex >= cutsceneActions.length) return null;
		return cutsceneActions[actionIndex];
	}

	public void reset() {
		actionIndex = -1;
		isFinished = false;
		for (CutsceneAction action : cutsceneActions) {
			action.reset();
		}
	}

	public String[] getYesFlags() { return yesFlags; }

	public String[] getNoFlags() { return noFlags; }
}
