package battle.cards.condition.solvent;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.Solvent;

public class AquariumWaterCard extends ConditionCard {

    public AquariumWaterCard() {
        super("Aquarium Water", 
              "The fishies provide polar protic goodness!", 
              "Sets the solvent to POLAR PROTIC. Gain LP equal to the number of cards containing \"fish\" you have selected.", 
              1,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().setSolvent(Solvent.POLAR_PROTIC);
        // TODO: Count fish cards and gain LP accordingly
        // int fishCards = countFishCards(player);
        // player.gainLP(fishCards);
    }
} 