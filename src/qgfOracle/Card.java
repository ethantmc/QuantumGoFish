package qgfOracle;
/**
 * @author Ethan Mc Donald
 *
 */
import java.util.HashSet;
import java.util.Set;

public final class Card {
	private Set<Integer> possibleSuitsbyID = new HashSet<>();
	public Card(int playerCount) {
		for (int i = 0; i < playerCount; i++) { this.possibleSuitsbyID.add(i); }
	}
	public Card(Set<Integer> set) { this.addPossibleSuitsbyID(set); }
	public void addPossibleSuitsbyID(Set<Integer> set) {
		this.possibleSuitsbyID.addAll(set);
	}
	@Override
	public boolean equals(Object obj) {
		return this == obj; // instance equality only
	}
	public Set<Integer> getPossibleSuitsbyID() {
		return possibleSuitsbyID; // not very useful
	}

	public boolean isCollapsed() {

		return possibleSuitsbyID.size() == 1; // HashSet uses a counter for size
												// that it updates when the set
												// membership changes, so size()
												// is O(1).
	}
	public void removePossibleSuitbyID(Integer ID) {
		this.possibleSuitsbyID.remove(ID);
	}
	public void removePossibleSuitsbyID(Set<Integer> set) {
		this.possibleSuitsbyID.removeAll(set);
	}
	@Override
	public String toString() {
		return possibleSuitsbyID.toString();

	}
}
