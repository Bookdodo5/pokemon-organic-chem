package menu;

public class Settings {

	private float sfxVolume = 0.5f;
	private float musicVolume = 0.5f;
	private TextSpeed textSpeed = TextSpeed.FAST;
	private int FPS = 240;

	public enum TextSpeed {
		FAST(2, 1), MEDIUM(3, 1), SLOW(4, 2);

		public final int baseSpeed, holdSpeed;

		private TextSpeed(int baseSpeed, int holdSpeed) {
			this.baseSpeed = baseSpeed;
			this.holdSpeed = holdSpeed;
		}
	}

	public TextSpeed getTextSpeed() { return textSpeed; }

	public void setTextSpeed(TextSpeed textSpeed) { this.textSpeed = textSpeed; }

	public float getSfxVolume() { return sfxVolume; }

	public void setSfxVolume(float sfxVolume) { this.sfxVolume = sfxVolume; }

	public float getMusicVolume() { return musicVolume; }

	public void setMusicVolume(float musicVolume) { this.musicVolume = musicVolume; }

	public int getFPS() { return FPS; }
	
	public void setFPS(int fPS) { FPS = fPS; }
	
	private static Settings instance;

	private Settings() {}
	
	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

}
