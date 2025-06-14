package main;

import gamestates.GameStates;
import gamestates.StateManager;
import gamestates.states.BattleState;
import gamestates.states.CutsceneState;
import gamestates.states.DialogueState;
import gamestates.states.OverworldState;
import gamestates.states.PausingState;
import gamestates.states.PokedexState;
import gamestates.states.SettingsState;
import gamestates.states.TitleState;
import input.KeyBindingHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import static main.Constants.*;
import menu.Settings;

public class GameScreen extends JPanel implements Runnable {

	final long FPS = Settings.getInstance().getFPS();
	final double targetTimePerFrame = 1000000000.0 / FPS;

	Thread gameThread;
	StateManager stateManager = new StateManager();
	KeyBindingHandler keyHandler = new KeyBindingHandler(stateManager);
	GameContentManager gameContentManager = new GameContentManager(keyHandler, stateManager);

	public GameScreen() {
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setBackground(Color.BLACK);
		setFocusable(true);
		StateManager.states.put(GameStates.TITLE, new TitleState(stateManager, keyHandler, gameContentManager));
		StateManager.states.put(GameStates.OVERWORLD, new OverworldState(stateManager, keyHandler, gameContentManager));
		StateManager.states.put(GameStates.CUTSCENE, new CutsceneState(stateManager, keyHandler, gameContentManager));
		StateManager.states.put(GameStates.PAUSING, new PausingState(stateManager, keyHandler, gameContentManager));
		StateManager.states.put(GameStates.BATTLE, new BattleState(stateManager, keyHandler, gameContentManager));
		StateManager.states.put(GameStates.POKEDEX, new PokedexState(stateManager, keyHandler, gameContentManager));
		StateManager.states.put(GameStates.DIALOGUE, new DialogueState(stateManager, keyHandler, gameContentManager));
		StateManager.states.put(GameStates.SETTINGS, new SettingsState(stateManager, keyHandler, gameContentManager));
		stateManager.setState(GameStates.TITLE);
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
		stateManager.draw(g2);
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