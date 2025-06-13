package battle.cards;

import battle.Battle;
import battle.BattlePlayer;
import battle.LPApplicable;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public abstract class Card extends LPApplicable {
	private final String name;
	private final String flavorText;
	private final String effects;
	private final String animationPath;
	private final AnimationPosition animationPosition;
	
	public Card(String name, String flavorText, String effects, int lpCost, String animationPath, AnimationPosition animationPosition) {
		super(lpCost);
		this.name = name;
		this.flavorText = flavorText;
		this.effects = effects;
		this.animationPath = animationPath;
		this.animationPosition = animationPosition;
	}
	
	public boolean canPlay(BattlePlayer player, ConditionBoard board, Battle battle) {
		int lpCost = getCurrentLP(player, board);
		int currentLP = player.getCurrentLP();
		boolean hasEnoughLP = currentLP >= lpCost;
		boolean isRightPhase = isRightTurn(battle);
		
		return hasEnoughLP && isRightPhase;
	}

	protected abstract boolean isRightTurn(Battle battle);
	
	public void play(BattlePlayer player, ConditionBoard board, int handIndex) {
		player.spend(getCurrentLP(player, board));
		player.addToQueue(this);
		handleCardAfterPlay(player, handIndex);
	}
	
	protected abstract void handleCardAfterPlay(BattlePlayer player, int handIndex);
	
	public abstract void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board);

	public String getName() {
		return name;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public String getEffects() {
		return effects;
	}

	public String getAnimationPath() {
		return animationPath;
	}

	public AnimationPosition getAnimationPosition() {
		return animationPosition;
	}

}
