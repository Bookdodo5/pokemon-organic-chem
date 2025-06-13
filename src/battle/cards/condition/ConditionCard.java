package battle.cards.condition;

import battle.Battle;
import battle.BattlePlayer;
import battle.battlephases.BattlePhases;
import battle.cards.Card;
import battle.event.AnimationPosition;

public abstract class ConditionCard extends Card {

	public ConditionCard(String name, String flavorText, String effects, int lpCost, String animationPath) {
		super(name, flavorText, effects, lpCost, animationPath, AnimationPosition.BOARD);
	}

	@Override
	protected void handleCardAfterPlay(BattlePlayer player, int handIndex) {
		player.getDeck().discard(handIndex);
	}

	@Override
	public boolean isRightTurn(Battle battle) {
		return battle.getCurrentPhase() == BattlePhases.CONDITION_PLAY;
	}
}
