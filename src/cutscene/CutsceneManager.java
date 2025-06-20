package cutscene;

import cutscene.cutsceneAction.BattleAction;
import cutscene.cutsceneAction.CommandAction;
import cutscene.cutsceneAction.DialogueAction;
import cutscene.cutsceneAction.EmoteAction;
import cutscene.cutsceneAction.FadeAction;
import cutscene.cutsceneAction.ImageBoxAction;
import cutscene.cutsceneAction.TeleportAction;
import cutscene.cutsceneAction.WaitAction;
import dialogue.Dialogue;
import entity.FacingDirections;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import gamestates.StateManager;
import gamestates.states.OverworldState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CutsceneManager {

	private final Map<String, List<Cutscene>> cutscenes;
	private final FlagManager flagManager;
	private final StateManager stateManager;
	private OverworldState overworldState;
	private final CameraManager cameraManager;
	private final Player player;

	public CutsceneManager(NPCManager npcManager, Player player, CameraManager cameraManager, FlagManager flagManager, StateManager stateManager) {
		cutscenes = new HashMap<>();
		this.flagManager = flagManager;
		this.stateManager = stateManager;
		this.cameraManager = cameraManager;
		this.player = player;
	}

	public void setOverworldState(OverworldState overworldState) {
		this.overworldState = overworldState;
		initializeCutscenes();
	}

	private void initializeCutscenes() {
		if (overworldState == null) return;
		
		// === OZONOLYSIS BATTLE CUTSCENE ===
		// Pre-battle cutscene
		cutscenes.put(getCutsceneKey(11, 13, "porbitalTown", false, null), List.of(
			
		new Cutscene(
			new String[] { "ozonolysis_intro" },
			new String[] { "ozonolysis_intro_done", "BATTLE_WIN", "BATTLE_LOSE" },
			new ImageBoxAction("/animations/electric1.png"),
			new WaitAction(60),
			new EmoteAction(player, 60, Emotes.WAIT1, cameraManager),
			new EmoteAction(player, 60, Emotes.WAIT2, cameraManager),
			new EmoteAction(player, 60, Emotes.WAIT3, cameraManager),
			new FadeAction(60,0,255, 180),
			new TeleportAction(player, 0, 0, "route1", overworldState),
			new FadeAction(60,255,0, 30),
			new EmoteAction(player, 30, Emotes.WAIT3, cameraManager),
			new EmoteAction(player, 60, Emotes.MUSIC, cameraManager),
			new DialogueAction(new Dialogue(new String[] {
				"Professor Smith: Today we'll be studying the Ozonolysis reaction!",
				"This powerful oxidative cleavage reaction can break double bonds in alkenes.",
				"You'll need to choose between oxidative (H2O2) or reductive (DMS) workup.",
				"Let's see if you can synthesize the target molecule!"
			})),
			
			new BattleAction(stateManager, flagManager, 1)
		),

		// Win cutscene
		new Cutscene(
			new String[] { "BATTLE_WIN" },
			new String[] { "ozonolysis_intro_done" },
			new DialogueAction(new Dialogue(new String[] {
				"Professor Smith: Excellent work!",
				"You successfully performed the Ozonolysis reaction!",
				"The oxidative cleavage of the double bond gave us our target molecule.",
				"Remember: O3 first cleaves the double bond, then the workup determines the final products!"
			})),
			new CommandAction(() -> {
				flagManager.addFlag("ozonolysis_intro_done");
				flagManager.removeFlag("BATTLE_WIN");
			})
		),

		// Lose cutscene`
		new Cutscene(
			new String[] { "BATTLE_LOSE" },
			new String[] { "ozonolysis_intro_done" },
			new DialogueAction(new Dialogue(new String[] {
				"Professor Smith: Not quite right this time...",
				"Remember: Ozonolysis requires careful control of the workup conditions.",
				"Try again and think about which workup would give the desired products!"
			})),
			new CommandAction(() -> {
				flagManager.removeFlag("BATTLE_LOSE");
			})
		)));

		// === SIGN DIALOGUE AT (29, 13) ===
		cutscenes.put(getCutsceneKey(29, 13, "porbitalTown", true, FacingDirections.UP), List.of(
			new Cutscene(
				new String[] { },  // No required flags
				new String[] { },  // No forbidden flags
				new EmoteAction(player, 30, Emotes.QUESTION, cameraManager),
				new DialogueAction(new Dialogue(new String[] {
					"Sign: Welcome to Porbital Town!",
					"Home of the Organic Chemistry Research Institute.",
					"Please respect the laboratory safety protocols.",
					"Remember: Always wear your safety goggles!"
				})),
				new EmoteAction(player, 30, Emotes.SMILE, cameraManager)
			)
		));
	}

	private String getCutsceneKey(int x, int y, String map, boolean interact, FacingDirections facing) {
		System.out.println(x + " " + y + " " + map + " " + interact + " " + facing);
		return x + " " + y + " " + map + " " + interact + " " + facing;
	}

	public Cutscene getCutscene(int x, int y, String map, boolean interact, FacingDirections facing) {
		List<Cutscene> cutsceneList = cutscenes.get(getCutsceneKey(x, y, map, interact, facing));
		if (cutsceneList == null) return null;
		for (Cutscene cutscene : cutsceneList) {
			if (flagManager.matchFlags(cutscene.getYesFlags(), cutscene.getNoFlags())) return cutscene;
		}

		if(facing != null) {
			return getCutscene(x, y, map, interact, null);
		}

		return null;
	}
}
