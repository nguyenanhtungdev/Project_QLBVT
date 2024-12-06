package other;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TrainCollapsiblePanel {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Collapsible Panel Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());

			// Create the collapsible panel
			JPanel collapsiblePanel = new JPanel();
			collapsiblePanel.setLayout(new BorderLayout());
			collapsiblePanel.setBorder(BorderFactory.createTitledBorder("Details"));

			// Add content to the collapsible panel
			JLabel label = new JLabel(
					"<html>This is some detailed information.<br>You can collapse or expand this panel.</html>");
			collapsiblePanel.add(label, BorderLayout.CENTER);

			// Wrapper panel with collapsible logic
			JPanel wrapperPanel = new JPanel();
			wrapperPanel.setLayout(new BorderLayout());
			JButton toggleButton = new JButton("Collapse");

			// Add action to toggle button
			toggleButton.addActionListener((ActionEvent e) -> {
				boolean isVisible = collapsiblePanel.isVisible();
				collapsiblePanel.setVisible(!isVisible);
				toggleButton.setText(isVisible ? "Expand" : "Collapse");
				wrapperPanel.revalidate(); // Recalculate layout
			});

			// Add components to wrapper panel
			wrapperPanel.add(toggleButton, BorderLayout.NORTH);
			wrapperPanel.add(collapsiblePanel, BorderLayout.CENTER);

			// Add the wrapper panel to the frame
			frame.add(wrapperPanel, BorderLayout.CENTER);

			frame.setSize(400, 300);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
