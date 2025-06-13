package battle.battlephases;

import battle.Battle;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

public class PhaseManager {

    private BattlePhases currentPhase;
    private final Battle battle;
    public static final Map<BattlePhases, BattlePhase> phases = new HashMap<>();

    public PhaseManager(Battle battle) {
        this.battle = battle;
        setPhase(BattlePhases.BATTLE_START);
    }

    public BattlePhases getPhase() {
        return currentPhase;
    }

    public Battle getBattle() {
        return battle;
    }

    public final void setPhase(BattlePhases phase) {
        if(currentPhase != null && currentPhase != phase) {
            phases.get(currentPhase).onExit();
        }
        currentPhase = phase;
        battle.setCurrentPhase(phase);
        if(phases.get(currentPhase) != null) {
            phases.get(currentPhase).onEnter();
        }
    }

    public void nextPhase() {
        setPhase(currentPhase.next());
    }

    public void update() {
        if(phases.get(currentPhase) != null) {
            phases.get(currentPhase).update();
            battle.getEventManager().update();
        }
    }

    public void draw(Graphics2D g2) {
        if(phases.get(currentPhase) != null) {
            phases.get(currentPhase).draw(g2);
        }
    }

    public void keyTapped() {
        if(phases.get(currentPhase) != null) {
            phases.get(currentPhase).keyTapped();
        }
    }
}
