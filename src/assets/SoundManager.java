package assets;

public class SoundManager {
	private static final Sound musicPlayer = new Sound();
	private static final Sound sfxPlayer = new Sound();
	
	public static Sound getMusicplayer() { return musicPlayer; }
	public static Sound getSfxplayer() { return sfxPlayer; }
	
}
