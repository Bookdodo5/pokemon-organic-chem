package battle.cards.condition.solvent;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.Solvent;

public class AquariumCard extends ConditionCard {

    public AquariumCard() {
        super("Aquarium", 
              "Did you know that in terms of human and fish breeding--", 
              "Sets the solvent to POLAR PROTIC. Gain LP equal to the number of cards containing \"fish\" you have in your deck.", 
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