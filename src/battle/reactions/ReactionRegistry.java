package battle.reactions;

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

    static {
	// Battle 1 - Radical Halogenation
        registerReaction("Radical Halogenation", List.of(
            new SubReaction(List.of("Cl2"),
                Map.of("Methane", "Chloromethane")
            )
        ));

        registerReaction("Ozonolysis", List.of(
            new SubReaction(List.of("O3", "H2O2"),
                Map.of("Cyclohexene", "Hexanedioic acid")
            ),
            new SubReaction(List.of("O3", "DMS"),
                Map.of("Cyclohexene", "Hexanedial")
            )
        ));
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
        List<SubReaction> subReactions = getSubReactions(reactionName);
        if(subReactions.isEmpty()) return null;

        SubReaction selected = subReactions.stream()
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
