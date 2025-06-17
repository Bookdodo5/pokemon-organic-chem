package pokedex;

import battle.molecules.Molecule;
import battle.reactions.Reaction;
import java.awt.Graphics2D;
import java.util.List;

public class MoleculeTabRenderer extends TabRenderer {

    public MoleculeTabRenderer(PlayerDeckManager playerDeckManager, List<Molecule> moleculeRecord, List<Reaction> reactionRecord, SelectionState selectionState) {
        super(playerDeckManager, moleculeRecord, reactionRecord, selectionState);
    }

    @Override
    protected void drawTabContent(Graphics2D g2) {
        drawScroll(g2, 0);
        if (selectedMolecule == null) {
            return;
        }

        drawName(g2, 120);
        int moleculeX = marginX + scrollBoxWidth + marginX;
        int moleculeY = topBarHeight + marginY + 110;
        int nameWidth = scaledWidth - moleculeX - marginX;
        selectedMolecule.draw(g2, moleculeX + nameWidth / 2, moleculeY);

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
}
