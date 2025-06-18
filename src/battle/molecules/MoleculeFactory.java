package battle.molecules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MoleculeFactory {

	private static final Map<String, Molecule> molecules = new HashMap<>();
	private static String name = null;
	private static String smiles = null;
	private static String formula = null;
	private static int molecularWeight = 0;
	private static Map<String, Integer> atoms = new HashMap<>();

	static {
		loadMolecules();
	}

	private static void resetData() {
		name = null;
		smiles = null;
		formula = null;
		molecularWeight = 0;
		atoms.clear();
	}

	private static void loadMolecules() {
		try (InputStream is = MoleculeFactory.class.getResourceAsStream("/data/molecules/molecules.yaml")) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) continue;
				if(line.startsWith("#")) putMolecule();
				if (line.startsWith("Name:")) name = line.split(":")[1].trim();
				if (line.startsWith("SMILES:")) smiles = line.split(":")[1].trim();
				if (line.startsWith("Formula:")) formula = line.split(":")[1].trim();
				if (line.startsWith("Molecular Weight:")) molecularWeight = Integer.parseInt(line.split(":")[1].trim());
				if (line.startsWith("Atoms:")) atoms = new HashMap<>();
				if (line.startsWith("  ")) {
					String[] parts = line.trim().split(":");
					if(parts.length != 2) continue;
					atoms.put(parts[0].trim(), Integer.valueOf(parts[1].trim()));
				}
			}
			putMolecule();
		} catch (IOException e) {
			System.err.println("Failed to load molecules: " + e.getMessage());
		}
	}

	private static void putMolecule() {
		if(name == null || smiles == null || formula == null || molecularWeight <= 0 || atoms.isEmpty()) return;
		molecules.put(name, new Molecule.Builder()
			.name(name)
			.smiles(smiles)
			.formula(formula)
			.molecularWeight(molecularWeight)
			.atoms(atoms)
			.build());
		resetData();
	}

	public static Molecule create(String name) {
		if(!molecules.containsKey(name)) throw new IllegalArgumentException("Invalid molecule name: " + name);
		return molecules.get(name);
	}
}
