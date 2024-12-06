package other;

import constant.ColorConstants;

//import javax.swing.ImageIcon;

public class DangerPrimaryButton extends RoundedButton {

	private static final long serialVersionUID = 7549327916357325624L;

	public DangerPrimaryButton(String label) {
		super(label, ColorConstants.DANGER_COLOR);
	}

//	public DangerPrimaryButton(ImageIcon icon) {
//		super(icon, ColorConstants.DANGER_COLOR);
//	}

	public DangerPrimaryButton(String label, String iconPath) {
		super(label, iconPath, ColorConstants.DANGER_COLOR);
	}

}
