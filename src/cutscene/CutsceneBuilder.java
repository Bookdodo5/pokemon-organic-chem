package cutscene;

import cutscene.cutsceneAction.*;
import dialogue.Dialogue;
import entity.FacingDirections;
import entity.Human;
import gamestates.CameraManager;
import gamestates.FlagManager;
import gamestates.StateManager;
import gamestates.states.OverworldState;
import java.util.ArrayList;
import java.util.List;

public class CutsceneBuilder {
    private final List<CutsceneAction> actions = new ArrayList<>();
    private final List<String> yesFlags = new ArrayList<>();
    private final List<String> noFlags = new ArrayList<>();

    public CutsceneBuilder require(String flag) {
        yesFlags.add(flag);
        return this;
    }

    public CutsceneBuilder forbid(String flag) {
        noFlags.add(flag);
        return this;
    }
    
    // ============================================================================
    // BASIC TIMING ACTIONS
    // ============================================================================
    
    public CutsceneBuilder wait(int frames) {
        actions.add(new WaitAction(frames));
        return this;
    }
    
    public CutsceneBuilder waitForInput() {
        actions.add(new WaitForInputAction());
        return this;
    }
    
    // ============================================================================
    // AUDIO ACTIONS
    // ============================================================================
    
    public CutsceneBuilder sfx(String soundName) {
        actions.add(new PlaysoundAction(soundName, false));
        return this;
    }
    
    public CutsceneBuilder music(String musicName) {
        actions.add(new PlaysoundAction(musicName, true));
        return this;
    }
    
    // ============================================================================
    // CHARACTER MOVEMENT ACTIONS
    // ============================================================================
    
    public CutsceneBuilder move(Human character, int x, int y) {
        actions.add(new MovementAction(character, x, y));
        return this;
    }

    public CutsceneBuilder moveYthenX(Human character, int x, int y) {
        int startX = character.getMapX();
        System.out.println("Moving " + character + " from " + startX + "," + y + " to " + x + "," + y);
        actions.add(new MovementAction(character, startX, y));
        actions.add(new MovementAction(character, x, y));
        return this;
    }

    public CutsceneBuilder moveXthenY(Human character, int x, int y) {
        int startY = character.getMapY();
        actions.add(new MovementAction(character, x, startY));
        actions.add(new MovementAction(character, x, y));
        return this;
    }
    
    public CutsceneBuilder face(Human character, FacingDirections direction) {
        actions.add(new FaceDirectionAction(character, direction));
        return this;
    }
    
    public CutsceneBuilder faceTowards(Human character, Human target) {
        actions.add(new FaceDirectionAction(character, target));
        return this;
    }
    
    public CutsceneBuilder emote(Human character, int duration, Emotes emote, CameraManager camera) {
        actions.add(new EmoteAction(character, duration, emote, camera));
        return this;
    }

    public CutsceneBuilder waitEmote(Human character, CameraManager camera) {
        actions.add(new EmoteAction(character, 60, Emotes.WAIT1, camera));
        actions.add(new EmoteAction(character, 60, Emotes.WAIT2, camera));
        actions.add(new EmoteAction(character, 60, Emotes.WAIT3, camera));
        return this;
    }
    
    public CutsceneBuilder tp(Human character, int x, int y, String targetMap, OverworldState overworldState) {
        actions.add(new TeleportAction(character, x, y, targetMap, overworldState));
        return this;
    }
    
    // ============================================================================
    // DIALOGUE ACTIONS
    // ============================================================================
    
    public CutsceneBuilder say(Dialogue dialogue) {
        actions.add(new DialogueAction(dialogue));
        return this;
    }

    public CutsceneBuilder say(String text) {
        actions.add(new DialogueAction(new Dialogue(text)));
        return this;
    }

    public CutsceneBuilder say(String... pages) {
        actions.add(new DialogueAction(new Dialogue(pages)));
        return this;
    }
    
    // ============================================================================
    // CAMERA ACTIONS
    // ============================================================================
    
    public CutsceneBuilder camMove(CameraManager camera, int x, int y, int time) {
        actions.add(new CameraAction(camera, x, y, time));
        return this;
    }
    
    public CutsceneBuilder camFocus(CameraManager camera, Human target) {
        actions.add(new CameraAction(camera, target));
        return this;
    }
    
    public CutsceneBuilder camSet(CameraManager camera, int x, int y) {
        actions.add(new CameraAction(camera, x, y));
        return this;
    }
    
    public CutsceneBuilder camShake(CameraManager camera, int duration) {
        actions.add(new CameraAction(camera, duration));
        return this;
    }
    
    // ============================================================================
    // VISUAL EFFECTS ACTIONS
    // ============================================================================
    
    public CutsceneBuilder fadeIn(int duration) {
        actions.add(new FadeAction(duration, 0, 255, 20));
        return this;
    }
    
    public CutsceneBuilder fadeOut(int duration) {
        actions.add(new FadeAction(duration, 255, 0));
        return this;
    }
    
    public CutsceneBuilder animation(String name, int x, int y, double scale) {
        actions.add(new AnimationAction(name, x, y, scale));
        return this;
    }
    
    public CutsceneBuilder showImage(String imagePath) {
        actions.add(new ImageBoxAction(imagePath));
        return this;
    }
    
    // ============================================================================
    // GAME STATE ACTIONS
    // ============================================================================
    
    public CutsceneBuilder setFlag(FlagManager flagManager, String... flags) {
        actions.add(new SetFlagAction(flagManager, flags));
        return this;
    }
    
    public CutsceneBuilder removeFlag(FlagManager flagManager, String... flags) {
        actions.add(new RemoveFlagAction(flagManager, flags));
        return this;
    }
    
    public CutsceneBuilder execute(Runnable command) {
        actions.add(new CommandAction(command));
        return this;
    }
    
    public CutsceneBuilder battle(StateManager stateManager, FlagManager flagManager, int battleID) {
        actions.add(new BattleAction(stateManager, flagManager, battleID));
        return this;
    }
    
    // ============================================================================
    // COMPOSITE ACTIONS - COMMON COMBINATIONS
    // ============================================================================
    
    public CutsceneBuilder approachPlayer(Human character, int targetX, int targetY, CameraManager camera) {
        return this
            .react(character, camera, Emotes.SURPRISE)
            .move(character, targetX, targetY)
            .faceTowards(character, null)
            .wait(10);
    }
    
    public CutsceneBuilder react(Human character, CameraManager camera, Emotes emote) {
        return this
            .sfx("GameCursor")
            .emote(character, 60, emote, camera)
            .wait(10);
    }

    public CutsceneBuilder speak(String text) {
        return this
            .sfx("GameCursor")
            .say(text);
    }

    public CutsceneBuilder speak(String... pages) {
        return this
            .sfx("GameCursor")
            .say(pages);
    }

    public CutsceneBuilder speak(Dialogue dialogue) {
        return this
            .sfx("GameCursor")
            .say(dialogue);
    }
    
    // ============================================================================
    // CONTROL ACTIONS
    // ============================================================================
    
    public CutsceneBuilder parallel(CutsceneAction... parallelActions) {
        actions.add(new ParallelAction(parallelActions));
        return this;
    }
    
    public CutsceneBuilder action(CutsceneAction action) {
        actions.add(action);
        return this;
    }
    
    public Cutscene build() {
        return new Cutscene(
            yesFlags.toArray(String[]::new),
            noFlags.toArray(String[]::new),
            actions.toArray(CutsceneAction[]::new)
        );
    }
}