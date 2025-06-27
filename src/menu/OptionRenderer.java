package menu;

import java.awt.Graphics2D;
import static main.Constants.SCALE;
import static main.Constants.SCREEN_HEIGHT;
import static main.Constants.SCREEN_WIDTH;
import ui.BoxRenderer;
import ui.BoxStyle;
import ui.TextRenderer;
import ui.TextStyle;

public class OptionRenderer {

	private int selectionIndex = 0;
	private final Option[] options;
	private final BoxStyle boxStyle;
	private final TextStyle textStyle;

	public OptionRenderer(Option[] options, BoxStyle boxStyle, TextStyle textStyle) {
		this.options = options;
		this.boxStyle = boxStyle;
		this.textStyle = textStyle;
	}

	public void setSelectionIndex(int selectionIndex) { this.selectionIndex = selectionIndex; }

	public void updateSettingsOptionText(int selectionIndex, String text) { options[selectionIndex].setText(text); }

	private int getOptionBoxWidth(Graphics2D g2) {
		g2.setFont(textStyle.getFont());
		int maxTextWidth = 0;

		for (Option option : options) { maxTextWidth = Math.max(maxTextWidth, option.getTextWidth(g2)); }

		int optionBoxWidth = maxTextWidth + textStyle.getTextMarginX() * 2 + boxStyle.getBorderThickness() * 2
				+ boxStyle.getInnerHighlightStrokeWidth() * 2;
		return optionBoxWidth;
	}

	public void renderOptionsCenter(Graphics2D g2, int offsetY) {
		int boxWidth = getOptionBoxWidth(g2);
		int boxHeight = options.length * textStyle.getLineHeight() + textStyle.getTextMarginY() * 2
				+ boxStyle.getBorderThickness() * 2
				+ boxStyle.getInnerHighlightStrokeWidth() * 2;
		int boxX = (int) (SCREEN_WIDTH / (2 * SCALE)) - boxWidth / 2;
		int boxY = (int) (SCREEN_HEIGHT / (2 * SCALE)) + boxHeight / 2 + offsetY;
		renderOptions(g2, boxX, boxY, boxWidth);
	}

	public void renderOptionsRight(Graphics2D g2, int boxX, int boxY) {
		int boxWidth = getOptionBoxWidth(g2);
		boxX = boxX - boxWidth;
		renderOptions(g2, boxX, boxY, boxWidth);
	}

	public void renderOptions(Graphics2D g2, int boxX, int boxY, int boxWidth) {

		int offset = boxStyle.getBorderThickness() + boxStyle.getInnerHighlightStrokeWidth();
		int offsetX = offset + textStyle.getTextMarginX();
		int offsetY = offset + textStyle.getTextMarginY();

		int optionHeight = options.length * textStyle.getLineHeight() + offsetY * 2;
		int optionX = boxX;
		int optionY = boxY - optionHeight;
		int textX = optionX + offsetX;
		int textY = optionY + offsetY;

		BoxRenderer optionBoxRenderer = new BoxRenderer(boxStyle);
		optionBoxRenderer.renderBox(g2, optionX, optionY, boxWidth, optionHeight);

		TextRenderer textRenderer = new TextRenderer(textStyle);

		for (int i = 0; i < options.length; i++) {
			int lineY = textY + textStyle.getLineHeight() * i;
			textRenderer.renderLine(g2, textX, lineY, options[i].getText());
		}

		drawSelectionPointer(g2, textX, textY);

	}

	void drawSelectionPointer(Graphics2D g2, int textX, int textY) {
		int pointerX = textX;
		int pointerY = textY + selectionIndex * textStyle.getLineHeight() + textStyle.getLineHeight() / 2;

		int[] xPoints = {
			pointerX - 5,
			pointerX - 12,
			pointerX - 12
		};
		int[] yPoints = {
			pointerY,
			pointerY + 5,
			pointerY - 5
		};
		g2.setColor(textStyle.getTextColor());
		g2.fillPolygon(xPoints, yPoints, 3);
	}

}
