package battle.cards.condition.temperature;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;

public class IceCreamCard extends ConditionCard {

    public IceCreamCard() {
        super("Ice Cream", 
              "Cool and sweet, but don't eat it in the lab!", 
              "Decrease TEMPERATURE by 1 level.", 
              1,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().decreaseTemp();
    }
} 