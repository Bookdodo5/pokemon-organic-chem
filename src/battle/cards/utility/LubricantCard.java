package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class LubricantCard extends UtilityCard {

    public LubricantCard() {
        super("Lubricant", 
            "A slippery card", 
            "You became slippery and draw 2 cards", 
            0,
            "water3",
            AnimationPosition.BOARD
        );
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        player.getDeck().draw(2);
    }
} 