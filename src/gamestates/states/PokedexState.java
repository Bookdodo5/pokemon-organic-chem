package gamestates.states;

import assets.SoundManager;
import battle.molecules.Molecule;
import battle.reactions.Reaction;
import gamestates.GameState;
import gamestates.GameStates;
import gamestates.StateManager;
import input.KeyBindingHandler;
import input.Keys;
import java.awt.Graphics2D;
import java.util.List;
import main.GameContentManager;
import pokedex.PlayerDeckManager;
import pokedex.PokedexRenderer;
import pokedex.PokedexTab;
import pokedex.SelectionArea;
import pokedex.SelectionState;

public class PokedexState extends GameState {

    private boolean waitForInput;
    private boolean firstTimePressing;

    private final PlayerDeckManager playerDeckManager;
    private final List<Molecule> moleculeRecord;
    private final List<Reaction> reactionRecord;

    private final PokedexRenderer pokedexRenderer;

    private final SelectionState selection;
    private Molecule selectedMolecule;
    private Reaction selectedReaction;

    public PokedexState(StateManager stateManager, KeyBindingHandler keyHandler, GameContentManager gameContentManager) {
        super(stateManager, keyHandler, gameContentManager);

        this.playerDeckManager = gameContentManager.getPlayerDeckManager();
        this.moleculeRecord = gameContentManager.getMoleculeRecord().getMoleculeRecord();
        this.reactionRecord = gameContentManager.getReactionRecord().getAvailableReactionsList();
        this.selection = new SelectionState();
        this.selectedMolecule = null;
        this.selectedReaction = null;
        this.pokedexRenderer = new PokedexRenderer(playerDeckManager, moleculeRecord, reactionRecord, selection);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        pokedexRenderer.draw(g2, selectedMolecule, selectedReaction);
    }

    @Override
    public void onEnter(GameStates prevState) {
        SoundManager.getSfxplayer().playSE("GUIMenuOpen");
        waitForInput = false;
    }

    @Override
    public void keyPressed() {
        if(firstTimePressing) {
            firstTimePressing = false;
            return;
        }

        boolean isScroll = selection.getArea() == SelectionArea.SCROLL;
        boolean isBeginningOfScroll = selection.getScrollIndex() == 0;
        boolean isEndOfScroll = selection.getScrollIndex() + 1 == getMaxScrollIndex();

        if(keyHandler.pressingKey(Keys.DOWN) && isScroll && !isEndOfScroll) {
            handleDown();
        }
        else if(keyHandler.pressingKey(Keys.UP) && isScroll && !isBeginningOfScroll) {
            handleUp();
        }
    }

    @Override
    public void keyReleased(Keys key) {
        waitForInput = !keyHandler.pressingKey(Keys.P);
    }

    @Override
    public void onExit(GameStates nextState) {
        SoundManager.getSfxplayer().playSE("GUIMenuClose");
    }

    @Override
    public void keyTapped() {
        if (!waitForInput) return;

        firstTimePressing = true;

        switch (keyHandler.getCurrentKey()) {
            case Keys.ESCAPE ->
                stateManager.setState(GameStates.OVERWORLD);
            case Keys.P ->
                stateManager.setState(GameStates.OVERWORLD);
            case Keys.UP -> handleUp();
            case Keys.DOWN -> handleDown();
            case Keys.LEFT -> handleLeft();
            case Keys.RIGHT -> handleRight();
            case Keys.INTERACT -> handleInteract();
            default -> {}
        }

        System.out.println(selection.getArea() + " " + selection.getTabIndex() + " " + selection.getScrollIndex() + " " + selection.getFocusIndex() + " " + selection.getCardIndex());
    }

    private void handleUp() {
        switch (selection.getArea()) {
            case TAB_SELECTION -> {}
            case SCROLL -> {
                if (selection.getScrollIndex() > 0) {
                    selection.setScrollIndex(selection.getScrollIndex() - 1);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
                else {
                    selection.setArea(SelectionArea.TAB_SELECTION); 
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }

                if(selection.getScrollIndex() < selection.getFocusIndex()) {
                    selection.setFocusIndex(selection.getScrollIndex());
                }
            }
            case PLAYER_DECK -> {
                if (selection.getCardIndex() >= 4) {
                    selection.setCardIndex(selection.getCardIndex() - 4);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
                else {
                    selection.setArea(SelectionArea.SCROLL);
                    selection.setScrollIndex(getMaxScrollIndex() - 1);
                    selection.setFocusIndex(getMaxScrollIndex() - selection.getCurrentTab().getScrollDisplay());
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
            }
        }
    }

    private int getMaxScrollIndex() {
        return switch (selection.getCurrentTab()) {
            case DECK -> playerDeckManager.getAvailableCardTypes();
            case REACTION -> reactionRecord.size();
            case MOLECULE -> moleculeRecord.size();
        };
    }

    private void handleDown() {
        switch (selection.getArea()) {
            case TAB_SELECTION -> {
                selection.setArea(SelectionArea.SCROLL);
                selection.setScrollIndex(0);
                selection.setFocusIndex(0);
                SoundManager.getSfxplayer().playSE("GameCursor");
            }
            case SCROLL -> {
                int maxScrollIndex = getMaxScrollIndex();
                int maxDisplay = selection.getCurrentTab().getScrollDisplay();
                if (selection.getScrollIndex() + 1 < maxScrollIndex) {
                    selection.setScrollIndex(selection.getScrollIndex() + 1);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
                else if (selection.getCurrentTab() == PokedexTab.DECK) {
                    selection.setArea(SelectionArea.PLAYER_DECK);
                    selection.setCardIndex(0);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }

                if(selection.getScrollIndex() - selection.getFocusIndex() + 1 > maxDisplay) {
                    selection.setFocusIndex(selection.getFocusIndex() + 1);
                }
            }
            case PLAYER_DECK -> {
                if (selection.getCardIndex() + 4 < playerDeckManager.getDeckSize()) {
                    selection.setCardIndex(selection.getCardIndex() + 4);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
            }
        }
    }

    private void handleLeft() {
        switch (selection.getArea()) {
            case TAB_SELECTION -> {
                if (selection.getTabIndex() > 0) {
                    selection.setTabIndex(selection.getTabIndex() - 1);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
            }
            case SCROLL -> {}
            case PLAYER_DECK -> {
                if (selection.getCardIndex() >= 1) {
                    selection.setCardIndex(selection.getCardIndex() - 1);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
            }
        }
    }

    private void handleRight() {
        switch (selection.getArea()) {
            case TAB_SELECTION -> {
                if (selection.getTabIndex() < 2) {
                    selection.setTabIndex(selection.getTabIndex() + 1);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
            }
            case SCROLL -> {}
            case PLAYER_DECK -> {
                if (selection.getCardIndex() + 1 < playerDeckManager.getDeckSize()) {
                    selection.setCardIndex(selection.getCardIndex() + 1);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
            }
        }
    }

    private void handleInteract() {
        switch (selection.getArea()) {
            case TAB_SELECTION -> handleChangeTab();
            case SCROLL -> handleScrollClick();
            case PLAYER_DECK -> handleDeckClick();
        }
    }

    private void handleChangeTab() {
        selection.setCurrentTab(switch (selection.getTabIndex()) {
            case 0 -> PokedexTab.DECK;
            case 1 -> PokedexTab.REACTION;
            case 2 -> PokedexTab.MOLECULE;
            default -> selection.getCurrentTab();
        });
        selection.setScrollIndex(0);
        selection.setFocusIndex(0);
        selection.setCardIndex(0);
        SoundManager.getSfxplayer().playSE("GUIConfirm");
    }

    private void handleScrollClick() {
        int scrollIndex = selection.getScrollIndex();
        switch (selection.getCurrentTab()) {
            case DECK -> {
                String card = playerDeckManager.getAvailableCards().get(scrollIndex);
                if(playerDeckManager.addToDeck(card)) {
                    SoundManager.getSfxplayer().playSE("GameCursor");
                } else {
                    SoundManager.getSfxplayer().playSE("BattleDamageWeak");
                }
                if(selection.getScrollIndex() >= playerDeckManager.getAvailableCardTypes()) {
                    selection.setScrollIndex(playerDeckManager.getAvailableCardTypes() - 1);
                }
            }
            case REACTION -> {
                if (scrollIndex < reactionRecord.size()) {
                    selectedReaction = reactionRecord.get(scrollIndex);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
            }
            case MOLECULE -> {
                if (scrollIndex < moleculeRecord.size()) {
                    selectedMolecule = moleculeRecord.get(scrollIndex);
                    SoundManager.getSfxplayer().playSE("GameCursor");
                }
            }
        }
    }

    private void handleDeckClick() {
        int cardIndex = selection.getCardIndex();
        if(cardIndex < playerDeckManager.getDeckSize()) {
            playerDeckManager.removeFromDeck(cardIndex);
            SoundManager.getSfxplayer().playSE("GameCursor");
        }
        if(cardIndex >= playerDeckManager.getDeckSize()) {
            selection.setCardIndex(playerDeckManager.getDeckSize() - 1);
        }
        if(playerDeckManager.getDeckSize() == 0) {
            selection.setArea(SelectionArea.SCROLL);
            selection.setScrollIndex(getMaxScrollIndex() - 1);
            selection.setFocusIndex(getMaxScrollIndex() - selection.getCurrentTab().getScrollDisplay());
        }
    }
}
