package qgfOracle;

import java.util.UUID;

public class HumanPlayer implements Player {
	private final PlayerID id;
	private final String name;
	private final Hand hand;

	public HumanPlayer(String name) {
		this.name = name;
		this.hand = new Hand();
		this.id = new PlayerID(UUID.randomUUID(), IdGenerator.nextPlayerId());
	}

	@Override
	public Hand getHand() {
		return hand;
	}

	@Override
	public PlayerID getID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void receiveCard(Card card) {
		hand.addCard(card);
	}
}
