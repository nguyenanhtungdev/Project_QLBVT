package component.thongke;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constant.ColorConstants;
import constant.FontConstants;

public class ThongKeSummaryCard extends JPanel {

	private static final long serialVersionUID = -2454984566733070396L;

	private JLabel lName;
	private JLabel lValue;
	private JLabel lChanges;

	public ThongKeSummaryCard(String name) {
		init();
		lName.setText(name);
	}

	public ThongKeSummaryCard(String name, String value) {
		init();
		lName.setText(name);
		lValue.setText(value);
	}

	public ThongKeSummaryCard(String name, String value, String changes) {
		init();
		lName.setText(name);
		lValue.setText(value);
		lChanges.setText(changes);
	}

	private void init() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		add(lName = new JLabel());
		add(Box.createVerticalStrut(8));
		add(lValue = new JLabel());
		add(lChanges = new JLabel());

		lName.setFont(FontConstants.HEADING_6);
		lValue.setFont(FontConstants.HEADING_2B);
		lChanges.setFont(FontConstants.TEXT);

		lName.setForeground(ColorConstants.TEXT_COLOR);
		lValue.setForeground(ColorConstants.TEXT_COLOR);
		lChanges.setForeground(ColorConstants.TEXT_COLOR);
	}

	public void setNameText(String name) {
		lName.setText(name);
	}

	public void setValueText(String value) {
		lValue.setText(value);
	}

	public void setChangesText(String changes) {
		lChanges.setText(changes);
	}

	@Override
	public Insets getInsets() {
		return new Insets(16, 16, 16, 16);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(ColorConstants.PRIMARY_COLOR);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
	}

}
