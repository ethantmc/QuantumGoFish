package qgfOracle;

import java.util.Set;
import java.util.function.Supplier;

public class CardFactory {
	private final Supplier<Set<Integer>> suitIdSupplier;

	public CardFactory(Supplier<Set<Integer>> suitIdSupplier) {
		this.suitIdSupplier = suitIdSupplier;
	}

	public Card createCard() { return new Card(suitIdSupplier.get()); }
}
