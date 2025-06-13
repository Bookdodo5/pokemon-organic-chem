package battle.conditions;

import java.awt.Color;

public enum Solvent {
	NONPOLAR       (new Color(230, 210, 250)),  // pale lavender-ish gray (inert)
	POLAR_APROTIC  (new Color(170, 200, 230)),  // soft blue-gray (no hydrogen bonding)
	POLAR_PROTIC   (new Color(180, 160, 200));  // purplish-gray (can H-bond)

	private final Color color;

	Solvent(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
