package gamestates.states;

import cutscene.Cutscene;
import cutscene.CutsceneAction;
import cutscene.CutsceneManager;
import cutscene.InputCutsceneAction;
import entity.Player;
import gamestates.GameState;
import gamestates.GameStates;	
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import main.GameContentManager;

public class CutsceneState extends GameState {

    private final OverworldState overworldState;
    private final Player player;
    private final CutsceneManager cutsceneManager;

    private Cutscene currentCutscene;

    public CutsceneState(StateManager stateManager, KeyBindingHandler keyHandler,
            GameContentManager gameContentManager) {
        super(stateManager, keyHandler, gameContentManager);
        this.player = gameContentManager.getPlayer();
        this.cutsceneManager = gameContentManager.getCutsceneManager();
        this.overworldState = (OverworldState) StateManager.states.get(GameStates.OVERWORLD);
    }

    @Override
    public void update() {
        if (currentCutscene == null) {
            return;
        }

        overworldState.update();
        currentCutscene.update();

        if (currentCutscene.isFinished()) {
            stateManager.setState(GameStates.OVERWORLD);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        overworldState.draw(g2);
        currentCutscene.draw(g2);
    }

    @Override
    public void onEnter(GameStates prevState) {
        player.setAcceptInput(false);
        if (prevState == GameStates.BATTLE) {
            return;
        }

        currentCutscene = overworldState.takePendingCutscene();

        if (currentCutscene != null) {
            currentCutscene.start();
        }
    }

    @Override
    public void keyTapped() {
        CutsceneAction currentAction = currentCutscene.getCurrentAction();
        boolean canAcceptInput = currentAction instanceof InputCutsceneAction;
        if (!canAcceptInput) return;
		
        ((InputCutsceneAction) currentAction).keyTapped(keyHandler);
    }

    @Override
    public void keyPressed() {
        CutsceneAction currentAction = currentCutscene.getCurrentAction();
		boolean canAcceptInput = currentAction instanceof InputCutsceneAction;
        if (!canAcceptInput) return;

        ((InputCutsceneAction) currentAction).keyPressed(keyHandler);
    }

    @Override
    public void keyReleased(Keys key) {
		CutsceneAction currentAction = currentCutscene.getCurrentAction();
		boolean canAcceptInput = currentAction instanceof InputCutsceneAction;
        if (!canAcceptInput) return;

        ((InputCutsceneAction) currentAction).keyReleased(key);
    }

    @Override
    public void onExit(GameStates nextState) {
        player.setAcceptInput(true);
        if (nextState == GameStates.OVERWORLD) {
            cutsceneManager.restoreNPCAIMode();
            currentCutscene.reset();
            currentCutscene = null;
        }
    }

}
