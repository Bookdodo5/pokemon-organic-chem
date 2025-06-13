package battle.cards.reagent;

import battle.Battle;
import battle.BattlePlayer;
import battle.battlephases.BattlePhases;
import battle.cards.Card;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class ReagentCard extends Card {

	public ReagentCard(String name, String flavorText, int lpCost) {
		super (
			name,
			flavorText,
			"Added to the condition board, enabling reactions that require it.",
			lpCost,
			"poison4",
			AnimationPosition.BOARD
		);
	}

	@Override
	protected void handleCardAfterPlay(BattlePlayer player, int handIndex) {
		player.getDeck().discard(handIndex);
	}

	@Override
	public boolean isRightTurn(Battle battle) {
		return battle.getCurrentPhase() == BattlePhases.REACTION_PLAY;
	}

	@Override
	public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
		board.setEffect(getName(), 1);
	}
}
