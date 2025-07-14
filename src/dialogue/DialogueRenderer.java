package dialogue;

import gamestates.FlagManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
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
	private double textAnimationCounter = 0.0;
	private double textAnimationSpeed = Settings.getInstance().getTextSpeed().baseSpeed;
	private int indicatorAnimationCounter = 0;
	private final int indicatorAnimationSpeed = 30;
	private boolean animationFinished = false;

	private Dialogue dialogue;
	private String page;

	private TextStyle dialogueTextStyle = TextStyle.getDialogueStyle().build();
	private BoxStyle dialogueBoxStyle = getDialogueBoxStyleBuilder(Color.WHITE, false).build();

	private final TextStyle optionTextStyle = TextStyle.getOptionStyle().build();
	private final BoxStyle optionBoxStyle = BoxStyle.getOptionStyle()
		.shadowColor(new Color(0, 0, 0, 0))
		.shadowOffset(0)
		.cornerArc(15)
		.topBorderColor(new Color(80, 80, 80, 120))
		.bottomBorderColor(new Color(80, 80, 80, 150))
		.topFillColor(new Color(250, 250, 250))
		.bottomFillColor(new Color(240, 240, 240))
		.build();

	private final int DIALOGUE_BOX_MARGIN;
	private static final int DIALOGUE_BOX_HEIGHT = 100;
	private static final int OPTION_BOX_MARGIN = 10;

	private static final int INDICATOR_OFFSET = 5;
	private static final int INDICATOR_STROKE_WIDTH = 2;
	private static final Color INDICATOR_COLOR = new Color(210, 60, 60);
	private static final Color INDICATOR_STROKE_COLOR = new Color(100, 50, 50);

	private Color speakerColor;

	private static final Map<String, Color> SPEAKER_COLORS = new HashMap<>();

	public DialogueRenderer(boolean isBattle) {
		this.DIALOGUE_BOX_MARGIN = isBattle ? 5 : 20;
	}

	static {
		SPEAKER_COLORS.put("", new Color(255, 255, 255));
		SPEAKER_COLORS.put("THINKING", new Color(255, 255, 255));
		SPEAKER_COLORS.put("YUUKI", new Color(190, 50, 230));
		SPEAKER_COLORS.put("DECANE", new Color(70, 130, 170));
		SPEAKER_COLORS.put("CELLULOSE", new Color(160, 255, 80));
		SPEAKER_COLORS.put("CHLOROPHYLL", new Color(60, 250, 210));
		SPEAKER_COLORS.put("DIRECTOR", new Color(255, 100, 100));
		SPEAKER_COLORS.put("MOLECULAR GASTRONOMIST", new Color(210, 150, 70));
		SPEAKER_COLORS.put("PSYCHIC", new Color(100, 70, 150));
		SPEAKER_COLORS.put("PORBITAL COP", new Color(60, 120, 200));
		SPEAKER_COLORS.put("DISGUISED COP", new Color(80, 150, 180));
		SPEAKER_COLORS.put("KUSARI", new Color(240, 50, 170));
		SPEAKER_COLORS.put("PORBITAL MAYOR", new Color(230, 200, 0));
		SPEAKER_COLORS.put("RED", new Color(255, 0, 0));
		SPEAKER_COLORS.put("BLUE", new Color(0, 0, 255));
		SPEAKER_COLORS.put("GREEN", new Color(0, 255, 0));
		SPEAKER_COLORS.put("YELLOW", new Color(255, 255, 0));
		SPEAKER_COLORS.put("CYAN", new Color(0, 255, 255));
		SPEAKER_COLORS.put("MAGENTA", new Color(255, 0, 255));
		SPEAKER_COLORS.put("MANIAC", new Color(20, 100, 220));
		SPEAKER_COLORS.put("OLD MAN", new Color(180, 120, 80)); // Warm brown for elderly
		SPEAKER_COLORS.put("OLD WOMAN", new Color(200, 140, 100)); // Lighter warm brown
		SPEAKER_COLORS.put("LAZY COP", new Color(120, 120, 120)); // Gray for lazy attitude
		SPEAKER_COLORS.put("SERVICE SELLER", new Color(100, 180, 220)); // Light blue for service
		SPEAKER_COLORS.put("DIRT SELLER", new Color(139, 69, 19)); // Saddle brown for dirt
		SPEAKER_COLORS.put("AIRCON REPAIR MAN", new Color(70, 130, 180)); // Steel blue for technical work
		SPEAKER_COLORS.put("COMPUTER REPAIR MAN", new Color(25, 25, 112)); // Midnight blue for tech
		SPEAKER_COLORS.put("CHEF", new Color(255, 140, 0)); // Dark orange for cooking
		SPEAKER_COLORS.put("INTERN", new Color(255, 215, 0)); // Gold for ambitious interns
		SPEAKER_COLORS.put("OLD INTERN", new Color(160, 82, 45)); // Saddle brown for experienced interns
		SPEAKER_COLORS.put("MAID", new Color(255, 182, 193)); // Light pink for maids
		SPEAKER_COLORS.put("MAID FAKE", new Color(255, 20, 147)); // Deep pink for suspicious maid
		SPEAKER_COLORS.put("PRIME MINISTER", new Color(138, 43, 226)); // Blue violet for authority
		SPEAKER_COLORS.put("RECEPTIONIST", new Color(244, 191, 100)); // Deep sky blue for medical staff
		SPEAKER_COLORS.put("MART WORKER", new Color(0, 191, 255)); // Deep sky blue for medical staff
		SPEAKER_COLORS.put("RESEARCHER", new Color(70, 130, 180)); // Steel blue for research
		SPEAKER_COLORS.put("OLD CITIZENS", new Color(176, 196, 222)); // Light steel blue for elderly
		SPEAKER_COLORS.put("AROMA THERAPIST", new Color(221, 160, 221)); // Plum for aromatherapy
		SPEAKER_COLORS.put("BLUE", new Color(30, 144, 255)); // Dodger blue for blue apartment residents
		SPEAKER_COLORS.put("YELLOW", new Color(255, 215, 0)); // Gold for yellow apartment residents
		SPEAKER_COLORS.put("GAMBLER", new Color(178, 34, 34)); // Fire brick for gambling addiction
		SPEAKER_COLORS.put("MAY", new Color(85, 107, 47)); // Dark olive green for house residents
		SPEAKER_COLORS.put("BLACKBELT", new Color(85, 107, 47)); // Dark olive green for house residents
	}

	private Color tintWith(Color color, double weight, Color tintColor) {
		double r = color.getRed() * weight + tintColor.getRed() * (1.0 - weight);
		double g = color.getGreen() * weight + tintColor.getGreen() * (1.0 - weight);
		double b = color.getBlue() * weight + tintColor.getBlue() * (1.0 - weight);
		return new Color((int) r, (int) g, (int) b);
	}

	private BoxStyle.Builder getDialogueBoxStyleBuilder(Color color, boolean black) {
		return BoxStyle.getDialogueStyle()
			.cornerArc(0)
			.topBorderColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 70).brighter())
			.bottomBorderColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 90).brighter())
			.topFillColor(tintWith(color, black ? 0.2 : 0.05, black ? new Color(0, 0, 0) : new Color(255, 255, 255)))
			.bottomFillColor(tintWith(color, black ? 0.3 : 0.1, black ? new Color(0, 0, 0) : new Color(255, 255, 255)))
			.shadowOffset(0)
			.shadowColor(new Color(0, 0, 0, 0));
	}

	public void setTextAnimationSpeed(double textAnimationSpeed) { this.textAnimationSpeed = textAnimationSpeed; }

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
		if(dialogue.getSpeaker() == null) return;
		this.speakerColor = SPEAKER_COLORS.getOrDefault(
			dialogue.getSpeaker(), new Color(255, 255, 255)
		);
		this.dialogueBoxStyle = getDialogueBoxStyleBuilder(speakerColor, false).build();
	}

	public void update() {
		if (page == null) return;

		if (currentCharIndex == page.length()) {
			animationFinished = true;
			indicatorAnimationCounter = (indicatorAnimationCounter + 1) % indicatorAnimationSpeed;
			return;
		}

		textAnimationCounter++;
		while (textAnimationCounter >= textAnimationSpeed && currentCharIndex < page.length()) {
			currentText += page.charAt(currentCharIndex);
			currentCharIndex++;
			textAnimationCounter -= textAnimationSpeed;
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
		BoxDimensions dims = calculateDimensions(dialogueBoxStyle, dialogueTextStyle);

		drawBox(g2, dims, dialogueBoxStyle);
		drawSpeaker(g2, dims);
		TextRenderResult textRenderResult = drawText(g2, dims);
		if (animationFinished) drawPostAnimationDetail(g2, dims, textRenderResult);
	}

	private void drawSpeaker(Graphics2D g2, BoxDimensions dims) {
		if (dialogue == null || dialogue.getSpeaker() == null || dialogue.getSpeaker().equals("") || dialogue.getSpeaker().equals("THINKING")) return;
		
		TextStyle speakerTextStyle = TextStyle.getOptionStyle()
				.fontSize(16 ,"powerclearbold")
				.textColor(Color.WHITE)
				.textMarginX(10)
				.textMarginY(3)
				.build();
		TextRenderer speakerRenderer = new TextRenderer(speakerTextStyle);
		boolean knowSpeaker = FlagManager.getInstance().hasFlag(dialogue.getSpeaker().toUpperCase()+"_KNOW");
		String speakerText = knowSpeaker ? dialogue.getSpeaker().toUpperCase() : "???";

		int textWidth = speakerRenderer.getTextWidth(g2, speakerText);
		int textHeight = speakerRenderer.getTextHeight(g2) * 2;
		int boxBorders = dialogueBoxStyle.getBorderThickness() + dialogueBoxStyle.getInnerHighlightStrokeWidth();
		int boxWidth = Math.max(120, textWidth + 2 * speakerTextStyle.getTextMarginX() + 2 * boxBorders);
		int boxHeight = textHeight + 2 * speakerTextStyle.getTextMarginY() + 2 * boxBorders;
		int boxX = dims.boxX() - boxBorders;
		int boxY = dims.boxY() - boxHeight + boxBorders;

		BoxRenderer speakerBoxRenderer = new BoxRenderer(
			getDialogueBoxStyleBuilder(speakerColor, true)
			.innerHighlightStrokeWidth(0)
			.borderThickness(0)
			.cornerArc(5)
			.shadowOffset(1)
			.shadowColor(new Color(0, 0, 0, 50))
			.build()
		);
		speakerBoxRenderer.renderBox(g2, boxX, boxY, boxWidth, boxHeight);

		int textX = boxX + boxWidth/2 - textWidth/2;
		int textY = boxY + boxBorders + speakerTextStyle.getTextMarginY();
		speakerRenderer.renderLine(g2, textX, textY, speakerText);
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