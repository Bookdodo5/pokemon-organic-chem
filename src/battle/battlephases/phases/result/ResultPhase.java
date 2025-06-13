package battle.battlephases.phases.result;

import battle.Battle;
import battle.BattlePlayer;
import battle.battlephases.BattlePhase;
import battle.battlephases.BattlePhases;
import battle.battlephases.PhaseManager;
import battle.cards.Card;
import battle.event.AnimationBattleEvent;
import battle.event.AnimationPosition;
import battle.event.CardBattleEvent;
import battle.event.DialogueBattleEvent;
import battle.event.ReactionBattleEvent;
import battle.molecules.Molecule;
import battle.reactions.Reaction;
import battle.ui.BattleRenderer;
import dialogue.Dialogue;
import input.KeyBindingHandler;
import java.awt.Graphics2D;

public class ResultPhase extends BattlePhase {

    private final Battle battle;
    private final BattlePlayer player;
    private final BattlePlayer opponent;
    private final BattleRenderer battleRenderer;
    private boolean wasEventManagerBusy = false;

    public ResultPhase(PhaseManager phaseManager, KeyBindingHandler keyHandler) {
        super(phaseManager, keyHandler);
        this.battle = phaseManager.getBattle();
        this.player = battle.getPlayer();
        this.opponent = battle.getOpponent();
        this.battleRenderer = new BattleRenderer(battle, battle.getBattleTheme());
    }

    @Override
    public void update() {
        boolean isEventManagerFinished = battle.getEventManager().isFinished();
        if (wasEventManagerBusy && isEventManagerFinished) {
            phaseManager.nextPhase();
        }
        
        wasEventManagerBusy = !isEventManagerFinished;
    }

    @Override
    public void draw(Graphics2D g2) {
        battleRenderer.render(g2);
        battle.getEventManager().draw(g2);
    }

    @Override
    public void keyTapped() {
    }

    @Override
    public void onEnter() {
        wasEventManagerBusy = false;
        handleCardResult();
        boolean isReactionResult = battle.getCurrentPhase() == BattlePhases.REACTION_RESULT;
        if (isReactionResult) {
            handleReactionResult();
        }
    }

    private void handleCardResult() {
        boolean bothEmpty = player.isQueueEmpty() && opponent.isQueueEmpty();

        if (bothEmpty) {
            battle.getEventManager().addEvent(new DialogueBattleEvent(
                    new Dialogue(new String[]{"Nobody played any cards!"})
            ));
        }
        while (!bothEmpty) {
            boolean playerPlay = player.isPlayedBefore(opponent);
            BattlePlayer actor = playerPlay ? player : opponent;
            Card topCard = actor.getTopCard();

            String animationPath = topCard.getAnimationPath();
            AnimationPosition position = topCard.getAnimationPosition();
            if(!playerPlay) position = position.getOpposite();

            String dialogue = playerPlay ? "You played " : "Opponent played ";
            dialogue += topCard.getName();

            battle.getEventManager().addEvent(new AnimationBattleEvent(
                    animationPath, position, 1, 5
            ));
            battle.getEventManager().addEvent(new CardBattleEvent(
                    actor, topCard, battle
            ));
            battle.getEventManager().addEvent(new DialogueBattleEvent(
                    new Dialogue(new String[]{dialogue})
            ));

            bothEmpty = player.isQueueEmpty() && opponent.isQueueEmpty();
        }
    }

    private void handleReactionResult() {
        Molecule playerMolecule = player.getMolecule();
        Molecule opponentMolecule = opponent.getMolecule();
        boolean playerFirst = playerMolecule.isPlayedBefore(opponentMolecule);

        BattlePlayer firstActor = playerFirst ? player : opponent;
        BattlePlayer secondActor = playerFirst ? opponent : player;

        Reaction firstReaction = firstActor.getSelectedReaction();
        Reaction secondReaction = secondActor.getSelectedReaction();

        AnimationPosition firstPosition = AnimationPosition.PLAYER;
        if(!playerFirst) firstPosition = firstPosition.getOpposite();

        if (firstReaction != null) {
            battle.getEventManager().addEvent(new AnimationBattleEvent(
                    firstReaction.getAnimationPath(), firstPosition, 1, 5
            ));
            battle.getEventManager().addEvent(new ReactionBattleEvent(
                    firstActor, battle
            ));
        }
        if (secondReaction != null) {
            battle.getEventManager().addEvent(new AnimationBattleEvent(
                    secondReaction.getAnimationPath(), firstPosition.getOpposite(), 1, 5
            ));
            battle.getEventManager().addEvent(new ReactionBattleEvent(
                    secondActor, battle
            ));
        }
        if (firstReaction == null && secondReaction == null) {
            battle.getEventManager().addEvent(new DialogueBattleEvent(
                    new Dialogue(new String[]{"No player played any reaction!"})
            ));
        }
    }

    @Override
    public void onExit() {
    }
}
