package battle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EffectApplicable {

    private final Map<String, Integer> effectsInPlay;
    private final Map<String, Integer> effectsTimestamp;
    private int timer;

    public EffectApplicable() {
        effectsInPlay = new HashMap<>();
        effectsTimestamp = new HashMap<>();
        timer = 0;
    }

    public void addEffect(String effect, int duration) {
        int currentDuration = effectsInPlay.getOrDefault(effect, 0);
        effectsInPlay.put(effect, currentDuration + duration);
        effectsTimestamp.put(effect, ++timer);
        for(String effectString : effectsInPlay.keySet()) {
            System.out.println(effectString + " " + effectsInPlay.get(effectString));
        }
    }

    public void setEffect(String effect, int duration) {
        effectsInPlay.put(effect, duration);
        effectsTimestamp.put(effect, ++timer);
        for(String effectString : effectsInPlay.keySet()) {
            System.out.println(effectString + " " + effectsInPlay.get(effectString));
        }
    }

    public void triggerTurn() {
        Iterator<Map.Entry<String, Integer>> iterator = effectsInPlay.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            int currentDuration = entry.getValue();
            if(currentDuration > 1) {
                entry.setValue(currentDuration - 1);
            } else {
                effectsTimestamp.remove(entry.getKey());
                iterator.remove();
            }
        }
    }

    public boolean hasEffect(String effect) {
	return effectsInPlay.containsKey(effect);
    }

    public void removeEffect(String effect) {
	    effectsInPlay.remove(effect);
        effectsTimestamp.remove(effect);
    }

    public Map<String, Integer> getEffectsMap() {
	    return effectsInPlay;
    }

    public Set<String> getAllEffects() {
	    return effectsInPlay.keySet();
    }

    public void clearEffect() {
	    effectsInPlay.clear();
        effectsTimestamp.clear();
    }

    public List<String> getEffectsInOrder() {
        System.out.println("--------------------------------");
        for(String effectString : effectsTimestamp.keySet()) {
            System.out.println(effectString + " " + effectsTimestamp.get(effectString));
        }
        System.out.println("--------------------------------");
        return effectsInPlay.keySet().stream()
                .sorted((a, b) -> effectsTimestamp.get(a) - effectsTimestamp.get(b))
                .toList();
    }
}
