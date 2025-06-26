package input;

import gamestates.StateManager;
import java.awt.event.ActionEvent;
import java.util.Stack;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class KeyBindingHandler {

	private Keys currentKey = Keys.NONE;
	private final Stack<Keys> keyStack = new Stack<>();
	private final StateManager stateManager;

	public KeyBindingHandler(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	public Keys getCurrentKey() {
		return currentKey;
	}

	public boolean pressingKey(Keys key) {
		return keyStack.contains(key);
	}

	public boolean movementKeyPressed() {
		return currentKey == Keys.UP || currentKey == Keys.DOWN || currentKey == Keys.LEFT || currentKey == Keys.RIGHT;
	}

	public void setupKeyBindings(JComponent component) {
		InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = component.getActionMap();

		// Up key bindings
		inputMap.put(KeyStroke.getKeyStroke("W"), "upPressed");
		inputMap.put(KeyStroke.getKeyStroke("UP"), "upPressed");
		inputMap.put(KeyStroke.getKeyStroke("released W"), "upReleased");
		inputMap.put(KeyStroke.getKeyStroke("released UP"), "upReleased");

		// Down key bindings
		inputMap.put(KeyStroke.getKeyStroke("S"), "downPressed");
		inputMap.put(KeyStroke.getKeyStroke("DOWN"), "downPressed");
		inputMap.put(KeyStroke.getKeyStroke("released S"), "downReleased");
		inputMap.put(KeyStroke.getKeyStroke("released DOWN"), "downReleased");

		// Left key bindings
		inputMap.put(KeyStroke.getKeyStroke("A"), "leftPressed");
		inputMap.put(KeyStroke.getKeyStroke("LEFT"), "leftPressed");
		inputMap.put(KeyStroke.getKeyStroke("released A"), "leftReleased");
		inputMap.put(KeyStroke.getKeyStroke("released LEFT"), "leftReleased");

		// Right key bindings
		inputMap.put(KeyStroke.getKeyStroke("D"), "rightPressed");
		inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "rightPressed");
		inputMap.put(KeyStroke.getKeyStroke("released D"), "rightReleased");
		inputMap.put(KeyStroke.getKeyStroke("released RIGHT"), "rightReleased");

		// State changes
		inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "escapePressed");
		inputMap.put(KeyStroke.getKeyStroke("released ESCAPE"), "escapeReleased");
		inputMap.put(KeyStroke.getKeyStroke("P"), "pPressed");
		inputMap.put(KeyStroke.getKeyStroke("released P"), "pReleased");
		inputMap.put(KeyStroke.getKeyStroke("Z"), "zPressed");
		inputMap.put(KeyStroke.getKeyStroke("released Z"), "zReleased");

		inputMap.put(KeyStroke.getKeyStroke("SPACE"), "runPressed");
		inputMap.put(KeyStroke.getKeyStroke("released SPACE"), "runReleased");

		// Set up actions
		actionMap.put("upPressed", new KeyAction(Keys.UP, true));
		actionMap.put("upReleased", new KeyAction(Keys.UP, false));
		actionMap.put("downPressed", new KeyAction(Keys.DOWN, true));
		actionMap.put("downReleased", new KeyAction(Keys.DOWN, false));
		actionMap.put("leftPressed", new KeyAction(Keys.LEFT, true));
		actionMap.put("leftReleased", new KeyAction(Keys.LEFT, false));
		actionMap.put("rightPressed", new KeyAction(Keys.RIGHT, true));
		actionMap.put("rightReleased", new KeyAction(Keys.RIGHT, false));
		actionMap.put("escapePressed", new KeyAction(Keys.ESCAPE, true));
		actionMap.put("escapeReleased", new KeyAction(Keys.ESCAPE, false));
		actionMap.put("pPressed", new KeyAction(Keys.P, true));
		actionMap.put("pReleased", new KeyAction(Keys.P, false));
		actionMap.put("zPressed", new KeyAction(Keys.INTERACT, true));
		actionMap.put("zReleased", new KeyAction(Keys.INTERACT, false));
		actionMap.put("runPressed", new KeyAction(Keys.RUN, true));
		actionMap.put("runReleased", new KeyAction(Keys.RUN, false));
	}

	private class KeyAction extends AbstractAction {
		private final Keys key;
		private final boolean isPressing;

		public KeyAction(Keys key, boolean isPressing) {
			this.key = key;
			this.isPressing = isPressing;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (isPressing) {
				if (key != Keys.RUN) {
					currentKey = key;
				}
				if (!keyStack.contains(key)) stateManager.keyTapped();
				keyStack.remove(key);
				keyStack.push(key);
				stateManager.keyPressed();
			}
			else {
				keyStack.remove(key);
				stateManager.keyReleased(key);
				if (keyStack.empty()) {
					currentKey = Keys.NONE;
				} else if (key == currentKey) {
					currentKey = findNextKey();
				}
			}
		}
		
		private Keys findNextKey() {
			for (int i = keyStack.size() - 1; i >= 0; i--) {
				Keys k = keyStack.get(i);
				if (k != Keys.RUN) return k;
			}
			return Keys.NONE;
		}
	}
}