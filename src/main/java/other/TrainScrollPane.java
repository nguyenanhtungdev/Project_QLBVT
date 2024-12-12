package other;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;

import constant.ColorConstants;

public class TrainScrollPane extends JScrollPane {

	private static final long serialVersionUID = -2183391918076567051L;

	public TrainScrollPane() {
		super();
		init();
	}

	public TrainScrollPane(Component view) {
		super(view);
		init();
	}

	private void init() {
		setBorder(null);
		setOpaque(false);
		getVerticalScrollBar().setUnitIncrement(16);
		getHorizontalScrollBar().setUnitIncrement(16);
		setAlignmentX(LEFT_ALIGNMENT);
	}

	@Override
	public Color getBackground() {
		return ColorConstants.BACKGROUND_COLOR;
	}
}
