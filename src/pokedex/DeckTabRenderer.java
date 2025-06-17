package pokedex;

import battle.cards.Card;
import battle.cards.CardFactory;
import battle.molecules.Molecule;
import battle.reactions.Reaction;
import java.awt.Graphics2D;
import java.util.List;

public class DeckTabRenderer extends TabRenderer {

    public DeckTabRenderer(PlayerDeckManager playerDeckManager, List<Molecule> moleculeRecord, List<Reaction> reactionRecord, SelectionState selectionState) {
        super(playerDeckManager, moleculeRecord, reactionRecord, selectionState);
    }

    @Override
    protected void drawTabContent(Graphics2D g2) {
        int deckHeight = 120;
        int deckWidth = scaledWidth - 2 * marginX;
        int deckX = marginX;
        int deckY = scaledHeight - deckHeight - marginY;

        drawBox(g2, deckX, deckY, deckWidth, deckHeight,
                3, 4, 2, 8, 4);
        drawScroll(g2, marginY + deckHeight);

        drawCardDetails(g2);

        drawDeck(g2, deckX, deckY, deckWidth, deckHeight);
    }

    private void drawDeck(Graphics2D g2, int deckX, int deckY, int deckWidth, int deckHeight) {
        List<String> customDeck = playerDeckManager.getCustomDeck();
        String deckSizeText = customDeck.size() + " / 16 CARDS";
        int deckSizeTextWidth = textRenderer.getTextWidth(g2, deckSizeText);
        int deckSizeTextX = deckX + (deckWidth - deckSizeTextWidth) / 2;
        int deckSizeTextY = deckY + 10;
        textRenderer.renderLine(g2, deckSizeTextX, deckSizeTextY, deckSizeText);

        int cardSizeX = (deckWidth - 20) / 4;
        int cardSizeY = (deckHeight - 40) / 4;
        int cardIndex = selectionState.getCardIndex();
        if (selectionState.getArea() != SelectionArea.PLAYER_DECK) {
            cardIndex = -1;
        }

        for (int i = 0; i < 16; i++) {
            String card = "-";
            if (i < customDeck.size()) {
                card = customDeck.get(i);
            }
            int cardX = deckX + 10 + (i % 4) * cardSizeX;
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
        if (selectedCard == null) {
            return;
        }

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
} 