package dialogue;

public class Dialogue {
	private int currentPage;
	private final String[] pages;
	private DialogueOption[] options;
	private boolean hasOptions = false;

	private int selectionIndex = 0;

	public Dialogue(String[] pages) {
		currentPage = 0;
		this.pages = pages;
	}

	public Dialogue(String[] pages, DialogueOption[] options) {
		currentPage = 0;
		this.pages = pages;
		this.options = options;
		this.hasOptions = true;
	}

	public String getCurrentPage() {
		if (currentPage >= pages.length) return "";
		return pages[currentPage];
	}

	public int getSelectionIndex() { return selectionIndex; }

	public void nextSelectionIndex() {
		if (!hasOptions) return;
		selectionIndex = (selectionIndex + 1) % options.length;
	}

	public void previousSelectionIndex() {
		if (!hasOptions) return;
		selectionIndex = (selectionIndex + options.length - 1) % options.length;
	}

	public boolean isFinalPage() { return currentPage >= pages.length - 1; }

	public void nextPage() { currentPage++; }

	public void resetPage() { currentPage = 0; }

	public boolean hasOption() { return hasOptions; }

	public DialogueOption[] getOptions() { return options; }

	public DialogueOption getCurrentOption() { 
		if(selectionIndex >= options.length) return null;
		return options[selectionIndex];
	}

	public boolean canShowOptions() { return isFinalPage() && hasOption(); }
}
