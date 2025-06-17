package pokedex;

public enum PokedexTab {
    DECK,
    REACTION,
    MOLECULE;

    public int getScrollDisplay() {
        return switch (this) {
            case DECK -> 7;
            case REACTION -> 7;
            case MOLECULE -> 7;
        };
    }
}
