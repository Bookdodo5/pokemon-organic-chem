package cutscene.cutsceneAction;

import assets.SoundManager;
import cutscene.CutsceneAction;
import java.awt.Graphics2D;

public class PlaysoundAction implements CutsceneAction {
	private final String sound;
	private final boolean isMusic;
	private boolean isFinished;

	public PlaysoundAction(String sound, boolean isMusic) {
		this.sound = sound;
		this.isMusic = isMusic;
		this.isFinished = false;
	}

	@Override
	public void start() {
		if (isMusic) {
			if(sound.equals("Stop")) {
				SoundManager.getMusicplayer().stop(false);
			}
			else {
				SoundManager.getMusicplayer().play(sound);
			}
		}
		else {
			SoundManager.getSfxplayer().playSE(sound);
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
