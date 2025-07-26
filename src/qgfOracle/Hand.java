package qgfOracle;
/**
 * @author Ethan Mc Donald
 *
 */
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Hand {
	private Set<Card> cards = new HashSet<>();

	public void addCard(Card card) { cards.add(card); }

	public Set<Card> getCards() { return cards; }
	public String getCardsPrintable() {
		return cards.stream().map(Card::toString)
				.collect(Collectors.joining(", ")); // put a comma between each
													// hand. Eventually do UI
													// elements for each card
	}
	public boolean removeCard(Card card) { return cards.remove(card); }

}
