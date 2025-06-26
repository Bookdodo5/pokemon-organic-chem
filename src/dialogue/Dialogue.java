package dialogue;

public class Dialogue {
	private int currentPage;
	private final String[] pages;
	private DialogueOption[] options;

	private int selectionIndex = 0;

	public Dialogue(String[] pages) {
		this(pages, new DialogueOption[0]);
	}

	public Dialogue(String page) {
		this(new String[] { page });
	}

	public Dialogue(String page, DialogueOption... options) {
		this(new String[] { page }, options);
	}

	public Dialogue(String[] pages, DialogueOption... options) {
		currentPage = 0;
		this.pages = pages;
		this.options = options;
	}

	public String getCurrentPage() {
		if (currentPage >= pages.length) return "";
		return pages[currentPage];
	}

	public int getSelectionIndex() { return selectionIndex; }

	public void nextSelectionIndex() {
		if (!hasOption()) return;
		selectionIndex = (selectionIndex + 1) % options.length;
	}

	public void previousSelectionIndex() {
		if (!hasOption()) return;
		selectionIndex = (selectionIndex + options.length - 1) % options.length;
	}

	public boolean isFinalPage() { return currentPage >= pages.length - 1; }

	public void nextPage() { currentPage++; }

	public void resetPage() { currentPage = 0; selectionIndex = 0; }

	public boolean hasOption() { return options.length > 0; }

	public DialogueOption[] getOptions() { return options; }

	public DialogueOption getCurrentOption() { 
		if(selectionIndex >= options.length) return null;
		return options[selectionIndex];
	}

	public boolean canShowOptions() { return isFinalPage() && hasOption(); }
}
