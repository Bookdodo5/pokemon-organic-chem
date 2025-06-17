package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class OmegaCard extends UtilityCard {

    public OmegaCard() {
        super("Omega", 
              "The end brings aquatic enlightenment!", 
              "Add 3 \"Fish\" to your draw pile. Next turn, randomize the solvent condition.", 
              3,
              "water3",
              AnimationPosition.BOARD);
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        player.getDeck().addToDrawPile(new FishCard());
        player.getDeck().addToDrawPile(new FishCard());
        player.getDeck().addToDrawPile(new FishCard());
        board.addEffect("Randomize Solvent", 1);
    }
} 