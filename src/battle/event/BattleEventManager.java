package battle.event;

import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.Deque;

public class BattleEventManager {
    
    private final Deque<BattleEvent> eventQueue;
    private BattleEvent currentEvent;

    public BattleEventManager() {
	eventQueue = new ArrayDeque<>();
    }
    
    public void update() {
        if(currentEvent == null) {
            nextEvent();
        }
        if(currentEvent != null) {
            currentEvent.update();
            if(currentEvent.isFinished()) nextEvent();
        }
    }
    
    public void draw(Graphics2D g2) {
        if(currentEvent != null) {
            currentEvent.draw(g2);
        }
    }
    
    public BattleEvent currentEvent() {
	return currentEvent;
    }
    
    public void nextEvent() {
        if(eventQueue.isEmpty()) {
            currentEvent = null;
            return;
        }
        currentEvent = eventQueue.poll();
        currentEvent.start();
    }
    
    public boolean isFinished() {
	    return eventQueue.isEmpty() && (currentEvent == null || currentEvent.isFinished());
    }
    
    public void addEvent(BattleEvent event) {
	    eventQueue.add(event);
    }

    public void insertEvent(BattleEvent event) {
        eventQueue.addFirst(event);
    }
}
