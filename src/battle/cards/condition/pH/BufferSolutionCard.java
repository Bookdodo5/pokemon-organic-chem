package battle.cards.condition.pH;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.pH;

public class BufferSolutionCard extends ConditionCard {

    public BufferSolutionCard() {
        super("Buffer Solution", 
              "Maintains perfect equilibrium against all pH disturbances.", 
              "Sets the pH to either WEAK BASE, NEUTRAL, or WEAK ACID (your choice), and the pH cannot change for 2 turns.", 
              2,
              "fire4");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        // TODO: For now, default to NEUTRAL - in full implementation, this would present a choice to the player
        board.getCondition().setpH(pH.NEUTRAL);
        board.addEffect("Buffer Solution", 2);
    }
} 