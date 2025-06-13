package battle.cards.condition.temperature;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;

public class GlobalWarmingCard extends ConditionCard {

    public GlobalWarmingCard() {
        super("Global Warming", 
              "Slowly but surely, everything gets hotter...", 
              "At the end of each turn, TEMPERATURE is raised by 1 level. This effect cannot be stacked.", 
              1,
              "fire5");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.addEffect("Global Warming", -1);
    }
} 