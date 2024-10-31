package other;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

public class PrimaryButton extends RoundedButton {

	private static final long serialVersionUID = 8626192094139620401L;
	private int iconWidth, iconHeight;

	public int getIconWidth() {
		return iconWidth;
	}

	public void setIconWidth(int iconWidth) {
		this.iconWidth = iconWidth;
	}

	public int getIconHeight() {
		return iconHeight;
	}

	public void setIconHeight(int iconHeight) {
		this.iconHeight = iconHeight;
	}

	public PrimaryButton(ImageIcon icon) {
		this(null, icon);
	}

	public PrimaryButton(String label) {
		this(label, null);
	}

	public PrimaryButton(String label, ImageIcon icon) {
		super(label);

		if (icon != null) {
			borderRadius = 50;
			this.insetTop = 8;
			this.insetRight = 8;
			this.insetBottom = 8;
			this.insetLeft = 8;

			if (label == null || label.isBlank()) {
				this.iconWidth = 16;
				this.iconHeight = 16;
			} else {
				this.iconWidth = 32;
				this.iconWidth = 32;
			}

			Image scaledIcon = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
			setIcon(new ImageIcon(scaledIcon));
			setIconTextGap(8);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);

		super.paintComponent(g);
	}

}
