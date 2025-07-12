package qgfOracle;

import java.util.HashSet;

public final class Card {
	private HashSet<Integer> possibleSuitsbyID = new HashSet<>();
	public Card(HashSet<Integer> possibleSuitsbyID) {
		this.addPossibleSuitsbyID(possibleSuitsbyID);
	}

	public void addPossibleSuitsbyID(HashSet<Integer> possibleSuitsbyID) {
		this.possibleSuitsbyID.addAll(possibleSuitsbyID);
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj; // instance equality only
	}

	public HashSet<Integer> getPossibleSuitsbyID() {
		return possibleSuitsbyID; // not very useful
	}
	public boolean isCollapsed() {

		return possibleSuitsbyID.size() == 1; // HashSet uses a counter for size that it updates when the set membership changes, so size() is O(1).
	}
}
