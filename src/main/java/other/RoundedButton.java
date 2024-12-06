package other;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import constant.ColorConstants;
import util.ColorUtils;

public class RoundedButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 8730610760264205199L;
	protected int borderRadius;
	protected Color normalColor, hoveredColor, pressedColor;
	protected Insets insets;
	protected int iconWidth, iconHeight;
	protected ImageIcon icon;

	public int getIconWidth() {
		return iconWidth;
	}

	public int getIconHeight() {
		return iconHeight;
	}

	public void setIconSize(int width, int height) {
		this.iconWidth = width;
		this.iconHeight = height;

		Image scaledIcon = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		setIcon(new ImageIcon(scaledIcon));
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

	public RoundedButton(String label, Color normalColor) {
		this(label, null, normalColor);
	}

	public RoundedButton(String label, String iconPath, Color normalColor) {
		this.borderRadius = 8;
		this.insets = new Insets(8, 16, 8, 16);

		this.normalColor = normalColor;
		this.hoveredColor = ColorUtils.darken(normalColor);
		this.pressedColor = ColorUtils.brighten(normalColor);

		if (iconPath != null) {
			this.icon = new ImageIcon(getClass().getResource(iconPath));
			this.iconWidth = icon.getIconWidth();
			this.iconHeight = icon.getIconHeight();

			setIcon(icon);
		}

		setContentAreaFilled(false);
		setFocusPainted(false);
		setBackground(normalColor);
		setBorder(null);

		setFont(new Font("Arial", Font.BOLD, 16));
		setForeground(ColorConstants.TEXT_COLOR);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		setMinimumSize(new Dimension(200, 44));
		setText(label);

		setIcon(icon);
		setIconTextGap(8);

		addMouseListener(this);
	}

	@Override
	public Insets getInsets() {
		return insets;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

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

}
