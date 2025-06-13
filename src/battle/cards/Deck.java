package battle.cards;

import battle.BattleConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	private final List<Card> drawPile, hand, discardPile;

	public Deck(List<Card> cards) {
		drawPile = new ArrayList<>(cards);
		Collections.shuffle(drawPile);
		hand = new ArrayList<>();
		discardPile = new ArrayList<>();
	}

	public void shuffle() {
		drawPile.addAll(discardPile);
		discardPile.clear();
		Collections.shuffle(drawPile);
	}

	public void draw() {
		if (hand.size() >= BattleConstants.HAND_SIZE) return;
		if (drawPile.isEmpty()) shuffle();
		hand.add(drawPile.remove(0));
	}

	public void draw(int amount) {
		for(int i = 0; i < amount; i++) {
			draw();
		}
	}

	public void discard(int handIndex) {
		discardPile.add(hand.remove(handIndex));
	}

	public void discardHand() {
		discardPile.addAll(hand);
		hand.clear();
	}

	public void removeCard(int handIndex) {
		hand.remove(handIndex);
	}

	public List<Card> getAllCards() {
		List<Card> allCards = new ArrayList<>();
		allCards.addAll(discardPile);
		allCards.addAll(hand);
		allCards.addAll(drawPile);
		return allCards;
	}

	public List<Card> getHand() {
		return hand;
	}

	public List<Card> getDiscardPile() {
		return discardPile;
	}

	public List<Card> getDrawPile() {
		return drawPile;
	}

	public void addToDrawPile(Card card) {
		drawPile.add(card);
	}
}
