package battle.cards.condition.pH;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.pH;

public class ProtonSinkCard extends ConditionCard {

    public ProtonSinkCard() {
        super("Proton Sink", 
              "Protons are delicious.", 
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