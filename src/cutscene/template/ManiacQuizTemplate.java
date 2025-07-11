package cutscene.template;

import cutscene.Cutscene;
import cutscene.CutsceneAction;
import cutscene.CutsceneBuilder;
import cutscene.Emotes;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.FacingDirections;
import entity.NPC;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import gamestates.states.OverworldState;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ManiacQuizTemplate extends CutsceneTemplate {

    public static DialogueOption[] getOptions(NPC maniac, int correctIndex, String... options) {
        return IntStream.range(0, options.length).mapToObj(i -> {
            return new DialogueOption(options[i], new Dialogue(new String[] {
                "..."
            }, "MANIAC"),
            ()->FlagManager.getInstance().addFlag(
                maniac.getId() + (i == correctIndex ? "_FINISHED" : "_WRONG")
            ));
        }).toArray(DialogueOption[]::new);
    }

    public static void addManiacQuiz(
        Map<String, List<Cutscene>> cutscenes, NPC maniac, Player player, OverworldState overworldState, CameraManager cameraManager,
        int viewRange, int tpX, int tpY, String tpMap, FacingDirections tpDirection,
        CutsceneAction[] quizActions, CutsceneAction[] rightAction,
        CutsceneAction[] wrongAction, String talkAction
    ) {
        String quizKey = maniac.getId();
        int originalX = maniac.getMapX();
        int originalY = maniac.getMapY();
        String originalMap = maniac.getMap();
        FacingDirections originalDirection = maniac.getCurrentDirection();
        IntStream.rangeClosed(1, viewRange).forEach(i -> {
            int facingX = maniac.getCurrentDirection().getX();
            int facingY = maniac.getCurrentDirection().getY();
            int localX = maniac.getMapX() + i * facingX;
            int localY = maniac.getMapY() + i * facingY;
            addCutscene(cutscenes, new CutsceneBuilder()
                .forbid(quizKey + "_FINISHED")
                .require("DECK_FOUND")
                .react(maniac, cameraManager, Emotes.SURPRISE)
                .wait(30)
                .faceTowards(player, maniac)
                .wait(30)
                .move(maniac, localX - facingX, localY - facingY)
                .wait(30)
                .music("BattleTrainer")
                .actions(quizActions)
                .condition(quizKey + "_FINISHED", new CutsceneBuilder()
                    .actions(rightAction)
                    .buildActions()
                )
                .condition(quizKey + "_WRONG", new CutsceneBuilder()
                    .actions(wrongAction)
                    .fadeIn(40)
                    .tp(player, tpX, tpY, tpMap, overworldState)
                    .tp(maniac, originalX, originalY, originalMap, overworldState)
                    .face(player, tpDirection)
                    .face(maniac, originalDirection)
                    .fadeOut(60)
                    .wait(30)
                    .speak("THINKING",
                        "You fainted and woke up again..."
                    )
                    .removeFlag(quizKey + "_WRONG")
                    .buildActions()
                )
                .buildCutscene(),
                getKeyLocation(localX, localY, maniac.getMap())
            );
        });

        addCutscene(cutscenes, new CutsceneBuilder()
            .require(quizKey + "_FINISHED")
            .faceTowards(maniac, player)
            .speak("MANIAC", talkAction)
            .buildCutscene(),
            getKeyNPC(maniac)
        );
    }
}
