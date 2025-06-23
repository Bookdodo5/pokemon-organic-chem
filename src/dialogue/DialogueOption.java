package dialogue;

import menu.Option;

public class DialogueOption extends Option {
	private final Dialogue nextDialogue;
	private final Runnable customExecution;
	
	public DialogueOption(String text, Dialogue nextDialogue) {
		this(text, nextDialogue, null);
	}
	
	public DialogueOption(String text, Dialogue nextDialogue, Runnable customExecution) {
		super(text);
		this.nextDialogue = nextDialogue;
		this.customExecution = customExecution;
	}

	@Override
	public void execute() {
		if (customExecution != null) {
			customExecution.run();
		}
	}
	
	public Dialogue getNextDialogue() {
		return nextDialogue;
	}
}
