package other;

import javax.swing.ImageIcon;

public class PrimaryButton extends RoundedButton {

	private static final long serialVersionUID = 8626192094139620401L;

	public PrimaryButton(String label) {
		super(label, ColorConstants.PRIMARY_COLOR);
	}

	public PrimaryButton(ImageIcon icon) {
		super(icon, ColorConstants.PRIMARY_COLOR);
	}

	public PrimaryButton(String label, String iconPath) {
		super(label, iconPath, ColorConstants.PRIMARY_COLOR);
	}

}
