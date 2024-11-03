package other;

import java.awt.Font;

import javax.swing.JLabel;

public class TieuDe extends JLabel {

	private static final long serialVersionUID = -4642950248528301177L;
	public static final int H1 = 32;
	public static final int H2 = 24;
	public static final int H3 = 16;
	public static final int H4 = 8;

	public TieuDe(String value) {
		this(value, H1);
	}

	public TieuDe(String value, int size) {
		setText(value);
		setFont(new Font(null, Font.BOLD, size));
		setForeground(ColorConstants.PRIMARY_COLOR);
	}

}
