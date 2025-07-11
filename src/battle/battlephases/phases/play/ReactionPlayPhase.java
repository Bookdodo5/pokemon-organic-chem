package battle.battlephases.phases.play;

import battle.battlephases.PhaseManager;
import battle.cards.CardFactory;
import battle.conditions.ConditionBoard;
import input.KeyBindingHandler;

public class ReactionPlayPhase extends PlayPhase {

    public ReactionPlayPhase(PhaseManager phaseManager, KeyBindingHandler keyHandler) {
        super(phaseManager, keyHandler);
    }

    @Override
    public void onEnter() {
        ConditionBoard board = phaseManager.getBattle().getBoard();
        if(board.hasEffect("Place Cl2")) {
            phaseManager.getBattle().getPlayer().addToQueue(CardFactory.create("Cl2"));
        }
        else if(board.hasEffect("Place Br2")) {
            phaseManager.getBattle().getPlayer().addToQueue(CardFactory.create("Br2"));
        }
    }
}