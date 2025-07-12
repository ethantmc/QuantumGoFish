package qgfOracle;

public class QuantumGoFish {
	public static void main(String[] args) {
		GameState gameState = new GameState();

		Player alice = new HumanPlayer("Alice");
		Player bob = new HumanPlayer("Bob");
		// add players
		gameState.addPlayer(alice);
		gameState.addPlayer(bob);
		// Give each player a card or two (for testing)
		gameState.getPlayerCount();
		alice.receiveCard(new Card());
		bob.receiveCard(new Card(Card.Suit.TWO));

		new UI(gameState);
	}
}
