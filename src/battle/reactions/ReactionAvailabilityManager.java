package battle.reactions;

import gamestates.FlagManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReactionAvailabilityManager {

    private final FlagManager flagManager;
    private final Map<String, Set<String>> battleReactionMap;
    
    public ReactionAvailabilityManager(FlagManager flagManager) {
        this.flagManager = flagManager;
        battleReactionMap = new HashMap<>();
        initializeBattleReactionMap();
    }
    
    private void initializeBattleReactionMap() {
        battleReactionMap.put("BATTLE_1", Set.of(
            "Radical Halogenation",
            "Ozonolysis"
        ));
        battleReactionMap.put("BATTLE_2", Set.of(
            "Alkene Hydrogenation",
            "Alkene Halogenation"
        ));
        battleReactionMap.put("BATTLE_3", Set.of(
            "Hydrohalogenation",
            "Radical Hydrohalogenation",
            "Acid-Catalyzed Hydration",
            "Oxymecuration",
            "Hydroboration",
            "Halohydrin Formation"
        ));
        battleReactionMap.put("BATTLE_4", Set.of(
            "Sn1 Substitution",
            "Sn2 Substitution",
            "E1 Elimination",
            "E2 Elimination"
        ));
        battleReactionMap.put("BATTLE_5", Set.of(
            "Partial Hydrogenation",
            "Alkyne Hydration",
            "Alkylation of Terminal Alkyne",
            "Red Hot Iron Tube"
        ));
        battleReactionMap.put("BATTLE_6", Set.of(
            "Dihydroxylation",
            "Oxidative Cleavage KMnO4",
            "Allylic-Benzylic Halogenation"
        ));
        battleReactionMap.put("BATTLE_7", Set.of(
            "Williamson Ether Synthesis",
            "Ether Cleavage",
            "Epoxidation",
            "Epoxide Opening"
        ));
        battleReactionMap.put("BATTLE_8", Set.of(
            "Weak Oxidation",
            "Strong Oxidation",
            "Reduction",
            "Cyanohydrin Formation",
            "Grignard Reaction",
            "Wittig Reaction",
            "Clemmensen Reduction"
        ));
        battleReactionMap.put("BATTLE_9", Set.of(
            "Fischer Esterification",
            "Saponification",
            "Transesterification",
            "Hydrolysis",
            "Nucleophilic acyl substitution"
        ));
        battleReactionMap.put("BATTLE_10", Set.of(
            "Hofmann Rearrangement",
            "Hofmann Elimination"
        ));
        battleReactionMap.put("BATTLE_11", Set.of(
            "Aromatic Halogenation",
            "Aromatic Nitration",
            "Aromatic Sulfonation",
            "Friedel-Crafts Alkylation",
            "Friedel-Crafts Acylation",
            "Benzylic Oxidation",
            "Phenol Oxidation",
            "Nitro Reduction"
        ));
    }

    public Set<String> getAvailableReactions() {
        Set<String> availableReactions = new HashSet<>();
        for(String battleNumber : battleReactionMap.keySet()) {
            if(flagManager.matchFlags(new String[] {battleNumber}, new String[] {})) {
                availableReactions.addAll(battleReactionMap.get(battleNumber));
            }
        }
        return availableReactions;
    }

    public boolean isAvailable(String reaction) {
        return getAvailableReactions().contains(reaction);
    }

    public void unlockBattle(int battleNumber) {
        flagManager.addFlag("BATTLE_" + String.valueOf(battleNumber));
    }

    public void lockBattle(int battleNumber) {
        flagManager.removeFlag("BATTLE_" + String.valueOf(battleNumber));
    }
}
