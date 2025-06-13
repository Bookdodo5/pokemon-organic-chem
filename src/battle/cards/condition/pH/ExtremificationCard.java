package battle.cards.condition.pH;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.pH;

public class ExtremificationCard extends ConditionCard {

    public ExtremificationCard() {
        super("Extremification", 
              "Takes things to the extreme!", 
              "If the current pH condition is WEAK, make it STRONG (WEAK ACID → STRONG ACID, WEAK BASE → STRONG BASE).", 
              1,
              "fire5");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
    	if(board.hasEffect("Buffer Solution")) return;
    	
        pH currentpH = board.getCondition().getpH();
        if (currentpH == pH.WEAK_ACID) {
            board.getCondition().setpH(pH.STRONG_ACID);
        } else if (currentpH == pH.WEAK_BASE) {
            board.getCondition().setpH(pH.STRONG_BASE);
        }
    }
} 