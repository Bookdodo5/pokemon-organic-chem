package battle.battlephases.phases.play;

import assets.SoundManager;
import battle.BattlePlayer;
import input.KeyBindingHandler;

public class BattleActionSelection {

    private final KeyBindingHandler keyHandler;
    private final BattlePlayer player;
    private int selectionIndex;
    private int focusIndex;

    private ActionPages currentPage;
    private BattleAction action;

    public BattleActionSelection(BattlePlayer player, KeyBindingHandler keyHandler) {
	this.currentPage = ActionPages.MAIN;
        this.player = player;
        this.selectionIndex = 0;
        this.focusIndex = 0;
        this.keyHandler = keyHandler;
    }

    public BattleAction getAction() {
        return action;
    }

    public int getSelectionIndex() {
        return selectionIndex;
    }

    public int getFocusIndex() {
        return focusIndex;
    }

    public void setSelectionIndex(int selectionIndex) {
        this.selectionIndex = selectionIndex;
    }
    
    public ActionPages getCurrentPage() {
        return currentPage;
    }

    public void keyTapped() {
        switch (keyHandler.getCurrentKey()) {
            case UP -> handleUp();
            case DOWN -> handleDown();
            case LEFT -> handleLeft();
            case RIGHT -> handleRight();
            case INTERACT -> handleInteract();
            default -> {}
        }
    }

    public void handleUp() {
        SoundManager.getSfxplayer().playSE("GameCursor");
        switch (currentPage) {
            case MAIN -> selectionIndex = 0;
            case CARDS -> selectionIndex = (selectionIndex >= 4) ? selectionIndex - 4 : selectionIndex;
            case REACTIONS -> {
                selectionIndex = (selectionIndex >= 1) ? selectionIndex - 1 : selectionIndex;
                if(selectionIndex < focusIndex) focusIndex--;
            }
        }
    }

    public void handleDown() {
        SoundManager.getSfxplayer().playSE("GameCursor");
        switch (currentPage) {
            case MAIN -> selectionIndex = 1;
            case CARDS -> {
                if (selectionIndex >= 4) return;
                selectionIndex = Math.min(selectionIndex + 4, player.getHandSize() - 1);
            }
            case REACTIONS -> {
                selectionIndex = Math.min(selectionIndex + 1, player.getReactionCount() - 1);
                if(selectionIndex > focusIndex + 2) focusIndex++;
            }
        }
    }

    public void handleLeft() {
        SoundManager.getSfxplayer().playSE("GUIMenuOpen");
        switch (currentPage) {
            case MAIN -> {
                currentPage = ActionPages.REACTIONS;
                selectionIndex = player.getReactionCount() - 1;
                focusIndex = Math.max(0, Math.min(selectionIndex, player.getReactionCount() - 3));
            }
            case REACTIONS -> {
                currentPage = ActionPages.CARDS;
                selectionIndex = player.getHandSize() - 1;
            }
            case CARDS -> {
                if (selectionIndex == 0) {
                    currentPage = ActionPages.MAIN;
                    return;
                }
                selectionIndex = selectionIndex - 1;
            }
                
        }
    }

    public void handleRight() {
        SoundManager.getSfxplayer().playSE("GUIMenuOpen");
        switch (currentPage) {
            case MAIN -> {
                currentPage = ActionPages.CARDS;
                selectionIndex = 0;
            }
            case CARDS -> {
                if (selectionIndex == player.getHandSize() - 1) {
                    currentPage = ActionPages.REACTIONS;
                    selectionIndex = 0;
                    focusIndex = 0;
                    return;
                }
                selectionIndex = selectionIndex + 1;
            }
            case REACTIONS -> {
                currentPage = ActionPages.MAIN;
                selectionIndex = 0;
            }
        }
    }

    public void handleInteract() {
        SoundManager.getSfxplayer().playSE("GUIConfirm");
        action = null;
        switch (currentPage) {
            case MAIN -> action = selectionIndex == 0 ? BattleAction.END_PHASE : BattleAction.RUN;
            case CARDS -> {
                if(player.getHandSize() != 0) {
                    action = BattleAction.CARD_PLAY;
                    selectionIndex = Math.min(selectionIndex, player.getHandSize() - 1);
                }
            }
            case REACTIONS -> {
                 if(player.getReactionCount() != 0) {
                    action = BattleAction.REACTION_PLAY;
                 }
            }
        }
    }
}
