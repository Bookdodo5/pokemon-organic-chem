package cutscene;

import cutscene.cutsceneAction.BattleCutsceneAction;
import cutscene.cutsceneAction.CommandCutsceneAction;
import cutscene.cutsceneAction.DialogueCutsceneAction;
import cutscene.cutsceneAction.ImageBoxCutsceneAction;
import cutscene.cutsceneAction.WaitCutsceneAction;
import dialogue.Dialogue;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import gamestates.StateManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CutsceneManager {

	private final Map<String, List<Cutscene>> cutscenes;
	private final FlagManager flagManager;
	private final StateManager stateManager;

	public CutsceneManager(NPCManager npcManager, Player player, CameraManager cameraManager, FlagManager flagManager, StateManager stateManager) {
		cutscenes = new HashMap<>();
		this.flagManager = flagManager;
		this.stateManager = stateManager;

		initializeCutscenes();
	}

	private void initializeCutscenes() {
		// === OZONOLYSIS BATTLE CUTSCENE ===
		// Pre-battle cutscene
		cutscenes.put(getCutsceneKey(11, 13, "outside"), List.of(
			
		new Cutscene(
			new String[] { "ozonolysis_intro" },
			new String[] { "ozonolysis_intro_done", "BATTLE_WIN", "BATTLE_LOSE" },
			new ImageBoxCutsceneAction("/animations/electric1.png"),
			new WaitCutsceneAction(60),
			
			new DialogueCutsceneAction(new Dialogue(new String[] {
				"Professor Smith: Today we'll be studying the Ozonolysis reaction!",
				"This powerful oxidative cleavage reaction can break double bonds in alkenes.",
				"You'll need to choose between oxidative (H2O2) or reductive (DMS) workup.",
				"Let's see if you can synthesize the target molecule!"
			})),
			
			new BattleCutsceneAction(stateManager, flagManager, 1)
		),

		// Win cutscene
		new Cutscene(
			new String[] { "BATTLE_WIN" },
			new String[] { "ozonolysis_intro_done" },
			new DialogueCutsceneAction(new Dialogue(new String[] {
				"Professor Smith: Excellent work!",
				"You successfully performed the Ozonolysis reaction!",
				"The oxidative cleavage of the double bond gave us our target molecule.",
				"Remember: O3 first cleaves the double bond, then the workup determines the final products!"
			})),
			new CommandCutsceneAction(() -> {
				flagManager.addFlag("ozonolysis_intro_done");
				flagManager.removeFlag("BATTLE_WIN");
			})
		),

		// Lose cutscene`
		new Cutscene(
			new String[] { "BATTLE_LOSE" },
			new String[] { "ozonolysis_intro_done" },
			new DialogueCutsceneAction(new Dialogue(new String[] {
				"Professor Smith: Not quite right this time...",
				"Remember: Ozonolysis requires careful control of the workup conditions.",
				"Try again and think about which workup would give the desired products!"
			})),
			new CommandCutsceneAction(() -> {
				flagManager.removeFlag("BATTLE_LOSE");
			})
		)));
	}

	private String getCutsceneKey(int x, int y, String map) {
		return x + " " + y + " " + map;
	}

	public Cutscene getCutscene(int x, int y, String map) {
		List<Cutscene> cutsceneList = cutscenes.get(getCutsceneKey(x, y, map));
		if (cutsceneList == null) return null;
		for (Cutscene cutscene : cutsceneList) {
			if (flagManager.matchFlags(cutscene.getYesFlags(), cutscene.getNoFlags())) return cutscene;
		}
		return null;
	}
}
