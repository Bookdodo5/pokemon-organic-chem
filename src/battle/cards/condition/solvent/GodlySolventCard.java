package battle.cards.condition.solvent;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;

public class GodlySolventCard extends ConditionCard {

    public GodlySolventCard() {
        super("Godly Solvent", 
              "Can god create a solvent that he himself cannot get rid of?", 
              "Both players can play reactions regardless of the current solvent for 3 turns. Only you can change the solvent during that time.", 
              3,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.addEffect("Godly Solvent", 3);
        player.addEffect("Solvent Exclusive", 3);
    }
} 