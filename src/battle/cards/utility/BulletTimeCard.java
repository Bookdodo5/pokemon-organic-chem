package battle.cards.utility;

import battle.BattlePlayer;
import battle.conditions.ConditionBoard;
import battle.event.AnimationPosition;

public class BulletTimeCard extends UtilityCard {

    public BulletTimeCard() {
        super("Bullet Time", 
              "Time slows down, but your fate is still the same.", 
              "You can play any cards for free this turn.", 
              5,
              "water3",
              AnimationPosition.PLAYER);
    }

    @Override
    public void executeEffect(BattlePlayer player, BattlePlayer opponent, ConditionBoard board) {
        player.addEffect("Free Cards", 1); // All cards cost 0 this turn
    }
} 