package battle.event;

import battle.Battle;
import battle.BattlePlayer;
import battle.cards.Card;   
import java.awt.Graphics2D;

public class CardBattleEvent extends BattleEvent {
    
    private final BattlePlayer actor;
    private final Card card;
    private final Battle battle;

    public CardBattleEvent(BattlePlayer actor, Card card, Battle battle) {
	this.actor = actor;
	this.card = card;
	this.battle = battle;
    }

    @Override
    public void start() {
        card.executeEffect(actor, battle.getOpponent(), battle.getBoard());
        end();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g2) {
    }

}
