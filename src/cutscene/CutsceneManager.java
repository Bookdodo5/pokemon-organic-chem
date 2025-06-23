package cutscene;

import cutscene.cutsceneAction.AnimationAction;
import cutscene.cutsceneAction.BattleAction;
import cutscene.cutsceneAction.CameraAction;
import cutscene.cutsceneAction.CommandAction;
import cutscene.cutsceneAction.DialogueAction;
import cutscene.cutsceneAction.EmoteAction;
import cutscene.cutsceneAction.FaceDirectionAction;
import cutscene.cutsceneAction.FadeAction;
import cutscene.cutsceneAction.ImageBoxAction;
import cutscene.cutsceneAction.MovementAction;
import cutscene.cutsceneAction.ParallelAction;
import cutscene.cutsceneAction.PlaysoundAction;
import cutscene.cutsceneAction.SetFlagAction;
import cutscene.cutsceneAction.TeleportAction;
import cutscene.cutsceneAction.WaitAction;
import cutscene.cutsceneAction.WaitForInputAction;
import dialogue.Dialogue;
import dialogue.DialogueOption;
import entity.AIMode;
import entity.FacingDirections;
import entity.NPC;
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
	private final NPCManager npcManager;
	
	private NPC currentCutsceneNPC;
	private AIMode previousAIMode;

	public CutsceneManager(NPCManager npcManager, Player player, CameraManager cameraManager, FlagManager flagManager, StateManager stateManager) {
		cutscenes = new HashMap<>();
		this.flagManager = flagManager;
		this.stateManager = stateManager;
		this.cameraManager = cameraManager;
		this.npcManager = npcManager;
		this.player = player;
		this.currentCutsceneNPC = null;
		this.previousAIMode = null;
	}

	public void setOverworldState(OverworldState overworldState) {
		this.overworldState = overworldState;
		initializeCutscenes();
	}

	private void initializeCutscenes() {
		if (overworldState == null) return;

		cutscenes.put(getCutsceneKey(npcManager.getNPC("test")), List.of(
			new Cutscene(
				new String[] { },
				new String[] { },
				new FaceDirectionAction(npcManager.getNPC("test"), player),
				new PlaysoundAction("GameCursor", false),
				new DialogueAction(new Dialogue(new String[] { "Hello, I'm the trainer!" }))
			)
		));
		
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
				"Let's see if you can synthesize the target molecule!",
				"How would you synthesize carboxylic acid from the alkene?"
			}, new DialogueOption[] {
				new DialogueOption("Oxidative", new Dialogue(new String[] {
					"Professor Smith: Excellent work!",
					"You successfully performed the Ozonolysis reaction!",
					"The oxidative cleavage of the double bond gave us our target molecule.",
					"Remember: O3 first cleaves the double bond, then the workup determines the final products!"
				}), () -> {
					flagManager.addFlag("ozonolysis_intro_done");
					flagManager.removeFlag("BATTLE_WIN");
				}),
				new DialogueOption("Reductive", new Dialogue(new String[] {
					"Professor Smith: Not quite right this time...",
					"Remember: Ozonolysis requires careful control of the workup conditions.",
					"Try again and think about which workup would give the desired products!"
				}), () -> {
					flagManager.removeFlag("BATTLE_LOSE");
				})
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

		// Lose cutscene
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

		// === COMPREHENSIVE CUTSCENE DEMONSTRATION AT (29, 13) ===
		cutscenes.put(getCutsceneKey(29, 13, "porbitalTown", true, FacingDirections.UP), List.of(
			
			// First-time cutscene - showcases all features
			new Cutscene(
				new String[] { },  // No required flags
				new String[] { "cutscene_demo_completed" },  // Don't show if already completed
				
				// Phase 1: Initial setup with parallel actions
				new ParallelAction(
					new EmoteAction(player, 60, Emotes.SURPRISE, cameraManager),
					new PlaysoundAction("GUIMenuOpen", false),
					new AnimationAction("26", 100, 100, 1)
				),

				new ParallelAction(
					new FadeAction(60, 0, 255, -1),
					new WaitForInputAction()
				),
				new FadeAction(60, 255, 0, 30),
				
				new CameraAction(cameraManager, 28 * 16, 12 * 16, 120), // Move camera to focus on sign
				new DialogueAction(new Dialogue(new String[] {
					"Sign: Welcome to Porbital Town!",
					"Home of the Organic Chemistry Research Institute.",
					"Please respect the laboratory safety protocols.",
					"Remember: Always wear your safety goggles!"
				})),
				new CameraAction(cameraManager, player),
				
				// Phase 3: Player reactions with parallel animations
				new ParallelAction(
					new EmoteAction(player, 90, Emotes.QUESTION, cameraManager),
					new AnimationAction("fire4", 30 * 16, 13 * 16, 0.8),
					new PlaysoundAction("GameCursor", false)
				),
				
				// Phase 4: Wait for player input
				new WaitForInputAction(),
				
				// Phase 5: Dramatic camera shake and effects
				new ParallelAction(
					new CameraAction(cameraManager, 60), // Camera shake for 60 frames
					new PlaysoundAction("PlayerBump", false),
					new AnimationAction("electric1", 29 * 16, 13 * 16, 1.2)
				),
				
				// Phase 6: Player movement and facing
				new ParallelAction(
					new MovementAction(player, 29, 14), // Move player down one tile
					new FaceDirectionAction(player, FacingDirections.UP),
					new EmoteAction(player, 120, Emotes.MUSIC, cameraManager)
				),
				
				// Phase 7: Fade effects and more dialogue
				new FadeAction(60, 0, 128, 30), // Fade to half darkness
				new DialogueAction(new Dialogue(new String[] {
					"Sign: This town is famous for its cutting-edge research!",
					"Many breakthrough reactions were discovered here.",
					"Perhaps you'll make the next great discovery?"
				})),
				new FadeAction(60, 128, 0, 30), // Fade back to normal
				
				// Phase 8: Final parallel showcase
				new ParallelAction(
					new EmoteAction(player, 60, Emotes.SMILE, cameraManager),
					new AnimationAction("water3", 28 * 16, 13 * 16, 0.9),
					new AnimationAction("poison4", 30 * 16, 13 * 16, 0.9),
					new PlaysoundAction("PkmnHealing", false),
					new CameraAction(cameraManager, player) // Return camera to follow player
				),
				
				// Phase 9: Set completion flag
				new SetFlagAction(flagManager, "cutscene_demo_completed"),
				new CommandAction(() -> {
					System.out.println("Cutscene demonstration completed!");
				})
			),
			
			// Repeat cutscene - simpler version for subsequent visits
			new Cutscene(
				new String[] { "cutscene_demo_completed" },  // Only show if completed before
				new String[] { },  // No forbidden flags
				
				// Simple parallel action demonstration
				new ParallelAction(
					new EmoteAction(player, 45, Emotes.FRIENDLY, cameraManager),
					new PlaysoundAction("GUIConfirm", false)
				),
				
				new DialogueAction(new Dialogue(new String[] {
					"Sign: Welcome back to Porbital Town!",
					"You've seen the full cutscene demonstration.",
					"All cutscene actions are working perfectly!"
				})),
				
				new ParallelAction(
					new EmoteAction(player, 45, Emotes.LOVE, cameraManager),
					new AnimationAction("24", 29 * 16, 12 * 16, 1.0)
				)
			)
		));
	}

	private String getCutsceneKey(int x, int y, String map, boolean interact, FacingDirections facing) {
		return x + " " + y + " " + map + " " + interact + " " + facing;
	}

	private String getCutsceneKey(NPC npc) {
		if(npc == null) return "null";
		return npc.getId();
	}

	public Cutscene getCutscene(Player player, boolean interact) {
		int x = player.getMapX();
		int y = player.getMapY();
		String map = overworldState.getCurrentMapID();
		FacingDirections facing = player.getCurrentDirection();
		int facingX = player.getMapX() + facing.getX();
		int facingY = player.getMapY() + facing.getY();

		// Find NPC the player is facing
		NPC facingNPC = npcManager.getNPCs().stream()
			.filter(npc -> npc.getMapX() == facingX && npc.getMapY() == facingY && npc.getMap().equals(map))
			.findFirst()
			.orElse(null);

		Cutscene cutsceneDirection = getLocationCutscene(x, y, map, interact, facing);
		Cutscene cutsceneNoDirection = getLocationCutscene(x, y, map, interact, null);
		if(cutsceneDirection != null) return cutsceneDirection;
		if(cutsceneNoDirection != null) return cutsceneNoDirection;

		if (!interact || facingNPC == null) return null;
		Cutscene cutsceneNPC = getNPCCutscene(facingNPC);
		if(cutsceneNPC != null) return cutsceneNPC;
		return null;
	}

	private Cutscene getLocationCutscene(int x, int y, String map, boolean interact, FacingDirections facing) {
		List<Cutscene> locationCutscenes = cutscenes.get(
			getCutsceneKey(x, y, map, interact, facing)
		);
		if(locationCutscenes == null) return null;
		for (Cutscene cutscene : locationCutscenes) {
			if (flagManager.matchFlags(cutscene.getYesFlags(), cutscene.getNoFlags())) {
				return cutscene;
			}
		}
		return null;
	}

	private Cutscene getNPCCutscene(NPC npc) {
		List<Cutscene> npcCutscenes = cutscenes.get(getCutsceneKey(npc));
		if(npcCutscenes == null) return null;
		for (Cutscene cutscene : npcCutscenes) {
			if (flagManager.matchFlags(cutscene.getYesFlags(), cutscene.getNoFlags())) {
				setNPCCutsceneMode(npc);
				return cutscene;
			}
		}
		return null;
	}
	
	private void setNPCCutsceneMode(NPC npc) {
		currentCutsceneNPC = npc;
		previousAIMode = npc.getAIMode();
		npc.setAIMode(AIMode.STILL);
	}

	public void restoreNPCAIMode() {
		if (currentCutsceneNPC != null && previousAIMode != null) {
			currentCutsceneNPC.setAIMode(previousAIMode);
			currentCutsceneNPC = null;
			previousAIMode = null;
		}
	}
}
