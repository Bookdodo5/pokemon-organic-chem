package cutscene.cutsceneAction;

import assets.SoundManager;
import cutscene.InputCutsceneAction;
import dialogue.Dialogue;
import dialogue.DialogueRenderer;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import menu.Settings;

public class DialogueAction implements InputCutsceneAction {

	private final DialogueRenderer dialogueRenderer;
	private final Dialogue originalDialogue;
	private Dialogue dialogue;
	private boolean isFinished;
	
	public DialogueAction(Dialogue dialogue) {
		this.originalDialogue = dialogue;
		this.dialogue = dialogue;
		this.dialogueRenderer = new DialogueRenderer();
		isFinished = false;
	}

	public void setDialogue(Dialogue dialogue) { this.dialogue = dialogue; }

	public Dialogue getDialogue() { return dialogue; }

	public DialogueRenderer getDialogueRenderer() { return dialogueRenderer; }

	@Override
	public void start() {
		dialogueRenderer.setRenderingDialogue(dialogue);
	}

	@Override
	public void update() {
		dialogueRenderer.update();
	}

	@Override
	public void end() {
		isFinished = true;
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public void draw(Graphics2D g2) {
		dialogueRenderer.renderDialogue(g2);
	}

	@Override
	public void reset() {
		isFinished = false;
		dialogue = originalDialogue;
		dialogue.resetPage();
	}	

	@Override
	public void keyTapped(KeyBindingHandler keyHandler) {
		if(isFinished) return;
		if(!dialogueRenderer.isAnimationFinished()) return;

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
					dialogue.getCurrentOption().execute();
					setDialogue(dialogue.getCurrentOption().getNextDialogue());
					dialogueRenderer.setRenderingDialogue(dialogue);
				}
				else if (dialogue.isFinalPage()) {
					dialogue.resetPage();
					end();
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
	public void keyPressed(KeyBindingHandler keyHandler) {
		if (keyHandler.getCurrentKey() == Keys.INTERACT) {
			dialogueRenderer.setTextAnimationSpeed(
				Settings.getInstance().getTextSpeed().holdSpeed
			);
		}
	}

	@Override
	public void keyReleased(Keys key) {
		if (key == Keys.INTERACT) {
			dialogueRenderer.setTextAnimationSpeed(
				Settings.getInstance().getTextSpeed().baseSpeed
			);
		}
	}
}
