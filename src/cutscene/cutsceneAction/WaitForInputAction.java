package cutscene.cutsceneAction;

import cutscene.InputCutsceneAction;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;

public class WaitForInputAction implements InputCutsceneAction {

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

	@Override
	public void keyTapped(KeyBindingHandler keyHandler) {
		if(keyHandler.getCurrentKey() == Keys.INTERACT) {
			end();
		}
	}

	@Override
	public void keyPressed(KeyBindingHandler keyHandler) {
	}

	@Override
	public void keyReleased(Keys key) {
	}
}
