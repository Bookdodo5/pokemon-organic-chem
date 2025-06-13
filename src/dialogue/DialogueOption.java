package dialogue;

import menu.Option;

public class DialogueOption extends Option{
	private final Dialogue nextDialogue;
	
	public DialogueOption(String text, Dialogue nextDialogue) {
		super(text);
		this.nextDialogue = nextDialogue;
	}

	@Override
	public void execute() {
	}
	
	public Dialogue getNextDialogue() {
		return nextDialogue;
	}
}
