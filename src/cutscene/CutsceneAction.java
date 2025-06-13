package cutscene;

import java.awt.Graphics2D;

public interface CutsceneAction {
	void start();
	void update();
	void end();
	void reset();
	void draw(Graphics2D g2);
	boolean isFinished();
}
