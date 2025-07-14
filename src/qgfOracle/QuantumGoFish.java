package qgfOracle;

public class QuantumGoFish {
	public static void main(String[] args) {
		GameState gameState = new GameState();

		gameState.addPlayer(new HumanPlayer("Thomas"));
		gameState.addPlayer(new HumanPlayer("Logan"));
		gameState.addPlayer(new HumanPlayer("Ethan"));
		gameState.addPlayer(new HumanPlayer("Daniel"));
		CardFactory cardFactory = new CardFactory(() -> gameState.getActiveSuitIds());
		// give initial hands
		gameState.dealInitialHands(cardFactory, 4);

		new UI(gameState);
	}
}
