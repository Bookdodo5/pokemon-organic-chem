package cutscene.template;

import cutscene.Cutscene;
import cutscene.cutsceneAction.DialogueAction;
import cutscene.cutsceneAction.PlaysoundAction;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.FacingDirections;
import gamestates.states.OverworldState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ElevatorTemplate extends CutsceneTemplate {

    public static void addElevator(OverworldState overworldState, Map<String, List<Cutscene>> cutscenes, String mapName, String buildingName, int x,
            int y, int floors) {

        String buildingPath = mapName + "__" + buildingName + "_f";

        IntStream.rangeClosed(1, floors).forEach(currentFloor -> {
            String floorPath = buildingPath + currentFloor;
            String key = getKeyLocation(x, y, floorPath, true, FacingDirections.UP);

            List<DialogueOption> optionsList = new ArrayList<>();
            
            IntStream.rangeClosed(1, floors)
                .filter(targetFloor -> targetFloor != currentFloor)
                .mapToObj(targetFloor -> new DialogueOption(getFloorLabel(targetFloor),
                        () -> overworldState.transitionToMap(x, y, buildingPath + targetFloor)))
                .forEach(optionsList::add);

            optionsList.add(new DialogueOption(
                "Goodbye", "Come back anytime!"
            ));

            Cutscene cutscene = new Cutscene(
                new String[] {}, new String[] {},
                new PlaysoundAction("GameCursor", false),
                new DialogueAction(
                    new Dialogue(
                        "Which floor do you want to go to?",
                        optionsList.toArray(DialogueOption[]::new)
                    )
                )
            );

            addCutscene(cutscenes, cutscene, key);
        });
    }

    private static String getFloorLabel(int floor) {
        return switch (floor) {
        case 1 -> "1st floor";
        case 2 -> "2nd floor";
        case 3 -> "3rd floor";
        default -> floor + "th floor";
        };
    }
}
