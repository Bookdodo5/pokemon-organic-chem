package cutscene.cutsceneAction;

import assets.SoundManager;
import cutscene.CutsceneAction;
import java.awt.Graphics2D;

public class PlaysoundCutsceneAction implements CutsceneAction {
	private final String soundFile;
	private final boolean isMusic;
	private boolean isFinished;

	public PlaysoundCutsceneAction(String soundFile, boolean isMusic) {
		this.soundFile = soundFile;
		this.isMusic = isMusic;
		this.isFinished = false;
	}

	@Override
	public void start() {
		if (isMusic) {
			SoundManager.getMusicplayer().play(soundFile);
		}
		else {
			SoundManager.getSfxplayer().playSE(soundFile);
		}
		isFinished = true;
	}

	@Override
	public void update() {
	}

	@Override
	public void end() {
	}

	@Override
	public void draw(Graphics2D g2) {
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void reset() {
		isFinished = false;
	}
}
