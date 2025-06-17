package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class ClockRewindCard extends UtilityCard {

    public ClockRewindCard() {
        super("Clock Rewind", 
              "You failed. You should just start it all over again.", 
              "Return your opponent's molecule and reaction conditions to their original state at the start of battle. You skip the next 2 turns.", 
              5,
              "water3",
              AnimationPosition.OPPONENT);
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        opponent.setMolecule(opponent.getOriginalMolecule());
        player.addEffect("Skip Turn", 2); // Skip next 2 turns
    }
} 