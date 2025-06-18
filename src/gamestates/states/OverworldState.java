package gamestates.states;

import assets.SoundManager;
import cutscene.Cutscene;
import cutscene.CutsceneManager;
import dialogue.Dialogue;
import dialogue.DialogueManager;
import entity.Entity;
import entity.FacingDirections;
import entity.MovementStates;
import entity.NPC;
import entity.NPCManager;
import entity.Player;
import gamestates.CameraManager;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static main.Constants.ORIGINAL_TILE_SIZE;
import static main.Constants.SCALE;
import main.GameContentManager;
import tile.MapData;
import tile.MapManager;
import tile.TransitionPoint;

public class OverworldState extends GameState {
	private final Player player;
	private final MapManager mapManager;
	private final DialogueManager dialogueManager;
	private final NPCManager npcManager;
	private final CutsceneManager cutsceneManager;
	private final CameraManager cameraManager;

	private List<Entity> entities;

	public OverworldState(StateManager stateManager, KeyBindingHandler keyHandler,
			GameContentManager gameContentManager) {
		super(stateManager, keyHandler, gameContentManager);
		this.player = gameContentManager.getPlayer();
		this.mapManager = gameContentManager.getMapManager();
		this.dialogueManager = gameContentManager.getDialogueManager();
		this.cutsceneManager = gameContentManager.getCutsceneManager();
		this.npcManager = gameContentManager.getNpcManager();
		this.cameraManager = gameContentManager.getCameraManager();
		initializeEntities();
	}

	private void checkMapTransition() {
		if (stateManager.getState() == GameStates.CUTSCENE) return;
		TransitionPoint mapTransition = mapManager.checkTransition(
			player.getMapX(), player.getMapY()
		);

		if (mapTransition != null && player.isIdle()) {
			transitionMap(mapTransition);
		}
	}

	private void transitionMap(TransitionPoint mapTransition) {
		String currentMusic = mapManager.getCurrentMusic();
		stateManager.transitionToState(GameStates.OVERWORLD, true, currentMusic, () -> {
			setMap(mapTransition);
		});
	}

	private void setMap(TransitionPoint mapTransition) {
		player.setMapX(mapTransition.getToX());
		player.setMapY(mapTransition.getToY());
		cameraManager.update();
		mapManager.setCurrentMap(mapTransition.getMapTo());
		initializeEntities();
	}

	private void checkDialogue() {
		if (stateManager.getState() == GameStates.CUTSCENE) return;
		int playerX = player.getMapX();	
		int playerY = player.getMapY();
		FacingDirections playerFacing = player.currentDirection;

		Dialogue dialogue = dialogueManager.getDialogue(playerX, playerY, playerFacing, getCurrentMapID());
		if (dialogue != null) stateManager.setState(GameStates.DIALOGUE);
	}

	private void checkCutscene() {
		if (stateManager.getState() == GameStates.CUTSCENE) return;
		int playerX = player.getMapX();
		int playerY = player.getMapY();

		Cutscene cutscene = cutsceneManager.getCutscene(playerX, playerY, getCurrentMapID());
		if (cutscene != null && !cutscene.isFinished() && player.isIdle()) stateManager.setState(GameStates.CUTSCENE);
	}

	private void initializeEntities() {
		entities = new ArrayList<>();
		entities.add(player);
		entities.addAll(npcManager.getNPCs().stream()
				.filter(npc -> npc.getMap().equals(mapManager.getCurrentMapID()))
				.collect(Collectors.toList()));
	}

	private MapData findNextMap(int playerGlobalX, int playerGlobalY) {
		for(MapData map : mapManager.getVisibleMaps()) {
			if(playerGlobalX >= map.getGlobalX() &&
				playerGlobalX <= map.getGlobalX() + map.getWidth() &&
				playerGlobalY >= map.getGlobalY() &&
				playerGlobalY <= map.getGlobalY() + map.getHeight()) {
				return map;
			}
		}
		return null;
	}

	private void checkWalkAcrossMap() {
		if (player.getMapX() < 0 || player.getMapX() >= mapManager.getWidth() ||
			player.getMapY() < 0 || player.getMapY() >= mapManager.getHeight()) {
			int playerGlobalX = player.getMapX() + mapManager.getGlobalX();
			int playerGlobalY = player.getMapY() + mapManager.getGlobalY();
			MapData nextMap = findNextMap(playerGlobalX, playerGlobalY);
			if(nextMap == null) return;

			int nextMapX = playerGlobalX - nextMap.getGlobalX();
			int nextMapY = playerGlobalY - nextMap.getGlobalY();
			player.setMapX(nextMapX);
			player.setMapY(nextMapY);
			initializeEntities();
			mapManager.setCurrentMap(nextMap.getMapName());
		}
	}

	@Override
	public void update() {
		player.update(mapManager.getCurrentLayers(), entities);
		for (NPC npc : npcManager.getNPCs()) {
			for(MapData map : mapManager.getVisibleMaps()) {
				if(!map.getMapName().equals(npc.getMap())) continue;
				npc.update(map.getLayers(), entities);
				break;
			}
		}
		checkMapTransition();
		checkCutscene();
		checkWalkAcrossMap();
		mapManager.updateVisibleMaps(player.getMapX(), player.getMapY());
		cameraManager.update();
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform originalTransform = g2.getTransform();
		g2.scale(SCALE, SCALE);
		
		Set<MapData> visibleMaps = mapManager.getVisibleMaps();
		
		// Draw ground, decoration, and obstacle layers
		for(MapData map : visibleMaps) {
			for(int i = 0; i <= 2; i++) {
				drawLayer(g2, map, i, cameraManager.getCameraX(), cameraManager.getCameraY());
			}
		}

		drawEntities(g2);

		// Draw air layer
		for(MapData map : visibleMaps) {
			drawLayer(g2, map, 3, cameraManager.getCameraX(), cameraManager.getCameraY());
		}

		g2.setTransform(originalTransform);
	}

	private void drawLayer(Graphics2D g2, MapData map, int layerIndex, int cameraX, int cameraY) {
		int relativeX = map.getGlobalX() - mapManager.getGlobalX();
		int relativeY = map.getGlobalY() - mapManager.getGlobalY();
		int realX = cameraX - relativeX * ORIGINAL_TILE_SIZE;
		int realY = cameraY - relativeY * ORIGINAL_TILE_SIZE;
		map.getLayers()[layerIndex].drawLayer(g2, realX, realY);
	}

	private void drawEntities(Graphics2D g2) {
		entities.sort(Comparator.comparingDouble(Entity::getY));

		for (Entity entity : entities) {
			if (entity == null) continue;
			if (entity instanceof Player) {
				entity.draw(g2, cameraManager.getCameraX(), cameraManager.getCameraY());
			} else {
				entity.draw(g2, cameraManager.getCameraX(), cameraManager.getCameraY());
			}
		}
	}

	@Override
	public void onEnter(GameStates prevState) {
		if (!SoundManager.getMusicplayer().isPlaying(mapManager.getCurrentMusic())) {
			SoundManager.getMusicplayer().play(mapManager.getCurrentMusic());
		}
		cameraManager.setFocusPoint(player);
	}

	@Override
	public void keyTapped() {

		switch (keyHandler.getCurrentKey()) {
			case ESCAPE -> stateManager.setState(GameStates.PAUSING);
			case INTERACT -> {
				if (player.currentMovementState == MovementStates.IDLE) checkDialogue();
			}
			case P -> stateManager.setState(GameStates.POKEDEX);
			default -> {}
		}

	}

	@Override
	public void keyPressed() {

	}

	@Override
	public void keyReleased(Keys key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onExit(GameStates nextState) {

	}

	public String getCurrentMapID() { return mapManager.getCurrentMapID(); }

}
