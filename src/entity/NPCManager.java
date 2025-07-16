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
		npcs.add(new NPC.Builder("OldMan1", NPCSprites.OLD_MAN_1)
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
			.position(11, 19, "methanopolis")
			.facing(FacingDirections.DOWN)
			.rect(1, 1)
			.build()
		);

		npcs.add(new NPC.Builder("Chef", NPCSprites.CHEF)
			.position(30, 17, "methanopolis")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("ComputerRepairMan", NPCSprites.ENGINEER)
			.position(29, 40, "methanopolis")
			.facing(FacingDirections.RIGHT)
			.rect(2, 1)
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

		/*
		 * WORKSHOP Interns
		 */

		npcs.add(new NPC.Builder("Intern1_1", NPCSprites.SUNGLASSES)
			.position(10, 8, "methanopolis__workshop1")
			.facing(FacingDirections.RIGHT)
			.build()
		);

		npcs.add(new NPC.Builder("Intern1_2", NPCSprites.SUNGLASSES)
			.position(3, 4, "methanopolis__workshop1")
			.facing(FacingDirections.LEFT)
			.build()
		);

		npcs.add(new NPC.Builder("Intern1_3", NPCSprites.SUNGLASSES)
			.position(17, 3, "methanopolis__workshop1")
			.facing(FacingDirections.RIGHT)
			.build()
		);

		npcs.add(new NPC.Builder("Intern1_4", NPCSprites.SUNGLASSES)
			.position(11, 4, "methanopolis__workshop1")
			.facing(FacingDirections.UP)
			.build()
		);

		npcs.add(new NPC.Builder("Intern2", NPCSprites.SUNGLASSES)
			.position(6, 3, "methanopolis__workshop2")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("Intern3_1", NPCSprites.SUNGLASSES)
			.position(2, 5, "methanopolis__workshop3")
			.facing(FacingDirections.LEFT)
			.build()
		);

		npcs.add(new NPC.Builder("Intern3_2", NPCSprites.SUNGLASSES)
			.position(14, 3, "methanopolis__workshop3")
			.facing(FacingDirections.UP)
			.build()
		);

		npcs.add(new NPC.Builder("OldIntern1", NPCSprites.OLD_MAN_1)
			.position(8, 4, "methanopolis__workshop3")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("OldIntern2", NPCSprites.OLD_MAN_2)
			.position(9, 4, "methanopolis__workshop3")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("Intern4_1", NPCSprites.FAT)
			.position(7, 8, "methanopolis__workshop4")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("Intern4_2", NPCSprites.FAT)
			.position(8, 8, "methanopolis__workshop4")
			.facing(FacingDirections.DOWN)
			.build()
		);

		/*
		 * Townhall
		 */

		npcs.add(new NPC.Builder("PrimeMinister", NPCSprites.CAMPER)
			.position(12, 9, "methanopolis__townhall_f1")
			.facing(FacingDirections.DOWN)
			.rect(3,1)
			.build()
		);

		npcs.add(new NPC.Builder("Maid1", NPCSprites.MAID)
			.position(3, 3, "methanopolis__townhall_f1")
			.facing(FacingDirections.UP)
			.build()
		);

		npcs.add(new NPC.Builder("Maid2", NPCSprites.MAID)
			.position(4, 7, "methanopolis__townhall_f1")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("Maid3", NPCSprites.MAID)
			.position(14, 6, "methanopolis__townhall_f1")
			.facing(FacingDirections.RIGHT)
			.build()
		);

		npcs.add(new NPC.Builder("Maid4", NPCSprites.MAID)
			.position(10, 3, "methanopolis__townhall_f2")
			.facing(FacingDirections.RIGHT)
			.build()
		);

		npcs.add(new NPC.Builder("MaidFake", NPCSprites.MAID)
			.position(7, 5, "methanopolis__townhall_f2")
			.facing(FacingDirections.UP)
			.build()
		);

		/* 
		 * Pokecenter
		*/

		npcs.add(new NPC.Builder("CenterReceptionist", NPCSprites.NURSE)
			.position(6, 4, "methanopolis__pokecenter_f1")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("CenterWorker", NPCSprites.NURSE)
			.position(9, 3, "methanopolis__pokecenter_f1")
			.facing(FacingDirections.DOWN)
			.rect(0, 3)
			.build()
		);

		npcs.add(new NPC.Builder("CenterResearcher", NPCSprites.SUNGLASSES)
			.position(7, 3, "methanopolis__pokecenter_f2")
			.facing(FacingDirections.UP)
			.build()
		);

		npcs.add(new NPC.Builder("CenterOld_1", NPCSprites.OLD_WOMAN_1)
			.position(2, 5, "methanopolis__pokecenter_f1")
			.facing(FacingDirections.UP)
			.rect(1, 2)
			.build()
		);

		npcs.add(new NPC.Builder("CenterOld_2", NPCSprites.OLD_MAN_2)
			.position(8, 6, "methanopolis__pokecenter_f1")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("CenterOld_3", NPCSprites.OLD_MAN_1)
			.position(11, 9, "methanopolis__pokecenter_f1")
			.facing(FacingDirections.UP)
			.rect(2, 1)
			.build()
		);

		npcs.add(new NPC.Builder("CenterOld_4", NPCSprites.OLD_WOMAN_2)
			.position(1, 4, "methanopolis__pokecenter_f2")
			.facing(FacingDirections.UP)
			.build()
		);

		/* 
		 * Pokemart
		*/

		npcs.add(new NPC.Builder("MartWorker1", NPCSprites.SHOPKEEPER)
			.position(12, 5, "methanopolis__pokemart")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("MartWorker2", NPCSprites.SHOPKEEPER)
			.position(10, 4, "methanopolis__pokemart")
			.facing(FacingDirections.LEFT)
			.build()
		);

		npcs.add(new NPC.Builder("Shopper1", NPCSprites.MAID)
			.position(6, 7, "methanopolis__pokemart")
			.facing(FacingDirections.LEFT)
			.rect(7, 4)
			.build()
		);

		npcs.add(new NPC.Builder("Shopper2", NPCSprites.MAID)
			.position(4, 8, "methanopolis__pokemart")
			.facing(FacingDirections.LEFT)
			.rect(6, 4)
			.build()
		);

		/* 
		 * Aroma house
		 */

		npcs.add(new NPC.Builder("AromaTherapist2", NPCSprites.AROMALADY)
			.position(7, 4, "methanopolis__botanist")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("AromaTherapist3", NPCSprites.AROMALADY)
			.position(8, 10, "methanopolis__botanist")
			.facing(FacingDirections.RIGHT)
			.rect(1, 1)
			.build()
		);

		/*
		 * apartment1 blue
		 */

		npcs.add(new NPC.Builder("BlueApartmentReception", NPCSprites.BLUE)
			.position(4, 3, "methanopolis__apartment1_f1")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("Blue1", NPCSprites.BLUE)
			.position(6, 4, "methanopolis__apartment1_f2")
			.facing(FacingDirections.UP)
			.rect(4, 3)
			.build()
		);

		npcs.add(new NPC.Builder("Blue2", NPCSprites.BLUE)
			.position(12, 6, "methanopolis__apartment1_f2")
			.facing(FacingDirections.RIGHT)
			.rect(2, 2)
			.build()
		);

		npcs.add(new NPC.Builder("Blue3", NPCSprites.BLUE)
			.position(9, 8, "methanopolis__apartment1_f2")
			.facing(FacingDirections.DOWN)
			.rect(8, 1)
			.build()
		);

		npcs.add(new NPC.Builder("Blue4", NPCSprites.BLUE)
			.position(2, 7, "methanopolis__apartment1_f3")
			.facing(FacingDirections.RIGHT)
			.rect(8, 3)
			.build()
		);

		npcs.add(new NPC.Builder("Blue5", NPCSprites.BLUE)
			.position(4, 6, "methanopolis__apartment1_f3")
			.facing(FacingDirections.UP)
			.rect(8, 3)
			.build()
		);

		npcs.add(new NPC.Builder("Blue6", NPCSprites.BLUE)
			.position(6, 7, "methanopolis__apartment1_f3")
			.facing(FacingDirections.DOWN)
			.rect(8, 3)
			.build()
		);

		npcs.add(new NPC.Builder("Blue7", NPCSprites.BLUE)
			.position(8, 6, "methanopolis__apartment1_f3")
			.facing(FacingDirections.RIGHT)
			.rect(8, 3)
			.build()
		);

		npcs.add(new NPC.Builder("Blue8", NPCSprites.BLUE)
			.position(11, 9, "methanopolis__apartment1_f3")
			.facing(FacingDirections.LEFT)
			.build()
		);

		/*
		 * apartment2 yellow
		 */

		npcs.add(new NPC.Builder("YellowApartmentReception", NPCSprites.YELLOW)
			.position(5, 3, "methanopolis__apartment2_f1")
			.facing(FacingDirections.DOWN)
			.build()
		);

		npcs.add(new NPC.Builder("Yellow1", NPCSprites.YELLOW)
			.position(2, 4, "methanopolis__apartment2_f2")
			.facing(FacingDirections.LEFT)
			.build()
		);

		npcs.add(new NPC.Builder("Yellow2", NPCSprites.YELLOW)
			.position(7, 6, "methanopolis__apartment2_f2")
			.facing(FacingDirections.RIGHT)
			.rect(4, 3)
			.build()
		);

		npcs.add(new NPC.Builder("Yellow3", NPCSprites.YELLOW)
			.position(4, 3, "methanopolis__apartment2_f4")
			.facing(FacingDirections.UP)
			.rect(3, 1)
			.build()
		);

		npcs.add(new NPC.Builder("Yellow4", NPCSprites.YELLOW)
			.position(11, 8, "methanopolis__apartment2_f4")
			.facing(FacingDirections.UP)
			.rect(1, 1)
			.build()
		);

		npcs.add(new NPC.Builder("Yellow5", NPCSprites.YELLOW)
			.position(5, 8, "methanopolis__apartment2_f4")
			.facing(FacingDirections.UP)
			.rect(5, 2)
			.build()
		);

		npcs.add(new NPC.Builder("Yellow6", NPCSprites.YELLOW)
			.position(7, 8, "methanopolis__apartment2_f5")
			.facing(FacingDirections.DOWN)
			.rect(6, 2)
			.build()
		);

		npcs.add(new NPC.Builder("Yellow7", NPCSprites.YELLOW)
			.position(9, 6, "methanopolis__apartment2_f5")
			.facing(FacingDirections.RIGHT)
			.rect(2, 2)
			.build()
		);

		npcs.add(new NPC.Builder("Gambler", NPCSprites.OLD_MAN_1)
			.position(1, 3, "methanopolis__apartment2_f3")
			.facing(FacingDirections.UP)
			.build()
		);
	
		/*
		 * house 2
		 */

		npcs.add(new NPC.Builder("House2Person", NPCSprites.MAY)
			.position(3, 7, "methanopolis__house2")
			.facing(FacingDirections.DOWN)
			.build()
		);

		/*
		 * house 1
		 */

		npcs.add(new NPC.Builder("House1Person", NPCSprites.BLACKBELT)
			.position(10, 7, "methanopolis__house1")
			.facing(FacingDirections.DOWN)
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
