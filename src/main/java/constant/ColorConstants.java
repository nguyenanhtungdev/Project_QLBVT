package constant;

import java.awt.Color;

import util.ColorUtils;

public class ColorConstants {
	public static final Color PRIMARY_COLOR = Color.decode("#4682A9"); // màu xanh dương đậm
	public static final Color SECONDARY_COLOR = Color.decode("#1E56A0"); // màu xanh dương đậm hơn
	public static final Color DANGER_COLOR = Color.decode("#FF3014"); // màu đỏ
	public static final Color CLICK_COLOR = new Color(70, 130, 180); // màu đỏ
	public static final Color TEXT_COLOR = Color.WHITE;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	public static final Color HOVER_COLOR = ColorUtils.darken(PRIMARY_COLOR);
	public static final Color PRESSED_COLOR = ColorUtils.brighten(PRIMARY_COLOR);
	public static final Color SELECT_COLOR = Color.LIGHT_GRAY;
}
