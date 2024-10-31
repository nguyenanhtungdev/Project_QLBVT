package other;

import javax.swing.ImageIcon;

public class DangerPrimaryButton extends PrimaryButton {

	private static final long serialVersionUID = 7549327916357325624L;

	public DangerPrimaryButton(ImageIcon icon) {
		this(null, icon);
	}

	public DangerPrimaryButton(String label) {
		this(label, null);
	}

	public DangerPrimaryButton(String label, ImageIcon icon) {
		super(label, icon);

		this.normalColor = ColorConstants.DANGER;
		this.hoveredColor = ColorUtilities.darken(ColorConstants.DANGER);
		this.pressedColor = ColorUtilities.brighten(ColorConstants.DANGER);
		setBackground(normalColor);
	}

}
