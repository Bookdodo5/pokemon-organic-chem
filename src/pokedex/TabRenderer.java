package pokedex;

import battle.cards.CardFactory;
import battle.molecules.Molecule;
import battle.reactions.Reaction;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import static main.Constants.SCALE;
import static main.Constants.SCREEN_HEIGHT;
import static main.Constants.SCREEN_WIDTH;
import ui.TextRenderer;
import ui.TextStyle;

public abstract class TabRenderer {

    protected static final Color LIGHT = new Color(245, 245, 245);
    protected static final Color DARK = new Color(30, 30, 30);

    protected final TextStyle textStyle = TextStyle.getOptionStyle()
            .textMarginX(0).textMarginY(0).build();
    protected final TextRenderer textRenderer = new TextRenderer(textStyle);

    protected final int scaledWidth = SCREEN_WIDTH / (int) SCALE;
    protected final int scaledHeight = SCREEN_HEIGHT / (int) SCALE;

    protected final int topBarHeight = 40;
    protected final int marginX = 20;
    protected final int marginY = 15;
    protected final int cornerArc = 10;
    protected final int scrollBoxWidth = 225;
    protected final int scrollBoxWidthSmall = 185;
    protected final int nameHeight = 30;

    protected final PlayerDeckManager playerDeckManager;
    protected final List<Molecule> moleculeRecord;
    protected final List<Reaction> reactionRecord;
    protected final SelectionState selectionState;
    protected Molecule selectedMolecule;
    protected Reaction selectedReaction;
    protected Color[] currentPalette;

    public TabRenderer(PlayerDeckManager playerDeckManager, List<Molecule> moleculeRecord, List<Reaction> reactionRecord, SelectionState selectionState) {
        this.playerDeckManager = playerDeckManager;
        this.moleculeRecord = moleculeRecord;
        this.reactionRecord = reactionRecord;
        this.selectionState = selectionState;
    }

    public void draw(Graphics2D g2, Molecule selectedMolecule, Reaction selectedReaction, Color[] currentPalette) {
        this.selectedMolecule = selectedMolecule;
        this.selectedReaction = selectedReaction;
        this.currentPalette = currentPalette;
        drawTabContent(g2);
    }

    protected abstract void drawTabContent(Graphics2D g2);

    protected void renderLineWithPointer(Graphics2D g2, int x, int y, String text, int selectionIndex, int textIndex) {
        textRenderer.renderLine(g2, x, y, text);
        if (selectionIndex != textIndex) {
            return;
        }

        y = y + textRenderer.getTextHeight(g2);
        int[] xPoints = {x - 3, x - 10, x - 10};
        int[] yPoints = {y, y + 5, y - 5};
        g2.setColor(textStyle.getTextColor());
        g2.fillPolygon(xPoints, yPoints, 3);
    }

    protected void drawBox(Graphics2D g2, int x, int y, int width, int height,
            int borderSize, int secondXOffset, int secondYOffset, int thirdXOffset, int thirdYOffset) {

        g2.setColor(new Color(20, 20, 20, 100));
        g2.fillRoundRect(x + 4, y + 4, width, height, cornerArc, cornerArc);
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

    protected void drawScroll(Graphics2D g2, int scrollOffsetBottom) {
        int scrollBoxX = marginX;
        int scrollBoxY = topBarHeight + marginY;
        int scrollBoxHeight = scaledHeight - topBarHeight - marginY * 2 - scrollOffsetBottom;
        int realWidth = selectionState.getCurrentTab() == PokedexTab.DECK ? scrollBoxWidthSmall : scrollBoxWidth;
        drawBox(g2, scrollBoxX, scrollBoxY, realWidth, scrollBoxHeight,
                3, 10, 0, 15, 4);

        int scrollIndex = selectionState.getScrollIndex();
        int focusIndex = selectionState.getFocusIndex();
        int maxDisplay = selectionState.getCurrentTab().getScrollDisplay();

        for (int i = focusIndex; i < focusIndex + maxDisplay; i++) {
            if (outOfBounds(i)) {
                break;
            }
            String displayText = switch (selectionState.getCurrentTab()) {
                case DECK -> {
                    if (i == focusIndex && playerDeckManager.getAvailableCards().isEmpty()) {
                        yield "No cards available";
                    }
                    String card = playerDeckManager.getAvailableCards().get(i);
                    int count = playerDeckManager.getCardCount(card);
                    yield card.toUpperCase() + " [x" + count + "]";
                }
                case REACTION -> {
                    if (i == focusIndex && reactionRecord.isEmpty()) {
                        yield "No reactions available";
                    }
                    Reaction reaction = reactionRecord.get(i);
                    yield reaction.getName().toUpperCase();
                }
                case MOLECULE -> {
                    if (i == focusIndex && moleculeRecord.isEmpty()) {
                        yield "No molecules available";
                    }
                    Molecule molecule = moleculeRecord.get(i);
                    yield molecule.getName().toUpperCase();
                }
            };

            if (selectionState.getArea() != SelectionArea.SCROLL) {
                scrollIndex = -1;
            }
            renderLineWithPointer(g2, scrollBoxX + 25, scrollBoxY + 20 + (i - focusIndex) * 20,
                    displayText, scrollIndex, i);
        }
    }

    protected boolean outOfBounds(int index) {
        return switch (selectionState.getCurrentTab()) {
            case DECK ->
                index >= Math.max(playerDeckManager.getAvailableCardTypes(), 1);
            case REACTION ->
                index >= Math.max(reactionRecord.size(), 1);
            case MOLECULE ->
                index >= Math.max(moleculeRecord.size(), 1);
        };
    }

    protected void drawName(Graphics2D g2, int nameOffsetTop) {
        int realWidth = selectionState.getCurrentTab() == PokedexTab.DECK ? scrollBoxWidthSmall : scrollBoxWidth;
        int nameX = marginX + realWidth + marginX;
        int nameY = topBarHeight + marginY + nameOffsetTop;
        int nameWidth = scaledWidth - nameX - marginX;

        drawBox(g2, nameX, nameY, nameWidth, nameHeight,
                2, 10, 0, 15, 3);

        String name = switch (selectionState.getCurrentTab()) {
            case DECK -> {
                var selectedCard = CardFactory.create(playerDeckManager.getAvailableCards().get(selectionState.getScrollIndex()));
                if (selectedCard == null) {
                    yield "";
                }
                yield selectedCard.getName().toUpperCase() + " (" + selectedCard.getOriginalLP() + " LP)";
            }
            case REACTION -> {
                if (selectedReaction == null) {
                    yield "";
                }
                yield selectedReaction.getName().toUpperCase();
            }
            case MOLECULE -> {
                if (selectedMolecule == null) {
                    yield "";
                }
                yield selectedMolecule.getName().toUpperCase();
            }
            default ->
                "";
        };

        int nameStringWidth = textRenderer.getTextWidth(g2, name);
        int nameStringHeight = textRenderer.getTextHeight(g2) * 2;
        int nameStringX = nameX + (nameWidth - nameStringWidth) / 2;
        int nameStringY = nameY + (nameHeight - nameStringHeight) / 2 + 1;

        textRenderer.renderLine(g2, nameStringX, nameStringY, name);
    }
} 