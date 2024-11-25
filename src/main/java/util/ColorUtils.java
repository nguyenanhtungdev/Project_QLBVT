package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * Lớp tiện ích chứa các phương thức hỗ trợ về màu sắc
 */
public class ColorUtils {

	/**
	 * Giá trị ảnh hưởng của thuật toán đổi màu<br>
	 * Giá trị càng cao thì màu sẽ càng sẽ bị thay đổi càng nhiều<br>
	 * Giá trị phải trong khoảng 1-127
	 */
	public static int effectiveness = 15;

	/**
	 * Làm tối màu
	 * 
	 * @param color Màu cần làm tối
	 * @return màu đã được làm tối dựa trên giá trị <code>effectiveness</code>
	 */
	public static Color darken(Color color) {
		int red = (int) clamp(color.getRed() - effectiveness, 0, 255);
		int green = (int) clamp(color.getGreen() - effectiveness, 0, 255);
		int blue = (int) clamp(color.getBlue() - effectiveness, 0, 255);

		return new Color(red, green, blue);
	}

	/**
	 * Làm sáng màu
	 * 
	 * @param color Màu cần làm sáng
	 * @return màu đã được làm sáng dựa trên giá trị <code>effectiveness</code>
	 */
	public static Color brighten(Color color) {
		int red = (int) clamp(color.getRed() + effectiveness, 0, 255);
		int green = (int) clamp(color.getGreen() + effectiveness, 0, 255);
		int blue = (int) clamp(color.getBlue() + effectiveness, 0, 255);

		return new Color(red, green, blue);
	}

	/**
	 * Hàm giới hạn giá trị
	 * 
	 * @param value giá trị cần giới hạn
	 * @param min   giá trị nhỏ nhất
	 * @param max   giá trị lớn nhất
	 * @return giá trị sau khi đã được giới hạn trong khoảng [<code>min</code>,
	 *         <code>max</code>]
	 */
	public static float clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}

	/**
	 * Chuyển từ một <code>ImageIcon</code> sang <code>BufferedImage</code>
	 * 
	 * @param icon <code>ImageIcon</code> cần chuyển
	 * @return <code>BufferedImage</code> tương ứng với <code>ImageIcon</code>
	 */
	public static BufferedImage copyImageIconToBufferedImage(ImageIcon icon) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();
		BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = buffered.createGraphics();
		g2d.drawImage(icon.getImage(), 0, 0, null);
		g2d.dispose();

		return buffered;
	}

	/**
	 * Đổi một màu của một <code>BufferedImage</code> từ màu <code>from</code> sang
	 * màu <code>to</code>
	 * 
	 * @param original Ảnh cần đổi 1 phần màu
	 * @param from     Màu cần đổi
	 * @param to       Màu đổi thành
	 * @return Ảnh đã được đổi 1 phần màu
	 */
	public static BufferedImage changeColorToColor(BufferedImage original, Color from, Color to) {
		int width = original.getWidth();
		int height = original.getHeight();
		BufferedImage changed = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < original.getHeight(); y++) {
			for (int x = 0; x < original.getWidth(); x++) {
				int pixel = original.getRGB(x, y);

				if ((pixel & from.getRGB()) == from.getRGB()) {
					int alpha = (pixel >> 24) & 0xFF;
					int targetPixel = (alpha << 24) | (to.getRGB());
					changed.setRGB(x, y, targetPixel);
				} else {
					changed.setRGB(x, y, pixel);
				}
			}
		}

		return changed;
	}

}
