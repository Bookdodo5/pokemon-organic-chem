package battle.cards.condition.pH;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.pH;

public class LemonadeCard extends ConditionCard {

    public LemonadeCard() {
        super("Lemonade", 
              "Refreshingly acidic, but not too harsh!", 
              "Sets the pH to WEAK ACID. Citric acid cannot be synthesized while this card is active.", 
              1,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
    	if(board.hasEffect("Buffer Solution")) return;
    	
        board.getCondition().setpH(pH.WEAK_ACID);
        board.addEffect("No Citric Acid", -1);
    }
} 