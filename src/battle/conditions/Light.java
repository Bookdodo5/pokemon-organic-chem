package battle.conditions;

import java.awt.Color;

public enum Light {
	LIGHT(new Color(220, 220, 250)),  // gentle misty white
	DARK (new Color(50, 50, 60));     // deep twilight gray-blue

	private final Color color;

	Light(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

}
