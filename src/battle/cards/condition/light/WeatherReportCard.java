package battle.cards.condition.light;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.Light;

public class WeatherReportCard extends ConditionCard {

    public WeatherReportCard() {
        super("Weather Report", 
              "The forecast predicts radical conditions... with a chance of oxidation!", 
              "Radical reactions become available and cost 1 less LP until the end of combat. In 3 turns, destroy every catalyst and reagent card without an Oxygen atom.", 
              3,
              "fire4");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().setLight(Light.LIGHT);
        board.addEffect("Radical Discount", -1);
        board.addEffect("Oxidation Event", 3);
    }
}