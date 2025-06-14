package gamestates;

import assets.SoundManager;
import input.Keys;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;
import static main.Constants.SCALE;

public class StateManager {
	private GameStates currentState = GameStates.TITLE;
	public static Map<GameStates, GameState> states = new HashMap<>();
	private final TransitionManager transitionManager = new TransitionManager();

	public GameStates getState() { return currentState; }

	public void setState(GameStates newState) {
		GameStates prevState = currentState;

		if (states.get(prevState) != null) {
			states.get(prevState).onExit(newState);
		}

		currentState = newState;
		states.get(newState).onEnter(prevState);
	}

	public void transitionToState(GameStates newState, boolean fadeMusicOut, String musicToFadeOut, Runnable midFunction) {

		if (fadeMusicOut && SoundManager.getMusicplayer().isPlaying(musicToFadeOut)) {
			SoundManager.getMusicplayer().stop(true);
		}
		transitionManager.startTransition(0.0, 1.0, () -> {
			midFunction.run();
			setState(newState);
			transitionManager.startTransition(1.0, 0.0, () -> {
			});
		});
	}

	public void update() {
		transitionManager.update();
		if (states.get(currentState) != null && !transitionManager.isTransitioning()) {
			states.get(currentState).update();
		}
	}

	public void draw(Graphics2D g2) {
		if (states.get(currentState) != null) {
			states.get(currentState).draw(g2);
		}
		
		AffineTransform originalTransform = g2.getTransform();
		g2.scale(SCALE, SCALE);

		transitionManager.draw(g2);
		g2.setTransform(originalTransform);
	}

	public void keyTapped() {
		if (states.get(currentState) != null && !transitionManager.isTransitioning()) {
			states.get(currentState).keyTapped();
		}
	}

	public void keyPressed() {
		if (states.get(currentState) != null && !transitionManager.isTransitioning()) {
			states.get(currentState).keyPressed();
		}
	}

	public void keyReleased(Keys key) {
		if (states.get(currentState) != null && !transitionManager.isTransitioning()) {
			states.get(currentState).keyReleased(key);
		}
	}
}