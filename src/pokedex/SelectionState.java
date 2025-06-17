package pokedex;

public class SelectionState {

    private int tabIndex;
    private int scrollIndex;
    private int focusIndex;
    private int cardIndex;
    private SelectionArea area;
    private PokedexTab currentTab;

    public SelectionState() {
        this.tabIndex = 0;
        this.scrollIndex = 0;
        this.focusIndex = 0;
        this.cardIndex = 0;
        this.area = SelectionArea.TAB_SELECTION;
        this.currentTab = PokedexTab.DECK;
    }

    public SelectionArea getArea() { return area; }

    public int getTabIndex() { return tabIndex; }

    public int getScrollIndex() { return scrollIndex; }

    public int getFocusIndex() { return focusIndex; }

    public int getCardIndex() { return cardIndex; }
    
    public void setArea(SelectionArea area) { this.area = area; }

    public void setTabIndex(int tabIndex) { this.tabIndex = tabIndex; }

    public void setScrollIndex(int scrollIndex) { this.scrollIndex = scrollIndex; }

    public void setFocusIndex(int focusIndex) { this.focusIndex = focusIndex; }

    public void setCardIndex(int cardIndex) { this.cardIndex = cardIndex; }

    public void setCurrentTab(PokedexTab currentTab) { this.currentTab = currentTab; }

    public PokedexTab getCurrentTab() { return currentTab; }
}
