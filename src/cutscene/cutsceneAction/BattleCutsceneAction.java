package cutscene.cutsceneAction;

import assets.SoundManager;
import cutscene.CutsceneAction;
import gamestates.FlagManager;
import gamestates.GameStates;
import gamestates.StateManager;
import java.awt.Graphics2D;

public class BattleCutsceneAction implements CutsceneAction {

    private final StateManager stateManager;
    private final FlagManager flagManager;
    private boolean isFinished;
    private final int battleID;

    public BattleCutsceneAction(StateManager stateManager, FlagManager flagManager, int battleID) {
        this.stateManager = stateManager;
        this.isFinished = false;
        this.flagManager = flagManager;
        this.battleID = battleID;
    }

    @Override
    public void start() {
        String currentTrack = SoundManager.getMusicplayer().getCurrentTrackName();
        flagManager.setFlag("BATTLE_NUMBER", battleID);
        stateManager.transitionToState(GameStates.BATTLE,
            true,
            currentTrack,
            () -> {
        });
    }

    @Override
    public void update() {
        boolean win = flagManager.hasFlag("BATTLE_WIN");
        boolean lose = flagManager.hasFlag("BATTLE_LOSE");
        if(win || lose) end();
        else stateManager.setState(GameStates.BATTLE);
    }

    @Override
    public void end() {
        isFinished = true;
    }

    @Override
    public void reset() {
        isFinished = false;
    }

    @Override
    public void draw(Graphics2D g2) {
    }

    @Override
    public boolean isFinished() {
	    return isFinished;
    }

}
