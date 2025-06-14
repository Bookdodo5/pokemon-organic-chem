package battle;

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
		initializeDefaultDeck();
	}

	private void initializeAvailableCards() {
		addToCollection("Fish", 5);
		addToCollection("Cl2", 3);
		addToCollection("Br2", 3);
		addToCollection("Sunny Day", 2);
		addToCollection("Rain Dance", 2);
	}

	private void initializeDefaultDeck() {
		for (String card : availableCards.keySet()) {
			for (int i = 0; i < availableCards.get(card); i++) { customDeck.add(card); }
		}
	}

	private void addToCollection(String card, int amount) {
		availableCards.put(card, availableCards.getOrDefault(card, 0) + amount);
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
