package qgfOracle;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

	private void updateDisplay() {
		Player current = gameState.getCurrentPlayer();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(new JLabel("Current Player: " + current.getName()));
		panel.add(new JLabel("Player ID: " + current.getID()));
		panel.add(new JLabel("Hand: " + current.getHand().getCards()));

		JButton nextButton = new JButton("Next Player");
		nextButton.addActionListener(e -> {
			gameState.advanceToNextPlayer();
			updateDisplay();
		});

		panel.add(nextButton);
		frame.setContentPane(panel);
		frame.revalidate();
		frame.repaint();
	}
}
