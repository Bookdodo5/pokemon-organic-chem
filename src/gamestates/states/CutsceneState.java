package gamestates.states;

import assets.SoundManager;
import cutscene.Cutscene;
import cutscene.CutsceneManager;
import cutscene.cutsceneAction.DialogueCutsceneAction;
import cutscene.cutsceneAction.ImageBoxCutsceneAction;
import dialogue.Dialogue;
import dialogue.DialogueRenderer;
import entity.Player;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import main.GameContentManager;
import menu.Settings;

public class CutsceneState extends GameState {

	private final CutsceneManager cutsceneManager;
	private final OverworldState overworldState;
	private final Player player;

	private Cutscene currentCutscene;

	public CutsceneState(StateManager stateManager, KeyBindingHandler keyHandler,
			GameContentManager gameContentManager) {
		super(stateManager, keyHandler, gameContentManager);
		this.cutsceneManager = gameContentManager.getCutsceneManager();
		this.player = gameContentManager.getPlayer();
		this.overworldState = (OverworldState) StateManager.states.get(GameStates.OVERWORLD);
	}

	@Override
	public void update() {
		if (currentCutscene == null) return;

		overworldState.update();
		currentCutscene.update();
		
		if(currentCutscene.isFinished()) {
			stateManager.setState(GameStates.OVERWORLD);
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		overworldState.draw(g2);
		currentCutscene.draw(g2);
	}

	@Override
	public void onEnter(GameStates prevState) {
		player.setAcceptInput(false);
		if(prevState == GameStates.BATTLE) return;

		int playerX = player.getWorldX();
		int playerY = player.getWorldY();

		currentCutscene = cutsceneManager.getCutscene(playerX, playerY, overworldState.getCurrentMapID());
		if (currentCutscene != null) {
			currentCutscene.start();
		}
	}

	@Override
	public void keyTapped() {

		boolean isDialogue = currentCutscene.getCurrentAction() instanceof DialogueCutsceneAction;
		boolean isImageBox = currentCutscene.getCurrentAction() instanceof ImageBoxCutsceneAction;
		if (!isDialogue && !isImageBox) return;

		if(isImageBox && keyHandler.getCurrentKey() == Keys.INTERACT) {
			currentCutscene.getCurrentAction().end();
			return;
		}

		DialogueCutsceneAction dialogueAction = (DialogueCutsceneAction) currentCutscene.getCurrentAction();
		DialogueRenderer dialogueRenderer = dialogueAction.getDialogueRenderer();
		Dialogue dialogue = dialogueAction.getDialogue();

		if(!dialogueRenderer.isAnimationFinished()) { return; }

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
					dialogueAction.setDialogue(dialogue.getCurrentOption().getNextDialogue());
					dialogueRenderer.setRenderingDialogue(dialogue);
				}
				else if (dialogue.isFinalPage()) {
					dialogue.resetPage();
					dialogueAction.end();
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
		boolean isDialogue = currentCutscene.getCurrentAction() instanceof DialogueCutsceneAction;
		if (!isDialogue) return;

		DialogueCutsceneAction dialogueAction = (DialogueCutsceneAction) currentCutscene.getCurrentAction();
		DialogueRenderer dialogueRenderer = dialogueAction.getDialogueRenderer();

		if (keyHandler.getCurrentKey() == Keys.INTERACT)
			dialogueRenderer.setTextAnimationSpeed(Settings.getInstance().getTextSpeed().holdSpeed);

	}

	@Override
	public void keyReleased(Keys key) {
		boolean isDialogue = currentCutscene.getCurrentAction() instanceof DialogueCutsceneAction;
		if (!isDialogue) return;

		DialogueCutsceneAction dialogueAction = (DialogueCutsceneAction) currentCutscene.getCurrentAction();
		DialogueRenderer dialogueRenderer = dialogueAction.getDialogueRenderer();

		if (keyHandler.getCurrentKey() == Keys.INTERACT)
			dialogueRenderer.setTextAnimationSpeed(Settings.getInstance().getTextSpeed().baseSpeed);
	}

	@Override
	public void onExit(GameStates nextState) {
		player.setAcceptInput(true);
		if(nextState == GameStates.OVERWORLD) {
			currentCutscene.reset();
			currentCutscene = null;
		}
	}

}
