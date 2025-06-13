package battle.cards.condition.light;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.Light;

public class RainDanceCard extends ConditionCard {

    public RainDanceCard() {
        super("Rain Dance", 
              "The clouds block out radical-inducing light, but provide water for hydration!", 
              "Radical reactions become unavailable. Hydration reactions cost 1 less LP.", 
              1,
              "24");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().setLight(Light.DARK);
        board.removeEffect("Hydration Penalty");
        board.addEffect("Hydration Discount", -1);
    }
} 