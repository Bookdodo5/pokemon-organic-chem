package cutscene.template;

import cutscene.Cutscene;
import entity.FacingDirections;
import entity.NPC;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;  

public abstract class CutsceneTemplate {

    public static void addCutscene(Map<String, List<Cutscene>> cutscenes, Cutscene cutscene, String... keys) {
        for(String key : keys) {
            cutscenes.computeIfAbsent(key, k -> new ArrayList<>()).add(cutscene);
        }
    }

    public static String getKeyLocation(int x, int y, String map, boolean interact, FacingDirections facing) {
        return x + " " + y + " " + map + " " + interact + " " + facing;
    }

    public static String getKeyLocation(int x, int y, String map, boolean interact) {
        return getKeyLocation(x, y, map, interact, null);
    }

    public static String getKeyLocation(int x, int y, String map) {
        return getKeyLocation(x, y, map, false);
    }

    public static String getKeyNPC(NPC npc) {
		if(npc == null) return "null";
		return npc.getId();
	}

    public static String getKeyLook(int x, int y, String map) {
        return x + " " + y + " " + map + " looking";
    }
}
