package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import java.awt.Graphics2D;

public class WaitForInputAction implements CutsceneAction {

	private boolean isFinished;

	public WaitForInputAction() {
		this.isFinished = false;
	}

	@Override
	public void start() {
        isFinished = false;
    }

	@Override
	public void update() {
	}

	@Override
	public void end() {
        isFinished = true;
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
	}
}
