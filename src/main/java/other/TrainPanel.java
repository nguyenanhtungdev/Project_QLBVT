package other;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import constant.ColorConstants;

public class TrainPanel extends JPanel {

	private static final long serialVersionUID = -8726900339596017904L;

	private Insets insets;

	public TrainPanel() {
		this(new FlowLayout(FlowLayout.LEADING, 4, 4));
	}

	public TrainPanel(LayoutManager layout) {
		this(layout, new Insets(0, 0, 0, 0));
	}

	public TrainPanel(LayoutManager layout, Insets insets) {
		super(layout);
		this.insets = insets;
		this.setAlignmentX(LEFT_ALIGNMENT);
	}

	@Override
	public Color getBackground() {
		return ColorConstants.BACKGROUND_COLOR;
	}

	@Override
	public Insets getInsets() {
		return insets;
	}
}
