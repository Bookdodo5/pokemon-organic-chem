package battle.cards.condition.solvent;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.Solvent;
import battle.conditions.Temperature;

public class PolarBearCard extends ConditionCard {

    public PolarBearCard() {
        super("Polar Bear", 
              "Polar but doesn't share protons. Selfish.", 
              "Sets the solvent to POLAR APROTIC. Gain 2 LP if the TEMPERATURE is COLD.", 
              1,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().setSolvent(Solvent.POLAR_APROTIC);
        if (board.getCondition().getTemp() == Temperature.COLD) {
            player.gainLP(2);
        }
    }
} 