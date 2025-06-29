package battle;

import battle.battlephases.BattlePhases;
import battle.cards.Deck;
import battle.conditions.ConditionBoard;
import battle.event.BattleEventManager;
import battle.event.DialogueBattleEvent;
import battle.molecules.Molecule;
import battle.molecules.MoleculeFactory;
import dialogue.Dialogue;
import gamestates.GameStates;
import gamestates.StateManager;
import gamestates.states.BattleState;
import pokedex.MoleculeRecord;
import pokedex.PlayerDeckManager;

public class Battle {
    private final ConditionBoard board;
    private final BattlePlayer player, opponent;
    private final Molecule targetMolecule;
    private final BattleEventManager eventManager;
    private final BattleState battleState;
    private BattlePhases currentPhase;
    private final String battleTheme;
    private int turn;

    public Battle(PlayerDeckManager playerDeckManager, BattleData data, StateManager stateManager, MoleculeRecord moleculePokedexManager) {
	board = new ConditionBoard();
	this.eventManager = new BattleEventManager();

	Deck playerDeck = playerDeckManager.buildPlayerDeck(data);
	Deck opponentDeck = playerDeckManager.buildOpponentDeck(data);

	Molecule playerMolecule = MoleculeFactory.create(data.getPlayerMolecule());
	Molecule opponentMolecule = MoleculeFactory.create(data.getOpponentMolecule());

    moleculePokedexManager.addMolecule(playerMolecule);
    moleculePokedexManager.addMolecule(opponentMolecule);

	player = new BattlePlayer(playerDeck, playerMolecule, this, moleculePokedexManager);
	opponent = new BattlePlayer(opponentDeck, opponentMolecule, this, moleculePokedexManager);
	currentPhase = BattlePhases.CONDITION_PLAY;

	this.targetMolecule = MoleculeFactory.create(data.getTargetMolecule());
	this.battleTheme = data.getBattleTheme();
	this.battleState = (BattleState) StateManager.states.get(GameStates.BATTLE);
	this.turn = 0;
    }

    public BattlePhases getCurrentPhase() {
	return currentPhase;
    }

    public void setCurrentPhase(BattlePhases phase) {
	currentPhase = phase;
    }

    public BattlePlayer getPlayer() {
	return player;
    }

    public BattlePlayer getOpponent() {
	return opponent;
    }

    public ConditionBoard getBoard() {
	return board;
    }

    public BattleEventManager getEventManager() {
	return eventManager;
    }

    public Molecule getTargetMolecule() {
	return targetMolecule;
    }

    public String getBattleTheme() {
	return battleTheme;
    }

    public int getTurn() {
	return turn;
    }

    public void nextTurn() {
	turn++;
    }

    public void checkWin() {
        if (player.getMolecule().getName().equals(targetMolecule.getName())) {
            win();
        }
        else if (opponent.getMolecule().getName().equals(targetMolecule.getName())) {
            lose(false);
        }
    }

    public void win() {
        eventManager.addEvent(new DialogueBattleEvent(
                new Dialogue( new String[] {
                    "You successfully synthesized the target molecule!\nYou win the battle!",
                }
        )));
        battleState.getPhaseManager().setPhase(BattlePhases.BATTLE_WIN);
    }

    public void lose(boolean run) {
        eventManager.addEvent(new DialogueBattleEvent(
                new Dialogue( new String[] {
                    run ? "You ran away cowardly.\nYou lose the battle!" :
                    "You failed to synthesize the target molecule before your opponent.\nYou lose the battle!",
                }
        )));
        battleState.getPhaseManager().setPhase(BattlePhases.BATTLE_LOSE);
    }
}
