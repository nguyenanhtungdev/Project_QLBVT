package other;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import constant.ColorConstants;

//import javax.swing.ImageIcon;

public class PrimaryButton extends RoundedButton {

	private static final long serialVersionUID = 8626192094139620401L;

	public PrimaryButton(String label) {
		super(label, ColorConstants.PRIMARY_COLOR);
	}

//	public PrimaryButton(ImageIcon icon) {
//		super(icon, ColorConstants.PRIMARY_COLOR);
//	}

	public PrimaryButton(String label, String iconPath) {
		super(label, iconPath, ColorConstants.PRIMARY_COLOR);
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
