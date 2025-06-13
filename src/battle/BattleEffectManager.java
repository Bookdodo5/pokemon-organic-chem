package battle;

import battle.conditions.ConditionBoard;

public class BattleEffectManager {

	public static int modifyLpCost(BattlePlayer player, ConditionBoard board, LPApplicable target) {
		return target.getOriginalLP();
	}
}
