package battle.reactions;

import battle.conditions.Light;
import battle.conditions.Solvent;
import battle.conditions.Temperature;
import battle.conditions.pH;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ReactionFactory {

	private static final Map<String, Reaction> reactions = new HashMap<>();
	private static String name = null;
    private static int cost = 0;
	private static String description = null;
	private static Light requiredLight = null;
    private static pH requiredPH = null;
    private static Solvent requiredSolvent = null;
    private static Temperature requiredTemperature = null;
	private static String animationPath = null;

	static {
		loadReactions();
	}

    private static void resetData() {
        name = null;
        cost = 0;
        description = null;
        requiredLight = null;
        requiredPH = null;
        requiredSolvent = null;
        requiredTemperature = null;
        animationPath = null;
    }

	private static void loadReactions() {
		try (InputStream is = ReactionFactory.class.getResourceAsStream("/data/reactions/reactions.yaml")) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) continue;
				if(line.startsWith("#")) putReaction();
				if (line.startsWith("Name:")) {
                    name = line.split(":")[1].trim();
                }
				if (line.startsWith("Cost:")) {
                    cost = Integer.parseInt(line.split(":")[1].trim());
                }
				if (line.startsWith("Description:")) {
                    description = line.split(":")[1].trim();
                }
				if (line.startsWith("RequiredLight:")) {
                    requiredLight = Light.valueOf(line.split(":")[1].trim());
                }
				if (line.startsWith("RequiredPH:")) {
                    requiredPH = pH.valueOf(line.split(":")[1].trim());
                }
				if (line.startsWith("RequiredSolvent:")) {
                    requiredSolvent = Solvent.valueOf(line.split(":")[1].trim());
                }
				if (line.startsWith("RequiredTemperature:")) {
                    requiredTemperature = Temperature.valueOf(line.split(":")[1].trim());
                }
				if (line.startsWith("AnimationPath:")) {
                    animationPath = line.split(":")[1].trim();
                }
			}
			putReaction();
		} catch (IOException e) {
			System.err.println("Failed to load reactions: " + e.getMessage());
		}
	}

	private static void putReaction() {
		if(name == null || cost < 0 || description == null || animationPath == null) return;
		reactions.put(name, new Reaction.Builder()
			.name(name)
			.cost(cost)
			.description(description)
			.requiredLight(requiredLight)
			.requiredpH(requiredPH)
			.requiredSolvent(requiredSolvent)
			.requiredTemp(requiredTemperature)
			.animationPath(animationPath)
			.build());
		resetData();
	}

	public static Reaction create(String name) {
		if(!reactions.containsKey(name)) throw new IllegalArgumentException("Invalid reaction name: " + name);
		return reactions.get(name);
	}
}
