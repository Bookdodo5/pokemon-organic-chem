package main;

import gamestates.StateManager;
import input.KeyBindingHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import static main.Constants.*;
import menu.Settings;

public class GameScreen extends JPanel implements Runnable {

	Thread gameThread;
	GameContentManager gameContentManager;
	StateManager stateManager;
	KeyBindingHandler keyHandler;
	
	public GameScreen() {
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.BLACK);
		setFocusable(true);
		gameContentManager = new GameContentManager();
		stateManager = gameContentManager.getStateManager();
		keyHandler = gameContentManager.getKeyHandler();
	}

	public void update() { 
		stateManager.update();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		AffineTransform originalTransform = g2.getTransform();
		g2.scale(SCALE, SCALE);
		stateManager.draw(g2);
		g2.setTransform(originalTransform);
	}

	public void setupKeyBindings() { keyHandler.setupKeyBindings(this); }

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double delta = 0;
		long lastTime = System.nanoTime();
		System.out.println("Game thread started");

		while (gameThread != null) {
			long currentTime = System.nanoTime();
			long elapseTime = currentTime - lastTime;

			long FPS = Settings.getInstance().getFPS();
			if (gameContentManager.getDeveloperMode().isHyperSpeed()) {
				FPS = 3000;
				keyHandler.simulateInteractPress();
			}
			double targetTimePerFrame = 1000000000.0 / FPS;

			delta += elapseTime / targetTimePerFrame;
			lastTime = currentTime;

			if (delta > 1.0) {
				update();
				repaint();
				delta--;
			}
		}
	}
}