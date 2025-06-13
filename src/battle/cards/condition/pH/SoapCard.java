package battle.cards.condition.pH;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.pH;

public class SoapCard extends ConditionCard {

    public SoapCard() {
        super("Soap", 
              "Clean and basic, but ironically prevents soap-making!", 
              "Sets the pH to WEAK BASE. Saponification reactions cannot be performed while this card is active.", 
              1,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
    	if(board.hasEffect("Buffer Solution")) return;
    	
        board.getCondition().setpH(pH.WEAK_BASE);
        board.addEffect("No Saponification", -1);
    }
} 