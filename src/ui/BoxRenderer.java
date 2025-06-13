package ui;

import java.awt.BasicStroke;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class BoxRenderer {

	private final BoxStyle boxStyle;

	public BoxRenderer(BoxStyle boxStyle) { this.boxStyle = boxStyle; }

	public void renderBox(Graphics2D g2, int x, int y, int width, int height) {
		drawShadow(g2, x, y, width, height, boxStyle);
		drawBorder(g2, x, y, width, height, boxStyle);
		drawInnerFill(g2, x, y, width, height, boxStyle);
		drawHighlight(g2, x, y, width, height, boxStyle);
	}

	public void drawShadow(Graphics2D g2, int x, int y, int width, int height, BoxStyle boxStyle) {
		int shadowX = x + boxStyle.getShadowOffset();
		int shadowY = y + boxStyle.getShadowOffset();
		g2.setColor(boxStyle.getShadowColor());
		g2.fillRoundRect(shadowX, shadowY, width, height, boxStyle.getCornerArc(), boxStyle.getCornerArc());
	}

	public void drawBorder(Graphics2D g2, int x, int y, int width, int height, BoxStyle boxStyle) {
		GradientPaint borderGradient = new GradientPaint(x, y, boxStyle.getTopBorderColor(), x, y + height,
			boxStyle.getBottomBorderColor());
		g2.setPaint(borderGradient);
		g2.fillRoundRect(x, y, width, height, boxStyle.getCornerArc(), boxStyle.getCornerArc());
	}

	public void drawInnerFill(Graphics2D g2, int x, int y, int width, int height, BoxStyle boxStyle) {
		int innerX = x + boxStyle.getBorderThickness();
		int innerY = y + boxStyle.getBorderThickness();
		int innerWidth = width - 2 * boxStyle.getBorderThickness();
		int innerHeight = height - 2 * boxStyle.getBorderThickness();
		int innerCornerArc = Math.max(0, boxStyle.getCornerArc() - boxStyle.getBorderThickness());

		GradientPaint boxGradient = new GradientPaint(innerX, innerY, boxStyle.getTopFillColor(), innerX,
			innerY + innerHeight, boxStyle.getBottomFillColor());
		g2.setPaint(boxGradient);
		g2.fillRoundRect(innerX, innerY, innerWidth, innerHeight, innerCornerArc, innerCornerArc);
	}

	public void drawHighlight(Graphics2D g2, int x, int y, int width, int height, BoxStyle boxStyle) {
		int innerX = x + boxStyle.getBorderThickness() + boxStyle.getInnerHighlightStrokeWidth() / 2;
		int innerY = y + boxStyle.getBorderThickness() + boxStyle.getInnerHighlightStrokeWidth() / 2;
		int innerWidth = width - 2 * boxStyle.getBorderThickness() - boxStyle.getInnerHighlightStrokeWidth();
		int innerHeight = height - 2 * boxStyle.getBorderThickness() - boxStyle.getInnerHighlightStrokeWidth();
		int innerCornerArc = Math.max(0,
				boxStyle.getCornerArc() - boxStyle.getBorderThickness() - boxStyle.getInnerHighlightStrokeWidth());

		g2.setColor(boxStyle.getInnerHighlightColor());
		g2.setStroke(new BasicStroke(boxStyle.getInnerHighlightStrokeWidth()));
		g2.drawRoundRect((int) (innerX), (int) (innerY), innerWidth, innerHeight, innerCornerArc, innerCornerArc);
	}
}
