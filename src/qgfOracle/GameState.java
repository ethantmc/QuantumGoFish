package qgfOracle;
/**
 * @author Ethan Mc Donald
 *
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameState {
	private List<Player> players = new ArrayList<>();
	private int currentPlayerIndex = 0;

	public void addPlayer(Player player) { players.add(player); }

	public void advanceToNextPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}
	public void dealInitialHands(CardFactory factory, int cardsPerPlayer) {
		for (Player player : players) {
			for (int i = 0; i < cardsPerPlayer; i++) {
				Card card = factory.createCard();
				player.receiveCard(card);
			}
		}
	}

	public Set<Integer> getActiveSuitIds() {
		return players.stream().map(p -> p.getID().index())
				.collect(Collectors.toSet());
	}

	public Player getCurrentPlayer() { return players.get(currentPlayerIndex); }
	public Player getPlayerById(Player.PlayerID id) {
		return players.stream().filter(p -> p.getID().equals(id)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						"No player with ID: " + id));
	}

	public int getPlayerCount() { return players.size(); }
	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public void resetGame() {
		// TODO Auto-generated method stub
		return;
	}
}
