package battle.cards.condition.pH;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.pH;

public class HydroxideWaveCard extends ConditionCard {

    public HydroxideWaveCard() {
        super("Hydroxide Wave", 
              "A surge of OH⁻ ions strips away all acidic conditions!", 
              "Sets the pH to STRONG BASE. This effect cancels \"Buffer Solution.\"", 
              2,
              "fire5");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().setpH(pH.STRONG_BASE);
        board.removeEffect("Buffer Solution");
    }
} 