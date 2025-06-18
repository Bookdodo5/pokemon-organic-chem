package battle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BattleDataManager {

    private static final List<BattleData> battleData = new ArrayList<>();

    private static int id = 0;
    private static List<String> battleSpecificCards = null;
    private static List<String> opponentDeck = null;
    private static String playerMolecule = null;
    private static String opponentMolecule = null;
    private static String targetMolecule = null;
    private static String battleTheme = null;

    private static void resetData() {
        id = 0;
        battleSpecificCards = null;
        opponentDeck = null;
        playerMolecule = null;
        opponentMolecule = null;
        targetMolecule = null;
        battleTheme = null;
    }

    private static void loadBattleData() {
        try (InputStream is = BattleDataManager.class.getResourceAsStream("/data/battles/battles.yaml")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                if (line.startsWith("#")) putBattleData();
                if (line.startsWith("ID:")) {
                    id = Integer.parseInt(line.split(":")[1].trim());
                }
                if (line.startsWith("Battle Specific Cards:")) {
                    battleSpecificCards = new ArrayList<>();
                }
                if (line.startsWith("Opponent Deck:")) {
                    opponentDeck = new ArrayList<>();
                }
                if (line.startsWith("  - ")) {
                    if (battleSpecificCards != null) {
                        battleSpecificCards.add(line.split("-")[1].trim());
                    } else if (opponentDeck != null) {
                        opponentDeck.add(line.split("-")[1].trim());
                    }
                }
                if (line.startsWith("Player Molecule:")) {
                    playerMolecule = line.split(":")[1].trim();
                }
                if (line.startsWith("Opponent Molecule:")) {
                    opponentMolecule = line.split(":")[1].trim();
                }
                if (line.startsWith("Target Molecule:")) {
                    targetMolecule = line.split(":")[1].trim();
                }
                if (line.startsWith("Battle Theme:")) {
                    battleTheme = line.split(":")[1].trim();
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load battle data: " + e.getMessage());
        }
    }

    static {
        loadBattleData();
    }

    private static void putBattleData() {
        if(id == 0 || battleSpecificCards == null || opponentDeck == null || playerMolecule == null || opponentMolecule == null || targetMolecule == null || battleTheme == null) return;
        battleData.add(new BattleData.Builder()
                .id(id)
                .battleSpecificCard(battleSpecificCards)
                .opponentDeck(opponentDeck)
                .playerMolecule(playerMolecule)
                .opponentMolecule(opponentMolecule)
                .targetMolecule(targetMolecule)
                .battleTheme(battleTheme)
                .build());
        resetData();
    }

    public BattleData getBattleData(int id) {
        return battleData.stream()
        .filter(battleDatum -> battleDatum.getId() == id)
        .findFirst()
        .orElse(null);
    }
}