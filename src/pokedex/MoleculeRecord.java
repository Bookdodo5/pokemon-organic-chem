package pokedex;

import battle.molecules.Molecule;
import battle.molecules.MoleculeFactory;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MoleculeRecord {

    private final Set<Molecule> moleculeRecord;

    public MoleculeRecord() {
        this.moleculeRecord = new HashSet<>();
        addMolecule(MoleculeFactory.create("Hexanedial"));
        addMolecule(MoleculeFactory.create("Hexanedioic acid"));
        addMolecule(MoleculeFactory.create("Cyclohexene"));
    }

    public final void addMolecule(Molecule molecule) {
        if(moleculeRecord.contains(molecule)) return;
        moleculeRecord.add(molecule);
    }

    public final List<Molecule> getMoleculeRecord() {
        return new ArrayList<>(moleculeRecord).stream()
            .sorted(Comparator
                .comparing(Molecule::getMolecularWeight)
                .thenComparing(Molecule::getName))
            .toList();
    }
}
