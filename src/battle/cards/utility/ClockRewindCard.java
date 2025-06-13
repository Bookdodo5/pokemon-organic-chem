package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class ClockRewindCard extends UtilityCard {

    public ClockRewindCard() {
        super("Clock Rewind", 
              "Sometimes the best strategy is starting over!", 
              "Return your opponent's molecule and reaction conditions to their original state at the start of battle. You skip the next 2 turns.", 
              1,
              "water3",
              AnimationPosition.OPPONENT);
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        opponent.setMolecule(opponent.getOriginalMolecule());
        player.addEffect("Skip Turn", 2); // Skip next 2 turns
    }
} 