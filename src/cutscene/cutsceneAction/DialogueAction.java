package cutscene.cutsceneAction;

import cutscene.CutsceneAction;
import dialogue.Dialogue;
import dialogue.DialogueRenderer;
import java.awt.Graphics2D;

public class DialogueAction implements CutsceneAction {

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
}
