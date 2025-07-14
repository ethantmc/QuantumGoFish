/**
 * 
 */
package qgfOracle;
import java.util.UUID;
/**
 * @author Ethan Mc Donald
 *
 */
public interface Player {
	record PlayerID(UUID id, Integer index) {
		@Override
		public String toString() {
			return "P" + index + " (" + id + ")";
		}
	};
	// Called when someone asks this player about a label
	Hand getHand();

	PlayerID getID();

	String getName();
	// Called when this player receives a card
	void receiveCard(Card card);
}
