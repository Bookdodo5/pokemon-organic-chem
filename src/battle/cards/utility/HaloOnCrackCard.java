package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;
import java.util.Random;

public class HaloOnCrackCard extends UtilityCard {

    private static final Random random = new Random();
    private String chosenHalogen;

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
    public void onPlay(BattlePlayer player, ConditionBoard board) {
        if (random.nextBoolean()) {
            this.chosenHalogen = "Cl2";
        } else {
            this.chosenHalogen = "Br2";
        }
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        player.getDeck().draw(3);
        if ("Cl2".equals(this.chosenHalogen)) {
            board.addEffect("Place Cl2", 1);
        } else {
            board.addEffect("Place Br2", 1);
        }
    }

    @Override
    public String[] getPlayDialogue(boolean isPlayer) {
        String actorName = isPlayer ? "You" : "Opponent";
        return new String[] {
            actorName + " played " + getName(),
            chosenHalogen + " will be added to the board."
        };
    }
} 