package battle.cards.condition.temperature;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.Temperature;

public class FlashHeatingCard extends ConditionCard {

    public FlashHeatingCard() {
        super("Flash Heating", 
              "A brief but intense burst of thermal energy!", 
              "Instant HOT temperature for this turn only. You cannot use this card again this combat.", 
              0,
              "fire4");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        if(player.hasEffect("Flash Heating Used")) return;

    	Temperature currentTemp = board.getCondition().getTemp();
        board.getCondition().setTemp(Temperature.HOT);
        player.addEffect("Flash Heating Used", -1);

        switch (currentTemp) {
        	case COLD -> board.addEffect("Return to COLD", 1);
        	case ROOM -> board.addEffect("Return to ROOM", 1);
        	case HOT -> board.addEffect("Return to HOT", 1);
		}
    }
} 