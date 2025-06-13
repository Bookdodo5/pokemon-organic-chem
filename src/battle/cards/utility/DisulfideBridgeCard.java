package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.conditions.Temperature;
import battle.event.AnimationPosition;

public class DisulfideBridgeCard extends UtilityCard {

    public DisulfideBridgeCard() {
        super("Disulfide Bridge", 
              "Sulfur bonds provide stability and energy!", 
              "If TEMPERATURE is not HOT, gain LP equal to 2 times the number of Sulfur atoms in your current molecule.", 
              0,
              "water3",
              AnimationPosition.PLAYER);
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        if (board.getCondition().getTemp() != Temperature.HOT) {
            int sulfurAtoms = player.getMolecule().getAtomCount("S");
            player.gainLP(sulfurAtoms * 2);
        }
    }
} 