package battle.cards.utility;

import battle.BattlePlayer;
import battle.cards.Deck;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class EyeForAnEyeCard extends UtilityCard {

    public EyeForAnEyeCard() {
        super("Eye for an Eye", 
              "What's yours is mine, what's mine is yours!", 
              "Swap you and your opponent's deck for 1 turn.", 
              2,
              "water3",
              AnimationPosition.BOARD);
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.setEffect("Swap Decks", 1);
        Deck tempDeck = player.getDeck();
        player.setDeck(opponent.getDeck());
        opponent.setDeck(tempDeck);
    }
} 