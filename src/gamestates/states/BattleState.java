package gamestates.states;

import assets.SoundManager;
import battle.Battle;
import battle.BattleData;
import battle.BattleDataManager;
import battle.PlayerDeckManager;
import battle.battlephases.BattlePhases;
import battle.battlephases.PhaseManager;
import battle.battlephases.phases.BattleLoseState;
import battle.battlephases.phases.BattleStartPhase;
import battle.battlephases.phases.BattleWinState;
import battle.battlephases.phases.play.ConditionPlayPhase;
import battle.battlephases.phases.play.ReactionPlayPhase;
import battle.battlephases.phases.result.ConditionResultPhase;
import battle.battlephases.phases.result.ReactionResultPhase;
import battle.event.BattleEvent;
import battle.event.DialogueBattleEvent;
import battle.reactions.ReactionAvailabilityManager;
import dialogue.Dialogue;
import dialogue.DialogueRenderer;
import gamestates.FlagManager;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import main.GameContentManager;

public class BattleState extends GameState {

    private final PlayerDeckManager playerDeckManager;
    private final FlagManager flagManager;
    private final BattleDataManager battleDataManager;
    private final ReactionAvailabilityManager reactionAvailabilityManager;

    private PhaseManager phaseManager;
    private Battle battle;

    public BattleState(StateManager stateManager, KeyBindingHandler keyHandler, GameContentManager gameContentManager) {
        super(stateManager, keyHandler, gameContentManager);
        this.playerDeckManager = gameContentManager.getPlayerDeckManager();
        this.flagManager = gameContentManager.getFlagManager();
        this.battleDataManager = gameContentManager.getBattleDataManager();
        this.reactionAvailabilityManager = gameContentManager.getReactionAvailabilityManager();
    }

    private void initializePhases() {
	PhaseManager.phases.put(BattlePhases.BATTLE_START, new BattleStartPhase(phaseManager, keyHandler));
	PhaseManager.phases.put(BattlePhases.CONDITION_PLAY, new ConditionPlayPhase(phaseManager, keyHandler));
	PhaseManager.phases.put(BattlePhases.CONDITION_RESULT, new ConditionResultPhase(phaseManager, keyHandler));
	PhaseManager.phases.put(BattlePhases.REACTION_PLAY, new ReactionPlayPhase(phaseManager, keyHandler));
	PhaseManager.phases.put(BattlePhases.REACTION_RESULT, new ReactionResultPhase(phaseManager, keyHandler));
	PhaseManager.phases.put(BattlePhases.BATTLE_WIN, new BattleWinState(phaseManager, keyHandler, stateManager, flagManager));
	PhaseManager.phases.put(BattlePhases.BATTLE_LOSE, new BattleLoseState(phaseManager, keyHandler, stateManager, flagManager));
    }

    public PhaseManager getPhaseManager() {
        return phaseManager;
    }

    @Override
    public void update() {
        phaseManager.update();
        battle.getEventManager().update();
    }

    @Override
    public void draw(Graphics2D g2) {
	phaseManager.draw(g2);
	battle.getEventManager().draw(g2);
    }

    @Override
    public void onEnter(GameStates prevState) {
        int battleNumber = flagManager.getFlag("BATTLE_NUMBER");
        if(battleNumber <= 0 || battleNumber >= 13) battleNumber = 1;

        reactionAvailabilityManager.unlockBattle(battleNumber);
        BattleData battleData = battleDataManager.getBattleData(battleNumber);
        this.battle = new Battle(playerDeckManager, battleData, stateManager);
        this.phaseManager = new PhaseManager(battle);
        this.phaseManager.setPhase(BattlePhases.BATTLE_START);

        SoundManager.getMusicplayer().play("BattleGymLeader");

        initializePhases();
    }

    @Override
    public void keyPressed() {

    }

    @Override
    public void keyReleased(Keys key) {

    }

    @Override
    public void onExit(GameStates nextState) {

    }

    @Override
    public void keyTapped() {
        phaseManager.keyTapped();

        BattleEvent currentEvent = battle.getEventManager().currentEvent();
        boolean isDialogueEvent = currentEvent instanceof DialogueBattleEvent;

        if (!isDialogueEvent)
            return;

        DialogueBattleEvent dialogueEvent = (DialogueBattleEvent) currentEvent;
        DialogueRenderer renderer = dialogueEvent.getDialogueRenderer();
        Dialogue dialogue = dialogueEvent.getDialogue();

        if (!renderer.isAnimationFinished()) return;

        if (keyHandler.getCurrentKey() == Keys.INTERACT) {
            if (dialogue.isFinalPage()) {
                currentEvent.end();
            } else {
                dialogue.nextPage();
                renderer.setRenderingDialogue(dialogue);
            }
        }
    }

}
