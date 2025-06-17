package battle.cards.condition.temperature;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;

public class HomeostasisCard extends ConditionCard {

    public HomeostasisCard() {
        super("Homeostasis", 
              "Don't forget to sweat once in a while to maintain body temperature!", 
              "For 3 turns, if the temperature is ROOM TEMPERATURE, all reactions that require ROOM TEMPERATURE cost 1 less LP. Other reactions cost 1 more LP.", 
              2,
              "fire5");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.addEffect("Thermal Equilibrium", 3);
    }
} 