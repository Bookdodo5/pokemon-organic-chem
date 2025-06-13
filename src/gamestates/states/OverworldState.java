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
import java.util.stream.Collectors;
import static main.Constants.SCALE;
import main.GameContentManager;
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
		TransitionPoint mapTransition = mapManager.checkTransition(player.getWorldX(), player.getWorldY());

		if (mapTransition != null && player.isIdle()) {
			transitionMap(mapTransition);
		}
	}

	private void transitionMap(TransitionPoint mapTransition) {
		String currentMusic = mapManager.getCurrentMusic();
		stateManager.transitionToState(GameStates.OVERWORLD, true, currentMusic, () -> {
			player.setWorldX(mapTransition.getToX());
			player.setWorldY(mapTransition.getToY());
			cameraManager.update();
			mapManager.setCurrentMap(mapTransition.getMapTo());
			initializeEntities();
		});
	}

	private void checkDialogue() {
		if (stateManager.getState() == GameStates.CUTSCENE) return;
		int playerX = player.getWorldX();
		int playerY = player.getWorldY();
		FacingDirections playerFacing = player.currentDirection;

		Dialogue dialogue = dialogueManager.getDialogue(playerX, playerY, playerFacing, getCurrentMapID());
		if (dialogue != null) stateManager.setState(GameStates.DIALOGUE);
	}

	private void checkCutscene() {
		if (stateManager.getState() == GameStates.CUTSCENE) return;
		int playerX = player.getWorldX();
		int playerY = player.getWorldY();

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

	@Override
	public void update() {
		player.update(mapManager.getCurrentLayers(), entities);
		for (NPC npc : npcManager.getNPCs()) { npc.update(mapManager.getCurrentLayers(), entities); }

		checkMapTransition();
		checkCutscene();
		cameraManager.update();
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform originalTransform = g2.getTransform();
		g2.scale(SCALE, SCALE);
		
		mapManager.getCurrentLayers()[0].drawLayer(g2, cameraManager.getCameraX(), cameraManager.getCameraY()	);
		mapManager.getCurrentLayers()[1].drawLayer(g2, cameraManager.getCameraX(), cameraManager.getCameraY());
		mapManager.getCurrentLayers()[2].drawLayer(g2, cameraManager.getCameraX(), cameraManager.getCameraY());

		drawEntities(g2);

		mapManager.getCurrentLayers()[3].drawLayer(g2, cameraManager.getCameraX(), cameraManager.getCameraY());

		g2.setTransform(originalTransform);
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
			default -> {}
		}

	}

	@Override
	public void keyPressed() {

		switch (keyHandler.getCurrentKey()) {
			case P -> stateManager.setState(GameStates.POKEDEX);
			default -> {}
		}

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
