package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import gamestates.FlagManager;
import java.awt.Graphics2D;

public class SetFlagAction implements CutsceneAction {

    private final String[] flags;
    private boolean isFinished;
    private final FlagManager flagManager;
    
    public SetFlagAction(FlagManager flagManager, String... flags) {
        this.flags = flags;
        this.flagManager = flagManager;
        this.isFinished = false;
    }

    @Override
    public void start() {
        for (String flag : flags) {
            flagManager.addFlag(flag);
        }
	    isFinished = true;
    }

    @Override
    public void update() {
	
    }

    @Override
    public void end() {
	
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
