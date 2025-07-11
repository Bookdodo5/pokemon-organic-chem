package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPCManager {
	private final List<NPC> npcs;
	
	public NPCManager() {
		npcs = new ArrayList<>();
		initializePorbitalTownNPCs();
		initializeRoute1NPCs();
		initializeMethanopolisNPCs();
	}

	private void initializeMethanopolisNPCs() {
		npcs.add(new NPC.Builder("OldMan1", NPCSprites.OLD_MAN)
			.position(22, 52, "methanopolis")
			.facing(FacingDirections.LEFT)
			.path(new NPCPath(new ArrayList<>(Arrays.asList(
				new NPCPath.Point(22, 52),
				new NPCPath.Point(13, 52),
				new NPCPath.Point(13, 46),
				new NPCPath.Point(22, 46)
			))))
			.build()
		);

		npcs.add(new NPC.Builder("OldMan2", NPCSprites.OLD_MAN_2)
			.position(22, 46, "methanopolis")
			.facing(FacingDirections.DOWN)
			.path(new NPCPath(new ArrayList<>(Arrays.asList(
				new NPCPath.Point(22, 46),
				new NPCPath.Point(22, 52),
				new NPCPath.Point(13, 52),
				new NPCPath.Point(13, 46)
			))))
			.build()
		);

		npcs.add(new NPC.Builder("OldWoman1", NPCSprites.OLD_WOMAN_1)
			.position(34, 17, "methanopolis")
			.facing(FacingDirections.DOWN)
			.rect(5, 5)
			.build()
		);

		npcs.add(new NPC.Builder("OldWoman2", NPCSprites.OLD_WOMAN_2)
			.position(42, 28, "methanopolis")
			.facing(FacingDirections.LEFT)
			.rect(5, 5)
			.build()
		);

		npcs.add(new NPC.Builder("LazyCop", NPCSprites.COP)
			.position(29, 47, "methanopolis")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("ServiceSeller", NPCSprites.SHOPKEEPER)
			.position(15, 44, "methanopolis")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("DirtSeller", NPCSprites.SHOPKEEPER)
			.position(40, 16, "methanopolis")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("AirconRepairMan", NPCSprites.ENGINEER)
			.position(29, 40, "methanopolis")
			.facing(FacingDirections.DOWN)
			.rect(2, 1)
			.build()
		);

		npcs.add(new NPC.Builder("Chef", NPCSprites.CHEF)
			.position(30, 17, "methanopolis")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("ComputerRepairMan", NPCSprites.ENGINEER)
			.position(11, 19, "methanopolis")
			.facing(FacingDirections.RIGHT)
			.rect(1, 1)
			.build()
		);

		npcs.add(new NPC.Builder("PrimeMinisterPsychic1", NPCSprites.PSYCHIC)
			.position(14, 24, "methanopolis")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("PrimeMinisterPsychic2", NPCSprites.PSYCHIC)
			.position(18, 24, "methanopolis")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("Aromatherapist", NPCSprites.AROMALADY)
			.position(28, 28, "methanopolis")
			.facing(FacingDirections.RIGHT)
			.rect(2, 0)
			.build()
		);
	}

	private void initializeRoute1NPCs() {
		npcs.add(new NPC.Builder("Maniac1", NPCSprites.MANIAC)
			.position(11, 50, "route1")
			.facing(FacingDirections.LEFT)
			.build()
		);

		npcs.add(new NPC.Builder("Maniac2", NPCSprites.MANIAC)
			.position(18, 45, "route1") 
			.facing(FacingDirections.LEFT)
			.build()
		);

		npcs.add(new NPC.Builder("Maniac3", NPCSprites.MANIAC)
			.position(13, 23, "route1")
			.facing(FacingDirections.LEFT)
			.build()
		);

		npcs.add(new NPC.Builder("Maniac4", NPCSprites.MANIAC)
			.position(16, 17, "route1")
			.facing(FacingDirections.RIGHT)
			.build()
		);

		npcs.add(new NPC.Builder("Maniac5", NPCSprites.MANIAC)
			.position(10, 8, "route1")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("Maniac6", NPCSprites.MANIAC)
			.position(11, 8, "route1")
			.facing(FacingDirections.DOWN)
			.build()
		);
		
		npcs.add(new NPC.Builder("Maniac7", NPCSprites.MANIAC)
			.position(13, 44, "route1")
			.facing(FacingDirections.UP)
			.build()
		);
		
		npcs.add(new NPC.Builder("Maniac8", NPCSprites.MANIAC)
			.position(12, 5, "route1")
			.facing(FacingDirections.RIGHT)
			.build()
		);
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

		npcs.add(new NPC.Builder("Chlorophyll", NPCSprites.GIRL_GREEN)
			.position(24, 16, "porbital_town")
			.facing(FacingDirections.UP)
			.build()
		);

		npcs.add(new NPC.Builder("Director", NPCSprites.DIRECTOR)
			.position(4, 5, "porbital_town__room")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("PorbitalMayor", NPCSprites.MASKED)
			.position(7, 6, "porbital_town__townhall")
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
