package other;

import java.awt.Color;

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

}
