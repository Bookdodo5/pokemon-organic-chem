package battle.molecules;

public class MoleculeFactory {

	public static Molecule create(String name) {
		return switch (name) {
			case "Cyclohexene" -> new Molecule.Builder()
				.name("Cyclohexene")
				.smiles("C1CCC=CC1")
				.formula("C6H10")
				.molecularWeight(82)
				.atom("C", 6)
				.atom("H", 10)
				.build();
			case "Hexanedioic acid" -> new Molecule.Builder()
				.name("Hexanedioic acid")
				.smiles("OC(=O)CCCCC(=O)O")
				.formula("C6H10O4")
				.molecularWeight(146)
				.atom("C", 6)
				.atom("H", 10)
				.atom("O", 4)
				.build();

			case "Hexanedial" -> new Molecule.Builder()
				.name("Hexanedial")
				.smiles("O=CCCCCC=O")
				.formula("C6H10O2")
				.molecularWeight(114)
				.atom("C", 6)
				.atom("H", 10)
				.atom("O", 2)
				.build();

			default -> throw new IllegalArgumentException("Invalid molecule name: " + name);
		};
	}
}
