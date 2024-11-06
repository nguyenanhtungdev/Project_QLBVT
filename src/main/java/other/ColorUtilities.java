package other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ColorUtilities {

	public static int effectiveness = 15;

	public static Color darken(Color color) {
		int red = (int) clamp(color.getRed() - effectiveness, 0, 255);
		int green = (int) clamp(color.getGreen() - effectiveness, 0, 255);
		int blue = (int) clamp(color.getBlue() - effectiveness, 0, 255);

		return new Color(red, green, blue);
	}

	public static Color brighten(Color color) {
		int red = (int) clamp(color.getRed() + effectiveness, 0, 255);
		int green = (int) clamp(color.getGreen() + effectiveness, 0, 255);
		int blue = (int) clamp(color.getBlue() + effectiveness, 0, 255);

		return new Color(red, green, blue);
	}

	public static float clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}

	public static BufferedImage convertImageIconToBufferedImage(ImageIcon icon) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();
		BufferedImage buffered = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = buffered.createGraphics();
		g2d.drawImage(icon.getImage(), 0, 0, null);
		g2d.dispose();

		return buffered;
	}

	public static BufferedImage changeColorToColor(BufferedImage buffered, Color from, Color to) {
		int width = buffered.getWidth();
		int height = buffered.getHeight();
		BufferedImage changed = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < buffered.getHeight(); y++) {
			for (int x = 0; x < buffered.getWidth(); x++) {
				int pixel = buffered.getRGB(x, y);

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
