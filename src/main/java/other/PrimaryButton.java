package other;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import constant.ColorConstants;

public class PrimaryButton extends RoundedButton {

	private static final long serialVersionUID = 8626192094139620401L;

	public PrimaryButton(String label) {
		this(label, null);
	}

	public PrimaryButton(String label, String iconPath) {
		super(label, iconPath, ColorConstants.PRIMARY_COLOR);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setBackground(pressedColor);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				setBackground(normalColor);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(hoveredColor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(normalColor);
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);

		super.paintComponent(g);
	}

}
