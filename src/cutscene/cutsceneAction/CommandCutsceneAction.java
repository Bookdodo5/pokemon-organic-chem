package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import java.awt.Graphics2D;

public class CommandCutsceneAction implements CutsceneAction {

	private final Runnable command;
	private boolean isFinished;

	public CommandCutsceneAction(Runnable command) {
		this.command = command;
		this.isFinished = false;
	}

	@Override
	public void start() { command.run(); end(); }

	@Override
	public void update() { }

	@Override
	public void end() { isFinished = true; }

	@Override
	public void draw(Graphics2D g2) { }

	@Override
	public boolean isFinished() { return isFinished; }

	@Override
	public void reset() {
		isFinished = false;
	}

}
