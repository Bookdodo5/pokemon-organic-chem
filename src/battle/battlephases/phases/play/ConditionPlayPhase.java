package battle.battlephases.phases.play;

import battle.battlephases.PhaseManager;
import input.KeyBindingHandler;

public class ConditionPlayPhase extends PlayPhase {

    public ConditionPlayPhase(PhaseManager phaseManager, KeyBindingHandler keyHandler) {
	super(phaseManager, keyHandler);
    }

    @Override
    public void onEnter() {
        resetPlayers();
        phaseManager.getBattle().getBoard().triggerTurn();
        phaseManager.getBattle().nextTurn();
    }

    private void resetPlayers() {
        phaseManager.getBattle().getPlayer().newTurn();
        phaseManager.getBattle().getOpponent().newTurn();
    }
}
