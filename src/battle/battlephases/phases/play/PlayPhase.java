package battle.battlephases.phases.play;

import assets.SoundManager;
import battle.Battle;
import battle.BattlePlayer;
import battle.battlephases.BattlePhase;
import battle.battlephases.PhaseManager;
import battle.cards.Card;
import battle.reactions.Reaction;
import battle.ui.BattleInputRenderer;
import battle.ui.BattleRenderer;
import input.KeyBindingHandler;
import java.awt.Graphics2D;

public class PlayPhase extends BattlePhase {

    private final Battle battle;
    private final BattlePlayer player;
    private final BattleActionSelection actionSelection;
    private final BattleRenderer battleRenderer;
    private final BattleInputRenderer inputRenderer;

    public PlayPhase(PhaseManager phaseManager, KeyBindingHandler keyHandler) {
	super(phaseManager, keyHandler);
	this.battle = phaseManager.getBattle();
	this.player = battle.getPlayer();
	this.actionSelection = new BattleActionSelection(player, keyHandler);

	this.battleRenderer = new BattleRenderer(battle, battle.getBattleTheme());
	this.inputRenderer = new BattleInputRenderer(battle, player);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g2) {
	battleRenderer.render(g2);
	inputRenderer.renderActionSelection(g2, actionSelection.getCurrentPage(), actionSelection.getSelectionIndex(), actionSelection.getFocusIndex());
    }

    @Override
    public void keyTapped() {
	switch (keyHandler.getCurrentKey()) {
	case UP -> actionSelection.handleUp();
	case DOWN -> actionSelection.handleDown();
	case LEFT -> actionSelection.handleLeft();
	case RIGHT -> actionSelection.handleRight();
	case INTERACT -> {
	    actionSelection.handleInteract();
	    handleAction();
	}
	default -> {
	}
	}
    }

    private void handleAction() {
	BattleAction action = actionSelection.getAction();
	int selectionIndex = actionSelection.getSelectionIndex();
	switch (action) {
	case CARD_PLAY -> handleCardPlay(selectionIndex);
	case REACTION_PLAY -> handleReactionPlay(selectionIndex);
	case RUN -> handleRun();
	case END_PHASE -> handleEndPhase();
	}
    }

    private void handleCardPlay(int selectionIndex) {
	Card card = player.getDeck().getHand().get(selectionIndex);
	boolean canPlay = card.canPlay(player, battle.getBoard(), battle);
	boolean isLastCard = selectionIndex == player.getHandSize() - 1;

	if (canPlay) {
	    SoundManager.getSfxplayer().playSE("BattleDamageNormal");
	    card.play(player, battle.getBoard(), selectionIndex);
	    if (isLastCard) {
		actionSelection.setSelectionIndex(player.getHandSize() - 1);
	    }
	} else {
	    SoundManager.getSfxplayer().playSE("PlayerBump");
	}
    }

    private void handleReactionPlay(int selectionIndex) {
	Reaction reaction = player.getMolecule().getReactions().get(selectionIndex);
	boolean canPlay = reaction.canPlay(player, battle.getBoard(), battle);

	if (canPlay) {
	    reaction.play(player, battle.getBoard());
	    SoundManager.getSfxplayer().playSE("BattleDamageSuper");
	} else {
	    SoundManager.getSfxplayer().playSE("PlayerBump");
	}
    }

    private void handleRun() {
		SoundManager.getSfxplayer().playSE("BattleFlee");
		battle.lose(true);
    }

    private void handleEndPhase() {
	SoundManager.getSfxplayer().playSE("GUIMenuClose");
	phaseManager.nextPhase();
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }
}
