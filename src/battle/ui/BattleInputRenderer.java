package battle.ui;

import battle.Battle;
import battle.BattleConstants;
import battle.BattlePlayer;
import battle.battlephases.phases.play.ActionPages;
import battle.cards.Card;
import battle.conditions.Condition;
import battle.reactions.Reaction;
import dialogue.BoxDimensions;
import dialogue.DialogueRenderer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import static main.Constants.*;
import ui.BoxRenderer;
import ui.BoxStyle;
import ui.TextRenderer;
import ui.TextStyle;

public class BattleInputRenderer {

    private final Battle battle;
    private final BattlePlayer player;
    
    private final TextStyle blackTextStyle;
    private final TextStyle whiteTextStyle;
    private final TextStyle smallWhiteTextStyle;
    private final TextStyle disabledBlackTextStyle;
    private final TextStyle disabledWhiteTextStyle;
    private final TextStyle smallDisabledWhiteTextStyle;
    private final BoxStyle actionBoxStyle;
    private final BoxStyle optionBoxStyle;
    
    private static final int DIALOGUE_BOX_HEIGHT = BattleConstants.DIALOGUE_BOX_HEIGHT;
    
    public BattleInputRenderer(Battle battle, BattlePlayer player) {
        this.battle = battle;
        this.player = player;

        this.blackTextStyle = TextStyle.getDialogueStyle()
                .fontSize(16)
                .textMarginX(15)
                .build();
        this.whiteTextStyle = TextStyle.getDialogueStyle()
                .fontSize(16)
                .textMarginX(15)
                .textColor(new Color(240, 245, 250))
                .build();
        this.smallWhiteTextStyle = TextStyle.getDialogueStyle()
                .fontSize(10)
                .textMarginX(15)
                .textMarginY(0)
                .lineHeight(10)
                .textColor(new Color(240, 245, 250))
                .build();
        this.disabledWhiteTextStyle = TextStyle.getDialogueStyle()
                .fontSize(16)
                .textColor(whiteTextStyle.getTextColor().darker().darker())
                .shadowColor(whiteTextStyle.getShadowColor().darker().darker())
                .textMarginX(15)
                .textMarginY(0)
                .build();
        this.disabledBlackTextStyle = TextStyle.getDialogueStyle()
                .fontSize(16)
                .textColor(blackTextStyle.getTextColor().brighter().brighter())
                .shadowColor(blackTextStyle.getShadowColor().brighter().brighter())
                .textMarginX(15)
                .build();
        this.smallDisabledWhiteTextStyle = TextStyle.getDialogueStyle()
                .fontSize(10)
                .textColor(whiteTextStyle.getTextColor().darker().darker())
                .shadowColor(whiteTextStyle.getShadowColor().darker().darker())
                .textMarginX(15)
                .lineHeight(10)
                .build();
        this.actionBoxStyle = BoxStyle.getDialogueStyle()
                    .cornerArc(5).shadowOffset(0).build();
        this.optionBoxStyle = BoxStyle.getOptionStyle()
            .borderThickness(5).shadowOffset(0)
            .topBorderColor(Color.GRAY.brighter()).bottomBorderColor(Color.GRAY).build();
    }
    
    public void renderActionSelection(Graphics2D g2, ActionPages currentPage, int selectionIndex, int focusIndex) {
        
        AffineTransform originalTransform = g2.getTransform();
        g2.scale(SCALE, SCALE);
        
        DialogueRenderer dialogueRenderer = new DialogueRenderer();
        BoxDimensions dims = dialogueRenderer.calculateDimensions(actionBoxStyle, disabledWhiteTextStyle);
        
        switch (currentPage) {
            case MAIN -> drawMainPage(g2, dims, selectionIndex);
            case CARDS -> drawCardsPage(g2, dims, selectionIndex);
            case REACTIONS -> drawReactionsPage(g2, dims, selectionIndex, focusIndex);
        }
        
        g2.setTransform(originalTransform);
    }

    private void drawBox(Graphics2D g2, int x, int y, int width, int height, BoxStyle boxStyle) {
        BoxRenderer boxRenderer = new BoxRenderer(boxStyle);
        boxRenderer.renderBox(g2, x, y, width, height);
    }
    
    private void drawMainPage(Graphics2D g2, BoxDimensions dims, int selectionIndex) {

        int dividerX = dims.boxX() + dims.boxWidth() * 3 / 5;
        int width = dims.boxX() + dims.boxWidth() - dividerX;
        int addedHeight = DIALOGUE_BOX_HEIGHT + 6;
        int addedY = dims.boxY() - 3;
        drawBox(g2, dividerX, addedY, width, addedHeight, optionBoxStyle);

        String lines[] = {
            "Synthesize " + battle.getTargetMolecule().getName().toUpperCase(),
            "from " + player.getMolecule().getName().toUpperCase() + "!",
        };

        TextRenderer textRenderer = new TextRenderer(whiteTextStyle);
        int textX = dims.innerBoxX() + blackTextStyle.getTextMarginX();
        int textY = dims.innerBoxY() + blackTextStyle.getTextMarginY();
        for (String line : lines) {
            textRenderer.renderLine(g2, textX, textY, line);
            textY += whiteTextStyle.getLineHeight();
        }
        
        int optionX = dividerX + actionBoxStyle.getBorderThickness() + actionBoxStyle.getInnerHighlightStrokeWidth() + blackTextStyle.getTextMarginX();
        int optionY = dims.innerBoxY() + blackTextStyle.getTextMarginY();

        textRenderer = new TextRenderer(blackTextStyle);
        renderLineWithPointer(g2, optionX, optionY, "End Phase", textRenderer, selectionIndex, 0);
        renderLineWithPointer(g2, optionX, optionY + blackTextStyle.getLineHeight(), "Run", textRenderer, selectionIndex, 1);
    }
    
    private void drawCardsPage(Graphics2D g2, BoxDimensions dims, int selectionIndex) {
        TextRenderer textRenderer = new TextRenderer(whiteTextStyle);
        TextRenderer disabledRenderer = new TextRenderer(disabledWhiteTextStyle);
        TextRenderer smallRenderer = new TextRenderer(smallWhiteTextStyle);
        TextRenderer smallDisabledRenderer = new TextRenderer(smallDisabledWhiteTextStyle);
        
        List<Card> hand = player.getDeck().getHand();
        int availableWidth = dims.innerBoxWidth() - 2 * whiteTextStyle.getTextMarginX();
        int startY = dims.innerBoxY() + whiteTextStyle.getTextMarginY();
        int cardsPerRow = BattleConstants.CARDS_PER_ROW;
        int cardSpacing = availableWidth / cardsPerRow;
        
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            boolean canPlay = card.canPlay(player, battle.getBoard(), battle);
            String cardName = card.getName();
            int cardWidth = textRenderer.getTextWidth(g2, cardName);
            int cardX = dims.innerBoxX() + (i % cardsPerRow) * cardSpacing + (cardSpacing - cardWidth) / 2;
            int cardY = startY + (i / cardsPerRow) * blackTextStyle.getLineHeight();

            TextRenderer renderer = canPlay ? textRenderer : disabledRenderer;
            renderLineWithPointer(g2, cardX, cardY, cardName, renderer, selectionIndex, i);
            
            int costX = cardX + cardWidth + 2;
            int costY = cardY + blackTextStyle.getLineHeight()/2;
            String costString = "[" + card.getCurrentLP(player, battle.getBoard()) + "]";

            renderer = canPlay ? smallRenderer : smallDisabledRenderer;
            renderer.renderLine(g2, costX, costY, costString);
        }
    }
    
    private void drawReactionsPage(Graphics2D g2, BoxDimensions dims, int selectionIndex, int focusIndex) {
        
        int width = dims.boxWidth() * 2 / 5;
        int addedHeight = DIALOGUE_BOX_HEIGHT + 6;
        int addedY = dims.boxY() - 3;
        drawBox(g2, dims.boxX(), addedY, width, addedHeight, optionBoxStyle);

        if(selectionIndex < 0 || selectionIndex >= player.getMolecule().getReactionCount()) {
            return;
        }
        
        TextRenderer whiteRenderer = new TextRenderer(whiteTextStyle);
        TextRenderer blackRenderer = new TextRenderer(blackTextStyle);
        TextRenderer disabledRenderer = new TextRenderer(disabledBlackTextStyle);
        
        List<Reaction> reactions = player.getMolecule().getReactions();
        int startY = dims.innerBoxY() + whiteTextStyle.getTextMarginY();
        
        for (int i = focusIndex; i < focusIndex + BattleConstants.REACTION_DISPLAY_LIMIT; i++) {
            if(i >= reactions.size() || i < 0) break;
            Reaction reaction = reactions.get(i);
            boolean canPlay = reaction.canPlay(player, battle.getBoard(), battle);
            String reactionName = reaction.getName();

            int reactionX = dims.innerBoxX() + whiteTextStyle.getTextMarginX();
            int reactionY = startY + (i-focusIndex) * blackTextStyle.getLineHeight();
            TextRenderer renderer = canPlay ? blackRenderer : disabledRenderer;
            
            renderLineWithPointer(g2, reactionX, reactionY, reactionName, renderer, selectionIndex, i);
        }

        
        Reaction selectedReaction = reactions.get(selectionIndex);
        int detailY = dims.innerBoxY();
        int detailX = dims.innerBoxX() + width;
        
        int detailMaxWidth = dims.boxWidth() - width - whiteTextStyle.getTextMarginX() * 2 - actionBoxStyle.getBorderThickness();
        
        Condition condition = selectedReaction.getRequiredCondition();
        String detailString = selectedReaction.getDescription() + "\n";
        if(condition.getpH() != null) detailString += " " + condition.getpH();
        if(condition.getTemp() != null) detailString += " " + condition.getTemp();
        if(condition.getSolvent() != null) detailString += " " + condition.getSolvent();
        if(condition.getLight() != null) detailString += " " + condition.getLight();

        whiteRenderer.renderText(g2, detailX, detailY, detailMaxWidth, detailString, detailString);
    }

    private void renderLineWithPointer(Graphics2D g2, int x, int y, String text, TextRenderer renderer, int selectionIndex, int textIndex) {
        
        renderer.renderLine(g2, x, y, text);
        if(selectionIndex != textIndex) return;
        
        y = y + renderer.getTextHeight(g2);
        TextStyle textStyle = renderer.getTextStyle();
        int[] xPoints = { x - 5, x - 12, x - 12 };
		int[] yPoints = { y, y + 5, y - 5 };
        g2.setColor(textStyle.getTextColor());
		g2.fillPolygon(xPoints, yPoints, 3);
    }
}
