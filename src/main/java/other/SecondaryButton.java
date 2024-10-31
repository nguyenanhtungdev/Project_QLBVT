package other;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class SecondaryButton extends RoundedButton {

	private static final long serialVersionUID = -6386152828233821838L;

	private int borderThickness;
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

	public int getBorderThickness() {
		return borderThickness;
	}

	public void setBorderThickness(int borderThickness) {
		this.borderThickness = borderThickness;
	}

	public SecondaryButton(ImageIcon icon) {
		this(null, icon);
	}

	public SecondaryButton(String label) {
		this(label, null);
	}

	public SecondaryButton(String label, ImageIcon icon) {
		super(label);

		this.borderThickness = 2;

		setForeground(normalColor);

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

			BufferedImage originalImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = originalImage.createGraphics();
			g2d.drawImage(icon.getImage(), 0, 0, null);
			g2d.dispose();

			BufferedImage tintedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
					BufferedImage.TYPE_INT_ARGB);

			for (int y = 0; y < originalImage.getHeight(); y++) {
				for (int x = 0; x < originalImage.getWidth(); x++) {
					int pixel = originalImage.getRGB(x, y);

					if ((pixel & 0x00FFFFFF) == 0xFFFFFF) {
						int alpha = (pixel >> 24) & 0xFF; // Giữ alpha
						int targetPixel = (alpha << 24) | (getForeground().getRGB());
						tintedImage.setRGB(x, y, targetPixel);
					} else {
						tintedImage.setRGB(x, y, pixel);
					}
				}
			}

			Image scaledIcon = tintedImage.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
			setIcon(new ImageIcon(scaledIcon));
			setIconTextGap(8);
		}

	}

	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(getBackground());

		Shape outer;
		Shape inner;

		outer = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
		inner = new RoundRectangle2D.Float(borderThickness, borderThickness, getWidth() - borderThickness * 2,
				getHeight() - borderThickness * 2, borderRadius, borderRadius);

		Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
		path.append(outer, false);
		path.append(inner, false);
		g2d.fill(path);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		setBackground(pressedColor);
		setForeground(pressedColor);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setBackground(normalColor);
		setForeground(normalColor);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setBackground(hoveredColor);
		setForeground(hoveredColor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(normalColor);
		setForeground(normalColor);
	}

}
