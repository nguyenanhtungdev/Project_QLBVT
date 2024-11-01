package other;

import java.awt.Color;

public class ColorConstants {
	public static final Color PRIMARY_COLOR = Color.decode("#4682A9"); // màu xanh dương đậm
	public static final Color SECONDARY_COLOR = Color.decode("#1E56A0"); // màu xanh dương đậm hơn
	public static final Color DANGER = Color.decode("#FF3014");
	public static final Color TEXT_COLOR = Color.WHITE;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	public static final Color HOVER_COLOR = ColorUtilities.darken(PRIMARY_COLOR);
	public static final Color PRESSED_COLOR = ColorUtilities.brighten(PRIMARY_COLOR);
}
