package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;
import java.util.Random;

public class HaloOnCrackCard extends UtilityCard {

    private static final Random random = new Random();

    public HaloOnCrackCard() {
        super("Halo on Crack", 
            "A halogen gas on a crack",
            "Draw 3 cards. When reaction phase begins, create and play either Cl2 or Br2 randomly.", 
            0,
            "water3",
            AnimationPosition.BOARD
        );
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        player.getDeck().draw(3);
        if(random.nextBoolean()) {
            board.addEffect("Place Cl2", 1);
        } else {
            board.addEffect("Place Br2", 1);
        }
    }
} 