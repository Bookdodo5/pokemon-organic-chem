package battle.molecules;

import assets.AssetManager;
import battle.reactions.Reaction;
import battle.reactions.ReactionFactory;
import battle.reactions.ReactionRegistry;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import pokedex.ReactionRecord;

public class Molecule {

	private final BufferedImage moleculeImage;
    private final String name;
    private final String smiles;
	private final String formula;
    private final int molecularWeight;
    private final Map<String, Integer> atoms;
    private final Set<String> reactions;
	private final Random random;

	private static ReactionRecord reactionAvailabilityManager;

    public Molecule(String name, String smiles, String formula, int molecularWeight, Map<String, Integer> atoms,
	    Set<String> reactions) {
		this.moleculeImage = AssetManager.loadImage("/molecules/" + smiles + ".png");
		this.name = name;
		this.smiles = smiles;
		this.formula = formula;
		this.molecularWeight = molecularWeight;
		this.atoms = atoms;
		this.reactions = reactions;
		this.random = new Random();
    }

	public static void setReactionAvailabilityManager(ReactionRecord reactionAvailabilityManager) {
		Molecule.reactionAvailabilityManager = reactionAvailabilityManager;
	}

    public static class Builder {
	private String name;
	private String smiles;
	private String formula;
	private int molecularWeight;
	private final Map<String, Integer> atoms;

	public Builder() {
	    this.atoms = new HashMap<>();
	}

	public Builder name(String name) {
	    this.name = name;
	    return this;
	}

	public Builder smiles(String smiles) {
	    this.smiles = smiles;
	    return this;
	}

	public Builder formula(String formula) {
	    this.formula = formula;
	    return this;
	}

	public Builder molecularWeight(int molecularWeight) {
	    this.molecularWeight = molecularWeight;
	    return this;
	}

	public Builder atom(String atom, int count) {
	    this.atoms.put(atom, count);
	    return this;
	}

	public Molecule build() {
	    return new Molecule(name, smiles, formula, molecularWeight, atoms, ReactionRegistry.getReactions(name));
	}
    }

    public String getName() {
	return name;
    }

    public String getSmiles() {
	return smiles;
    }

	public String getFormula() {
		return formula;
	}

    public int getMolecularWeight() {
	return molecularWeight;
    }

    public Map<String, Integer> getAtoms() {
	return atoms;
    }

    public int getAtomCount(String atom) {
	return atoms.getOrDefault(atom, 0);
    }

    public List<Reaction> getReactions() {
		List<String> availableReaction = reactions.stream().filter(
			reaction -> reactionAvailabilityManager.isAvailable(reaction)
		).collect(Collectors.toList());

		List<Reaction> reactionList = new ArrayList<>();
		for(String reactionName : availableReaction) {
			reactionList.add(ReactionFactory.create(reactionName));
		}
		return reactionList;
    }

	public int getReactionCount() {
		return getReactions().size();
	}

	public boolean isPlayedBefore(Molecule molecule) {
		if(molecule.getMolecularWeight() > molecularWeight) {
			return true;
		}
		else if(molecule.getMolecularWeight() < molecularWeight) {
			return false;
		}
		return random.nextBoolean();
	}

	public void draw(Graphics2D g2, int x, int y) {
		if(moleculeImage == null) return;

		int moleculeWidth = moleculeImage.getWidth();
		int moleculeHeight = moleculeImage.getHeight();
		double maxWidth = 200;
		double maxHeight = 110;

		double aspectRatio = (double) moleculeWidth / moleculeHeight;

		if(aspectRatio > maxWidth/maxHeight) {
			moleculeWidth = (int) maxWidth;
			moleculeHeight = (int) (moleculeWidth / aspectRatio);
		}
		else {
			moleculeHeight = (int) maxHeight;
			moleculeWidth = (int) (moleculeHeight * aspectRatio);
		}

		int moleculeX = x - moleculeWidth / 2;
		int moleculeY = y - moleculeHeight;
		g2.drawImage(moleculeImage, moleculeX, moleculeY, moleculeWidth, moleculeHeight, null);
	}
}
