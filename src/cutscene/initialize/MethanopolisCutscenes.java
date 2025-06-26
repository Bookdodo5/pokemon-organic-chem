package cutscene.initialize;

import cutscene.Cutscene;
import cutscene.template.ElevatorTemplate;
import gamestates.states.OverworldState;
import java.util.List;
import java.util.Map;

public class MethanopolisCutscenes {
    public static void initialize(Map<String, List<Cutscene>> cutscenes, OverworldState overworldState  ) {
        ElevatorTemplate.addElevator(cutscenes, "methanopolis", "apartment1", 11, 3, 3, overworldState);
        ElevatorTemplate.addElevator(cutscenes, "methanopolis", "apartment2", 11, 3, 5, overworldState);
    }
}
