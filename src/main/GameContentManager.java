package main;

import battle.BattleDataManager;
import battle.PlayerDeckManager;
import battle.molecules.Molecule;
import battle.reactions.ReactionAvailabilityManager;
import cutscene.CutsceneManager;
import dialogue.DialogueManager;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import input.KeyBindingHandler;		
import tile.MapManager;

public class GameContentManager {
	private final DialogueManager dialogueManager;
	private final CutsceneManager cutsceneManager;
	private final MapManager mapManager;
	private final NPCManager npcManager;
	private final Player player;
	private final CameraManager cameraManager;
	private final FlagManager flagManager;
	private final PlayerDeckManager playerDeckManager;
	private final BattleDataManager battleDataManager;
	private final ReactionAvailabilityManager reactionAvailabilityManager;

	public GameContentManager(KeyBindingHandler keyHandler) {
		this.dialogueManager = new DialogueManager();
		this.mapManager = new MapManager();
		this.npcManager = new NPCManager();
		this.player = new Player(10, 12, keyHandler);
		this.cameraManager = new CameraManager(player);
		this.flagManager = new FlagManager();
		this.cutsceneManager = new CutsceneManager(npcManager, player, cameraManager, flagManager);
		this.playerDeckManager = new PlayerDeckManager();
		this.battleDataManager = new BattleDataManager();
		this.reactionAvailabilityManager = new ReactionAvailabilityManager(flagManager);
		Molecule.setReactionAvailabilityManager(reactionAvailabilityManager);
	}

	public DialogueManager getDialogueManager() { return dialogueManager; }

	public CutsceneManager getCutsceneManager() { return cutsceneManager; }

	public MapManager getMapManager() { return mapManager; }

	public NPCManager getNpcManager() { return npcManager; }

	public Player getPlayer() { return player; }

	public CameraManager getCameraManager() { return cameraManager; }

	public FlagManager getFlagManager() { return flagManager; }

	public PlayerDeckManager getPlayerDeckManager() { return playerDeckManager; }

	public BattleDataManager getBattleDataManager() { return battleDataManager; }

	public ReactionAvailabilityManager getReactionAvailabilityManager() { return reactionAvailabilityManager; }

}