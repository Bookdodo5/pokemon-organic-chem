package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPCManager {
	private final List<NPC> npcs;
	
	public NPCManager() {
		npcs = new ArrayList<>();
		initializePorbitalTownNPCs();
	}

	private void initializePorbitalTownNPCs() {
		npcs.add(new NPC.Builder("Yuuki", NPCSprites.COOL_F)
			.position(10, 3, "porbital_town__house1_f1")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("ProfDecane", NPCSprites.CHANNELER)
			.position(1, 3, "porbital_town__house1_f1")
			.facing(FacingDirections.UP)
			.build()
		);

		npcs.add(new NPC.Builder("ProfCellulose", NPCSprites.SUPER_NERD)
			.position(9, 8, "porbital_town__house2_f1")
			.facing(FacingDirections.LEFT)
			.build()
		);

		npcs.add(new NPC.Builder("Kusari", NPCSprites.COOL_M)
			.position(6, 4, "porbital_town__house2_f2")
			.facing(FacingDirections.LEFT)
			.build()
		);

		npcs.add(new NPC.Builder("MolecularGastronomist", NPCSprites.CHEF)
			.position(16, 11, "porbital_town")
			.facing(FacingDirections.UP)
			.rect(1, 2)
			.build()
		);

		npcs.add(new NPC.Builder("PorbitalCop1", NPCSprites.COP)
			.position(38, 16, "porbital_town")
			.facing(FacingDirections.UP)
			.path(new NPCPath(new ArrayList<>(Arrays.asList(
				new NPCPath.Point(38, 16),
				new NPCPath.Point(38, 20),
				new NPCPath.Point(32, 20),
				new NPCPath.Point(32, 22),
				new NPCPath.Point(24, 22),
				new NPCPath.Point(24, 18),
				new NPCPath.Point(31, 18),
				new NPCPath.Point(31, 16)
			))))
			.build()
		);

		npcs.add(new NPC.Builder("PorbitalCop2", NPCSprites.COP)
			.position(9, 18, "porbital_town")
			.facing(FacingDirections.UP)
			.path(new NPCPath(new ArrayList<>(Arrays.asList(
				new NPCPath.Point(9, 18),
				new NPCPath.Point(9, 23),
				new NPCPath.Point(12, 23),
				new NPCPath.Point(12, 18)
			))))
			.build()
		);

		npcs.add(new NPC.Builder("PorbitalCop3", NPCSprites.COP)
			.position(36, 11, "porbital_town")
			.facing(FacingDirections.UP)
			.rect(1, 1)
			.build()
		);

		npcs.add(new NPC.Builder("Psychic", NPCSprites.PSYCHIC)
			.position(18, 22, "porbital_town")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("ChlorophyllGirl", NPCSprites.GIRL_GREEN)
			.position(24, 16, "porbital_town")
			.facing(FacingDirections.UP)
			.build()
		);

		npcs.add(new NPC.Builder("Director", NPCSprites.DIRECTOR)
			.position(4, 5, "porbital_town__room")
			.facing(FacingDirections.DOWN)
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
