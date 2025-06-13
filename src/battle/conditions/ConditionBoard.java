package battle.conditions;

import battle.EffectApplicable;
import java.util.Map;

public class ConditionBoard extends EffectApplicable {

    private Condition currentCondition;

    public ConditionBoard() {
        resetBoard();
    }

    public final void resetBoard() {
        clearEffect();
        currentCondition = new Condition(pH.STRONG_ACID, Temperature.ROOM, Solvent.POLAR_APROTIC, Light.LIGHT);
    }

    public void setCondition(Condition condition) {
        this.currentCondition = condition;
    }

    public Condition getCondition() {
        return currentCondition;
    }

    public String getConditionsSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("\npH: ").append(currentCondition.getpH())
                .append("\nTemperature: ").append(currentCondition.getTemp())
                .append("\nSolvent: ").append(currentCondition.getSolvent())
                .append("\nLight: ").append(currentCondition.getLight());

        if (!getEffectsMap().isEmpty()) {
            summary.append("\nEffects: ");
            for (Map.Entry<String, Integer> entry : getEffectsMap().entrySet()) {
                summary.append("\n").append(entry.getKey()).append(": ").append(entry.getValue());
            }
        }

        return summary.toString();
    }

    public void printStatus() {
        // Board status printing removed from console output
    }
}
