package tile;

public class TransitionPoint {
	private final int fromX, fromY;
	private final int toX, toY;	
	String mapFrom, mapTo;

	public TransitionPoint(int fromX, int fromY, String mapFrom, int toX, int toY, String mapTo) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.mapFrom = mapFrom;
		this.mapTo = mapTo;
	}

	public int getFromX() { return fromX; }

	public int getFromY() { return fromY; }

	public int getToX() { return toX; }

	public int getToY() { return toY; }

	public String getMapFrom() { return mapFrom; }

	public String getMapTo() { return mapTo; }

}
