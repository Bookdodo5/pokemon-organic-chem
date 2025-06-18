package battle.reactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ReactionRegistry {

    private static class SubReaction {
        public final List<String> requiredReagents;
        public final Map<String, String> reactantProductsMap;

        public SubReaction(List<String> requiredReagents, Map<String, String> reactantProductsMap) {
            this.requiredReagents = requiredReagents;
            this.reactantProductsMap = reactantProductsMap;
        }
    }
    
    private static final Map<String, Set<String>> molecule2Reaction = new HashMap<>();
    private static final Map<String, List<SubReaction>> reaction2Map = new HashMap<>();

    private static String name = null;
    private static List<SubReaction> subReactions = null;

    private static void resetData() {
        name = null;
        subReactions = null;
    }

    private static void loadRegistry() {
        try (InputStream is = ReactionRegistry.class.getResourceAsStream("/data/reactions/reactionRegistry.yaml")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().isEmpty()) continue;
				if(line.startsWith("#")) putRegistry();
				if (line.startsWith("Name:")) {
                    name = line.split(":")[1].trim();
                }
				if (line.startsWith("Sub Reactions:")) {
                    subReactions = new ArrayList<>();
                }
                if (line.startsWith("  - Required Reagents:")) {
                    subReactions.add(new SubReaction(new ArrayList<>(), new HashMap<>()));
                }
                if (line.startsWith("    - ")) { // required reagents
                    subReactions.get(subReactions.size() - 1)
                        .requiredReagents.add(line.split("-")[1].trim());
                }
                if (line.startsWith("      ")) { // reactant products
                    subReactions.get(subReactions.size() - 1)
                        .reactantProductsMap.put(line.split(":")[0].trim(), line.split(":")[1].trim());
                }
			}
			putRegistry();
        } catch (IOException e) {
            System.err.println("Failed to load reaction registry: " + e.getMessage());
        }
    }

    private static void putRegistry() {
        if(name == null || subReactions == null) return;
        registerReaction(name, subReactions);
        resetData();
    }

    static {
        loadRegistry();
    }
    
    private static void registerReaction(String reactionName, List<SubReaction> subReactions) {
        reaction2Map.put(reactionName, subReactions);
        for (SubReaction subReaction : subReactions) {
            for (String molecule : subReaction.reactantProductsMap.keySet()) {
                Set<String> set = molecule2Reaction.getOrDefault(molecule, new HashSet<>());
                set.add(reactionName);
                molecule2Reaction.put(molecule, set);
            }
        }
    }
    
    public static Set<String> getReactions(String moleculeName) {
        return molecule2Reaction.getOrDefault(moleculeName, Collections.emptySet());
    }
    
    private static List<SubReaction> getSubReactions(String reactionName) {
        return reaction2Map.getOrDefault(reactionName, Collections.emptyList());
    }
    
    public static String getProduct(String reactionName, String moleculeName, List<String> reagents) {
        List<SubReaction> allSubReactions = getSubReactions(reactionName);
        if(allSubReactions.isEmpty()) return null;

        SubReaction selected = allSubReactions.stream()
            .filter(subReaction -> subReaction.requiredReagents.stream()
                .allMatch(reagents::contains))
            .max((a, b) -> compare(getIndexList(a, reagents), getIndexList(b, reagents)))
            .orElse(null);

        if(selected == null) return null;
        return selected.reactantProductsMap.get(moleculeName);
    }

    private static List<Integer> getIndexList(SubReaction subReaction, List<String> reagents) {
        return subReaction.requiredReagents.stream()
            .map(requiredReagent -> reagents.indexOf(requiredReagent))
            .sorted(Collections.reverseOrder())
            .toList();
    }

    private static int compare(List<Integer> listA, List<Integer> listB) {
        int minSize = Math.min(listA.size(), listB.size());
        for(int i = 0; i < minSize; i++) {
            int comp = listA.get(i).compareTo(listB.get(i));
            if(comp != 0) return comp;
        }
        return Integer.compare(listA.size(), listB.size());
    }

}
