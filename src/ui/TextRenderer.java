package ui;

import java.awt.Graphics2D;

public class TextRenderer {

	private final TextStyle textStyle;

	public TextRenderer(TextStyle textStyle) { this.textStyle = textStyle; }

	public class TextRenderResult {
		public final int endX;
		public final int endY;

		public TextRenderResult(int endX, int endY) {
			this.endX = endX;
			this.endY = endY;
		}
	}

	public TextStyle getTextStyle() { return textStyle; }

	public TextRenderResult renderText(Graphics2D g2, int innerBoxX, int innerBoxY, int maxWidth, String currentText,
			String fullText) {
		String[] fullParagraphs = fullText.split("\n");
		String[] paragraphs = currentText.split("\n");

		StringBuilder currentLine = new StringBuilder();
		int lineCount = 0;
		int lastLineWidth = 0;
		int textX = 0, textY = 0;

		for (int i = 0; i < paragraphs.length; i++) {
			String[] words = paragraphs[i].split(" ");
			String[] pageWords = fullParagraphs[i].split(" ");

			for (int j = 0; j < words.length; j++) {
				String word = pageWords[j];
				String testLine = currentLine.length() > 0 ? currentLine + " " + word : word;
				lastLineWidth = getTextWidth(g2,testLine);

				textX = innerBoxX + textStyle.getTextMarginX();
				textY = innerBoxY + textStyle.getTextMarginY() + lineCount * textStyle.getLineHeight();

				if (lastLineWidth > maxWidth) {
					renderLine(g2, textX, textY, currentLine.toString());
					lineCount++;
					currentLine = new StringBuilder();
					lastLineWidth = getTextWidth(g2,words[j]);
				}
				String appendString = currentLine.length() > 0 ? " " + words[j] : words[j];
				currentLine.append(appendString);
			}

			if (currentLine.length() >= 0) {
				textX = innerBoxX + textStyle.getTextMarginX();
				textY = innerBoxY + textStyle.getTextMarginY() + lineCount * textStyle.getLineHeight();
				lastLineWidth = getTextWidth(g2, currentLine.toString());
				renderLine(g2, textX, textY, currentLine.toString());
				currentLine = new StringBuilder();
				lineCount++;
			}
		}
		return new TextRenderResult(textX + lastLineWidth, textY + textStyle.getLineHeight() / 2);
	}

	public void renderLine(Graphics2D g2, int textX, int textY, String line) {
		int actualTextHeight = getTextHeight(g2);
		int baseline = textY + (textStyle.getLineHeight() + actualTextHeight) / 2;

		int textShadowX = textX + textStyle.getShadowOffset();
		int textShadowY = baseline + textStyle.getShadowOffset();

		g2.setFont(textStyle.getFont());
		g2.setColor(textStyle.getShadowColor());
		g2.drawString(line, textShadowX, textShadowY);

		g2.setColor(textStyle.getOutlineColor());
		for(int dx = -textStyle.getOutlineSize(); dx <= textStyle.getOutlineSize(); dx++) {
			for(int dy = -textStyle.getOutlineSize(); dy <= textStyle.getOutlineSize(); dy++) {
				g2.drawString(line, textX + dx, baseline + dy);
			}
		}

		g2.setColor(textStyle.getTextColor());
		g2.drawString(line, textX, baseline);
	}

	public int getTextHeight(Graphics2D g2) {
		g2.setFont(textStyle.getFont());
		return (g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent())/2;
	}
	
	public int getTextWidth(Graphics2D g2, String string) {
		g2.setFont(textStyle.getFont());
		return g2.getFontMetrics().stringWidth(string);
	}
}
