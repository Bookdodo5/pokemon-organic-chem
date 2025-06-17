package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class AlphaCard extends UtilityCard {

    public AlphaCard() {
        super("Alpha", 
              "The beginning of chaos...", 
              "Draw a card. Next turn, add Beta to your hand and randomize the pH condition.", 
              1,
              "water3",
              AnimationPosition.BOARD);
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        player.addEffect("Gain Beta", 1); // Replace with Beta next turn
        board.addEffect("Randomize pH", 1); // Randomize pH next turn
        player.getDeck().draw(1);
    }
} 