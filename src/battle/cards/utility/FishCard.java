package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class FishCard extends UtilityCard {

    public FishCard() {
        super("Fish", 
              "A fishy card", 
              "You became slippy and draw 2 cards", 
              0,
              "water3",
              AnimationPosition.PLAYER);
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        player.getDeck().draw(2);
    }
} 