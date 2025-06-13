package battle.cards.condition.light;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.Light;

public class SunnyDayCard extends ConditionCard {

    public SunnyDayCard() {
        super("Sunny Day", 
              "Bright light initiates radicals but evaporates precious water!", 
              "Radical reactions become available. Hydration reactions cost 1 more LP.", 
              1,
              "26");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().setLight(Light.LIGHT);
        board.removeEffect("Hydration Discount");
        board.addEffect("Hydration Penalty", -1);
    }
} 