package ui;

import java.awt.Color;

public class BoxStyle {

	private static final Color TRANSPARENT = new Color(0, 0, 0, 0);

	private final Color topBorderColor;
	private final Color bottomBorderColor;
	private final Color topFillColor;
	private final Color bottomFillColor;
	private final Color shadowColor;
	private final Color innerHighlightColor;
	private final int cornerArc;
	private final int borderThickness;
	private final int shadowOffset;
	private final int innerHighlightStrokeWidth;

	private BoxStyle(Builder builder) {
		this.cornerArc = builder.cornerArc;
		this.topBorderColor = builder.topBorderColor;
		this.bottomBorderColor = builder.bottomBorderColor;
		this.topFillColor = builder.topFillColor;
		this.bottomFillColor = builder.bottomFillColor;
		this.borderThickness = builder.borderThickness;
		this.shadowOffset = builder.shadowOffset;
		this.shadowColor = builder.shadowColor;
		this.innerHighlightStrokeWidth = builder.innerHighlightStrokeWidth;
		this.innerHighlightColor = builder.innerHighlightColor;
	}

	public Color getTopFillColor() { return topFillColor; }

	public Color getBottomFillColor() { return bottomFillColor; }

	public Color getTopBorderColor() { return topBorderColor; }

	public Color getBottomBorderColor() { return bottomBorderColor; }

	public int getCornerArc() { return cornerArc; }

	public int getBorderThickness() { return borderThickness; }

	public int getShadowOffset() { return shadowOffset; }

	public Color getShadowColor() { return shadowColor; }

	public int getInnerHighlightStrokeWidth() { return innerHighlightStrokeWidth; }

	public Color getInnerHighlightColor() { return innerHighlightColor; }

	public static Builder getDialogueStyle() { return new Builder(); }

	public static Builder getOptionStyle() {
		return new Builder()
				.cornerArc(10) // OPTION_CORNER_ARC
				.topBorderColor(new Color(70, 130, 170, 255)) // OPTION_BORDER_COLOR_TOP
				.bottomBorderColor(new Color(50, 100, 120, 255)); // OPTION_BORDER_COLOR_BOTTOM
	}

	public static Builder getTitleMenuStyle() {
		return new Builder()
				.topBorderColor(new Color(90, 250, 150)) // TITLE_BORDER_COLOR_TOP
				.bottomBorderColor(new Color(60, 220, 120)) // TITLE_BORDER_COLOR_BOTTOM
				.topFillColor(Color.BLACK)
				.bottomFillColor(Color.BLACK)
				.shadowColor(TRANSPARENT)
				.innerHighlightColor(TRANSPARENT);
	}
	
	public static Builder getBattleStyle() {
	    return new Builder()
		    .topBorderColor(new Color(100, 170, 130))
		    .bottomBorderColor(new Color(80, 120, 100))
		    .topFillColor(new Color(70, 90, 80))
		    .bottomFillColor(new Color(80, 100, 90))
		    .innerHighlightColor(new Color(60, 80, 70));
	}

	public static class Builder {
		private int cornerArc = 20;
		private Color topBorderColor = new Color(90, 210, 170, 255);
		private Color bottomBorderColor = new Color(50, 170, 130, 255);
		private Color topFillColor = new Color(245, 255, 250, 240);
		private Color bottomFillColor = new Color(230, 235, 245, 240);
		private int borderThickness = 4; // BORDER_THICKNESS
		private int shadowOffset = 2; // SHADOW_OFFSET
		private Color shadowColor = new Color(80, 80, 80); // SHADOW_COLOR
		private int innerHighlightStrokeWidth = 1; // INNER_HIGHLIGHT_STROKE_WIDTH
		private Color innerHighlightColor = new Color(255, 255, 255, 250); // INNER_HIGHLIGHT_COLOR

		public Builder swapBorder() {
			Color temp = this.topBorderColor;
			this.topBorderColor = this.bottomBorderColor;
			this.bottomBorderColor = temp;
			return this;
		}
		
		public Builder cornerArc(int cornerArc) {
			this.cornerArc = cornerArc;
			return this;
		}

		public Builder topBorderColor(Color topBorderColor) {
			this.topBorderColor = topBorderColor;
			return this;
		}

		public Builder bottomBorderColor(Color bottomBorderColor) {
			this.bottomBorderColor = bottomBorderColor;
			return this;
		}

		public Builder topFillColor(Color topFillColor) {
			this.topFillColor = topFillColor;
			return this;
		}

		public Builder bottomFillColor(Color bottomFillColor) {
			this.bottomFillColor = bottomFillColor;
			return this;
		}

		public Builder borderThickness(int borderThickness) {
			this.borderThickness = borderThickness;
			return this;
		}

		public Builder shadowOffset(int shadowOffset) {
			this.shadowOffset = shadowOffset;
			return this;
		}

		public Builder shadowColor(Color shadowColor) {
			this.shadowColor = shadowColor;
			return this;
		}

		public Builder innerHighlightStrokeWidth(int innerHighlightStrokeWidth) {
			this.innerHighlightStrokeWidth = innerHighlightStrokeWidth;
			return this;
		}

		public Builder innerHighlightColor(Color innerHighlightColor) {
			this.innerHighlightColor = innerHighlightColor;
			return this;
		}

		public BoxStyle build() { return new BoxStyle(this); }
	}
}