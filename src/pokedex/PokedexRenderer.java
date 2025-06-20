package pokedex;

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
    
    private final SelectionState selectionState;

    private final DeckTabRenderer deckTabRenderer;
    private final ReactionTabRenderer reactionTabRenderer;
    private final MoleculeTabRenderer moleculeTabRenderer;

    public PokedexRenderer(PlayerDeckManager playerDeckManager, List<Molecule> moleculeRecord, List<Reaction> reactionRecord, SelectionState selectionState) {
        this.selectionState = selectionState;
        this.deckTabRenderer = new DeckTabRenderer(playerDeckManager, moleculeRecord, reactionRecord, selectionState);
        this.reactionTabRenderer = new ReactionTabRenderer(playerDeckManager, moleculeRecord, reactionRecord, selectionState);
        this.moleculeTabRenderer = new MoleculeTabRenderer(playerDeckManager, moleculeRecord, reactionRecord, selectionState);
    }

    public void draw(Graphics2D g2, Molecule selectedMolecule, Reaction selectedReaction) {
        updateCurrentPalette();

        drawBackground(g2);
        drawSelectionBoxes(g2);

        switch (selectionState.getCurrentTab()) {
            case DECK ->
                deckTabRenderer.draw(g2, selectedMolecule, selectedReaction, currentPalette);
            case REACTION ->
                reactionTabRenderer.draw(g2, selectedMolecule, selectedReaction, currentPalette);
            case MOLECULE ->
                moleculeTabRenderer.draw(g2, selectedMolecule, selectedReaction, currentPalette);
        }
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
