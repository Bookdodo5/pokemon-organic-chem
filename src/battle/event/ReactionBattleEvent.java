package battle.event;

import battle.Battle;
import battle.BattlePlayer;
import battle.reactions.Reaction;
import java.awt.Graphics2D;

public class ReactionBattleEvent extends BattleEvent {

    private final BattlePlayer actor;
    private final Battle battle;

    public ReactionBattleEvent(BattlePlayer actor, Battle battle) {
        this.actor = actor;
        this.battle = battle;
    }

    @Override
    public void start() {
        executeReaction();
        end();
    }

    private void executeReaction() {
        Reaction reaction = actor.getSelectedReaction();
        if(reaction != null) {
            reaction.executeEffect(actor, battle.getBoard(), battle);
        }
        battle.checkWin();
    }

    @Override
    public void update() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void draw(Graphics2D g2) {
	// TODO Auto-generated method stub
	
    }

}
