package ui;

import assets.AssetManager;
import java.awt.Color;
import java.awt.Font;

public class TextStyle {

	public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

	private final Font font;
	private final Color textColor;
	private final Color shadowColor;
	private final Color outlineColor;
	private final int lineHeight;
	private final int textMarginX;
	private final int textMarginY;
	private final int shadowOffset;
	private final int outlineSize;

	private TextStyle(Builder builder) {
		if (builder.font == null) {
			throw new IllegalStateException("Font must be specified");
		}
		this.font = builder.font;
		this.textColor = builder.textColor;
		this.shadowColor = builder.shadowColor;
		this.outlineColor = builder.outlineColor;
		this.lineHeight = builder.lineHeight;
		this.textMarginX = builder.textMarginX;
		this.textMarginY = builder.textMarginY;
		this.shadowOffset = builder.shadowOffset;
		this.outlineSize = builder.outlineSize;
	}

	public Font getFont() { return font; }

	public Color getTextColor() { return textColor; }

	public Color getShadowColor() { return shadowColor; }

	public int getLineHeight() { return lineHeight; }

	public int getTextMarginX() { return textMarginX; }

	public int getTextMarginY() { return textMarginY; }

	public int getShadowOffset() { return shadowOffset; }

	public Color getOutlineColor() { return outlineColor; }

	public int getOutlineSize() { return outlineSize; }

	public static Builder getDialogueStyle() {
		return new Builder()
				.fontSize(20);
	}

	public static Builder getOptionStyle() {
		return new Builder()
				.fontSize(16)
				.lineHeight(20)
				.textMarginY(8);
	}

	public static Builder getBoldStyle() {
		return new Builder()
				.fontSize(22, "powerclearbold")
				.lineHeight(22)
				.textColor(new Color(255, 255, 255))
				.shadowColor(TRANSPARENT)
				.outlineColor(new Color(20, 20, 20))
				.outlineSize(2)
				.textMarginY(0)
				.textMarginX(0);
	}

	public static Builder getTitleStyle() {
		return new Builder()
				.fontSize(48)
				.textColor(new Color(90, 250, 150, 255))
				.shadowColor(new Color(30, 140, 100, 255))
				.lineHeight(40)
				.textMarginX(0)
				.textMarginY(0);
	}

	public static Builder getTitleMenuStyle() {
		return new Builder()
				.fontSize(20)
				.textColor(new Color(90, 250, 150, 255))
				.shadowColor(TRANSPARENT)
				.lineHeight(24)
				.textMarginX(20)
				.textMarginY(10);
	}

	public static class Builder {
		private Font font;
		private Color textColor = new Color(80, 80, 80, 225);
		private Color shadowColor = new Color(150, 150, 150, 120);
		private Color outlineColor = TRANSPARENT;
		private int lineHeight = 24;
		private int textMarginX = 20;
		private int textMarginY = 10;
		private int shadowOffset = 1;
		private int outlineSize = 0;

		public Builder fontSize(float size) {
			this.font = AssetManager.loadFont(size);
			return this;
		}
		
		public Builder fontSize(float size, String fontName) {
			this.font = AssetManager.loadFont(size, fontName);
			return this;
		}

		public Builder textColor(Color textColor) {
			this.textColor = textColor;
			return this;
		}

		public Builder shadowColor(Color shadowColor) {
			this.shadowColor = shadowColor;
			return this;
		}

		public Builder lineHeight(int lineHeight) {
			this.lineHeight = lineHeight;
			return this;
		}

		public Builder textMarginX(int textMarginX) {
			this.textMarginX = textMarginX;
			return this;
		}

		public Builder textMarginY(int textMarginY) {
			this.textMarginY = textMarginY;
			return this;
		}

		public Builder shadowOffset(int shadowOffset) {
			this.shadowOffset = shadowOffset;
			return this;
		}

		public Builder outlineSize(int outlineSize) {
			this.outlineSize = outlineSize;
			return this;
		}

		public Builder outlineColor(Color outlineColor) {
			this.outlineColor = outlineColor;
			return this;
		}

		public TextStyle build() { return new TextStyle(this); }
	}
}