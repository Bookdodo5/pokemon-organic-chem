package battle.conditions;

import java.awt.Color;

public enum Temperature {
	COLD (new Color(110, 130, 170)),  // Cool steel blue-gray
	ROOM (new Color(140, 140, 140)),  // Neutral soft gray
	HOT  (new Color(180, 120, 100));  // Warm taupe/burnt clay gray

	private final Color color;

	Temperature(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
