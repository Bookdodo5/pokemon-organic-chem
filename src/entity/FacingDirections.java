package entity;

public enum FacingDirections {
	UP, DOWN, LEFT, RIGHT;

	public int getX() {
		return switch (this) {
			case UP -> 0;
			case DOWN -> 0;
			case LEFT -> -1;
			case RIGHT -> 1;
		};
	}
	
	public int getY() {
		return switch (this) {
			case UP -> -1;
			case DOWN -> 1;
			case LEFT -> 0;
			case RIGHT -> 0;
		};
	}
}