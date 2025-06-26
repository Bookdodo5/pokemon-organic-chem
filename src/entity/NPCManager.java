package entity;

import java.util.ArrayList;
import java.util.List;

public class NPCManager {
	private final List<NPC> npcs;
	
	public NPCManager() {
		npcs = new ArrayList<>();

		npcs.add(new NPC.Builder("AdoptedChild", NPCSprites.PSYCHIC)
			.position(10, 3, "porbital_town__house1_f2")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("ProfDecane", NPCSprites.CHANNELER)
			.position(1, 3, "porbital_town__house1_f1")
			.facing(FacingDirections.UP)
			.build()
		);
	}

	public List<NPC> getNPCs() {
		return npcs;
	}

	public NPC getNPC(String id) {
		return npcs.stream().filter(npc -> npc.getId().equals(id)).findFirst().orElse(null);
	}
}
