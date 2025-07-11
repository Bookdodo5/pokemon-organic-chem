package pokedex;

import java.awt.Graphics2D;

public class ReactionTabRenderer extends TabRenderer {

    public ReactionTabRenderer(PlayerDeckManager playerDeckManager, MoleculeRecord moleculeRecord, ReactionRecord reactionRecord, SelectionState selectionState) {
        super(playerDeckManager, moleculeRecord, reactionRecord, selectionState);
    }

    @Override
    protected void drawTabContent(Graphics2D g2) {
        drawScroll(g2, 0);
        if (selectedReaction == null) {
            return;
        }

        drawName(g2, 0);

        int detailBoxX = marginX + scrollBoxWidth + marginX;
        int detailBoxY = topBarHeight + 2 * marginY + nameHeight;
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
}
