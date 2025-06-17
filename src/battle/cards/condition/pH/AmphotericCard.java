package battle.cards.condition.pH;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;

public class AmphotericCard extends ConditionCard {

    public AmphotericCard() {
        super("Amphoteric", 
              "Basically, just water, but on cracked.", 
              "Any reaction moves that require pH can be played by both players as if the pH is optimal for 3 turns.", 
              3,
              "fire4");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.addEffect("Amphoteric Adaptation", 3);
    }
} 