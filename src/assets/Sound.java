package assets;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import menu.Settings;

public class Sound implements Runnable {
	private Clip clip;
	private FloatControl gainControl;
	private static final Map<String, URL> SOUND_URL = new HashMap<>();
	private final int FADE_TIME = 1000;
	private float currDB = 0f;
	private float targetDB = 0f;
	private final float fadePerStep = 0.2f;
	private int totalStep = 0;
	private boolean fading = false;
	
	private String currentTrackName = null;

	private static void loadSound(String name, String path) {
		URL url = Sound.class.getResource(path);
		if (url != null) {
			SOUND_URL.put(name, url);
		}
	}

	private static final AudioFormat SUPPORTED_FORMAT = new AudioFormat(
		44100, // Sample rate
		16, // Sample size in bits
		2, // Channels (stereo)
		true, // Signed
		false // Big endian
	);

	public Sound() {
		// Load sound effects
		loadSound("PlayerBump", "/sound_effect/PlayerBump.wav");
		loadSound("PkmnHealing", "/sound_effect/PkmnHealing.wav");
		loadSound("PkmnGet", "/sound_effect/PkmnGet.wav");
		loadSound("PkmnFaint", "/sound_effect/PkmnFaint.wav");
		loadSound("DoorExit", "/sound_effect/DoorExit.wav");
		loadSound("DoorEnter", "/sound_effect/DoorEnter.wav");
		loadSound("GUIMenuOpen", "/sound_effect/GUIMenuOpen.wav");
		loadSound("GUIMenuClose", "/sound_effect/GUIMenuClose.wav");
		loadSound("GUIConfirm", "/sound_effect/GUIConfirm.wav");
		loadSound("BattleFlee", "/sound_effect/BattleFlee.wav");
		loadSound("BattleDamageWeak", "/sound_effect/BattleDamageWeak.wav");
		loadSound("BattleDamageSuper", "/sound_effect/BattleDamageSuper.wav");
		loadSound("BattleDamageNormal", "/sound_effect/BattleDamageNormal.wav");
		loadSound("GameCursor", "/sound_effect/GameCursor.wav");

		// Load music
		loadSound("Title", "/music/Title.wav");
		loadSound("BattleVictoryLeader", "/music/BattleVictoryLeader.wav");
		loadSound("BattleGymLeader", "/music/BattleGymLeader.wav");
		loadSound("BattleVictoryWild", "/music/BattleVictoryWild.wav");
		loadSound("BattleVictoryTrainer", "/music/BattleVictoryTrainer.wav");
		loadSound("BattleWild", "/music/BattleWild.wav");
		loadSound("BattleTrainer", "/music/BattleTrainer.wav");
		loadSound("Gym", "/music/Gym.wav");
		loadSound("Lab", "/music/Lab.wav");
		loadSound("Route2", "/music/Route2.wav");
		loadSound("Route1", "/music/Route1.wav");
		loadSound("Lerucean", "/music/Lerucean.wav");
		loadSound("Lappet", "/music/Lappet.wav");
		loadSound("NewStart", "/music/NewStart.wav");
		loadSound("Credits", "/music/Credits.wav");
		loadSound("Bicycle", "/music/Bicycle.wav");
		loadSound("Motorcycle", "/music/Motorcycle.wav");
		loadSound("Center", "/music/Center.wav");
		loadSound("Cave", "/music/Cave.wav");
		loadSound("Frontier", "/music/Frontier.wav");
		loadSound("Game", "/music/Game.wav");
		loadSound("Indigo", "/music/Indigo.wav");
		loadSound("Islands", "/music/Islands.wav");
		loadSound("Mart", "/music/Mart.wav");
		loadSound("Safari", "/music/Safari.wav");
		loadSound("Tiall", "/music/Tiall.wav");
		loadSound("Underwater", "/music/Underwater.wav");
	}

	public void setFile(String file) {
		try {
			URL soundUrl = SOUND_URL.get(file);
			if (soundUrl == null) {
				System.err.println("Sound file not found: " + file);
				return;
			}

			try (AudioInputStream originalStream = AudioSystem.getAudioInputStream(soundUrl);
					AudioInputStream convertedStream = AudioSystem.getAudioInputStream(SUPPORTED_FORMAT,
							originalStream)) {

				DataLine.Info info = new DataLine.Info(
					Clip.class,
					SUPPORTED_FORMAT);
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(convertedStream);
				currentTrackName = file;
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

			}
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.err.println("Error loading sound file: " + file);
		}
	}

	public void play(String file) {
		float musicVolume = Settings.getInstance().getMusicVolume();
		if (clip != null) stop(false);
		setFile(file);
		if (clip != null) {
			setVolume(volumeToDB(0.2 * musicVolume));
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			shiftVolume(0.2 * musicVolume, musicVolume);
		}
	}

	public void stop(boolean fade) {
        float musicVolume = Settings.getInstance().getMusicVolume();
        if (clip != null) {
            final Clip clipToStop = this.clip;
            final String trackThatWasPlaying = this.currentTrackName;

            if (fade) {
                shiftVolume(musicVolume, 0.05 * musicVolume);
                new Thread(() -> {
                    try {
                        Thread.sleep(FADE_TIME);
                        if (clipToStop != null && clipToStop.isOpen()) {
                            clipToStop.stop();
                            clipToStop.close();
                        }
                        if (trackThatWasPlaying != null && trackThatWasPlaying.equals(this.currentTrackName)) {
                            this.currentTrackName = null;
                        }
                    } catch (InterruptedException e) {}
                }).start();
            } else {
                clipToStop.stop();
                clipToStop.close();
                this.currentTrackName = null;
            }
        }
    }
	
    public String getCurrentTrackName() {
        return this.currentTrackName;
    }

    public boolean isPlaying(String trackName) {
    	if(trackName.equals("")) return true;
        return trackName.equals(this.currentTrackName) && clip != null && clip.isRunning();
    }

	public void playSE(String file) {
		float sfxVolume = Settings.getInstance().getSfxVolume();
		setFile(file);
		setVolume(volumeToDB(sfxVolume));
		if (clip != null) {
			clip.start();
		}
	}

	public float volumeToDB(double value) {
		value = (value <= 0.0) ? 0.001 : ((value > 1.0) ? 1.0 : value);
		float dB = (float) (Math.log(value) / Math.log(10.0) * 20.0);
		return Math.max(-80.0f, dB);
	}

	public void setVolume(float valueDB) { gainControl.setValue(valueDB); }

	public void shiftVolume(double valueFrom, double valueTo) {
		currDB = volumeToDB(valueFrom);
		targetDB = volumeToDB(valueTo);
		totalStep = (int)(Math.abs(targetDB - currDB) / fadePerStep);

		setVolume(currDB);

		if (!fading) {
			fading = true;
			Thread t = new Thread(
				this);
			t.start();
		}
	}

	@Override
	public void run() {
		fading = true;
		if (currDB > targetDB) {
			while (currDB > targetDB) {
				currDB -= fadePerStep;
				setVolume(currDB);
				try {
                    Thread.sleep(FADE_TIME / totalStep);
                } catch (InterruptedException e) {}
			}
		}
		else if (currDB < targetDB) {
			while (currDB < targetDB) {
				currDB += fadePerStep;
				setVolume(currDB);
				try {
                    Thread.sleep(FADE_TIME / totalStep);
                } catch (InterruptedException e) {}
			}
		}
		fading = false;
		setVolume(targetDB);
	}
}
