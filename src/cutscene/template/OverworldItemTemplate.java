package cutscene.template;

import battle.cards.CardFactory;
import cutscene.Cutscene;
import cutscene.CutsceneAction;
import cutscene.CutsceneBuilder;
import java.util.List;
import java.util.Map;
import pokedex.PlayerDeckManager;

public class OverworldItemTemplate extends CutsceneTemplate {

    public static CutsceneAction[] getItemAction(String cardName, PlayerDeckManager playerDeckManager) {
        return new CutsceneBuilder()
            .parallel(new CutsceneBuilder()
                .speak("THINKING",
                    "You received a " + cardName.toUpperCase() + "!",
                    "\"" + CardFactory.create(cardName).getFlavorText() + "\"",
                    CardFactory.create(cardName).getEffects(),
                    "YESS!!"
                )
                .sfx("PkmnGet")
                .wait(120)
                .buildActions()
            )
            .execute(() -> playerDeckManager.addToCollection(cardName, 1))
            .buildActions();
    }

    public static void addOverworldItem(Map<String, List<Cutscene>> cutscenes, int x, int y, String mapName, String cardName, PlayerDeckManager playerDeckManager) {
        String itemKey = mapName + "_" + x + "_" + y + "_FOUND";
        addCutscene(cutscenes, new CutsceneBuilder()
            .forbid(itemKey)
            .actions(getItemAction(cardName, playerDeckManager))
            .setFlag(itemKey)
            .buildCutscene(),
            getKeyLook(x, y, mapName)
        );
    }
}
