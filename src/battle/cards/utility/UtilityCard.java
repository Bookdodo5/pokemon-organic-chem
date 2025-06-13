package battle.cards.utility;

import battle.Battle;
import battle.BattlePlayer;
import battle.battlephases.BattlePhases;
import battle.cards.Card;
import battle.event.AnimationPosition;

public abstract class UtilityCard extends Card {

	public UtilityCard(String name, String flavorText, String effects, int lpCost, String animationPath, AnimationPosition animationPosition) {
		super(name, flavorText, effects, lpCost, animationPath, animationPosition);
	}

	@Override
	protected void handleCardAfterPlay(BattlePlayer player, int handIndex) {
		player.getDeck().removeCard(handIndex);
	}

	@Override
	public boolean isRightTurn(Battle battle) {
		return battle.getCurrentPhase() == BattlePhases.CONDITION_PLAY;
	}
}
