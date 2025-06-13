package dialogue;

import entity.FacingDirections;
import java.util.HashMap;
import java.util.Map;

public class DialogueManager {

	private final Map<String, Dialogue> dialogues;

	public DialogueManager() {
		this.dialogues = new HashMap<>();
		initializeDialogue();
	}

	private void initializeDialogue() {
		Dialogue endDialogue = new Dialogue(new String[] {
				"Thank you for your interest in organic chemistry!"
		});

		Dialogue advancedDialogue = new Dialogue(new String[] {
				"Advanced topics include:", "1. Stereochemistry", "2. Reaction mechanisms", "3. Spectroscopy"
		});

		Dialogue basicDialogue = new Dialogue(new String[] {
				"Let's start with the basics:", "1. Atomic structure", "2. Chemical bonding", "3. Molecular geometry"
		});

		Dialogue mainDialogue = new Dialogue(new String[] {
				"""
				Welcome to the Organic Chemistry Lab!
				Welcome to the Organic Chemistry Lab!
				Welcome to the Organic Chemistry Lab!
				""", "What would you like to learn about?"
		}, new DialogueOption[] {
				new DialogueOption("Basic concepts", basicDialogue),
				new DialogueOption("Advanced topics", advancedDialogue),
				new DialogueOption("I'm just looking around", endDialogue)
		});

		dialogues.put(getDialogueKey(4, 4, FacingDirections.UP, "outside"), mainDialogue);
	}

	private String getDialogueKey(int x, int y, FacingDirections facingDirections, String map) {
		return x + " " + y + " " + facingDirections + " " + map;
	}

	public Dialogue getDialogue(int x, int y, FacingDirections facingDirections, String map) {
		return dialogues.get(getDialogueKey(x, y, facingDirections, map));
	}
}
