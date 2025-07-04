package cutscene.template;

import battle.cards.CardFactory;
import cutscene.Cutscene;
import cutscene.CutsceneBuilder;
import java.util.List;
import java.util.Map;
import pokedex.PlayerDeckManager;

public class OverworldItemTemplate extends CutsceneTemplate {

    public static void addOverworldItem(Map<String, List<Cutscene>> cutscenes, int x, int y, String mapName, String cardName, PlayerDeckManager playerDeckManager) {
        String itemKey = mapName + "_" + x + "_" + y + "_FOUND";
        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid(itemKey)
            .parallel(new CutsceneBuilder()
                .speak("THINKING",
                    "You found a " + cardName.toUpperCase() + "!",
                    "\"" + CardFactory.create(cardName).getFlavorText() + "\"",
                    CardFactory.create(cardName).getEffects(),
                    "YESS!!"
                )
                .sfx("PkmnGet")
                .buildActions()
            )
            .wait(120)
            .execute(() -> playerDeckManager.addToCollection(cardName, 1))
            .setFlag(itemKey)
            .buildCutscene(),
            getKeyLook(x, y, mapName)
        );
    }
}
