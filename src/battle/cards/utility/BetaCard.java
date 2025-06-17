package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class BetaCard extends UtilityCard {

    public BetaCard() {
        super("Beta", 
              "The middle of madness...", 
              "Draw a card. Next turn, add Omega to your hand and randomize the temperature condition.", 
              2,
              "water3",
              AnimationPosition.BOARD);
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        player.addEffect("Gain Omega", 1); // Replace with Omega next turn
        board.addEffect("Randomize Temperature", 1); // Randomize temperature next turn
        player.getDeck().draw(1);
    }
} 