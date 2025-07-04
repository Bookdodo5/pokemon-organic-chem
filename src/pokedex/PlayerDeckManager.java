package pokedex;

import battle.BattleData;
import battle.cards.Card;
import battle.cards.CardFactory;
import battle.cards.Deck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDeckManager {

	Map<String, Integer> availableCards;
	List<String> customDeck;

	public PlayerDeckManager() {
		availableCards = new HashMap<>();
		customDeck = new ArrayList<>();
		initializeAvailableCards();
	}

	private void initializeAvailableCards() {
	}

	public int getDeckSize() { return customDeck.size(); }

	public int getAvailableCardTypes() { return availableCards.size(); }

	public List<String> getAvailableCards() {
		return new ArrayList<>(availableCards.keySet())
			.stream()
			.sorted()
			.toList();
	}

	public int getCardCount(String card) { return availableCards.get(card); }

	public void addToCollection(String card, int amount) {
		availableCards.put(card, availableCards.getOrDefault(card, 0) + amount);
	}

	private boolean removeFromCollection(String card) {
		availableCards.put(card, availableCards.getOrDefault(card, 0) - 1);
		if (availableCards.get(card) < 0) {
			availableCards.remove(card);
			return false;
		}
		if (availableCards.get(card) == 0) availableCards.remove(card);
		return true;
	}

	public boolean addToDeck(String card) {
		if(customDeck.size() >= 16) return false;
		if (removeFromCollection(card)) {
			customDeck.add(card);
			return true;
		}
		return false;
	}

	public boolean removeFromDeck(int index) {
		if (index >= 0 && index < customDeck.size()) {
			String card = customDeck.remove(index);
			addToCollection(card, 1);
			return true;
		}
		return false;
	}

	public List<String> getCustomDeck() { return customDeck; }

	public void setCustomDeck(List<String> customDeck) { this.customDeck = customDeck; }

	public Deck buildDeck(List<String> customDeck, List<String> battleSpecificCards) {
		List<Card> battleDeck = new ArrayList<>();
		for (String card : battleSpecificCards) { battleDeck.add(CardFactory.create(card)); }
		for (String card : customDeck) { battleDeck.add(CardFactory.create(card)); }
		return new Deck(battleDeck);
	}

	public Deck buildPlayerDeck(BattleData data) {
		return buildDeck(customDeck, data.getBattleSpecificCards());
	}
	
	public Deck buildOpponentDeck(BattleData data) {
		return buildDeck(data.getOpponentDeck(), data.getBattleSpecificCards());
	}
}
