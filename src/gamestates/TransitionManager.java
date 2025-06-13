package gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import static main.Constants.*;
import menu.Settings;

public class TransitionManager {
	private final double TRANSITION_TIME = 1.0;
	private final Color transitionColor = Color.BLACK;
	private double alpha = 0;
	private double alphaStart = 0;
	private double alphaTarget = 0;
	private boolean completed = false;
	private boolean transitioning = false;
	private Runnable onComplete;
	
	// Add timing variables for easing
	private double transitionProgress = 0;
	private final double transitionSpeed = 1.0 / (Settings.getInstance().getFPS() * TRANSITION_TIME);

	public void startTransition(double alphaStart, double alphaTarget, Runnable onComplete) {
		this.alphaTarget = alphaTarget;
		this.alphaStart = alphaStart;
		this.onComplete = onComplete;
		alpha = alphaStart;
		transitioning = true;
		completed = false;
		transitionProgress = 0; // Reset progress
	}

	public static double easeOutCubic(double factor) {
        factor = Math.max(0.0, Math.min(1.0, factor));
        
        return 1 - Math.pow(1 - factor, 3);
    }

	public static double easeInCubic(double factor) {
        factor = Math.max(0.0, Math.min(1.0, factor));
        
        return Math.pow(factor, 3);
    }

	private boolean checkComplete() {
		return transitionProgress >= 1.0;
	}

	public void update() {
		if (!transitioning) return;

		transitionProgress += transitionSpeed;
		transitionProgress = Math.min(1.0, transitionProgress);

		double easedProgress = easeOutCubic(transitionProgress);
		
		alpha = alphaStart + (alphaTarget - alphaStart) * easedProgress;
		alpha = Math.max(0, Math.min(1, alpha));

		completed = checkComplete();
		transitioning = !completed;
		
		if (completed) {
			alpha = alphaTarget;
			onComplete.run();
		}
	}

	public void draw(Graphics2D g2) {
		Color color = new Color(
			transitionColor.getRed(),
			transitionColor.getGreen(),
			transitionColor.getBlue(),
			(int) (alpha * 255));
		g2.setColor(color);
		g2.fillRect(0, 0, (int) (SCREEN_WIDTH / SCALE), (int) (SCREEN_HEIGHT / SCALE));
	}

	public boolean isTransitioning() { return transitioning; }
}
