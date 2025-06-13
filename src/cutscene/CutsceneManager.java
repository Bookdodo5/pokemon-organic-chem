package cutscene;

import cutscene.cutsceneAction.AnimationCutsceneAction;
import cutscene.cutsceneAction.CameraCutsceneAction;
import cutscene.cutsceneAction.CommandCutsceneAction;
import cutscene.cutsceneAction.DialogueCutsceneAction;
import cutscene.cutsceneAction.ImageBoxCutsceneAction;
import cutscene.cutsceneAction.PlaysoundCutsceneAction;
import cutscene.cutsceneAction.WaitCutsceneAction;
import dialogue.Dialogue;
import entity.FacingDirections;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import java.util.HashMap;
import java.util.Map;

public class CutsceneManager {

	private final Map<String, Cutscene> cutscenes;
	private final FlagManager flagManager;

	public CutsceneManager(NPCManager npcManager, Player player, CameraManager cameraManager, FlagManager flagManager) {
		cutscenes = new HashMap<>();
		this.flagManager = flagManager;

		// === ORGANIC CHEMISTRY LAB INTRODUCTION CUTSCENE ===
		// A comprehensive tutorial introducing the player to Professor Smith's lab
		cutscenes.put(getCutsceneKey(11, 13, "outside"), new Cutscene(
			new String[] { "organic_chemistry_lab_intro" },
			new String[] { "organic_chemistry_lab_intro_done" },
			// === SCENE 1: DRAMATIC ENTRANCE ===
			// Fade in with mysterious lab atmosphere
			new PlaysoundCutsceneAction("Title", true),
			new ImageBoxCutsceneAction("/animations/electric1.png"),
			new WaitCutsceneAction(60),
			new CameraCutsceneAction(cameraManager, 100, 100, 150), // Slow zoom on lab entrance
			
			new DialogueCutsceneAction(new Dialogue(new String[] {
				"*The heavy oak doors of the Organic Chemistry Laboratory creak open...*",
				"*Strange vapors and colorful lights emanate from within*",
				"*This is it - your first day as a chemistry student*"
			})),

			new CommandCutsceneAction(() -> {
				player.setFacingDirection(FacingDirections.UP);
				npcManager.getNPC("test").setFacingDirection(FacingDirections.UP);
			}),
			
			// === SCENE 2: PROFESSOR SMITH'S INTRODUCTION ===
			new CameraCutsceneAction(cameraManager, npcManager.getNPC("test")), // Focus on professor
			new WaitCutsceneAction(80),
			new CameraCutsceneAction(cameraManager, 100, 100), // Focus on professor
			
			new DialogueCutsceneAction(new Dialogue(new String[] {
				"Ah! You must be my new student. Welcome!",
				"I'm Professor Smith, head of Organic Chemistry here at the university.",
				"I've been teaching molecular science for over twenty years...",
				"...and I can tell you, chemistry is pure magic when you understand it!"
			})),
			new CameraCutsceneAction(cameraManager, 200), // Focus on professor
			
			// Professor demonstrates with a small reaction
			new AnimationCutsceneAction("electric1", 15, 13, 2.0), // Electrical sparkle near professor
			new PlaysoundCutsceneAction("GameCursor", false),
			new WaitCutsceneAction(60),
			
			new DialogueCutsceneAction(new Dialogue(new String[] {
				"That little spark? Just a simple electrolysis demonstration.",
				"But don't worry - we'll start with much safer experiments today.",
				"Safety first in my laboratory!"
			}))

		));
	}

	private String getCutsceneKey(int x, int y, String map) {
		return x + " " + y + " " + map;
	}

	public Cutscene getCutscene(int x, int y, String map) {
		Cutscene cutscene = cutscenes.get(getCutsceneKey(x, y, map));
		if (cutscene == null) return null;
		if (flagManager.matchFlags(cutscene.getYesFlags(), cutscene.getNoFlags())) return cutscene;
		return null;
	}
}
