package battle;

import battle.cards.Card;
import battle.cards.Deck;
import battle.conditions.ConditionBoard;
import battle.molecules.Molecule;
import battle.reactions.Reaction;
import java.util.LinkedList;
import java.util.Queue;
import pokedex.MoleculeRecord;

public class BattlePlayer extends EffectApplicable {

	private final Battle battle;
	private final Molecule originalMolecule;
	private Molecule molecule;
	private Reaction selectedReaction;
	private Queue<Card> playerQueue;
	private int refillLP;
	private int currentLP;
	private Deck deck;

	private final MoleculeRecord moleculeRecord;

	public BattlePlayer(Deck deck, Molecule molecule, Battle battle, MoleculeRecord moleculeRecord) {
		this.deck = deck;
		this.refillLP = 3;
		this.molecule = molecule;
		this.originalMolecule = molecule;
		this.selectedReaction = null;
		this.battle = battle;
		this.moleculeRecord = moleculeRecord;
	}

	public void setDeck(Deck deck) { this.deck = deck; }

	public Molecule getOriginalMolecule() { return originalMolecule; }

	public void refillLP() {
		currentLP = refillLP;
	}

	public void setRefillLP(int refillLP) { this.refillLP = refillLP; }

	public int getRefillLP() { return refillLP; }

	public void gainLP(int amount) {
		currentLP += amount;
	}

	public void newTurn() {
		deck.discardHand();
		deck.draw(6);
		deck.shuffle();
		
		refillLP();
		triggerTurn();

		playerQueue = new LinkedList<>();
		selectedReaction = null;
	}

	public int getCurrentLP() { return currentLP; }

	public void spend(int cost) { currentLP -= cost; }

	public boolean canPlay(int handIndex) {
		Card card = deck.getHand().get(handIndex);
		return card.canPlay(this, battle.getBoard(), battle);
	}

	public void playCard(int handIndex, ConditionBoard board) {
		Card card = deck.getHand().get(handIndex);
		card.play(this, board, handIndex);
		playerQueue.add(card);
	}

	public Deck getDeck() { return deck; }

	public Molecule getMolecule() { return molecule; }

	public Reaction getSelectedReaction() { return selectedReaction; }

	public void setMolecule(Molecule molecule) {
		this.molecule = molecule;
		moleculeRecord.addMolecule(molecule);
	}

	public void setSelectedReaction(Reaction selectedReaction) { this.selectedReaction = selectedReaction; }

	public boolean isQueueEmpty() { return playerQueue.isEmpty(); }

	public void addToQueue(Card card) { playerQueue.add(card); }

	public boolean isPlayedBefore(BattlePlayer opponent) {
		if(isQueueEmpty()) return false;
		if(opponent.isQueueEmpty()) return true;
		return opponent.getMolecule().isPlayedBefore(molecule);
	}

	public Card getTopCard() {
		return playerQueue.poll();
	}

	public int getHandSize() {
	    return deck.getHand().size();
	}
	
	public int getReactionCount() {
	    return molecule.getReactionCount();
	}
}
