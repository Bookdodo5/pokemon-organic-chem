package battle.event;

import java.awt.Graphics2D;

public abstract class BattleEvent {
    
    protected boolean isFinished = false;
    
    protected abstract void start();
    protected abstract void update();
    protected abstract void draw(Graphics2D g2);
    
    public void end() {
	    isFinished = true;
    }
    
    protected boolean isFinished() {
	return isFinished;
    }
}
