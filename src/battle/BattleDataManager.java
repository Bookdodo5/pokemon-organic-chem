package battle;

import java.util.ArrayList;
import java.util.List;

public class BattleDataManager {

    private final List<BattleData> battleData;

    public BattleDataManager() {
        battleData = new ArrayList<>();
        battleData.add(new BattleData.Builder()
                .id(1)
                .battleSpecificCard("O3", "H2O2", "DMS")
                .opponentDeck("O3", "H2O2", "DMS", "O3", "H2O2", "DMS", "O3", "H2O2", "DMS", "O3", "H2O2", "DMS")
                .playerMolecule("Cyclohexene")
                .opponentMolecule("Cyclohexene")
                .targetMolecule("Hexanedial")
                .battleTheme(BattleThemes.CHAMPION2)
                .build());
        battleData.add(new BattleData.Builder() 
                .id(2)
                .battleSpecificCard("Sunny Day", "Rain Dance")
                .opponentDeck("Sunny Day", "Cl2", "Br2", "Fish", "Cl2", "Br2", "Fish", "Cl2", "Br2", "Fish", "Fish")
                .playerMolecule("H2O")
                .opponentMolecule("H2O")
                .targetMolecule("C2H4")
                .battleTheme(BattleThemes.SNOW)
                .build());
    }

    public BattleData getBattleData(int id) {
        return battleData.stream()
        .filter(battleDatum -> battleDatum.getId() == id)
        .findFirst()
        .orElse(null);
    }

}
