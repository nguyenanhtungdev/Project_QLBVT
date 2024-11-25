package other;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constant.FontConstants;

public class TrainStatisticCard extends JPanel {

	private static final long serialVersionUID = -2454984566733070396L;

	private JLabel lName, lValue;
	private int arc = 16;

	/**
	 * Tạo một thẻ thống kê với tên và giá trị
	 * 
	 * @param name  Tên thống kê
	 * @param value Giá trị thống kê
	 */
	public TrainStatisticCard(String name, String value) {
		setOpaque(false);
		setPreferredSize(new Dimension(300, 125));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(lName = new JLabel(name));
		add(Box.createGlue());
		add(lValue = new JLabel(value));

		lName.setFont(FontConstants.HEADING_5);
		lValue.setFont(FontConstants.HEADING_2B);

		lName.setForeground(ColorConstants.TEXT_COLOR);
		lValue.setForeground(ColorConstants.SECONDARY_COLOR); // ACCENT was used in the original version

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
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
	}

}
