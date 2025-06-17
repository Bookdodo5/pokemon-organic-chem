package battle.cards.condition.solvent;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;
import battle.conditions.Solvent;

public class LondonCityCard extends ConditionCard {

    public LondonCityCard() {
        super("London City", 
              "London bridge is falling down, but the dispersion forces will take care of it.", 
              "Sets the solvent to NONPOLAR. Gain LP equal to the number of cards containing \"clock\", \"bridge\", or \"eye\" you have selected.", 
              1,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.getCondition().setSolvent(Solvent.NONPOLAR);
        // TODO: Count clock, bridge, eye cards and gain LP accordingly
        // int specialCards = countSpecialCards(player);
        // player.gainLP(specialCards);
    }
} 