package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import java.awt.Graphics2D;

public class WaitCutsceneAction implements CutsceneAction {

	private boolean isFinished, isRunning;
	private final int endTimer;
	private int currentTimer;

	public WaitCutsceneAction(int endTimer) {
		this.isFinished = false;
		this.currentTimer = 0;
		this.endTimer = endTimer;
	}

	@Override
	public void start() { isRunning = true; }

	@Override
	public void update() {
		if (!isRunning) return;

		currentTimer++;
		if (currentTimer >= endTimer) {
			isRunning = false;
			isFinished = true;
		}
	}

	@Override
	public void end() {
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void draw(Graphics2D g2) { }

	@Override
	public void reset() {
		isFinished = false;
		currentTimer = 0;
	}
}
