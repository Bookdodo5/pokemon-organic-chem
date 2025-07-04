package cutscene.template;

import cutscene.Cutscene;
import cutscene.CutsceneAction;
import cutscene.CutsceneBuilder;
import cutscene.cutsceneAction.DialogueAction;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import gamestates.FlagManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class NumericInputTemplate extends CutsceneTemplate {

    public static void addNumericInput(Map<String, List<Cutscene>> cutscenes, String mapName, int x, int y, List<Integer> correctCode, String successFlag, String[] requiredFlags, String[] forbiddenFlags) {
        String key = getKeyLook(x, y, mapName);

        List<DialogueOption> digits = new ArrayList<>();

        IntStream.rangeClosed(0, 9).forEach(digit -> {
            digits.add(new DialogueOption(
                String.valueOf(digit),
                new Dialogue("You inputted " + digit + "...", "THINKING"),
                () -> processDigitInput(digit, correctCode))
            );
        });

        CutsceneAction[] inputActions = new CutsceneAction[correctCode.size()];
        for(int i = 0; i < correctCode.size(); i++) {
            final int digitIndex = i;
            inputActions[i] = new DialogueAction(new Dialogue(
                "The " + getOrdinalString(digitIndex + 1) + " digit is...",
                "THINKING",
                digits.toArray(DialogueOption[]::new)
            ));
        }

        Cutscene numericInputCutscene = new CutsceneBuilder()
            .require(requiredFlags)
            .forbid(forbiddenFlags)
            .speak("THINKING",
                "It's locked...",
                "I need to input a " + correctCode.size() + " digits code to access it..."
            )
            .actions(inputActions)
            .condition(() -> isNumericInputSuccessful(), createSuccessCutscene(successFlag))
            .condition(() -> isNumericInputFailed(), createFailureCutscene())
            .buildCutscene();
        
        addCutscene(cutscenes, numericInputCutscene, key);
    }
    
    private static String getOrdinalString(int number) {
        if(number % 10 == 1 && number != 11) {
            return number + "st";
        } else if(number % 10 == 2 && number != 12) {
            return number + "nd";
        } else if(number % 10 == 3 && number != 13) {
            return number + "rd";
        } else {
            return number + "th";
        }
    }

    private static void processDigitInput(int digit, List<Integer> correctCode) {
        FlagManager flagManager = FlagManager.getInstance();
        int currentIndex = flagManager.getFlag("NUMERIC_INPUT_CURRENT");
        
        flagManager.setFlag("NUMERIC_INPUT_DIGIT_" + currentIndex, digit);
        flagManager.setFlag("NUMERIC_INPUT_CURRENT", currentIndex + 1);
        
        if(currentIndex + 1 >= correctCode.size()) {
            boolean isCorrect = true;
            for(int i = 0; i < correctCode.size(); i++) {
                int inputDigit = flagManager.getFlag("NUMERIC_INPUT_DIGIT_" + i);
                if(inputDigit != correctCode.get(i)) {
                    isCorrect = false;
                    break;
                }
            }
            
            if(isCorrect) {
                flagManager.addFlag("NUMERIC_INPUT_SUCCESS");
            } else {
                flagManager.addFlag("NUMERIC_INPUT_FAILED");
            }
        }
    }

    public static void resetNumericInput() {
        FlagManager flagManager = FlagManager.getInstance();
        int currentLength = flagManager.getFlag("NUMERIC_INPUT_CURRENT");
        
        for(int i = 0; i < currentLength; i++) {
            flagManager.removeFlag("NUMERIC_INPUT_DIGIT_" + i);
        }
        
        flagManager.removeFlag("NUMERIC_INPUT_CURRENT");
        flagManager.removeFlag("NUMERIC_INPUT_SUCCESS");
        flagManager.removeFlag("NUMERIC_INPUT_FAILED");
    }

    public static boolean isNumericInputSuccessful() {
        return FlagManager.getInstance().hasFlag("NUMERIC_INPUT_SUCCESS");
    }

    public static boolean isNumericInputFailed() {
        return FlagManager.getInstance().hasFlag("NUMERIC_INPUT_FAILED");
    }

    public static int getCurrentInputLength() {
        return FlagManager.getInstance().getFlag("NUMERIC_INPUT_CURRENT");
    }

    public static CutsceneAction[] createSuccessCutscene(String successFlag) {
        return new CutsceneBuilder()
            .require("NUMERIC_INPUT_SUCCESS")
            .speak("THINKING", "...", "The code was correct!", "YESS!!")
            .sfx("PkmnGet")
            .wait(120)
            .execute(() -> resetNumericInput())
            .setFlag(successFlag)
            .buildActions();
    }

    public static CutsceneAction[] createFailureCutscene() {
        return new CutsceneBuilder()
            .require("NUMERIC_INPUT_FAILED")
            .speak("THINKING", "...", "The code was incorrect. Try again.", "NOOOO!!")
            .sfx("PkmnFaint")
            .execute(() -> resetNumericInput())
            .buildActions();
    }
}
