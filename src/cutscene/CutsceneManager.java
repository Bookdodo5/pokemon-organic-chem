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
		MethanopolisCutscenes.initialize(cutscenes, overworldState);
		PorbitalTownCutscenes.initialize(cutscenes, overworldState);
		Route1Cutscenes.initialize(cutscenes, overworldState);
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
