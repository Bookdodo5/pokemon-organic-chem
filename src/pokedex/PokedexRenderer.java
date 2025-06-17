package pokedex;

import battle.cards.Card;
import battle.cards.CardFactory;
import battle.molecules.Molecule;
import battle.reactions.Reaction;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import static main.Constants.SCALE;
import static main.Constants.SCREEN_HEIGHT;
import static main.Constants.SCREEN_WIDTH;
import ui.TextRenderer;
import ui.TextStyle;

public class PokedexRenderer {

    private static final Color[] FIRE_PALETTE = {
        new Color(240, 70, 0), // Deep red-orange
        new Color(255, 100, 5), // Bright red-orange  
        new Color(255, 149, 15) // Warm orange
    };

    private static final Color[] OCEAN_PALETTE = {
        new Color(20, 100, 180), // Deep ocean blue
        new Color(40, 130, 210), // Bright blue
        new Color(70, 160, 240) // Light sky blue
    };

    private static final Color[] FOREST_PALETTE = {
        new Color(34, 139, 34), // Forest green
        new Color(50, 165, 50), // Medium green
        new Color(85, 190, 85) // Light green
    };

    private static final Color LIGHT = new Color(245, 245, 245);
    private static final Color MID = new Color(150, 150, 150);
    private static final Color DARK = new Color(30, 30, 30);

    private Color[] currentPalette;

    private final TextStyle textStyle = TextStyle.getOptionStyle()
        .textMarginX(0).textMarginY(0).build();
    private final TextRenderer textRenderer = new TextRenderer(textStyle);
    private final TextStyle pageSelectionStyle = TextStyle.getBoldStyle().build();
    private final TextRenderer pageSelectionRenderer = new TextRenderer(pageSelectionStyle);
    private final TextStyle pageSelectionDisabledStyle = TextStyle.getBoldStyle()
            .textColor(new Color(200, 200, 200))
            .outlineColor(new Color(140, 140, 140))
            .outlineSize(1)
            .build();
    private final TextRenderer pageSelectionDisabledRenderer = new TextRenderer(pageSelectionDisabledStyle);

    private final int scaledWidth = SCREEN_WIDTH / (int) SCALE;
    private final int scaledHeight = SCREEN_HEIGHT / (int) SCALE;

    private final int topBarHeight = 40;
    private final int marginX = 20;
    private final int marginY = 15;
    private final int cornerArc = 10;
    private final int scrollBoxWidth = 225;
    private final int scrollBoxWidthSmall = 180;
    private final int nameHeight = 30;

    private final PlayerDeckManager playerDeckManager;
    private final List<Molecule> moleculeRecord;
    private final List<Reaction> reactionRecord;
    private final SelectionState selectionState;
    private Molecule selectedMolecule;
    private Reaction selectedReaction;

    public PokedexRenderer(PlayerDeckManager playerDeckManager, List<Molecule> moleculeRecord, List<Reaction> reactionRecord, SelectionState selectionState) {
        this.playerDeckManager = playerDeckManager;
        this.moleculeRecord = moleculeRecord;
        this.reactionRecord = reactionRecord;
        this.selectionState = selectionState;
    }

    public void draw(Graphics2D g2, Molecule selectedMolecule, Reaction selectedReaction) {
        this.selectedMolecule = selectedMolecule;
        this.selectedReaction = selectedReaction;
        updateCurrentPalette();

        AffineTransform originalTransform = g2.getTransform();
        g2.scale(SCALE, SCALE);

        drawBackground(g2);
        drawSelectionBoxes(g2);

        switch (selectionState.getCurrentTab()) {
            case DECK ->
                drawDeck(g2);
            case REACTION ->
                drawReaction(g2);
            case MOLECULE ->
                drawMolecule(g2);
        }

        g2.setTransform(originalTransform);
    }

    private void renderLineWithPointer(Graphics2D g2, int x, int y, String text, int selectionIndex, int textIndex) {
        textRenderer.renderLine(g2, x, y, text);
        if(selectionIndex != textIndex) return;
        
        y = y + textRenderer.getTextHeight(g2);
        int[] xPoints = { x - 3, x - 10, x - 10 };
		int[] yPoints = { y, y + 5, y - 5 };
        g2.setColor(textStyle.getTextColor());
		g2.fillPolygon(xPoints, yPoints, 3);
    }

    private void drawBackground(Graphics2D g2) {
        g2.setColor(LIGHT);
        g2.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g2.setColor(MID);
        g2.setStroke(new BasicStroke(1.5f));
        int gridSize = 32;

        for (int x = 0; x <= scaledWidth; x += gridSize) {
            g2.drawLine(x, 0, x, scaledHeight);
        }
        for (int y = 0; y <= scaledHeight; y += gridSize) {
            g2.drawLine(0, y, scaledWidth, y);
        }
    }

    private void drawSelectionBoxes(Graphics2D g2) {
        g2.setColor(currentPalette[0]);
        g2.fillRect(0, 0, scaledWidth, topBarHeight);

        g2.setColor(currentPalette[1]);
        g2.fillRect(10, 0, scaledWidth - 20, topBarHeight);

        g2.setColor(currentPalette[2]);
        g2.fillRect(20, 0, scaledWidth - 40, topBarHeight - 5);

        g2.setColor(DARK);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(0, topBarHeight, scaledWidth, topBarHeight);

        drawPageSelection(g2, 0, PokedexTab.DECK);
        drawPageSelection(g2, 1, PokedexTab.REACTION);
        drawPageSelection(g2, 2, PokedexTab.MOLECULE);
    }

    private void drawPageSelection(Graphics2D g2, int boxNumber, PokedexTab tab) {
        int textWidth = pageSelectionRenderer.getTextWidth(g2, tab.toString());
        int textHeight = pageSelectionRenderer.getTextHeight(g2) * 2;

        int availableWidth = scaledWidth - 40;
        int boxX = availableWidth / 3 * boxNumber + 20;
        int centeredX = boxX + (availableWidth / 3 - textWidth) / 2;
        int centeredY = 20 - textHeight / 2;

        boolean isTabSelection = selectionState.getArea() == SelectionArea.TAB_SELECTION;
        int tabIndex = selectionState.getTabIndex();
        
        TextRenderer renderer = selectionState.getCurrentTab() == tab ? pageSelectionRenderer : pageSelectionDisabledRenderer;
        renderer.renderLine(g2, centeredX, centeredY, tab.toString());

        if (isTabSelection && boxNumber == tabIndex) {
            drawFancySelectionOutline(g2, centeredX, centeredY, textWidth, textHeight);
        }
    }

    private void drawFancySelectionOutline(Graphics2D g2, int x, int y, int width, int height) {
        g2.setColor(currentPalette[0]);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x - 8, y - 5, width + 16, height + 7, 2, 2);
        
        g2.setColor(LIGHT);
        g2.setStroke(new BasicStroke(2));
        
        g2.drawLine(x - 4, y - 1, x - 4, y + 2);
        g2.drawLine(x - 4, y - 1, x - 1, y - 1);
        
        g2.drawLine(x - 4, y + height - 2, x - 4, y + height - 6);
        g2.drawLine(x - 4, y + height - 2, x - 1, y + height - 2);
        
        g2.drawLine(x + width + 4, y - 1, x + width + 4, y + 2);
        g2.drawLine(x + width + 4, y - 1, x + width + 1, y - 1);
        
        g2.drawLine(x + width + 4, y + height - 2, x + width + 4, y + height - 6);
        g2.drawLine(x + width + 4, y + height - 2, x + width + 1, y + height - 2);
    }

    private void drawBox(Graphics2D g2, int x, int y, int width, int height,
            int borderSize, int secondXOffset, int secondYOffset, int thirdXOffset, int thirdYOffset) {

        g2.setColor(currentPalette[0]);
        g2.fillRoundRect(x, y, width, height, cornerArc, cornerArc);
        g2.setColor(currentPalette[2]);
        g2.fillRect(x + secondXOffset, y + secondYOffset, width - secondXOffset * 2, height - secondYOffset * 2);
        g2.setColor(LIGHT);
        g2.fillRect(x + thirdXOffset, y + thirdYOffset, width - thirdXOffset * 2, height - thirdYOffset * 2);
        g2.setColor(DARK);
        g2.setStroke(new BasicStroke(borderSize));
        g2.drawRoundRect(x, y, width, height, cornerArc, cornerArc);
    }

    private void drawScroll(Graphics2D g2, int scrollOffsetBottom) {
        int scrollBoxX = marginX;
        int scrollBoxY = topBarHeight + marginY;
        int scrollBoxHeight = scaledHeight - topBarHeight - marginY * 2 - scrollOffsetBottom;
        int realWidth = selectionState.getCurrentTab() == PokedexTab.DECK ? scrollBoxWidthSmall : scrollBoxWidth;
        drawBox(g2, scrollBoxX, scrollBoxY, realWidth, scrollBoxHeight,
                3, 10, 0, 15, 4);
        
        int scrollIndex = selectionState.getScrollIndex();
        int focusIndex = selectionState.getFocusIndex();
        int maxDisplay = selectionState.getCurrentTab().getScrollDisplay();

        for(int i = focusIndex; i < focusIndex + maxDisplay; i++) {
            if(outOfBounds(i)) break;
            String displayText = switch (selectionState.getCurrentTab()) {
                case DECK -> {
                    if(i == focusIndex && playerDeckManager.getAvailableCards().isEmpty()) {
                        yield "No cards available";
                    }
                    String card = playerDeckManager.getAvailableCards().get(i);
                    int count = playerDeckManager.getCardCount(card);
                    yield card.toUpperCase() + " [x" + count + "]";
                }
                case REACTION -> {
                    if(i == focusIndex && reactionRecord.isEmpty()) {
                        yield "No reactions available";
                    }
                    Reaction reaction = reactionRecord.get(i);
                    yield reaction.getName().toUpperCase();
                }
                case MOLECULE -> {
                    if(i == focusIndex && moleculeRecord.isEmpty()) {
                        yield "No molecules available";
                    }
                    Molecule molecule = moleculeRecord.get(i);
                    yield molecule.getName().toUpperCase();
                }
            };

            if(selectionState.getArea() != SelectionArea.SCROLL) scrollIndex = -1;
            renderLineWithPointer(g2, scrollBoxX + 25, scrollBoxY + 20 + (i - focusIndex) * 20,
                displayText, scrollIndex, i);
        }
    }

    private boolean outOfBounds(int index) {
        return switch (selectionState.getCurrentTab()) {
            case DECK -> index >= Math.max(playerDeckManager.getAvailableCardTypes(), 1);
            case REACTION -> index >= Math.max(reactionRecord.size(), 1);
            case MOLECULE -> index >= Math.max(moleculeRecord.size(), 1);
        };
    }

    private void drawName(Graphics2D g2, int nameOffsetTop) {
        int realWidth = selectionState.getCurrentTab() == PokedexTab.DECK ? scrollBoxWidthSmall : scrollBoxWidth;
        int nameX = marginX + realWidth + marginX;
        int nameY = topBarHeight + marginY + nameOffsetTop;
        int nameWidth = scaledWidth - nameX - marginX;

        drawBox(g2, nameX, nameY, nameWidth, nameHeight,
                2, 10, 0, 15, 3);

        String name = switch (selectionState.getCurrentTab()) {
            case DECK -> {
                Card selectedCard = getSelectedCard();
                if(selectedCard == null) yield "";
                yield selectedCard.getName().toUpperCase() + " (" + selectedCard.getOriginalLP() + " LP)";
            }
            case REACTION -> {
                if(selectedReaction == null) yield "";
                yield selectedReaction.getName().toUpperCase();
            }
            case MOLECULE -> {
                if(selectedMolecule == null) yield "";
                yield selectedMolecule.getName().toUpperCase();
            }
            default -> "";
        };

        int nameStringWidth = textRenderer.getTextWidth(g2, name);
        int nameStringHeight = textRenderer.getTextHeight(g2) * 2;
        int nameStringX = nameX + (nameWidth - nameStringWidth) / 2;
        int nameStringY = nameY + (nameHeight - nameStringHeight) / 2 + 1;

        textRenderer.renderLine(g2, nameStringX, nameStringY, name);
    }

    private void drawDeck(Graphics2D g2) {
        int deckHeight = 120;
        int deckWidth = scaledWidth - 2 * marginX;
        int deckX = marginX;
        int deckY = scaledHeight - deckHeight - marginY;

        drawBox(g2, deckX, deckY, deckWidth, deckHeight,
                3, 15, 0, 20, 4);
        drawScroll(g2, marginY + deckHeight);
        
        // Draw card details if a card is selected
        drawCardDetails(g2);
        
        List<String> customDeck = playerDeckManager.getCustomDeck();
        String deckSizeText = customDeck.size() + " / 16 CARDS";
        int deckSizeTextWidth = textRenderer.getTextWidth(g2, deckSizeText);
        int deckSizeTextX = deckX + (deckWidth - deckSizeTextWidth) / 2;
        int deckSizeTextY = deckY + 10;
        textRenderer.renderLine(g2, deckSizeTextX, deckSizeTextY, deckSizeText);

        int cardSizeX = (deckWidth - 40) / 4;
        int cardSizeY = (deckHeight - 40) / 4;
        int cardIndex = selectionState.getCardIndex();
        if(selectionState.getArea() != SelectionArea.PLAYER_DECK) cardIndex = -1;

        for(int i = 0; i < 16; i++) {
            String card = "-";
            if(i < customDeck.size()) card = customDeck.get(i);
            int cardX = deckX + 20 + (i % 4) * cardSizeX;
            int cardY = deckY + 30 + (i / 4) * cardSizeY;
            int cardWidth = textRenderer.getTextWidth(g2, card);
            int cardHeight = textRenderer.getTextHeight(g2);
            int renderX = cardX + (cardSizeX - cardWidth) / 2;
            int renderY = cardY + (cardSizeY - cardHeight) / 2;
            renderLineWithPointer(g2, renderX, renderY, card, cardIndex, i);
        }
    }
    
    private void drawCardDetails(Graphics2D g2) {
        Card selectedCard = getSelectedCard();
        if (selectedCard == null) return;

        drawName(g2, 0);
        
        int detailBoxX = marginX + scrollBoxWidthSmall + marginX;
        int detailBoxY = topBarHeight + 2 * marginY + nameHeight;
        int detailBoxWidth = scaledWidth - detailBoxX - marginX;
        int detailBoxHeight = scaledHeight - detailBoxY - marginY - 120 - marginY;
        
        drawBox(g2, detailBoxX, detailBoxY, detailBoxWidth, detailBoxHeight,
                2, 10, 0, 15, 4);
        
        String details = "";
        details += selectedCard.getEffects() + "\n";
        details += "\"" + selectedCard.getFlavorText() + "\"";
        
        textRenderer.renderText(g2, detailBoxX + 25, detailBoxY + 15, detailBoxWidth - 50, details, details);
    }
    
    private Card getSelectedCard() {
        boolean scrollNotEmpty = !playerDeckManager.getAvailableCards().isEmpty();
        boolean deckNotEmpty = !playerDeckManager.getCustomDeck().isEmpty();
        boolean inScroll = selectionState.getArea() == SelectionArea.SCROLL && scrollNotEmpty;
        boolean inDeck = selectionState.getArea() == SelectionArea.PLAYER_DECK && deckNotEmpty;

        if (inScroll) {
            int index = selectionState.getScrollIndex();
            String card = playerDeckManager.getAvailableCards().get(index);
            return CardFactory.create(card);
        } else if (inDeck) {
            int index = selectionState.getCardIndex();
            String card = playerDeckManager.getCustomDeck().get(index);
            return CardFactory.create(card);
        }
        return null;
    }

    private void drawReaction(Graphics2D g2) {
        drawScroll(g2, 0);
        if(selectedReaction == null) return;

        drawName(g2, 0);

        int detailBoxX = marginX + scrollBoxWidth + marginX;
        int detailBoxY = topBarHeight + 2* marginY + nameHeight;
        int detailBoxWidth = scaledWidth - detailBoxX - marginX;
        int detailBoxHeight = scaledHeight - detailBoxY - marginY;
        drawBox(g2, detailBoxX, detailBoxY, detailBoxWidth, detailBoxHeight,
                2, 10, 0, 15, 4);
        
        String details = "";
        details += String.valueOf(selectedReaction.getOriginalLP()) + " LP\n\n";
        details += selectedReaction.getDescription() + ".\n\n";
        details += "Requirements: " + selectedReaction.getRequiredCondition().getConditionString();
        
        textRenderer.renderText(g2, detailBoxX + 25, detailBoxY + 15, detailBoxWidth - 50, details, details);
    }

    private void drawMolecule(Graphics2D g2) {
        drawScroll(g2, 0);
        if(selectedMolecule == null) return;
        
        drawName(g2, 120);
        int moleculeX = marginX + scrollBoxWidth + marginX;
        int moleculeY = topBarHeight + marginY + 110;
        int nameWidth = scaledWidth - moleculeX - marginX;
        selectedMolecule.draw(g2, moleculeX + nameWidth/2, moleculeY);

        int detailBoxX = moleculeX;
        int detailBoxY = moleculeY + nameHeight + marginY + 10;
        int detailBoxWidth = nameWidth;
        int detailBoxHeight = scaledHeight - detailBoxY - marginY;
        drawBox(g2, detailBoxX, detailBoxY, detailBoxWidth, detailBoxHeight,
                2, 10, 0, 15, 4);
        
        String details = "";
        details += selectedMolecule.getMolecularWeight() + " g/mol\n\n";
        details += selectedMolecule.getSmiles() + "\n\n";
        details += selectedMolecule.getReactionCount() + " Available Reactions";
        
        textRenderer.renderText(g2, detailBoxX + 25, detailBoxY + 15, detailBoxWidth - 50, details, details);
    }

    private void updateCurrentPalette() {
        currentPalette = switch (selectionState.getCurrentTab()) {
            case DECK ->
                FIRE_PALETTE;
            case REACTION ->
                OCEAN_PALETTE;
            case MOLECULE ->
                FOREST_PALETTE;
        };
    }
}
