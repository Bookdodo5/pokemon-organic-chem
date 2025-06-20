package main;

import battle.BattleDataManager;
import battle.molecules.Molecule;
import cutscene.CutsceneManager;
import dialogue.DialogueManager;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.FlagManager;
import gamestates.GameStates;
import gamestates.StateManager;
import gamestates.states.BattleState;
import gamestates.states.CutsceneState;
import gamestates.states.OverworldState;
import gamestates.states.PausingState;
import gamestates.states.PokedexState;
import gamestates.states.SettingsState;
import gamestates.states.TitleState;
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
	private final StateManager stateManager;
	private final KeyBindingHandler keyHandler;

	public GameContentManager() {
		this.stateManager = new StateManager();
		this.keyHandler = new KeyBindingHandler(stateManager);
		
		this.dialogueManager = new DialogueManager();
		this.mapManager = new MapManager();
		this.npcManager = new NPCManager();
		this.player = new Player(10, 12, keyHandler);
		this.cameraManager = new CameraManager(player);
		this.flagManager = new FlagManager();
		this.playerDeckManager = new PlayerDeckManager();
		this.battleDataManager = new BattleDataManager();
		this.reactionRecord = new ReactionRecord(flagManager);
		this.moleculeRecord = new MoleculeRecord();
		Molecule.setReactionAvailabilityManager(reactionRecord);
		
		this.cutsceneManager = new CutsceneManager(
			npcManager, 
			player, 
			cameraManager, 
			flagManager, 
			stateManager
			);
		initializeGameStates();
		
		this.cutsceneManager.setOverworldState(
			(OverworldState) StateManager.states.get(GameStates.OVERWORLD)
		);
	}

	private void initializeGameStates() {
		StateManager.states.put(GameStates.TITLE, new TitleState(stateManager, keyHandler, this));
		StateManager.states.put(GameStates.OVERWORLD, new OverworldState(stateManager, keyHandler, this));
		StateManager.states.put(GameStates.CUTSCENE, new CutsceneState(stateManager, keyHandler, this));
		StateManager.states.put(GameStates.PAUSING, new PausingState(stateManager, keyHandler, this));
		StateManager.states.put(GameStates.BATTLE, new BattleState(stateManager, keyHandler, this));
		StateManager.states.put(GameStates.POKEDEX, new PokedexState(stateManager, keyHandler, this));
		StateManager.states.put(GameStates.SETTINGS, new SettingsState(stateManager, keyHandler, this));
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

	public StateManager getStateManager() { return stateManager; }

	public KeyBindingHandler getKeyHandler() { return keyHandler; }

}