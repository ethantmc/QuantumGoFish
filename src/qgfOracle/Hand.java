package qgfOracle;

import java.util.HashSet;
import java.util.Set;

public class Hand {
	private Set<Card> cards = new HashSet<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public Set<Card> getCards() {
		return cards;
	}

	public boolean removeCard(Card card) {
		return cards.remove(card);
	}

}
