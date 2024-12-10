package component.thongke;

import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;

import other.TrainPanel;

public class ThongKeDatePanel extends TrainPanel {

	private static final long serialVersionUID = 8471346772296133085L;

	private JLabel lTu;
	private JLabel lDen;
	private JDateChooser tfTu;
	private JDateChooser tfDen;

	public ThongKeDatePanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(lTu = new JLabel("Từ ngày"));
		add(Box.createHorizontalStrut(8));
		add(tfTu = new JDateChooser("dd/MM/yyyy", "##/##/####", '-'));
		add(Box.createHorizontalStrut(16));
		add(lDen = new JLabel("Đến ngày"));
		add(Box.createHorizontalStrut(8));
		add(tfDen = new JDateChooser("dd/MM/yyyy", "##/##/####", '-'));
	}

	public JLabel getLabelTuNgay() {
		return lTu;
	}

	public JLabel getLabelDenNgay() {
		return lDen;
	}

	public JDateChooser getTextFieldTuNgay() {
		return tfTu;
	}

	public JDateChooser getTextFieldfDenNgay() {
		return tfDen;
	}

	@Override
	public Insets getInsets() {
		return new Insets(4, 8, 4, 8);
	}

}