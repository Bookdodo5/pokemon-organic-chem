package main;

import assets.SoundManager;
import javax.swing.JFrame;

public class Game extends JFrame {
	public static void main(String[] args) {
		Game game = new Game();
		game.setDefaultCloseOperation(EXIT_ON_CLOSE);
		game.setResizable(false);
		game.setTitle("Learn Organic Chemistry with Pokemon!");

		GameScreen gameScreen = new GameScreen();
		game.add(gameScreen);

		game.pack();
		game.setLocationRelativeTo(null);
		game.setVisible(true);

		SoundManager.getSfxplayer().playSE("GameCursor");

		gameScreen.startGameThread();
		gameScreen.setupKeyBindings();
	}
}