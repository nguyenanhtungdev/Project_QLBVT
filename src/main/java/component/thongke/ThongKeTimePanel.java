package component.thongke;

import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import other.TrainPanel;

public class ThongKeTimePanel extends TrainPanel {

	private static final long serialVersionUID = -5592216142661271063L;

	private JLabel lTu;
	private JLabel lDen;
	private ThongKeTimeChooser tfTu;
	private ThongKeTimeChooser tfDen;

	public ThongKeTimePanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(lTu = new JLabel("Từ lúc"));
		add(Box.createHorizontalStrut(8));
		add(tfTu = new ThongKeTimeChooser());
		add(Box.createHorizontalStrut(16));
		add(lDen = new JLabel("Đến lúc"));
		add(Box.createHorizontalStrut(8));
		add(tfDen = new ThongKeTimeChooser());
	}

	public JLabel getLabelTuLuc() {
		return lTu;
	}

	public JLabel getLabelDenLuc() {
		return lDen;
	}

	public ThongKeTimeChooser getChooserTuLuc() {
		return tfTu;
	}

	public ThongKeTimeChooser getChooserDenLuc() {
		return tfDen;
	}

	@Override
	public Insets getInsets() {
		return new Insets(4, 8, 4, 8);
	}

}
