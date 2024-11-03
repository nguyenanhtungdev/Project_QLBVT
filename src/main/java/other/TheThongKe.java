package other;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TheThongKe extends JPanel {

	private static final long serialVersionUID = -2454984566733070396L;

	private JLabel lName, lValue;
	private int arc = 16;

	public TheThongKe(String name, String value) {
		lName = new JLabel(name);
		lValue = new JLabel(value);

		lName.setFont(new Font(null, Font.PLAIN, 24));
		lValue.setFont(new Font(null, Font.BOLD, 40));

		lName.setForeground(ColorConstants.TEXT_COLOR);
		lValue.setForeground(ColorConstants.TEXT_COLOR);

		add(lName);
		add(Box.createGlue());
		add(lValue);

		setOpaque(false);
		setPreferredSize(new Dimension(300, 125));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public void setNameText(String name) {
		lName.setText(name);
	}

	public void setValueText(String value) {
		lValue.setText(value);
	}

	@Override
	public Insets getInsets() {
		return new Insets(8, 16, 8, 16);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(ColorConstants.PRIMARY_COLOR);
		g2d.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, arc, arc);
	}

}
