package other;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public abstract class RoundedButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 8730610760264205199L;
	protected int borderRadius;
	protected int insetTop, insetRight, insetBottom, insetLeft;
	protected Color normalColor, hoveredColor, pressedColor;

	public int getInsetTop() {
		return insetTop;
	}

	public void setInsetTop(int insetTop) {
		this.insetTop = insetTop;
	}

	public int getInsetRight() {
		return insetRight;
	}

	public void setInsetRight(int insetRight) {
		this.insetRight = insetRight;
	}

	public int getInsetBottom() {
		return insetBottom;
	}

	public void setInsetBottom(int insetBottom) {
		this.insetBottom = insetBottom;
	}

	public int getInsetLeft() {
		return insetLeft;
	}

	public void setInsetLeft(int insetLeft) {
		this.insetLeft = insetLeft;
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

	public Color getHoveredColor() {
		return hoveredColor;
	}

	public void setHoveredColor(Color hoveredColor) {
		this.hoveredColor = hoveredColor;
	}

	public Color getPressedColor() {
		return pressedColor;
	}

	public void setPressedColor(Color pressedColor) {
		this.pressedColor = pressedColor;
	}

	public RoundedButton(String label) {
		this.borderRadius = 8;
		this.insetTop = 8;
		this.insetRight = 16;
		this.insetBottom = 8;
		this.insetLeft = 16;
		this.normalColor = ColorConstants.PRIMARY_COLOR;
		this.hoveredColor = ColorConstants.HOVER_COLOR;
		this.pressedColor = ColorConstants.PRESSED_COLOR;

		setContentAreaFilled(false);
		setFocusPainted(false);

		setFont(new Font("Arial", Font.BOLD, 16));
		setForeground(ColorConstants.TEXT_COLOR);
		setBackground(normalColor);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setMinimumSize(new Dimension(200, 44));

		if (label != null) {
			setText(label);
		}

		addMouseListener(this);

	}

	@Override
	public Insets getInsets() {
		return new Insets(insetTop, insetRight, insetBottom, insetLeft);
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
