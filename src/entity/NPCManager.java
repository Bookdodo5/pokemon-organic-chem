package entity;

import java.util.ArrayList;
import java.util.List;

public class NPCManager {
	private final List<NPC> npcs;
	
	public NPCManager() {
		npcs = new ArrayList<>();
		npcs.add(new NPC("test", 10, 13, 2, "porbitalTown", "trainer_CAMPER", AIMode.WANDER));
	}
	
	public List<NPC> getNPCs() {
		return npcs;
	}

	public NPC getNPC(String id) {
		return npcs.stream().filter(npc -> npc.getId().equals(id)).findFirst().orElse(null);
	}
}
