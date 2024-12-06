package view;

import javax.swing.JFrame;

import constant.ColorConstants;

public class View extends JFrame {

	private static final long serialVersionUID = 1674811842027823122L;

	private String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public View(String name, String imagePath) {
		this.setName(name);
		this.imagePath = imagePath;

		getContentPane().setBackground(ColorConstants.BACKGROUND_COLOR);
	}

}
