package assets;

import java.util.ArrayList;
import java.util.List;

public class SoundManager {
	private static final Sound musicPlayer = new Sound();
	private static final List<Sound> sfxPlayers = new ArrayList<>();
	private static int currentSfxIndex = 0;
	
	static {
		for (int i = 0; i < 5; i++) {
			sfxPlayers.add(new Sound());
		}
	}
	
	public static Sound getMusicplayer() { return musicPlayer; }
	
	public static Sound getSfxplayer() { 
		Sound player = sfxPlayers.get(currentSfxIndex);
		currentSfxIndex = (currentSfxIndex + 1) % sfxPlayers.size();
		return player;
	}
	
	public static Sound getSfxplayer(int index) {
		return sfxPlayers.get(index % sfxPlayers.size());
	}
}
