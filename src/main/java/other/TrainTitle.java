package other;

import java.awt.Font;

import javax.swing.JLabel;

import constant.ColorConstants;
import constant.FontConstants;

public class TrainTitle extends JLabel {

	private static final long serialVersionUID = -4642950248528301177L;

	/**
	 * Tạo tiêu đề với font mặc định <br>
	 * Tiêu đề có màu <code>ColorConstants.PRIMARY_COLOR</code>
	 * 
	 * @param value
	 */
	public TrainTitle(String value) {
		this(value, FontConstants.HEADING_5);
	}

	/**
	 * Tạo tiêu đề với font và giá trị truyền vào <br>
	 * Tiêu đề có màu <code>ColorConstants.PRIMARY_COLOR</code>
	 * 
	 * @param value
	 * @param font
	 */
	public TrainTitle(String value, Font font) {
		setText(value);
		setFont(font);
		setForeground(ColorConstants.PRIMARY_COLOR);
		setAlignmentX(LEFT_ALIGNMENT);
	}

}
