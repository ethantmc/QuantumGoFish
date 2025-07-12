package qgfOracle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {
	private List<Player> players = new ArrayList<>();
	private int currentPlayerIndex = 0;

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void advanceToNextPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
	}

	public Player getPlayerById(Player.PlayerID id) {
		return players.stream().filter(p -> p.getID().equals(id)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No player with ID: " + id));
	}

	public int getPlayerCount() {
		return players.size();
	}
	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
