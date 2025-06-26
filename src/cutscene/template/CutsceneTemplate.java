package cutscene.template;

import entity.FacingDirections;
import entity.NPC;  

public abstract class CutsceneTemplate {

    static String getCutsceneKey(int x, int y, String map, boolean interact, FacingDirections facing) {
        return x + " " + y + " " + map + " " + interact + " " + facing;
    }

    static String getCutsceneKey(NPC npc) {
		if(npc == null) return "null";
		return npc.getId();
	}
}
