package qgfOracle;

import java.util.HashSet;

public class Hand {
	private HashSet<Card> cards = new HashSet<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public HashSet<Card> getCards() {
		return cards;
	}

	public boolean removeCard(Card card) {
		return cards.remove(card);
	}

}
