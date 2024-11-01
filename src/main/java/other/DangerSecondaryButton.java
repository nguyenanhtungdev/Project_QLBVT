package other;

import javax.swing.ImageIcon;

public class DangerSecondaryButton extends SecondaryButton {

	private static final long serialVersionUID = 2901534715784654811L;

	public DangerSecondaryButton(ImageIcon icon) {
		this(null, icon);
	}

	public DangerSecondaryButton(String label) {
		this(label, null);
	}

	public DangerSecondaryButton(String label, ImageIcon icon) {
		super(label, icon);

		this.normalColor = ColorConstants.DANGER;
		this.hoveredColor = ColorUtilities.darken(ColorConstants.DANGER);
		this.pressedColor = ColorUtilities.brighten(ColorConstants.DANGER);

		setForeground(normalColor);
		setBackground(normalColor);
	}

}
