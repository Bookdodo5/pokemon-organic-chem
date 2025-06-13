package battle.cards.condition.solvent;

import battle.BattlePlayer;
import battle.cards.condition.ConditionCard;
import battle.conditions.ConditionBoard;

public class GodlySolventCard extends ConditionCard {

    public GodlySolventCard() {
        super("Godly Solvent", 
              "The universal solvent that transcends all limitations!", 
              "Both players can play reactions regardless of the current solvent for 3 turns. Only you can play solvolysis reactions during that time.", 
              3,
              "electric1");
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        board.addEffect("Godly Solvent", 3);
        player.addEffect("Solvolysis Exclusive", 3);
    }
} 