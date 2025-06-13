package dialogue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import static main.Constants.*;
import menu.OptionRenderer;
import menu.Settings;
import ui.BoxRenderer;
import ui.BoxStyle;
import ui.TextRenderer;
import ui.TextRenderer.TextRenderResult;
import ui.TextStyle;

public class DialogueRenderer {

	// Animation state
	private String currentText = "";
	private int currentCharIndex = 0;
	private int textAnimationCounter = 0;
	private int textAnimationSpeed = Settings.getInstance().getTextSpeed().baseSpeed;
	private int indicatorAnimationCounter = 0;
	private final int indicatorAnimationSpeed = 30;
	private boolean animationFinished = false;

	private Dialogue dialogue;
	private String page;

	private TextStyle dialogueTextStyle = TextStyle.getDialogueStyle().build();
	private BoxStyle dialogueBoxStyle = BoxStyle.getDialogueStyle().build();

	private final TextStyle optionTextStyle = TextStyle.getOptionStyle().build();
	private final BoxStyle optionBoxStyle = BoxStyle.getOptionStyle().build();

	private static final int DIALOGUE_BOX_MARGIN = 5;
	private static final int DIALOGUE_BOX_HEIGHT = 100;
	private static final int OPTION_BOX_MARGIN = 10;

	private static final int INDICATOR_OFFSET = 5;
	private static final int INDICATOR_STROKE_WIDTH = 2;
	private static final Color INDICATOR_COLOR = new Color(210, 60, 60);
	private static final Color INDICATOR_STROKE_COLOR = new Color(100, 50, 50);

	public void setTextAnimationSpeed(int textAnimationSpeed) { this.textAnimationSpeed = textAnimationSpeed; }

	public void setRenderingDialogue(Dialogue dialogue, BoxStyle boxStyle, TextStyle textStyle) {
		currentCharIndex = 0;
		textAnimationCounter = 0;
		indicatorAnimationCounter = 0;
		animationFinished = false;
		currentText = "";
		this.dialogue = dialogue;
		this.page = dialogue.getCurrentPage();
		this.dialogueBoxStyle = boxStyle;
		this.dialogueTextStyle = textStyle;
	}
	
	public void setRenderingDialogue(Dialogue dialogue) {
	    setRenderingDialogue(dialogue, dialogueBoxStyle, dialogueTextStyle);
	}

	public void update() {
		if (page == null) return;

		if (currentCharIndex == page.length()) {
			animationFinished = true;
			indicatorAnimationCounter = (indicatorAnimationCounter + 1) % indicatorAnimationSpeed;
			return;
		}

		textAnimationCounter++;
		if (textAnimationCounter >= textAnimationSpeed) {
			currentText += page.charAt(currentCharIndex);
			currentCharIndex++;
			textAnimationCounter = 0;
		}
	}

	public BoxDimensions calculateDimensions(BoxStyle dialogueBoxStyle, TextStyle dialogueTextStyle) {
		int boxWidth = (int) (SCREEN_WIDTH / SCALE - 2 * DIALOGUE_BOX_MARGIN);
		int boxHeight = DIALOGUE_BOX_HEIGHT;
		int boxX = DIALOGUE_BOX_MARGIN;
		int boxY = (int) (SCREEN_HEIGHT / SCALE - boxHeight - DIALOGUE_BOX_MARGIN);
		int offset = dialogueBoxStyle.getInnerHighlightStrokeWidth() + dialogueBoxStyle.getBorderThickness();
		int innerBoxX = boxX + offset;
		int innerBoxY = boxY + offset;
		int innerBoxWidth = boxWidth - 2 * offset;
		int maxTextWidth = innerBoxWidth - dialogueTextStyle.getTextMarginX() * 2;
		return new BoxDimensions(boxWidth, boxHeight, boxX, boxY, innerBoxX, innerBoxY, innerBoxWidth, maxTextWidth);
	}

	public void renderDialogue(Graphics2D g2) {
		AffineTransform originalTransform = g2.getTransform();
		g2.scale(SCALE, SCALE);
		BoxDimensions dims = calculateDimensions(dialogueBoxStyle, dialogueTextStyle);

		drawBox(g2, dims, dialogueBoxStyle);
		TextRenderResult textRenderResult = drawText(g2, dims);
		if (animationFinished) drawPostAnimationDetail(g2, dims, textRenderResult);

		g2.setTransform(originalTransform);
	}

	private TextRenderResult drawText(Graphics2D g2, BoxDimensions dims) {
		TextRenderer dialogueTextRenderer = new TextRenderer(dialogueTextStyle);
		return dialogueTextRenderer.renderText(g2, dims.innerBoxX(), dims.innerBoxY(), dims.maxTextWidth(), currentText,
				page);
	}

	public void drawBox(Graphics2D g2, BoxDimensions dims, BoxStyle dialogueBoxStyle) {
		BoxRenderer mainBoxRenderer = new BoxRenderer(dialogueBoxStyle);
		mainBoxRenderer.renderBox(g2, dims.boxX(), dims.boxY(), dims.boxWidth(), dims.boxHeight());
	}

	private void drawPostAnimationDetail(Graphics2D g2, BoxDimensions dims, TextRenderResult textRenderResult) {
		OptionRenderer dialogueOptionRenderer = new OptionRenderer(dialogue.getOptions(), optionBoxStyle,
			optionTextStyle);
		dialogueOptionRenderer.setSelectionIndex(dialogue.getSelectionIndex());
		if (!dialogue.canShowOptions()) drawContinueIndicator(g2, textRenderResult);
		else dialogueOptionRenderer.renderOptionsRight(g2, dims.innerBoxX() + dims.innerBoxWidth(),
				dims.innerBoxY() - OPTION_BOX_MARGIN);
	}

	private void drawContinueIndicator(Graphics2D g2, TextRenderResult result) {
		int x = result.endX + INDICATOR_OFFSET;
		int y = result.endY;

		if (indicatorAnimationCounter <= 5) y += 2;
		int[] xPoints = {
			x,
			x + 10,
			x + 10,
			x + 5,
			x
		};
		int[] yPoints = {
			y - 5,
			y - 5,
			y,
			y + 5,
			y
		};

		g2.setColor(INDICATOR_COLOR);
		g2.fillPolygon(xPoints, yPoints, 5);
		g2.setColor(INDICATOR_STROKE_COLOR);
		g2.setStroke(new BasicStroke(INDICATOR_STROKE_WIDTH));
		g2.drawPolygon(xPoints, yPoints, 5);
	}

	public boolean isAnimationFinished() { return animationFinished; }

	public boolean showingOption() { return isAnimationFinished() && dialogue.canShowOptions(); }
}