package cutscene;

import cutscene.initialize.*;
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
import java.util.Random;
import tile.MapManager;

public class CutsceneManager {

	private final Map<String, List<Cutscene>> cutscenes;
	private final FlagManager flagManager;
	private final StateManager stateManager;
	private OverworldState overworldState;
	private final CameraManager cameraManager;
	private final Player player;
	private final NPCManager npcManager;
	private final MapManager mapManager;
	private final Random random;
	private NPC currentCutsceneNPC;
	private AIMode previousAIMode;

	public CutsceneManager(NPCManager npcManager, Player player, CameraManager cameraManager, StateManager stateManager, MapManager mapManager) {
		cutscenes = new HashMap<>();
		this.flagManager = FlagManager.getInstance();
		this.stateManager = stateManager;
		this.cameraManager = cameraManager;
		this.npcManager = npcManager;
		this.player = player;
		this.mapManager = mapManager;
		this.random = new Random();
		this.currentCutsceneNPC = null;
		this.previousAIMode = null;
	}

	public void setOverworldState(OverworldState overworldState) {
		this.overworldState = overworldState;
		initializeCutscenes();
	}

	private void initializeCutscenes() {
		if (overworldState == null) return;
		MethanopolisCutscenes.initialize(cutscenes, overworldState);
		PorbitalTownCutscenes.initialize(cutscenes, stateManager, overworldState, npcManager, cameraManager, player, flagManager, mapManager);
		Route1Cutscenes.initialize(cutscenes, overworldState);
	}

	private String getKeyLocation(int x, int y, String map, boolean interact, FacingDirections facing) {
		return x + " " + y + " " + map + " " + interact + " " + facing;
	}

	private String getKeyLook(int x, int y, String map) {
		return x + " " + y + " " + map + " looking";
	}

	private String getKeyNPC(NPC npc) {
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
		Cutscene cutsceneLooking = interact ? getLookingCutscene(facingX, facingY, map) : null;
		if(cutsceneDirection != null) return cutsceneDirection;
		if(cutsceneLooking != null) return cutsceneLooking;
		if(cutsceneNoDirection != null) return cutsceneNoDirection;

		if (!interact || facingNPC == null) return null;
		Cutscene cutsceneNPC = getNPCCutscene(facingNPC);
		if(cutsceneNPC != null) return cutsceneNPC;
		return null;
	}

	private Cutscene getLookingCutscene(int facingX, int facingY, String map) {
		List<Cutscene> lookingCutscenes = cutscenes.get(
			getKeyLook(facingX, facingY, map)
		);
		if(lookingCutscenes == null) return null;
		lookingCutscenes = lookingCutscenes.stream()
			.filter(cutscene -> flagManager.matchFlags(cutscene.getYesFlags(), cutscene.getNoFlags()))
			.toList();
		if(lookingCutscenes.isEmpty()) return null;
		return lookingCutscenes.get(random.nextInt(lookingCutscenes.size()));
	}

	private Cutscene getLocationCutscene(int x, int y, String map, boolean interact, FacingDirections facing) {
		List<Cutscene> locationCutscenes = cutscenes.get(
			getKeyLocation(x, y, map, interact, facing)
		);
		if(locationCutscenes == null) return null;
		locationCutscenes = locationCutscenes.stream()
			.filter(cutscene -> flagManager.matchFlags(cutscene.getYesFlags(), cutscene.getNoFlags()))
			.toList();
		if(locationCutscenes.isEmpty()) return null;
		return locationCutscenes.get(random.nextInt(locationCutscenes.size()));
	}

	private Cutscene getNPCCutscene(NPC npc) {
		List<Cutscene> npcCutscenes = cutscenes.get(getKeyNPC(npc));
		if(npcCutscenes == null) return null;
		npcCutscenes = npcCutscenes.stream()
			.filter(cutscene -> flagManager.matchFlags(cutscene.getYesFlags(), cutscene.getNoFlags()))
			.toList();
		if(npcCutscenes.isEmpty()) return null;
		setNPCCutsceneMode(npc);
		return npcCutscenes.get(random.nextInt(npcCutscenes.size()));
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
