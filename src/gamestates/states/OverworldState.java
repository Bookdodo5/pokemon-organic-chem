package gamestates.states;

import assets.SoundManager;
import cutscene.Cutscene;
import cutscene.CutsceneManager;
import entity.Entity;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static main.Constants.ORIGINAL_TILE_SIZE;
import main.GameContentManager;
import tile.MapData;
import tile.MapManager;
import tile.TransitionPoint;

public class OverworldState extends GameState {
	private final Player player;
	private final MapManager mapManager;
	private final NPCManager npcManager;
	private final CutsceneManager cutsceneManager;
	private final CameraManager cameraManager;

	private List<Entity> entities;
	private Cutscene pendingCutscene;

	public OverworldState(StateManager stateManager, KeyBindingHandler keyHandler,
			GameContentManager gameContentManager) {
		super(stateManager, keyHandler, gameContentManager);
		this.player = gameContentManager.getPlayer();
		this.mapManager = gameContentManager.getMapManager();
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
			String currentMusic = mapManager.getCurrentMusic();
			stateManager.transitionToState(GameStates.OVERWORLD, true, currentMusic, () -> {
				int toX = mapTransition.getToX();
				int toY = mapTransition.getToY();
				String nextMap = mapTransition.getMapTo();
				setMap(toX, toY, nextMap);
				update();
			});
		}
	}

	public void setMap(int nextX, int nextY, String nextMap) {
		player.setMapX(nextX);
		player.setMapY(nextY);
		mapManager.setCurrentMap(nextMap);
		cameraManager.update();
		initializeEntities();
	}

	private void checkCutscene(boolean isInteracting) {
		if (stateManager.getState() == GameStates.CUTSCENE) return;

		Cutscene cutscene = cutsceneManager.getCutscene(player, isInteracting);
		if (cutscene != null && !cutscene.isFinished() && player.isIdle()) {
			pendingCutscene = cutscene;
			stateManager.setState(GameStates.CUTSCENE);
		}
	}

	private void initializeEntities() {
		entities = new ArrayList<>();
		entities.add(player);
		entities.addAll(npcManager.getNPCs().stream()
				.filter(npc -> npc.getMap().equals(mapManager.getCurrentMapID()))
				.collect(Collectors.toList()));
	}

	@Override
	public void update() {
		player.update(entities, mapManager);
		
		for (NPC npc : npcManager.getNPCs()) {
			for(MapData map : mapManager.getVisibleMaps()) {
				if(!map.getMapName().equals(npc.getMap())) continue;
				npc.update(entities, mapManager);
				break;
			}
		}
		
		checkMapTransition();
		checkCutscene(false);
		mapManager.updateVisibleMaps(player.getMapX(), player.getMapY());
		cameraManager.update();
	}

	@Override
	public void draw(Graphics2D g2) {
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
	}

	private void drawLayer(Graphics2D g2, MapData map, int layerIndex, int cameraX, int cameraY) {
		int relativeX = map.getGlobalX() - mapManager.getGlobalX();
		int relativeY = map.getGlobalY() - mapManager.getGlobalY();
		int realX = cameraX - relativeX * ORIGINAL_TILE_SIZE;
		int realY = cameraY - relativeY * ORIGINAL_TILE_SIZE;
		map.getLayers()[layerIndex].drawLayer(g2, realX, realY);
	}

	private void drawEntities(Graphics2D g2) {
		List<Entity> sortedEntities = entities.stream()
			.sorted(Comparator.comparingDouble(Entity::getY))
			.toList();

		for (Entity entity : sortedEntities) {
			if (entity == null) continue;
			if (entity instanceof Player) {
				entity.draw(g2, cameraManager.getCameraX(), cameraManager.getCameraY(), mapManager);
			} else {
				entity.draw(g2, cameraManager.getCameraX(), cameraManager.getCameraY(), mapManager);
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
				if (player.getCurrentMovementState() == MovementStates.IDLE) {
					checkCutscene(true);
				}
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
	}

	@Override
	public void onExit(GameStates nextState) {

	}

	public String getCurrentMapID() { return mapManager.getCurrentMapID(); }

	public Cutscene takePendingCutscene() {
		Cutscene cutscene = pendingCutscene;
		pendingCutscene = null;
		return cutscene;
	}

}
