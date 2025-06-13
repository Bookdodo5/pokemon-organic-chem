package battle.cards.condition.temperature;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;

public class IceCreamCard extends ConditionCard {

    public IceCreamCard() {
        super("Ice Cream", 
              "Cool and sweet, but ruins your sugar chemistry!", 
              "Decrease TEMPERATURE by 1 level. Sugar cannot be synthesized while this card is active.", 
              1,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().decreaseTemp();
        board.addEffect("No Sugar", -1);
    }
} 