package battle.conditions;

import java.awt.Color;

public enum pH {
	STRONG_ACID(new Color(180,  60,  60)),   // damp crimson (pH ~1)
	WEAK_ACID  (new Color(180, 120,  60)),   // burnt orange  (pH ~4)
	NEUTRAL    (new Color(100, 180, 100)),   // soft mint-green (pH ~7)
	WEAK_BASE  (new Color( 80, 140, 170)),   // dusty sky blue (pH ~9)
	STRONG_BASE(new Color( 60,  90, 200));   // deep steel blue(pH ~13)

	private final Color color;
	pH(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
