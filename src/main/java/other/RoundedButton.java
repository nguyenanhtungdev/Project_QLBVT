package other;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import constant.ColorConstants;
import util.ColorUtils;

public class RoundedButton extends JButton {

	private static final long serialVersionUID = 8730610760264205199L;

	protected int borderRadius;
	protected Color normalColor;
	protected Color hoveredColor;
	protected Color pressedColor;
	protected Insets insets;
	protected int iconWidth, iconHeight;
	protected ImageIcon icon;

	public RoundedButton(String label, String iconPath, Color normalColor) {
		this.borderRadius = 8;
		this.insets = new Insets(8, 16, 8, 16);

		this.normalColor = normalColor;
		this.hoveredColor = ColorUtils.darken(normalColor);
		this.pressedColor = ColorUtils.brighten(normalColor);

		if (iconPath != null) {
			this.setIcon(iconPath);
		}

		setOpaque(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBackground(normalColor);
		setBorder(null);

		setFont(new Font("Arial", Font.BOLD, 16));
		setForeground(ColorConstants.TEXT_COLOR);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setText(label);

		setIconTextGap(8);
	}

	public Dimension getIconSize() {
		return new Dimension(iconWidth, iconHeight);
	}

	public void setIcon(String iconPath) {
		this.icon = new ImageIcon(getClass().getResource(iconPath));
		this.iconWidth = icon.getIconWidth();
		this.iconHeight = icon.getIconHeight();

		super.setIcon(icon);
	}

	public void setIconSize(int width, int height) {
		this.iconWidth = width;
		this.iconHeight = height;

		Image scaledIcon = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		super.setIcon(new ImageIcon(scaledIcon));
	}

	public void setInsets(Insets insets) {
		this.insets = insets;
	}

	public int getBorderRadius() {
		return borderRadius;
	}

	public void setBorderRadius(int borderRadius) {
		this.borderRadius = borderRadius;
	}

	public Color getNormalColor() {
		return normalColor;
	}

	public void setNormalColor(Color normalColor) {
		this.normalColor = normalColor;
	}

	@Override
	public Insets getInsets() {
		return insets;
	}

}
