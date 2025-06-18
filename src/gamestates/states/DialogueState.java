package gamestates.states;

import assets.SoundManager;
import dialogue.Dialogue;
import dialogue.DialogueManager;
import dialogue.DialogueRenderer;
import entity.FacingDirections;
import entity.Player;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import main.GameContentManager;
import menu.Settings;

public class DialogueState extends GameState {

	private final DialogueManager dialogueManager;
	private final DialogueRenderer dialogueRenderer;
	private final OverworldState overworldState;
	private final Player player;

	private Dialogue dialogue;
	private boolean waitForInput = false;

	public DialogueState(StateManager stateManager, KeyBindingHandler keyHandler,
			GameContentManager gameContentManager) {
		super(stateManager, keyHandler, gameContentManager);
		this.dialogueManager = gameContentManager.getDialogueManager();
		this.dialogueRenderer = new DialogueRenderer();
		this.player = gameContentManager.getPlayer();
		this.overworldState = (OverworldState) StateManager.states.get(GameStates.OVERWORLD);
	}

	public void setDialogue(Dialogue dialogue) { this.dialogue = dialogue; }

	@Override
	public void update() {

		dialogueRenderer.update();
		waitForInput = dialogueRenderer.isAnimationFinished();

	}

	@Override
	public void draw(Graphics2D g2) {

		if (overworldState != null) {
			overworldState.draw(g2);
		}

		if (dialogue != null) {
			dialogueRenderer.renderDialogue(g2);
		}

	}

	@Override
	public void onEnter(GameStates prevState) {
		int playerX = player.getMapX();
		int playerY = player.getMapY();
		FacingDirections playerFacing = player.currentDirection;

		dialogue = dialogueManager.getDialogue(playerX, playerY, playerFacing, overworldState.getCurrentMapID());
		dialogueRenderer.setRenderingDialogue(dialogue);
		SoundManager.getSfxplayer().playSE("GameCursor");
	}

	@Override
	public void keyTapped() {
		if (!waitForInput) return;

		switch (keyHandler.getCurrentKey()) {
			case UP -> {
				if (!dialogueRenderer.showingOption()) break;
				dialogue.previousSelectionIndex();
				SoundManager.getSfxplayer().playSE("GameCursor");
			}
			case DOWN -> {
				if (!dialogueRenderer.showingOption()) break;
				dialogue.nextSelectionIndex();
				SoundManager.getSfxplayer().playSE("GameCursor");
			}
			case INTERACT -> {
				if (dialogue.canShowOptions()) {
					dialogue.resetPage();
					setDialogue(dialogue.getCurrentOption().getNextDialogue());
					dialogueRenderer.setRenderingDialogue(dialogue);
				}
				else if (dialogue.isFinalPage()) {
					dialogue.resetPage();
					stateManager.setState(GameStates.OVERWORLD);
				}
				else {
					dialogue.nextPage();
					dialogueRenderer.setRenderingDialogue(dialogue);
				}
				SoundManager.getSfxplayer().playSE("GameCursor");
			}
			default -> {}
		}
	}

	@Override
	public void keyPressed() {
		if (keyHandler.getCurrentKey() == Keys.INTERACT)
			dialogueRenderer.setTextAnimationSpeed(Settings.getInstance().getTextSpeed().holdSpeed);

	}

	@Override
	public void keyReleased(Keys key) {
		if (keyHandler.getCurrentKey() == Keys.INTERACT)
			dialogueRenderer.setTextAnimationSpeed(Settings.getInstance().getTextSpeed().baseSpeed);
	}

	@Override
	public void onExit(GameStates nextState) {
		if (dialogue != null) {
			dialogue = null;
			waitForInput = false;
			SoundManager.getSfxplayer().playSE("GameCursor");
		}
	}

}
