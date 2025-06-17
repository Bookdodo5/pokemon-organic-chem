package battle.cards.condition.temperature;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.Temperature;

public class CryogenicsCard extends ConditionCard {

    public CryogenicsCard() {
        super("Cryogenics", 
              "Freezes everything in an instant!", 
              "Sets the temperature to COLD. This turn, only reactions that require COLD temperature can be played.", 
              2,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().setTemp(Temperature.COLD);
        board.addEffect("Cryogenic Shock", 1);
    }
} 