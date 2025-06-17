package main;

import battle.BattleDataManager;
import battle.molecules.Molecule;
import cutscene.CutsceneManager;
import dialogue.DialogueManager;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import gamestates.StateManager;
import input.KeyBindingHandler;		
import pokedex.MoleculeRecord;
import pokedex.PlayerDeckManager;
import pokedex.ReactionRecord;
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
	private final ReactionRecord reactionRecord;
	private final MoleculeRecord moleculeRecord;

	public GameContentManager(KeyBindingHandler keyHandler, StateManager stateManager) {
		this.dialogueManager = new DialogueManager();
		this.mapManager = new MapManager();
		this.npcManager = new NPCManager();
		this.player = new Player(10, 12, keyHandler);
		this.cameraManager = new CameraManager(player);
		this.flagManager = new FlagManager();
		this.cutsceneManager = new CutsceneManager(npcManager, player, cameraManager, flagManager, stateManager);
		this.playerDeckManager = new PlayerDeckManager();
		this.battleDataManager = new BattleDataManager();
		this.reactionRecord = new ReactionRecord(flagManager);
		Molecule.setReactionAvailabilityManager(reactionRecord);
		this.moleculeRecord = new MoleculeRecord();
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

	public ReactionRecord getReactionRecord() { return reactionRecord; }

	public MoleculeRecord getMoleculeRecord() { return moleculeRecord; }

}