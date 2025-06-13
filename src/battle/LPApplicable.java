package battle;

import battle.conditions.ConditionBoard;

public class LPApplicable {
    
    public final int originalLP;

    public LPApplicable(int originalLP) {
	this.originalLP = originalLP;
    }

    public int getOriginalLP() {
	return originalLP;
    }

    public int getCurrentLP(BattlePlayer player, ConditionBoard board) {
	return BattleEffectManager.modifyLpCost(player, board, this);
    }

}
