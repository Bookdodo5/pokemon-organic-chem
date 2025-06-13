package battle.cards.condition.pH;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.pH;

public class ProtonStreamCard extends ConditionCard {

    public ProtonStreamCard() {
        super("Proton Stream", 
              "A torrent of H⁺ ions overwhelms all basic defenses!", 
              "Sets the pH to STRONG ACID. This effect cancels \"Buffer Solution.\"", 
              2,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().setpH(pH.STRONG_ACID);
        board.removeEffect("Buffer Solution");
    }
} 