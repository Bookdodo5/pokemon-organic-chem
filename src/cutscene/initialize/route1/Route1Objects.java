package cutscene.initialize.route1;

import cutscene.Cutscene;
import cutscene.template.CutsceneTemplate;
import cutscene.template.OverworldItemTemplate;
import entity.Player;
import gamestates.CameraManager;
import java.util.List;
import java.util.Map;
import pokedex.PlayerDeckManager;

public class Route1Objects extends CutsceneTemplate {
    public static void initialize(Map<String, List<Cutscene>> cutscenes, CameraManager cameraManager, Player player, PlayerDeckManager playerDeckManager) {

/*
* -----------------------------------------------------------------------------
* Location: Route 1
* -----------------------------------------------------------------------------
*/

//* Overworld Item

        OverworldItemTemplate.addOverworldItem(
            cutscenes, 8, 57, "route1",
            "Cl2", playerDeckManager
        );

        OverworldItemTemplate.addOverworldItem(
            cutscenes, 11, 27, "route1",
            "Lubricant", playerDeckManager
        );
    }
}
