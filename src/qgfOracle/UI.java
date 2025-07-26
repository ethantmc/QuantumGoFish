package qgfOracle;
/**
 * @author Ethan Mc Donald
 *
 */
import java.awt.BorderLayout;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class UI {
	private final GameState gameState;
	private JFrame frame;

	public UI(GameState gameState) {
		this.gameState = gameState;
		createUI();
	}

	private void createUI() {
		frame = new JFrame("Quantum Go Fish Oracle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 400);
		frame.setLayout(new BorderLayout());

		updateDisplay();

		frame.setVisible(true);
	}

	private void handleAskQuestion(Player current) {
		// Put together the Players for the combo box later
		List<Player> others = gameState.getPlayers().stream()
				.filter(p -> p != current).collect(Collectors.toList());

		Player[] playerArray = others.toArray(new Player[0]);

		Player selected = (Player) JOptionPane.showInputDialog(frame,
				"Select a player to ask:", "Choose Player",
				JOptionPane.PLAIN_MESSAGE, null, playerArray, playerArray[0]);

		if (selected == null) { return; }

		// Gather all known suit IDs
		Set<Integer> allSuitIDs = gameState.getPlayers().stream()
				.flatMap(p -> p.getHand().getCards().stream())
				.flatMap(card -> card.getPossibleSuitsbyID().stream())
				.collect(Collectors.toSet());

		Integer[] suitIDs = allSuitIDs.toArray(new Integer[0]);
		Integer suitAsked = (Integer) JOptionPane.showInputDialog(frame,
				"Choose a suit to ask about:", "Choose Suit",
				JOptionPane.PLAIN_MESSAGE, null, suitIDs, suitIDs[0]);

		if (suitAsked == null) { return; }
		// TODO: Validate that a player who asks about a suit could have a card
		// of that suit. If they could not, that's a paradox!

		int response = JOptionPane.showConfirmDialog(frame, selected.getName()
				+ ", do you have any cards of suit ID " + suitAsked + "?",
				"Answer", JOptionPane.YES_NO_OPTION);

		boolean hasCard = selected.getHand().getCards().stream().anyMatch(
				card -> card.getPossibleSuitsbyID().contains(suitAsked));

		long totalPossible = gameState.getPlayers().stream()
				.flatMap(p -> p.getHand().getCards().stream())
				.filter(card -> card.getPossibleSuitsbyID().contains(suitAsked))
				.count();

		boolean paradox = false;
		if ((response == JOptionPane.YES_OPTION) && !hasCard) {
			paradox = true; // Claimed to have, but doesn't
		}
		else if ((response == JOptionPane.NO_OPTION) && (totalPossible < 4)) {
			paradox = true; // Not enough cards could possibly be of this suit
		}
		// TODO: move this if(paradox) to a helper method.
		if (paradox) {
			int choice = JOptionPane.showOptionDialog(frame,
					"A Paradox has been created! Everybody loses!", "PARADOX",
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,
					new String[]{"Reset Game", "Continue"}, "Continue");

			if (choice == 0) {
				gameState.resetGame(); // Placeholder, needs to be implemented
			}
			else {
				gameState.advanceToNextPlayer();
				updateDisplay();
			}
			return;
		}

		if (response == JOptionPane.YES_OPTION) {
			Card transferred = selected.getHand().getCards().stream().filter(
					card -> card.getPossibleSuitsbyID().contains(suitAsked))
					.findFirst().orElse(null);

			if (transferred != null) {
				selected.getHand().removeCard(transferred);
				current.receiveCard(transferred);
			}
		}
		else {
			selected.getHand().getCards().stream()
					.forEach(card -> card.removePossibleSuitbyID(suitAsked));
			gameState.advanceToNextPlayer();
		}

		updateDisplay();
	}

	private void updateDisplay() {
		Player current = gameState.getCurrentPlayer();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(new JLabel("Current Player: " + current.getName()));
		panel.add(new JLabel("Player ID: " + current.getID()));
		panel.add(new JLabel("Hand: " + current.getHand().getCardsPrintable()));

		JButton askPlayer = new JButton("Ask Question");
		askPlayer.addActionListener(e -> handleAskQuestion(current));
		panel.add(askPlayer);

		frame.setContentPane(panel);
		frame.revalidate();
		frame.repaint();
	}
}
