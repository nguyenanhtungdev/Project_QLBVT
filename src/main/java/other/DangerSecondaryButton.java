package other;

import javax.swing.ImageIcon;

public class DangerSecondaryButton extends SecondaryButton {

	private static final long serialVersionUID = 2901534715784654811L;

	public DangerSecondaryButton(String label) {
		super(label);
		setForeground(ColorConstants.DANGER_COLOR);
	}

	public DangerSecondaryButton(ImageIcon icon) {
		super(icon);
		setForeground(ColorConstants.DANGER_COLOR);
	}

	public DangerSecondaryButton(String label, String iconPath) {
		super(label, iconPath);
		setForeground(ColorConstants.DANGER_COLOR);
	}

}
